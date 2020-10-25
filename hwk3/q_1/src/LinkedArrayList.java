import java.security.InvalidParameterException;
import java.util.*;

public class LinkedArrayList<T> extends AbstractList<T> implements Iterable<T> {
    private int size=0;
    private Node head;
    private Node tail;

    private final static int ARR_SIZE = 4;

    private static class Node<T> {
        private T[] elements;
        /**
         * reference to the next node
         */
        private Node next;
        /**
         * reference to the previous node
         */
        private Node prev;
        /**
         * current index in the array
         */
        private int current_i;
        /**
         * constant size
         */
        public Node() {
            current_i = 0;
            elements = (T[]) new Object[ARR_SIZE];
            next = null;
            prev = null;
        }

        private Node(T el, Node<T> next, Node<T> prev) {
            this();
            add(el);
            this.next = next;
            this.prev = prev;
        }

        /**
         * Enables an element to be added to the array
         *
         * @param el generic type
         */
        private boolean add(T el) {
            if (current_i != ARR_SIZE) {
                elements[current_i] = el;
                current_i++;
                return true;
            }
            return false;
        }

        /**
         * getter for the next element of the list
         *
         * @return reference to next Node
         */
        public Node getNext() {
            return next;
        }

        /**
         * getter for the prev element of the list
         *
         * @return reference to prev Node
         */
        public Node getPrev() {
            return prev;
        }

        /**
         * sets the next node
         *
         * @param next Node type
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * sets the next node
         *
         * @param prev reference to Node type
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        /**
         * Replaces the element at the specified position in this list with the specified element (optional operation).
         *
         * @param i  - index
         * @param el - element to be replaced with
         * @return generic type, added element
         */
        public T set(int i, T el) {
            elements[i] = el;
            return el;
        }

        /**
         * returns the maximum capacity of each partially filled array
         * @return integer
         */
        public int capacity() {
            return ARR_SIZE;
        }

        /**
         * returns the current size of the partially filled array
         *
         * @return int
         */
        public int size() {
            return current_i;
        }

        /**
         * returns an element in an array using its index
         * Throws an exception if out of bounds
         *
         * @return Generic type
         */
        public T get(int i) {
            if (i >= 0 && i < current_i)
                return elements[i];
            else
                throw new InvalidParameterException();
        }

        /**
         * removes an element in the array and shifts all the remaining elements
         *
         * @param index array's index
         * @return the elements removed
         */
        public T remove(int index) {
            T temp = elements[index];
            for (int i = index; i < this.size() - 1; ++i)
                elements[i] = elements[i + 1];
            --current_i;
            return temp;
        }

        @Override
        public String toString() {
            String str = "";
            for (int i = 0; i < current_i; i++)
                str += elements[i];
            return str;
        }
    }

    /**
     * No parameter consturctor for linkedarrayList
     * sets size to 0
     * allocates memory for the head
     */
    public LinkedArrayList() {
        size = 0;
        head = tail = new Node();
    }

    /**
     * returns node at the indexed position
     * @param index integer
     * @return Node<T> type
     */

    private Node<T> getNode(int index) {
        Node<T> temp = head;
        for (int i = 0; i < index; ++i)
            temp = temp.getNext();
        return temp;
    }

    /**
     * returns element from the index
     *
     * @param i int, index in our array
     * @return generic type T
     */
    public T get(int i) {
        if (i >= 0 && i < size()) {
            Node<T> temp = head;
            while( i >= temp.size() ){
                i -= temp.size();
                temp = temp.getNext();
            }
            return temp.get(i);
        } else
            throw new InvalidParameterException();
    }

    /**
     * Adding an elemnt to the our array
     *
     * @param el generic type
     * @param i  index
     */
    public void add(int i, T el) {
        if( size() == 0 )
            add(el);
        else if (i >= 0 && i < size() && size() != 0) {
            add(tail.get(tail.size() - 1));//making sure array is extended for new element that is to be added
            for (int index = size() - 1; index > i; --index)
                set(index, get(index - 1));
            set(i, el);
        }
        else if( i == size() )//add it as a new tail
            add(el);
        else
            throw new InvalidParameterException();
    }

    /**
     * adds an element to the list
     * @param el to be added
     * @return always true
     */
    @Override
    public boolean add(Object el) {//it does not fail
        if (!tail.add(el)) {
            tail.setNext(new Node(el, tail.getNext(), tail));
            tail = tail.getNext();
        }
        ++size;
        return true;
    }
    /**
     * removes an element from our array
     *
     * @param i index of the element
     * @return removed element
     */
    public T remove(int i) {
        T to_return = null;
        if (i >= 0 && i < size()) {
            Node<T> aimed_node = head;
            while( i >= aimed_node.size() ){
                i -= aimed_node.size();
                aimed_node = aimed_node.getNext();
            }
            to_return = aimed_node.remove(i);
            if (aimed_node.size() == 0) {
                if (aimed_node.getPrev() != null)
                    aimed_node.getPrev().setNext(aimed_node.getNext());
                if (aimed_node.getNext() != null)
                    aimed_node.getNext().setPrev(aimed_node.getPrev());
            }
            if (aimed_node == head && aimed_node.size() == 0)
                head = head.getNext();
            if (aimed_node == tail && aimed_node.size() == 0)
                tail = tail.getPrev();
            --size;
        } else
            throw new InvalidParameterException();
        return to_return;
    }

    /**
     * Adding an elemnt to the our array
     *
     * @param el generic type
     * @param i  index
     */
    public T set(int i, T el) {
        if (i >= 0 && i < size()) {
            Node<T> temp = head;
            while( i >= temp.size() ){
                i -= temp.size();
                temp = temp.getNext();
            }
            return temp.set(i,el);
        } else
            throw new InvalidParameterException();
    }

    /**
     * Returns the size of the LinkedArrayList
     *
     * @return integer
     */
    public int size() {
//        int size = 0;
//        Node<T> temp = head;
//        while (temp != null) {
//            size += temp.size();
//            temp = temp.getNext();
//        }
//        return size;
        return size;
    }

    @Override
    public String toString() {
        String str = "";
        Node temp = head;
        while (temp != null) {
            str += temp;
            if (temp != tail)
                str += " ==> ";
            temp = temp.getNext();
        }
        return str;
    }

    private Node<T> getHead(){
        return head;
    }
    // return Iterator instance
    public LinkedArrayListIter2  listIterator()
    {
        return new LinkedArrayListIter2();
    }
    public LinkedArrayListIter2 listIterator(int i)
    {
        return new LinkedArrayListIter2(i);
    }

    private class LinkedArrayListIter2 implements ListIterator<T>{

        private int node_index = 0,node_index_prev=0;
        private Node<T> node = null, prevNode = null;
        private T nextItemT, lastItemReturnedT;
        private int index = 0;
        private boolean forward = true;

        /**
         * No paramter consturcotr for listIterator of our class
         */
        public LinkedArrayListIter2() {
            node = head;
            if( node.size() > 0 )
                nextItemT = node.get(0);
            //maybe catch an exception here when size  is  0 since head can't be null as it is immediately set
        }

        /**
         * One parameter constructor for our class
         * @param i index of the element to be returned
         */
        public LinkedArrayListIter2( int i ){
            Node<T> aimed_node = head;
            index = i;
            while( i >= aimed_node.size() ){
                i -= aimed_node.size();
                aimed_node = aimed_node.getNext();
            }
            node_index = i;
            node = aimed_node;
            nextItemT = node.get(node_index);
        }

        /**
         * Returns if there is next element in the list when list is iterater with iterator
         * @return true if next element exists false otherwise
         */
        public boolean hasNext(){
            return node != null;
        }

        /**
         * Moves iterator one element forward and returns the last element
         * @return last element iterator was pointing to
         */
        public T next(){
            forward = true;
            if(!hasNext())
                throw new NoSuchElementException();
            lastItemReturnedT = nextItemT;
            node_index_prev = node_index;
            prevNode = node;
            ++node_index;
            if( node_index == node.size() ){
                node = node.next;
                node_index = 0;
            }
            if( node != null )
                nextItemT = node.get(node_index);
            ++index;
            return lastItemReturnedT;
        }

        /**
         * Returns if the previous element exists
         * @return true if it exists false otherwise
         */
        public boolean hasPrevious(){
            if( (node == head && prevNode == null ) )
                return false;
            else if( ( node == null && prevNode == head ) )
                return false;
            else
                return true;
        }
        /**
         * returns the previous element in the list
         * @return T generic type, element
         */
        public T previous(){
            forward = false;
            if( hasPrevious() ){
                if( node == null ) {
                    node = tail;
                    node_index = node.size()-1;
                }
                prevNode = node;
                node_index_prev = node_index;
                lastItemReturnedT = nextItemT;

                --node_index;
                if( node_index == -1 ) {
                    node = node.prev;
                    if( node != null )
                        node_index = node.size()-1;
                }
                if( node != null )
                    nextItemT = node.get(node_index);
                --index;
                return lastItemReturnedT;
            }
            else
                throw new NoSuchElementException();
        }

        /**
         * Removes the last element returned in the list
         */
        public void remove(){
            if( prevNode == null )
                prevNode = node;
            prevNode.remove(node_index_prev);
            --size;
            if( prevNode.size() == 0 ){
                if( prevNode.next != null )
                    prevNode.next.setPrev(prevNode.prev);
                if( prevNode.prev != null )
                    prevNode.prev.setNext(prevNode.next);
            }
            if( prevNode.size() == 0 && prevNode == head )
                head = prevNode.next;
            if( prevNode.size() == 0 && prevNode == tail )
                tail = tail.prev;
            if( forward && hasPrevious() )
                previous();
            return;
        }

        /**
         * sets the last element removed in the list
         * @param obj
         */
        public void set( T obj ){
            prevNode.set(node_index_prev,obj);
//            lastItemReturnedT = obj;
        }

        /**
         * Adds a new object at the position of the last returned element
         * @param obj object to be added
         */
        public void add(T obj){
            if( !tail.add(obj) ) {
                tail.next = new Node(obj, tail.getNext(), tail);
                tail = tail.next;
            }
            ++size;
            Node<T> temp = node;
            int i = index;
            int n_i = node_index;

            if( node == null ) {//when addition is at the end of the list it is already added than
//                temp = tail;
//                next();

                return;

//                n_i = tail.size()-1;
            }

            while( i<size() ){
                T val_temp = temp.get(n_i);
                temp.set(n_i, obj);
                obj = val_temp;

                ++n_i;
                if( n_i == temp.size() ) {
                    temp = temp.next;
                    n_i = 0;
                }
                ++i;
            }
            if(forward && hasNext())
                next();
//            else
//                previous();
        }

        /**
         * Returns index of the next element to be returned in the list
         * @return
         */
        public int nextIndex() {
            return index;
        }

        /**
         * Returns index of the last element that was returned
         * @return int
         */
        public int previousIndex(){
            return index -1;
        }
    }
}