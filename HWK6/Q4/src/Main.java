public class Main {
    /**
     * returns time in nano seconds
     */
    public static<K extends Comparable<K>,V extends Comparable<V>> long putTime( int n, KWHashMapInterface map ){
        long start = System.nanoTime();
        for( Integer i = 0; i<n; i++ )
            map.put(i,i);
        long end = System.nanoTime();
        return end - start;
    }
    public static<K extends Comparable<K>,V extends Comparable<V>> long getTime( int n, KWHashMapInterface map ){
        long start = System.nanoTime();
        for( Integer i = 0; i<n; i++ )
            map.get(i);
        long end = System.nanoTime();
        return end - start;
    }
    public static<K extends Comparable<K>,V extends Comparable<V>> long deleteTime( int n, KWHashMapInterface map ){
        long start = System.nanoTime();
        for( Integer i = 0; i<n; i++ )
            map.remove(i);
        long end = System.nanoTime();
        return end - start;
    }
    public static void main(String[] args) {
        KWHashMapChain<Integer,Integer> map = new KWHashMapChain<>();
        System.out.println("Time in microseconds required to put 400 elements in chained hash map is: " + putTime(400,map)/1000);
        System.out.println("Time in microseconds required to get 400 elements in chained hash map is: " + getTime(400,map)/1000);
        System.out.println("Time in microseconds required to remove 400 elements from chained hash map is: " + deleteTime(400,map)/1000);
        map = new KWHashMapChain<>();
        System.out.println();
        System.out.println("Time in microseconds required to put 1000 elements in chained hash map is: " + putTime(1000,map)/1000);
        System.out.println("Time in microseconds required to get 1000 elements in chained hash map is: " + getTime(1000,map)/1000);
        System.out.println("Time in microseconds required to remove 1000 elements from chained hash map is: " + deleteTime(1000,map)/1000);
        map = new KWHashMapChain<>();
        System.out.println();
        System.out.println("Time in microseconds required to put 4000 elements in chained hash map is: " + putTime(4000,map)/1000);
        System.out.println("Time in microseconds required to get 4000 elements in chained hash map is: " + getTime(4000,map)/1000);
        System.out.println("Time in microseconds required to remove 4000 elements from chained hash map is: " + deleteTime(4000,map)/1000);
        System.out.println();
        System.out.println();
        System.out.println();
        KWHashMapChain<Integer,Integer> map_open = new KWHashMapChain<>();
        System.out.println("Time in microseconds required to put 4000 elements in open hash map is: " + putTime(400,map_open)/1000);
        System.out.println("Time in microseconds required to get 4000 elements in open hash map is: " + getTime(400,map_open)/1000);
        System.out.println("Time in microseconds required to remove 4000 elements from open hash map is: " + deleteTime(400,map_open)/1000);
        map_open = new KWHashMapChain<>();
        System.out.println();
        System.out.println("Time in microseconds required to put 1000 elements in open hash map is: " + putTime(1000,map_open)/1000);
        System.out.println("Time in microseconds required to get 1000 elements in open hash map is: " + getTime(1000,map_open)/1000);
        System.out.println("Time in microseconds required to remove 1000 elements from open hash map is: " + deleteTime(1000,map_open)/1000);
        map_open = new KWHashMapChain<>();
        System.out.println();
        System.out.println("Time in microseconds required to put 4000 elements in open hash map is: " + putTime(4000,map_open)/1000);
        System.out.println("Time in microseconds required to get 4000 elements in open hash map is: " + getTime(4000,map_open)/1000);
        System.out.println("Time in microseconds required to remove 4000 elements from open hash map is: " + deleteTime(4000,map_open)/1000);

    }
}
