package gtu.automation_system_for_cargo_company;

/**
 * Dynamic Array is used for storing data in arrays
 * and maniplating it so that the array is not fixed
 * It is also generic so that any type could be used with it
 * @param <T> generic type
 */
public class DynamicArray<T>{
    /**
     * array of Objects
     */
    private Object[] arr;
    /**
     * Current index at which new element is added
     */
    private int current_index;
    /**
     * Set at 10
     */
    final private static int minimum_size = 10;
    /**
     * Constant at which array gets larger or smaller, afteer exceeding 10, it will extend by previous_size*extension_constant
     */
    final private static int extension_constant = 2;

    /**
     * No parameter constructor for dynamic array
     * sets the array to its minimum size of 10 elements
     */
    public DynamicArray(){
        arr = new Object[minimum_size];
        current_index = 0;
    }

    /**
     * Inserts a new element at the end of the array, does not allow duplicates
     * @param el Takes a generic type element
     * @return true if element is not already inside that array, false otherwise
     */
    public boolean insert( T el ){
        if( !elementExists(el) ) {

            if (current_index == arr.length)
                extendArrayCapacity();
            arr[current_index] = el;
            ++current_index;
            return true;
        }
        else
            return false;
    }

    /**
     *Removes an element form the array
     * @param el takes a generic type element as parameter
     * @return true if element exists, false otherwise
     */
    public boolean remove( T el ){//Does not check if the elemnt exists
        boolean success = false;
        int index_el = findElementIndex( el );
        if( index_el != -1 ) {
            success = remove(index_el);
        }
        return success;
    }

    /**
     * Removes an element providing its index
     * @param index_el index in the array
     * @return returns true if it the index is not out of bounds
     */
    public boolean remove( int index_el ){
        boolean success = false;
        if( index_el >= 0 && index_el < current_index ) {
            if (current_index > 1) {
                while (index_el != current_index - 1) {
                    arr[index_el] = arr[index_el + 1];
                    ++index_el;
                }
                if (index_el == arr.length - 1)
                    shrinkArrayCapacity();
            }
            --current_index;
        }
        return success;
    }

    /**
     * Finds the index of the element
     * @param el takes generic type
     * @return the index of the element or -1 if the element does not exist inside of the array
     */
    private int findElementIndex( T el ){//does not check if element exists
        for( int i=0;i<=current_index;++i)
            if( arr[i].equals( el ) )
                return i;
        return -1;//if there is no element in the array
    }

    /**
     * Inner private method that is used for manipulating the memory of the dynamic array
     * it make the array larger when needed
     */
    private void extendArrayCapacity(){
        Object[] temporary_array = new Object[ arr.length*extension_constant ];
        for( int i = 0; i<arr.length; ++i )
            temporary_array[i] = arr[i];
        arr = temporary_array;
    }

    /**
     * Inner private method that is used for manipulating the memory of the dynamic array
     * It makes an array smaller when needed
     */
    private void shrinkArrayCapacity(){
        Object[] temporary_array = new Object[ arr.length/extension_constant ];
        for( int i = 0; i < temporary_array.length; ++i )
            temporary_array[i] = arr[i];
        arr = temporary_array;
    }

    /**
     * returns the size of the array
     * @return gives the size of the array
     */
    public int Size(){
        return current_index;
    }

    /**
     * Returns the generic type that is inside the array
     * @param i - index in the array
     * @return will return null if paramter i is out of bounds, otherwise reference to the generic type object
     */
    public T get( int i ){
        if( i>= 0 && i<current_index )
         return (T)arr[i];
        else
            return null;
    }

    /**
     * @param el generic type parameter
     * @return true if the element is inside of the array
     */
    public boolean elementExists( T el ){
        for( int i = 0; i<current_index; ++i ) {
            if (el.equals( ((T)arr[i]) ))
                return true;
        }
        return false;
    }

    /**
     * @param el generic type obejct
     * @return returns the reference to the array's object that is equal to the given parameter's object
     */
    public T getElement( T el ){
        if( elementExists(el) )
            return (T)arr[findElementIndex(el)];
        else
            return null;
    }

    /**
     * Used to print the array
     * @return String of format 1) T1 .... size) Tsize
     */
    @Override
    public String toString() {
        String to_return = "";
        for( int i = 0; i<current_index; ++i )
            to_return += (i+1) + ") " + arr[i].toString() + "\n";
        return to_return;
    }
}//Dynamic array class ends here

