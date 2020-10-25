public class Main {
    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(new AgeDataComparator());

        heap.add( new AgeData(10) );
        heap.add( new AgeData(5) );
        heap.add( new AgeData(70) );
        heap.add( new AgeData(10) );
        heap.add( new AgeData(50) );
        heap.add( new AgeData(5) );
        heap.add( new AgeData(15) );
        
        System.out.println(heap);
        System.out.println("Number of people younger than 10");
        System.out.println(heap.youngerThan(10));
        System.out.println("Number of people older than 10");
        System.out.println(heap.olderThan(10));
        System.out.println("Finding someone with the age of 10");
        System.out.println(heap.find(new AgeData(10)));
        System.out.println("Now removing 10 and 5, one person each");
        heap.remove(new AgeData(10));
        heap.remove(new AgeData(5));
        System.out.println(heap);
        System.out.println("Now adding one person for 15");
        heap.add(new AgeData(15));
        System.out.println(heap);

    }
}
