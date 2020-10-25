import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {

        System.out.println("Using iterator to manipulate the list");
        System.out.println();
        LinkedArrayList<Character> list = new LinkedArrayList<>();
        ListIterator<Character> i = list.listIterator();

        i.add('1');
        i.add('2');
        i.add('3');
        i.add('4');
        i.add('5');
        i.add('6');
        i.add('7');
        i.add('8');
        i.add('9');
        i.add('0');
        i.add('1');
//        System.out.println(list);

        System.out.println("Printing using iterator: ");
       i = list.listIterator();
        while(i.hasNext())
            System.out.print(i.next() + " ");
        System.out.println("\nPrinting in reverse using iterator");
        while(i.hasPrevious())
            System.out.print(i.previous() + " ");
        System.out.println();

        System.out.println("Setting each '1' and '5' to '-' character");
        i=list.listIterator();
        while(i.hasNext()){
            Character ch = i.next();
            if( ch == '1' || ch == '5' ) {
                i.set('-');
            }
        }
        System.out.println("Printing it now");
        i = list.listIterator();
        while(i.hasNext())
            System.out.print(i.next() + " ");

        System.out.println("\nAdding '+' at 0th index, 2nd index and at 13th index using iterator");
        i = list.listIterator(0);
        i.add('+');
        i = list.listIterator(2);
        i.add('+');
        i = list.listIterator(12);
        i.add('+');
        i = list.listIterator();
        while(i.hasNext())
            System.out.print(i.next() + " ");

        System.out.println("\nTaking iterator at 5th position and checking its lastItem index nad NextItem index");
        i = list.listIterator(5);
        System.out.println("Previous index is: " + i.previousIndex());
        System.out.println("Next index is: " + i.nextIndex());
        System.out.println("\nIterating and finding all '+' characteds and '-' characters and removing them");
        i = list.listIterator();
        while( i.hasNext() ){
            Character ch = i.next();
            if( ch =='-' ||  ch == '+' )
                i.remove();
        }

        i = list.listIterator();
        while(i.hasNext())
            System.out.print(i.next() + " ");

        System.out.println("Manipulating list using its own functions");
        System.out.println();
        LinkedArrayList<Character> list2 = new LinkedArrayList<>();
        list2.add('1');
        list2.add('2');
        list2.add('3');
        list2.add('4');
        list2.add('5');
        list2.add('6');
        list2.add('7');
        list2.add('8');
        list2.add('9');
        list2.add('0');
        list2.add('1');
        System.out.println("Printing list after adding elements to it: \n" + list2 );
        System.out.println("Size of the list is" + " " + list2.size());
        System.out.println("Setting '1's and '5's' to '-' ");
        list2.set(0,'-');
        list2.set(10,'-');
        list2.set(4,'-');
        System.out.println("Printing after setting \n" + list2);
        System.out.println("\nAdding '+' at 0th index, 2nd index and at 12th index using iterator");
        list2.add(0,'+');
        list2.add(2,'+');
        list2.add(12,'+');
        System.out.println("Printing after adding");
        System.out.println(list2);
        System.out.println("Size after adding is" + list2.size() );
        System.out.println("Removing '2' from the list");
        list2.remove(new Character('2'));
        System.out.println("Printing after removing");
        System.out.println(list2);
        System.out.println("Size after removing is: " + list2.size() );
        i = list2.listIterator();
        System.out.println("Finding '-' and removing them also using iterator");
        while( i.hasNext() ){
            Character ch = i.next();
            if( ch == '-')
                i.remove();
        }

        i = list2.listIterator();
        while( i.hasNext() )
            System.out.print(i.next() + " ");
        System.out.println("\nSize is now " + list2.size());

    }
}
