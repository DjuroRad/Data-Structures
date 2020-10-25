import java.util.*;
public class q2 {
    static Random rand = new Random();
    public static Integer[] getArray(int n){
        Integer[] arr = new Integer[n];
        for( int i = 0; i<n; ++i )
            arr[i] = rand.nextInt(n)+1;
        return arr;
    }
    public static Integer[] getSortedArray(int n){
        Integer[] arr = new Integer[n];
        for( int i = 0; i<n; ++i )
            arr[i] = i;
        return arr;
    }
    public static LinkedList<Integer> getList(int n){
        LinkedList<Integer> list = new LinkedList<Integer>();
        for( int i = 0; i<n; ++i )
            list.add(rand.nextInt(n)+1);
        return list;
    }
    public static LinkedList<Integer> getSortedList(int n){
        LinkedList<Integer> list = new LinkedList<Integer>();
        for( int i = 0; i<n; ++i )
            list.add(i);
        return list;
    }
    public static<T extends Comparable<T>> void selection_sort(T[] table) {
        int n = table.length;
        for (int fill = 0; fill < n - 1; fill++) {
            int posMin = fill;
            for (int next = fill + 1; next < n; next++) {
                if (table[next].compareTo(table[posMin]) < 0) {
                    posMin = next;
                }
            }
            T temp = table[fill];
            table[fill] = table[posMin];
            table[posMin] = temp;
        }
    }
    public static<T extends Comparable<T>> void bubble_sort(T[] table) {
        int pass = 1;
        boolean exchanges = false;
        do {
            exchanges = false;
            for (int i = 0; i < table.length - pass; i++) {
                if (table[i].compareTo(table[i + 1]) > 0) {
                    T temp = table[i];
                    table[i] = table[i + 1];
                    table[i + 1] = temp;
                    exchanges = true; // Set flag.
                }
            }
            pass++;
        } while (exchanges);
    }

    public static <T extends Comparable<T>> void insertion_sort(T[] table) {
        for (int nextPos = 1; nextPos < table.length; nextPos++) {
            insert(table, nextPos);
        } // End for.
    } // End sort.
    private static <T extends Comparable<T>> void insert(T[] table,
                                                         int nextPos) {
        T nextVal = table[nextPos];
        while (nextPos > 0
                && nextVal.compareTo(table[nextPos - 1]) < 0) {
            table[nextPos] = table[nextPos - 1]; // Shift down.
            nextPos--; // Check next smaller element.
        }
        table[nextPos] = nextVal;
    }
    public static<T extends Comparable<T>> void merge_sort(List<T> list ){
        if( list.size() <= 1 )
            return;
        List<T> list_left = new LinkedList<>();
        List<T> list_right = new LinkedList<>();
        Iterator<T> it = list.iterator();
        for(int i =0;i<list.size()/2;++i)
            list_left.add(it.next());
        for(int i =list.size()/2;i<list.size();++i)
            list_right.add(it.next());
    }
    private static<T> void printList(List<T> list){
        for(T el: list ){
            System.out.print(el + " ");
        }
        System.out.println();
    }

    public static<T extends Comparable<T>> void merge(List<T> list_left, List<T> list_right, List<T> list){
        ListIterator<T> left = list_left.listIterator();
        ListIterator<T> right = list_right.listIterator();
        ListIterator<T> main = list.listIterator();
        main.next();
        T l_el = left.next();
        T r_el = right.next();
            while (l_el!=null && r_el!=null) {
                if (l_el.compareTo(r_el) < 0) {
                    main.set(l_el);
                    if(left.hasNext())
                        l_el = left.next();
                    else
                        l_el = null;
                    main.next();
                } else {
                    main.set(r_el);
                    if( right.hasNext() )
                        r_el = right.next();
                    else
                        r_el = null;
                    main.next();
                }
            }
        while( r_el != null ) {
            main.set(r_el);
            if( right.hasNext() ) {
                r_el = right.next();
                main.next();
            }
            else
                r_el = null;
        }
        while( l_el != null ){
            main.set(l_el);
            if( left.hasNext() ) {
                l_el = left.next();
                main.next();
            }
            else
                l_el = null;
        }
    }
    public static <T extends Comparable<T>> void quick_sort(List<T> list){
        quick_sort(list, 0, list.size()-1);
    }
    private static <T extends Comparable<T>> void quick_sort( List<T> list, int lb, int up){
        if( lb<up ) {
            int pivot = partition(list, lb, up);
            quick_sort(list, lb, pivot - 1);
            quick_sort(list, pivot + 1, up);
        }
    }
    private static <T extends Comparable<T>> int partition( List<T> list, int lb, int ub){
        T pivot = list.listIterator(lb).next();
        ListIterator<T> start_it = list.listIterator(lb);
        ListIterator<T> end_it = list.listIterator(ub);
        if( end_it.hasNext() )
            end_it.next();

        int start = lb;
        int end = ub;
        
        while( start<end ) {

            while ( start <= ub && pivot.compareTo(start_it.next()) >= 0) {
                start++;
            }
            start_it.previous();
            while ( end >= lb && pivot.compareTo(end_it.previous()) < 0) {
                --end;
            }
            end_it.next();
            if (start < end) {
                swap(list, start, end);
            }
        }
        if( lb!=end ) {
            swap(list, lb, end);
        }

        return end;
    }
    private static<T> void swap( List<T> list, int i, int j ){
        T temp = list.get(i);
        list.set(i,list.get(j));
        list.set(j,temp);
    }
    public static <T extends Comparable<T>> void shell_sort( T arr[] ){
        for( int gap = arr.length/2; gap>=1; gap = gap/2 ){
            for( int j = gap; j<arr.length; ++j ){
                for( int i = j-gap; i>=0; i=i-gap ){
                    if( arr[i+gap].compareTo(arr[i])>=0 ) {
                        break;
                    }
                    else {
                        swap(arr, i + gap, i);
                    }
                }
            }
        }
    }
    public static <T extends Comparable<T>>void merge_sort( T[] arr ){
        if( arr.length <= 1 )
            return;
        int mid = arr.length/2;
        T[] arr_left = Arrays.copyOfRange(arr, 0, mid);
        T[] arr_right = Arrays.copyOfRange(arr,mid, arr.length);

        merge_sort(arr_left);
        merge_sort(arr_right);
        merge(arr_left,arr_right, arr);
    }
    private static <T extends Comparable<T>> int merge(T[] left, T[] right, T[] arr){
        int l = 0;
        int r = 0;
        int i = 0;
        int count = 0;
        while( l < left.length && r < right.length ) {
            if( left[l].compareTo(right[r])<0){
                arr[i] = left[l];
                ++l;
            }
            else{
                arr[i] = right[r];
                r++;
            }
            ++i;
            ++count;
        }
        while( r < right.length ){
            arr[i] = right[r];
            ++r;
            ++i;
        }
        while( l < left.length ){
            arr[i] = left[l];
            ++l;
            ++i;
        }
        return count;
    }
    public static <T extends Comparable<T>> void quick_sort(T[] arr){
        quick_sort(arr, 0, arr.length-1);
    }
    private static <T extends Comparable<T>> void quick_sort( T[] arr, int lb, int up){
        if( lb<up ) {
            int pivot = partition(arr, lb, up);
            quick_sort(arr, lb, pivot - 1);
            quick_sort(arr, pivot + 1, up);
        }
    }
    private static <T extends Comparable<T>> int partition( T[] arr, int lb, int ub){
        T pivot = arr[lb];
        int start = lb;
        int end = ub;

        while( start<end ) {
            while ( start <= ub && pivot.compareTo(arr[start]) >= 0) {
                start++;
            }
            while ( end >= lb && pivot.compareTo(arr[end]) < 0) {
                --end;
            }
            if (start < end) {
                swap(arr, start, end);
            }
        }
        if( lb!=end) {
            swap(arr, lb, end);
        }

        return end;
    }
    public static <T extends Comparable<T>> void heap_sort(T[] arr){
        for( int i=arr.length/2; i>=0; --i ){//building max heap
            heapify(arr,arr.length,i);
        }
        for( int i = arr.length-1; i>= 0; i-- ){
            swap(arr,0,i);
            heapify(arr,i,0);
        }
    }
    private static <T extends Comparable<T>> void heapify(T[] arr, int n, int i ){
        int largest = i;
        int l = 2*i;
        int r = l + 1;
        if( l<n && arr[l].compareTo(arr[largest]) > 0)
            largest = l;
        if( r<n && arr[r].compareTo(arr[largest]) > 0 )
            largest = r;
        if( largest != i ){
            swap(arr, largest, i);
            heapify(arr, n, largest);
        }
    }
//    private static <T>void printArray( T[] arr ){
//        for( T el: arr )
//            System.out.print(el + " ");
//        System.out.println();
//    }

    private static <T>void swap( T[] arr, int i, int j ){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static void time(){
        System.out.println("For bubble average times are: ");
        System.out.println("100 - " + bubble_time(100,0)/1000);
        System.out.println("200 - " + bubble_time(200, 0)/1000);
        System.out.println("500 - " + bubble_time(500, 0)/1000);
        System.out.println("1000 - " + bubble_time(1000, 0)/1000);
        System.out.println("2000 - " + bubble_time(2000, 0)/1000);

        System.out.println("For insertion average times are: ");
        System.out.println("100 - " + bubble_time(100,1)/1000);
        System.out.println("200 - " + bubble_time(200,1)/1000);
        System.out.println("500 - " + bubble_time(500,1)/1000);
        System.out.println("1000 - " + bubble_time(1000,1)/1000);
        System.out.println("2000 - " + bubble_time(2000,1)/1000);

        System.out.println("For selection average times are: ");
        System.out.println("100 - " + bubble_time(100,2)/1000);
        System.out.println("200 - " + bubble_time(200,2)/1000);
        System.out.println("500 - " + bubble_time(500,2)/1000);
        System.out.println("1000 - " + bubble_time(1000,2)/1000);
        System.out.println("2000 - " + bubble_time(2000,2)/1000);

        System.out.println("For shell average times are: ");
        System.out.println("100 - " + bubble_time(100,3)/1000);
        System.out.println("200 - " + bubble_time(200,3)/1000);
        System.out.println("500 - " + bubble_time(500,3)/1000);
        System.out.println("1000 - " + bubble_time(1000,3)/1000);
        System.out.println("2000 - " + bubble_time(2000,3)/1000);

        System.out.println("For merge average times are: ");
        System.out.println("100 - " + bubble_time(100,4)/1000);
        System.out.println("200 - " + bubble_time(200,4)/1000);
        System.out.println("500 - " + bubble_time(500,4)/1000);
        System.out.println("1000 - " + bubble_time(1000,4)/1000);
        System.out.println("2000 - " + bubble_time(2000,4)/1000);

        System.out.println("For heap average times are: ");
        System.out.println("100 - " + bubble_time(100,5)/1000);
        System.out.println("200 - " + bubble_time(200,5)/1000);
        System.out.println("500 - " + bubble_time(500,5)/1000);
        System.out.println("1000 - " + bubble_time(1000,5)/1000);
        System.out.println("2000 - " + bubble_time(2000,5)/1000);

        System.out.println("For quick average times are: ");
        System.out.println("100 - " + bubble_time(100,6)/1000);
        System.out.println("200 - " + bubble_time(200,6)/1000);
        System.out.println("500 - " + bubble_time(500,6)/1000);
        System.out.println("1000 - " + bubble_time(1000,6)/1000);
        System.out.println("2000 - " + bubble_time(2000,6)/1000);

        System.out.println("For bubble average times on sorted arrays are: ");
        System.out.println("100 - " + sorted_time(100,0)/1000);
        System.out.println("200 - " + sorted_time(200, 0)/1000);
        System.out.println("500 - " + sorted_time(500, 0)/1000);
        System.out.println("1000 - " + sorted_time(1000, 0)/1000);
        System.out.println("2000 - " + sorted_time(2000, 0)/1000);

        System.out.println("For insertion average times on sorted arrays are: ");
        System.out.println("100 - " + sorted_time(100,1)/1000);
        System.out.println("200 - " + sorted_time(200,1)/1000);
        System.out.println("500 - " + sorted_time(500,1)/1000);
        System.out.println("1000 - " + sorted_time(1000,1)/1000);
        System.out.println("2000 - " + sorted_time(2000,1)/1000);

        System.out.println("For selection average times on sorted arrays are: ");
        System.out.println("100 - " + sorted_time(100,2)/1000);
        System.out.println("200 - " + sorted_time(200,2)/1000);
        System.out.println("500 - " + sorted_time(500,2)/1000);
        System.out.println("1000 - " + sorted_time(1000,2)/1000);
        System.out.println("2000 - " + sorted_time(2000,2)/1000);

        System.out.println("For shell average times on sorted arrays are: ");
        System.out.println("100 - " + sorted_time(100,3)/1000);
        System.out.println("200 - " + sorted_time(200,3)/1000);
        System.out.println("500 - " + sorted_time(500,3)/1000);
        System.out.println("1000 - " + sorted_time(1000,3)/1000);
        System.out.println("2000 - " + sorted_time(2000,3)/1000);

        System.out.println("For merge average times on sorted arrays are: ");
        System.out.println("100 - " + sorted_time(100,4)/1000);
        System.out.println("200 - " + sorted_time(200,4)/1000);
        System.out.println("500 - " + sorted_time(500,4)/1000);
        System.out.println("1000 - " + sorted_time(1000,4)/1000);
        System.out.println("2000 - " + sorted_time(2000,4)/1000);

        System.out.println("For heap average times on sorted arrays are: ");
        System.out.println("100 - " + sorted_time(100,5)/1000);
        System.out.println("200 - " + sorted_time(200,5)/1000);
        System.out.println("500 - " + sorted_time(500,5)/1000);
        System.out.println("1000 - " + sorted_time(1000,5)/1000);
        System.out.println("2000 - " + sorted_time(2000,5)/1000);

        System.out.println("For quick average times on sorted arrays are: ");
        System.out.println("100 - " + sorted_time(100,6)/1000);
        System.out.println("200 - " + sorted_time(200,6)/1000);
        System.out.println("500 - " + sorted_time(500,6)/1000);
        System.out.println("1000 - " + sorted_time(1000,6)/1000);
        System.out.println("2000 - " + sorted_time(2000,6)/1000);

        System.out.println("For merge( list ) average times are: ");
        System.out.println("100 - " + list_time(100,0)/1000);
        System.out.println("200 - " + list_time(200,0)/1000);
        System.out.println("500 - " + list_time(500,0)/1000);
        System.out.println("1000 - " + list_time(1000,0)/1000);
        System.out.println("2000 - " + list_time(2000,0)/1000);

        System.out.println("For quick( list ) average times are: ");
        System.out.println("100 - " + list_time(100,1)/1000);
        System.out.println("200 - " + list_time(200,1)/1000);
        System.out.println("500 - " + list_time(500,1)/1000);
        System.out.println("1000 - " + list_time(1000,1)/1000);
        System.out.println("2000 - " + list_time(2000,1)/1000);

        System.out.println("For merge( list ) average times on sorted lists are: ");
        System.out.println("100 - " + list_time_sorted(100,0)/1000);
        System.out.println("200 - " + list_time_sorted(200,0)/1000);
        System.out.println("500 - " + list_time_sorted(500,0)/1000);
        System.out.println("1000 - " + list_time_sorted(1000,0)/1000);
        System.out.println("2000 - " + list_time_sorted(2000,0)/1000);

        System.out.println("For quick( list ) average times on sorted lists are: ");
        System.out.println("100 - " + list_time_sorted(100,1)/1000);
        System.out.println("200 - " + list_time_sorted(200,1)/1000);
        System.out.println("500 - " + list_time_sorted(500,1)/1000);
        System.out.println("1000 - " + list_time_sorted(1000,1)/1000);
        System.out.println("2000 - " + list_time_sorted(2000,1)/1000);
    }

    private static long list_time( int n, int choice ){
        LinkedList<Integer>[] list = new LinkedList[20];
        for( int i = 0; i<20; ++i )
            list[i] = getList(n);

        long start = System.nanoTime();
        for( int i = 0; i<20; ++i ) {
            switch (choice) {
                case 0:
                    merge_sort(list[i]);
                    break;
                case 1:
                    quick_sort(list[i]);
                    break;
            }
        }
        long end = System.nanoTime();
        return (end-start)/20;
    }
    private static long list_time_sorted( int n, int choice ){
        LinkedList<Integer> list = new LinkedList();
        list = getSortedList(n);

        long start = System.nanoTime();
        for( int i = 0; i<20; ++i ) {
            switch (choice) {
                case 0:
                    merge_sort(list);
                    break;
                case 1:
                    quick_sort(list);
                    break;
            }
        }
        long end = System.nanoTime();
        return (end-start)/20;
    }
    //arbitrary name, has nothing to do with bubble sort
    private static long bubble_time( int n, int choice ){
        Integer[][] arr = new Integer[20][n];

        for( int i = 0; i<20; ++i ){
            arr[i] = getArray(n);
        }
        long start = System.nanoTime();
        for( int i = 0; i<20; ++i ){
                switch (choice){
                case 0:
                    bubble_sort(arr[i]);
                    break;
                case 1:
                    insertion_sort(arr[i]);
                    break;
                case 2:
                    selection_sort(arr[i]);
                    break;
                case 3:
                    shell_sort(arr[i]);
                    break;
                case 4:
                    merge_sort(arr[i]);
                    break;
                case 5:
                    heap_sort(arr[i]);
                    break;
                case 6:
                    quick_sort(arr[i]);
                    break;
            }
        }
        long end = System.nanoTime();
        return (end-start)/20;
    }
    private static long sorted_time( int n, int choice ){
        Integer[] arr = new Integer[n];
        arr = getSortedArray(n);
        long start = System.nanoTime();
        for( int i = 0; i<20; ++i ){
            switch (choice){
                case 0:
                    bubble_sort(arr);
                    break;
                case 1:
                    insertion_sort(arr);
                    break;
                case 2:
                    selection_sort(arr);
                    break;
                case 3:
                    shell_sort(arr);
                    break;
                case 4:
                    merge_sort(arr);
                    break;
                case 5:
                    heap_sort(arr);
                    break;
                case 6:
                    quick_sort(arr);
                    break;
            }
        }
        long end = System.nanoTime();
        return (end-start)/20;
    }
    public static void main(String[] args) {
        time();
    }
}
