import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        System.out.println("This test for Deque data strcture");
        System.out.println("Making deque with integers inside");
        Deque<Integer> deque = new Deque<Integer>();
        System.out.println("Adding numbers from 1 to 5 using addLast method");
        for (int i = 1; i <= 5; ++i)
            deque.addLast(i);
        System.out.println("Adding numbers from 5 to 10 using addFirst method");
        for (int i = 6; i <= 10; ++i)
            deque.addFirst(i);
        System.out.println("Printing deque using iterator");
        Iterator<Integer> i = deque.iterator();
        while (i.hasNext())
            System.out.print(i.next() + " ");
        System.out.println();

        System.out.println("Printing deque using descending iterator");
        i = deque.descendingIterator();
        while (i.hasNext())
            System.out.print(i.next() + " ");
        System.out.println();

        System.out.println("CHecking if deque contains 10, 1 and 5 ");
        if (deque.contains(10) && deque.contains(1) && deque.contains(5))
            System.out.println("It contains 10,1 and 5");
        else
            System.out.println("It does not contain 10,1 and 5");
        System.out.println("CHecking if deque contains 12");
        if (deque.contains(12))
            System.out.println("Contains 12");
        else
            System.out.println("Does not contain 12");
        System.out.println("Getting and printing the head of the deque using element method");
        System.out.println("Element() returns: " + deque.element());
        System.out.println("Getting and printing the head of the deque using getFirst method");
        System.out.println("GetFirst() returns: " + deque.getFirst());
        System.out.println("Getting and printing the last element of the deque using getLast method");
        System.out.println("GetLast() returns: " + deque.getLast());
        System.out.println("Inserting an element to queue of our deque using deque.offer(20)");
        deque.offer(20);
        System.out.println("Printing after inserting 20 with offer");
        System.out.println(deque);
        System.out.println("Inserting an element to our deque using deque.offerFirst(12)");
        deque.offerFirst(12);
        System.out.println("Printing after inserting 12 with offerFirst");
        System.out.println(deque);
        System.out.println("Inserting an element to our deque using deque.offerLast(14)");
        deque.offerLast(14);
        System.out.println("Printing after inserting 14 with offerFirst");
        System.out.println(deque);
        System.out.println("Returns the first element of the 'queue' of our deque using peek() method");
        System.out.println("Peek() returns: " + deque.peek());
        System.out.println("Using peekFirst() to check out the first element of deque");
        System.out.println("peekFirst() returns: " + deque.peekFirst());
        System.out.println("Using peekLast() to check out the last element of deque");
        System.out.println("peekLast() returns: " + deque.peekLast());
        System.out.println("Using poll to remove and get the first element of deque");
        System.out.println("Deque.poll() returns: " + deque.poll());
        System.out.println("Printing after call to poll() method - 12 should be removed from the head");
        System.out.println(deque);
        System.out.println("Using pollFirst to remove and get the first element of deque");
        System.out.println("Deque.pollFirst() returns: " + deque.pollFirst());
        System.out.println("Printing after call to pollFirst() method - 10 should be removed from the head");
        System.out.println(deque);
        System.out.println("Size of deque is: " + deque.size());
        System.out.println("Using pollLast to remove and get the last element of deque");
        System.out.println("Deque.pollLast() returns: " + deque.pollLast());
        System.out.println("Printing after call to pollLast() method - 14 should be removed from the tail");
        System.out.println(deque);
        System.out.println("Size of deque is: " + deque.size());
        System.out.println("Calling pop which pops an element from the 'stack' represented by this deque( pops element from the head(should remove 9 from deque ");
        System.out.println("Pop returns: " + deque.pop());
        System.out.println(deque);
        System.out.println("Calling push method which adds an element to 'stack' represented by deque");
        deque.push(999);
        System.out.println("After pushing 999 our deque is");
        System.out.println(deque);
        System.out.println("Size of deque is: " + deque.size());
        System.out.println("Calling remove() which removes head of our deque and removes it");
        System.out.println("Remove's output: " + deque.remove());
        System.out.println(deque);
        System.out.println("Size of deque is: " + deque.size());
        System.out.println("Removing a specific element using remove(T t) method. Calling it with '1'");
        deque.remove(1);
        System.out.println("After removing '1' deque is");
        System.out.println(deque);
        System.out.println("Deque size: " + deque.size());
        System.out.println("Calling removeFirst which removes the first element of deque");
        System.out.println("removeFirst returned: " + deque.removeFirst());
        System.out.println(deque);
        System.out.println("Deque size:" + deque.size());
        System.out.println("Adding 2 at the end and at the tail of our deque");
        deque.addLast(2);
        deque.addFirst(2);
        System.out.println(deque);
        System.out.println("Calling removeFirstOccurence for 2 ");
        deque.removeFirstOccurrence(2);
        System.out.println("After removing first occurrence of our deque it is:");
        System.out.println(deque);
        System.out.println("Removing the last occurrence of 2");
        deque.removeLastOccurrence(2);
        System.out.println(deque);
        System.out.println("Removing first occurence of it again");
        deque.removeFirstOccurrence(2);
        System.out.println(deque);
        System.out.println("Size of deque: " + deque.size());
        System.out.println("RemoveLast() returns: " + deque.removeLast());
        System.out.println(deque);
        System.out.println("Size of deque now is: " + deque.size());

        System.out.println("Trying to add a null element to queue");
        try{
            deque.add(null);
        }
        catch (Exception e ){
            System.out.println("Exception caught when trying to add null to our deque");
        }
        deque.add(2);
        System.out.println(deque);

        i = deque.iterator();
        while(i.hasNext()) {
            i.next();
            i.remove();
        }
        System.out.println("Size of deque after removing each element using iterator: " + deque.size());
        System.out.println("Adding 1,2,3 and 4 to deque");
        deque.add(1);
        deque.add(2);
        deque.add(3);
        deque.add(4);
        System.out.println(deque);
        i = deque.descendingIterator();
        System.out.println("Size of deque is: " + deque.size());
        while(i.hasNext()){
            i.next();
            i.remove();
        }
        System.out.println("size of deque after removing each element using descending iterator: " + deque.size() );
        System.out.println("Trying to use remove method on an empty deque");
        try{
            deque.remove();
        }
        catch (Exception e){
            System.out.println("Exception caught when trying to remove from an empty deque");
        }
    }
}