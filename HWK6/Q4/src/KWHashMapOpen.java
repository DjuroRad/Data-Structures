public class KWHashMapOpen<K extends Comparable<K>,V extends Comparable<V>> implements KWHashMapInterface<K,V> {
    /**
     * table/table
     */
    private Entry<K,V>[] map;
    /**
     * total number of currently used keys
     */
    private int numKeys = 0;
    /**
     * total number of deleted items
     */
    private int numDeletes = 0;
    /**
     * indicator for a delted entry
     */
    private final Entry<K,V> DELETED = new Entry<K,V>(null,null);
    /**
     * initial capacity
     */
    private static final int CAPACITY = 10;
    /**
     * maximum load factor
     */
    private static final double LOAD_TRESHOLD = 0.75;

    public KWHashMapOpen() {
        this.map = new Entry[CAPACITY];
    }
    @Override
    public V get(Object key) {
        int index = find(key);
        if( map[index] != null ) {
            return map[index].getValue();
        }
        return null;
    }

    public int find(Object key){
        int i = 0;
        int index =(hash(key) + i*hash2(key))%map.length;
        while( map[index] != null && !(key.equals(map[index].getKey())) && i < map.length){
            index = (hash(key) + i*hash2(key))%map.length;
            ++i;//avoiding infinite loop if it maybe occurs
        }
        return index;
    }

    private int hash( Object k ){
        int index = (k.hashCode()*2 + 3)%map.length;
        if( index < 0 )
            index += map.length;
        return index;
    }
    private int hash2( Object k ){
        int i = (k.hashCode()*3 + 1 )%map.length;
        if( i<0 )
            i += map.length;
        return i;
    }
    @Override
    public V put(K key, V value) {
        int index = find(key);
        if( map[index] == null ) {
            map[index] = new Entry<K, V>(key, value);
            numKeys++;
            double loadFactor = (double)(numKeys+numDeletes)/(map.length);
            if( loadFactor>LOAD_TRESHOLD) {
                rehash();
            }
            return null;
        }

        V val = map[index].getValue();
        map[index].setValue(value);

        return val;
    }

    private void rehash(){
        Entry<K,V>[] oldMap = map;
        map = new Entry[oldMap.length*2+1];
        numKeys = 0;
        for( int i = 0; i<oldMap.length; ++i ){
            if(oldMap[i] != null && oldMap[i] != DELETED)
                put(oldMap[i].getKey(), oldMap[i].getValue());
        }
    }

    @Override
    public V remove(Object key) {
        int index = find(key);
        if( map[index] != null ) {
            numKeys--;
            numDeletes++;
            V val = map[index].getValue();
            map[index] = DELETED;
            return val;
        }
        else
            return null;
    }

    @Override
    public int size() {
        return numKeys;
    }

    @Override
    public boolean isEmpty() {
        return numKeys==0;
    }
}
