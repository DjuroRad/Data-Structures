public interface KWHashMapInterface<K,V> {
    V get( Object key );//returns value associated with the specified key
    V put(K key, V value);//Associates the specified value with the specified key
    V remove(Object key);//Removes mapping for this key and returns its value
    int size();//returns size of the map
    boolean isEmpty();//checks if the size is 0
}
