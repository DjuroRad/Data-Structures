import java.security.InvalidParameterException;
import java.util.Iterator;//used for interface implementation....
import java.util.NoSuchElementException;

public class Deque<T> extends java.util.AbstractCollection<T> implements java.util.Deque<T> {
    /**
     * keeps the list of the removed elemetns
     */
    private LinkedList<T> removed_elements;
    /**
     * keeps the list of the elements in the queue
     */
    private LinkedList<T> elements;

    /**
     * This class
     * @param <T> generic type of the list
     */
    private static class LinkedList<T>{
        /**
         * keeps the front of the list
         */
        private Node<T> head;
        /**
         * keeps the end of the list
         */
        private Node<T> tail;
        /**
         * keeps the size of the list
         */
        private int size;
        /**
         * no parameter constructor for the list
         */
        public LinkedList(){
            head = tail = null;
            size = 0;
        }

        /**
         * adding an element to the head of the list
         * @param t element to be added
         */
        public void addFirst( T t ){
            if( size == 0 )
                head = tail = new Node<T>(t);
            else{
                Node<T> temp = new Node<T>(t);
                temp.next = head;
                head.prev = temp;
                head = temp;
            }
            ++size;
        }

        /**
         * Used to add an element to a linked list if there is a node already prepared( called when there is at least one node in removed_elements linked list
         * @param removed_node one that is to be removed
         * @param t element ot add
         */
        private void addFirst( Node<T> removed_node, T t ){
            removed_node.el = t;
            removed_node.next = removed_node.prev = null;
            addFirst(removed_node);//size is updated inside of this method
        }


        /**
         * Adds a node to the linked list. No need for creation of a new node sometimes
         * @param node_to_add Node to be added
         */
        private void addFirst( Node<T> node_to_add ){
            node_to_add.next = head;//updating it to show to head which either referes to an address or is null
            if( size == 0 )
                head = tail = node_to_add;
            else {
                head.prev = node_to_add;
                head = node_to_add;
            }
            ++size;
        }
        /**
         * Adding an element to a linked list
         * @param node_to_add node to be added
         * @param t to be added element
         */
        public void addLast( Node <T> node_to_add , T t ){
            if( size == 0 )
                addFirst( node_to_add, t );//doesn't matter here it will work the same as adding to the front
            else {
                node_to_add.el = t;
                node_to_add.next = null;
                node_to_add.prev = tail;
                tail.next = node_to_add;
                tail = node_to_add;
                ++size;
            }
        }

        /**
         * adds a node to the end of the list
         * @param t element to be added
         */
        private void addLast( T t ){
            addLast( new Node<T>(), t );
        }
        /**
         * returns the head node and removes it
         * @return head of the linked list
         */
        private Node<T> popNode(){
            Node<T> to_pop = head;
            head = head.next;
            if( head != null )
                head.prev = null;//setting it to be null to indeicate the last element is deleted
            else
                tail = head;//when the queue is emptied out
            --size;
            return to_pop;
        }

        /**
         * returns the tail of the list and removes it
         * @return tail of this list as a Node
         */
        private Node<T> popNodeLast(){
            Node<T> to_pop = tail;
            tail = tail.prev;
            if( tail != null )//when the size of the queue does not become 0
                tail.next = null;//removing the tail element here
            else
                head = tail;//when the queue is emptied out

            --size;
            return to_pop;
        }

        /**
         * removes the first occurence of a certain element
         * @param el element to be removed
         * @return Node of the element removed or null if the element is not found
         */
        private Node<T> removeFirstOccurence( T el ){

            Node<T> temp = head;
            while( temp != null){
                if( temp.el.equals(el) ){//gotta remove it
                    removeNode(temp);//size is updated in this method
                    break;//element found and removed, no need to continue searching for an element
                }
                else
                    temp = temp.next;
            }
            return temp;
        }
        /**
         * removes the last occurence of a certain element
         * @param el element to be removed
         * @return Node of the element removed or null if the element is not found
         */
        private Node<T> removeLastOccurence( T el ){
            Node<T> temp = tail;
            while( temp != null){
                if( temp.el.equals(el) ){//gotta remove it
                    removeNode(temp);//size is updated in this method
                    break;//element found and removed, no need to continue searching for an element
                }
                else
                    temp = temp.prev;
            }
            return temp;
        }
        /**
         * Removing a node that exists in the list( has to be checked before calling the method )
         * @param node node to be removed
         */
        private void removeNode( Node<T> node ){
            if( node.prev != null )
                node.prev.next = node.next;
            if( node.next != null )
                node.next.prev = node.prev;
            if( node == head )//updating head and tail accordingly
                head = head.next;
            if( node == tail )//updating head and tail accordingly
                tail = tail.prev;
            --size;//updated size of the list
        }
        /**
         * checks if the linked list is empty or not
         * @return boolean value
         */
        private boolean isEmpty(){
            return size == 0;
        }

        /**
         * to string method used since it is realiable for printing when testing
         * @return string
         */
        @Override
        public String toString() {
            Node<T> temp = head;
            String str = "";
            while( temp != null ){
                str += temp.el;
                if( temp.next != null )
                    str += " ";
                temp = temp.next;
            }
            return str;
        }
    }
    /**
     * Node that is used to keep elements in the linked list
     * @param <T> generic type here
     */
    private static class Node<T>{
        /**
         * Element the node contains
         */
        private T el;
        /**
         * next node it is pointing to
         */
        private Node<T> next;
        /**
         * prev node it is pointing to
         */
        private Node<T> prev;

        /**
         * no parameter constructor that assigns both el and next to null
         */
        public Node(){
            this(null);
        }

        /**
         * one paramter constructructor that assigns
         * @param t value that the node will contain
         */
        public Node( T t ){
            prev = next = null;
            el = t;
        }
    }

    //METHODS OF DEQUE

    /**
     * no parameter constructor that initializes two linked lists deque is manipulating
     */
    Deque(){
        removed_elements = new LinkedList<T>();
        elements = new LinkedList<T>();
    }

    /**
     * Normal iterator iterating form head to tail
     * @return iteraotr of deque
     */
    @Override
    public Iterator<T> iterator() {
        return new IteratorDeque(this);
    }

    /**
     * returns a descending iterator meaning it will iterate form tail to head
     * @return descending iteartor for this deque
     */
    @Override
    public Iterator<T> descendingIterator() {
        return new DescendingIteratorDequeue(this);
    }


    /**
     * add method that adds to the begining of the deque
     * @param t element to be added
     * @return true if added, false otherwise
     */
    @Override
    public boolean add( T t ){
        boolean success = offerFirst(t);
        if( !success )
            throw new IllegalStateException();
        return success;
    }
    /**
     * adds an element to the head of deque's list
     * @param t element to add
     * @throws InvalidParameterException when the parameter passed is invalid
     */
    @Override
    public void addFirst(T t) {
        if( !offerFirst(t) )
            throw new InvalidParameterException();
    }
    /**
     * adds an element to the tail of the list
     * @param t element to be added
     * @throws InvalidParameterException when the parameter passed is invalid
     */
    @Override
    public void addLast(T t) {
        if( !offerLast(t) )
            throw new InvalidParameterException();
    }

    /**
     * does the same thing as the addFirst method with the difference it returns booelan and does not throw an exception
     * @param t element to be added
     * @return true if parameter is valid false otherwise
     */
    @Override
    public boolean offerFirst(T t) {
        if( t != null ) {
            if (!removed_elements.isEmpty())
                elements.addFirst(removed_elements.popNode(), t);
            else
                elements.addFirst(t);
            return true;
        }
        return false;
    }
    /**
     * does the same thing as the addLast method with the difference it returns boolean and does not throw an exception
     * @param t element to be added
     * @return true if parameter is valid false otherwise
     */
    @Override
    public boolean offerLast(T t) {
        if( t != null ) {
            if (!removed_elements.isEmpty())
                elements.addLast(removed_elements.popNode(), t);
            else
                elements.addLast(t);
            return true;
        }
        return false;
    }
    /**
     * Retrieves and removes the first element of this deque( element at the head of this deque )
     * Element that is removed is added to the list of removed elements
     * @return element at the head
     * @throws RuntimeException when the queue is already empty
     */
    @Override
    public T removeFirst() {
        T to_remove = pollFirst();
        if( to_remove != null )
            return to_remove;
        else
            throw new RuntimeException("Removing from an empty deque not possible");
    }
    /**
     * Retrieves and removes the last element of this deque( element at the head of this deque )
     * Element that is removed is added to the list of removed elements
     * @return element at the head
     */
    @Override
    public T removeLast() {
        T to_remove = pollLast();
        if( to_remove != null )
            return to_remove;
        else
            throw new RuntimeException("Removing from an empty deque not possible");
    }

    /**
     * removes the first element in the queue
     * @return the element removed, null if the queue is empty
     */
    @Override
    public T pollFirst() {
        if( !elements.isEmpty() ) {
            T to_return = elements.head.el;//element to be removed from our queue and added to our list of removed elements
            Node<T> to_remove = elements.popNode();//gets the head of our elements, at the same time it decrees the size of the elements and removes the element removed
            removed_elements.addFirst(to_remove);
            return to_return;
        }
        else
            return null;
    }

    /**
     * Removes the last element in our queue
     * @return the element removed( one at the tail )
     */
    @Override
    public T pollLast() {
        if( !elements.isEmpty() ){
            T to_return = elements.tail.el;
            Node<T> to_remove = elements.popNodeLast();
            removed_elements.addFirst(to_remove);//just adding this node to the list of removed elemetns( does not matter where in the list i add it
            return to_return;
        }
        else
            return null;
    }
    /**
     * gives the first element in deque back
     * @return first element in deque( at head )
     * @throws RuntimeException if deque is empty
     */
    @Override
    public T getFirst() {
        T to_return = peekFirst();
        if( to_return != null )
            return to_return;
        else
            throw new RuntimeException("Trying to get an element of an empty deque");
    }

    /**
     * gives the last element in deque back
     * @return last element in deque( at tail )
     * @throws RuntimeException if deque is empty
     */
    @Override
    public T getLast() {
        T to_return = peekLast();
        if( to_return != null )
            return to_return;
        else
            throw new RuntimeException("Trying to get an element of an empty deque");
    }

    /**
     * Works the same as getFirst with difference that it does not throw an exception
     * @return first element of deque
     */
    @Override
    public T peekFirst() {
        if( !elements.isEmpty() )
            return elements.head.el;
        return null;
    }
    /**
     * Works the same as getFirst with difference that it does not throw an exception
     * @return last element of deque
     */
    @Override
    public T peekLast() {
        if( !elements.isEmpty() )
            return elements.tail.el;
        return null;
    }

    /**
     * Removes the first occurence of an element in deque
     * @param o element to be removed
     * @return false is is in deque and true otherwise
     */
    @Override
    public boolean removeFirstOccurrence(Object o) {
        @SuppressWarnings("unchecked") T el = (T)o;
        if( o == null )
            throw new NullPointerException("Deque does not support null elements");
        if( el == null ) {
            throw new ClassCastException();
        }
        Node<T> to_add = elements.removeFirstOccurence(el);//this method returns the node to be added to our removed_element in order to make things work faster
        if( to_add != null ) {
            removed_elements.addFirst(to_add);//adds the removed node to the removed_elements list
            return true;
        }
        else
            return false;

    }
    /**
     * Removes the first occurence of an element in deque
     * @param o element to be removed
     * @return false is is in deque and true otherwise
     */
    @Override
    public boolean removeLastOccurrence(Object o) {
        @SuppressWarnings("unchecked") T el = (T)o;
        if( o == null )
            throw new NullPointerException("Deque does not support null elements");
        if( el == null ) {
            throw new ClassCastException();
        }
        Node<T> to_add = elements.removeLastOccurence(el);//this method returns the node to be added to our removed_element in order to make things work faster
        if( to_add != null ) {
            removed_elements.addFirst(to_add);//adds the removed node to the removed_elements list
            return true;
        }
        else
            return false;
    }

    /**
     * inserts an element to the 'queue' of our deque or to be more precise to the tail of our deque
     * @param t element to be added
     * @return true if insertion is successful and false otherwise
     */
    @Override
    public boolean offer(T t) {
        return offerLast(t);//of course here is just calls offerLast method
    }

    /**
     * removes from the head of the queue
     * @return the same as removeFirst( the element removed
     * @throws RuntimeException since it calls removeFirst() method
     */
    @Override
    public T remove() {
        return removeFirst();
    }

    /**
     * removes the element at the head of deque( equivalent to pollFirst )
     * @return the element removed
     */
    @Override
    public T poll() {
        return pollFirst();
    }

    /**
     * does the same thing getFirst does( returns element at the head )
     * @return the first element
     * @throws RuntimeException when deque is empty the same way getFirst does
     */
    @Override
    public T element() {
        return getFirst();
    }

    /**
     * returns the first element in deque
     * @return first element or null if empty
     */
    @Override
    public T peek() {
        return peekFirst();
    }

    /**
     * Adding an element to the stack represented by this deque, equivalent to addFirst
     * @param t element to be added
     * @throws InvalidParameterException if the element to be added is invalid
     */
    @Override
    public void push(T t) {
        addFirst(t);
    }

    /**
     *Pops an element from the stack represented by this deque
     * @return element removed
     * @throws RuntimeException if the deque is empty( equivalent to removeFirst )
     */
    @Override
    public T pop() {
        return removeFirst();
    }

    /**
     * Returns the size of the elements in deque
     * @return number of elements in deque
     */
    @Override
    public int size() {
        return elements.size;
    }

    /**
     * returns the linked list's toString method's output ( for elements linked list which are current elements )
     * @return deque represented by the string
     */
    @Override
    public String toString() {
        return elements.toString();
    }

    //ITERATORS

    /**
     * Iterator of deque
     */
    private class IteratorDeque implements Iterator<T>{
        /**
         * points to the previous element
         * protected since descending itarotr inherits it
         */
        protected Node<T> prev;
        /**
         * points to the next element to be returned
         * protected since descending itarotr inherits it
         */
        protected Node<T> next;

        /**
         * no parameter constuctor
         */
        public IteratorDeque(){
            prev = next = null;
        }

        /**
         * one parameter constuructor making the iterator
         * @param deque deque it is iterating over
         */
        private IteratorDeque(Deque<T> deque){
            prev = next = deque.elements.head;
        }

        /**
         * checks if there is a next elemnt in iterator
         * @return true if such element exists and false otherwise
         */
        @Override
        public boolean hasNext() {
            return next != null;
        }

        /**
         * Impllements the next method
         * @return returns the element held in next and moves next for one position
         */
        @Override
        public T next() {
            if( !hasNext() )
                throw new NoSuchElementException();
            prev = next;
            next = next.next;
            return prev.el;

        }

        /**
         * removes the elemetn prev is currently pointing to
         */
        @Override
        public void remove() {
            elements.removeNode(prev);//removing the node of the last returned element
            removed_elements.addFirst(prev);//adding the removed node to removed_elemetns linked list of deque
            prev = null;//setting prev to be null after removing it
        }
    }
    /**
     * The same as normal Iterator made for this class with an excpetion that it is moving in backwards
     */
    private class DescendingIteratorDequeue extends IteratorDeque{
        /**
         * One parameter constructor that makes the iterator start fromt he tail of this list
         * @param deque deque it is iterating over
         */
        public DescendingIteratorDequeue(Deque<T> deque){
            next = prev = deque.elements.tail;
        }

        /**
         * Works a bit differently than normal iterator since it moves next to previous element
         * @return last element next pointed to
         */
        @Override
        public T next(){
            if( !hasNext() )
                throw new NoSuchElementException();
            prev = next;
            next = next.prev;
            return prev.el;
        }
    }
}
