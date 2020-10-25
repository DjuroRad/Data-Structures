public class test {
    public static void main(String[] args) {

        System.out.println("Making a new list of with maximum number of elements 4, and minimum 2");
        SkipListX<Integer> listx = new SkipListX<>(5);
        System.out.println("Inserting 5,6,6,8,9 into the list");
        listx.insert(5);
        listx.insert(6);
        listx.insert(6);
        listx.insert(8);
        listx.insert(9);
        System.out.println(listx);
        System.out.println("Now inserting elements from 0 to 100");
        for( int i= 0 ; i<100; ++i) {
            listx.insert(i);
        }
        System.out.println(listx);
        System.out.println("Now inserting 12,3,3,4 and -25");
        listx.insert(12);
        System.out.println(listx);

        listx.insert(3);
        System.out.println(listx);

        listx.insert(3);
        System.out.println(listx);

        listx.insert(4);

        listx.insert(-25);
        System.out.println(listx);

        System.out.println("Now removing 3 from the list");
        listx.remove(3);
        System.out.println(listx);
        System.out.println("Removing 3 again");
        listx.remove(3);
        System.out.println(listx);
        System.out.println("Removing 3 again");
        listx.remove(3);
        System.out.println(listx);
        System.out.println("Removing -25");
        listx.remove(-25);
        System.out.println(listx);
        System.out.println("Removing 5");
        listx.remove(5);
        System.out.println(listx);
        System.out.println("Removing 2");
        listx.remove(2);
        System.out.println(listx);
        System.out.println("Removing 97");
        listx.remove(97);
        System.out.println(listx);
        System.out.println("Removing 96");
        listx.remove(96);
        System.out.println(listx);
        System.out.println("Removing 98");
        listx.remove(98);
        System.out.println(listx);

        System.out.println("Removing elements from 0 to 100");
        for(int i = 99; i>=0; --i) {
            System.out.println("I is " + i);
            listx.remove(i);
        }
        System.out.println(listx);
        System.out.println("Finding 6");
        if(listx.find(6) != null)
            System.out.println("6 is found");
        System.out.println("Finding 15");
        if(listx.find(15)==null)
            System.out.println("15 is not in the list");
        System.out.println("Removing 6,8,9,12");
        listx.remove(6);
        listx.remove(8);
        listx.remove(9);
        listx.remove(12);
        System.out.println(listx);
        System.out.println("Removing 6");
        listx.remove(6);
        System.out.println(listx);
        System.out.println("Removing 4");
        listx.remove(4);
        System.out.println(listx);
        System.out.println("Size of the list is " + listx.size());
    }
}
