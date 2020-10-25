import java.util.TreeSet;

public class KWHashMapChain<K extends Comparable<K>,V extends Comparable<V>> implements KWHashMapInterface<K,V> {
    /**
     * table/table
     */
    private TreeSet<Entry<K,V>>[] map;
    /**
     * total number of currently used keys
     */
    private int numKeys = 0;
    /**
     * initial capacity
     */
    private static final int CAPACITY = 10;
    /**
     * maximum load factor
     */
    private static final double LOAD_TRESHOLD = 3.0;

    public KWHashMapChain() {
        this.map = new TreeSet[CAPACITY];
    }

    /**
     * gets the index of a certain index
     * @param key key
     * @return index of the provided key
     */
    private int keyIndex(Object key){
        int index = key.hashCode()%map.length;
        if( index < 0 )
            index += map.length;
        return index;
    }
    @Override
    public V get(Object key) {
        int index = keyIndex(key);
        if( map[index] == null )
            return null;
        for( Entry<K,V> item: map[index] ){
            if( item.getKey().equals(key) )
                return item.getValue();
        }
        return null;
    }


    @Override
    public V put(K key, V value) {
        int index = keyIndex(key);
        if( map[index] == null ) {
            map[index] = new TreeSet<Entry<K,V>>();
        }
        else {
            for( Entry<K,V> entry: map[index] ){
                if( entry.getKey().equals(key) ){
                    V val = entry.getValue();
                    entry.setValue(value);
                    return val;
                }
            }
        }
        map[index].add(new Entry<K,V>(key,value));
        numKeys++;
        if( numKeys > LOAD_TRESHOLD*map.length )
            rehash();
        return null;
    }

    private void rehash(){
        TreeSet<Entry<K,V>>[] oldMap = map;
        numKeys=0;
        map = new TreeSet[oldMap.length*2 + 1];
        for( TreeSet<Entry<K,V>> tree : oldMap ){
            for( Entry<K,V> entry : tree )
                put(entry.getKey(),entry.getValue() );
        }
    }
    @Override
    public V remove(Object key) {
        int index = keyIndex(key);
        if( map[index] == null )
            return null;

        for( Entry<K,V> entry: map[index] ){
            if( entry.getKey().equals(key) ) {
                numKeys--;
                V value = entry.getValue();
                map[index].remove(entry);
                if( map[index].isEmpty() )
                    map[index] = null;
                return value;
            }
        }
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
