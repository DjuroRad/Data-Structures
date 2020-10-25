import java.util.ArrayList;
import java.util.Comparator;

public class MaxHeap{
    /**
     * stores all the elements in the array list
     */
    private ArrayList<AgeData> theData;
    /**
     * optional reference to a comparator object
     */
    private Comparator<AgeData> comparator;

    /**
     * No parameter constructor that calls one parameter constructor with null as a comparator
     */
    public MaxHeap() {
        this(new AgeDataComparator());//so that i could write in main as it is written( without parameters )
    }

    /**
     * One parameter constructor
     * @param comparator comparator to be used fro comparing elements( if null is passed compareTo method will be called )
     */
    public MaxHeap(Comparator<AgeData> comparator) {
        theData = new ArrayList<AgeData>();
        this.comparator = comparator;
    }


    /**
     * adds an element to our MaxHeap
     * @param data element to be added
     * @return false if element passed is null, true if it didn't exist and true if it already existed( list size is not incremented in this case but the counter is )
     */
    public boolean add( AgeData data ){
        if( data == null )
            return false;
        //first we need to call a method with linear complexity to check if this element already exits
        //if it exists we will increase the counter and than update the heap so that it remains a heap structure
        int index = theData.indexOf(data);
        if (index == -1) {//if it does not exist
            theData.add(data);//adds an element to the end of the list
            index = theData.size()-1;//now the index will not be -1 but the last index instead
        }
        else//it already exists
            (theData.get(index)).increaseCount();
            updateMaxHeap(index);//updated according to the comparator and the passed one should work for counter value for this exact example

        return true;
    }

    /**
     * putting the child in its place
     * @param child child to be placed at its position
     */
    private void updateMaxHeap( int child ){
        int parent = (child-1)/2;
        int compResult;
        do{
            compResult = compare( theData.get(child), theData.get(parent) );
            if( compResult > 0 ) {
                swapData(child, parent);//swaps the element until the condition is satisfied
                child = parent;
                parent = (child - 1) / 2;
            }
        }while( compResult > 0 );//we don't need to compare it for the root
    }

    /**
     * swaps two elements in the list
     * @param i first element's index
     * @param j second element's index
     */
    private void swapData( int i, int j ){
        AgeData temp = theData.get(i);
        theData.set(i,theData.get(j));
        theData.set(j,temp);
    }

    /**
     * Compares two elements
     * @param left first element
     * @param right second element
     * @return smaller than 0 if smaller, bigger if graeter and 0 if equal
     */
    private int compare( AgeData left, AgeData right ){
        if( comparator != null )
            return comparator.compare( left, right );
        else
            return (comparator.compare(left, right));
    }

    @Override
    public String toString() {
        String str ="";
        for( AgeData t: theData )
            str += t.toString() + '\n';
        return str;
    }

    /**
     * removes an element passed as a parameter here
     * @param data element to be removed
     * @return true if removed and false otherwise
     */
    public boolean remove(AgeData data){
        int index = theData.indexOf(data);
        if( index == -1 )//element does not exist
            return false; //since the element does not exist in here
        else{
            if((theData.get(index)).getCount() == 1 )
                theData.remove(data);//just remove it if that is the case, no problem
            else{
                (theData.get(index)).decreaseCount();
                updateMaxHeapDown( index );
            }
        }
        return true;
    }

    /**
     * used when the removal is done
     * @param parent
     */
    private void updateMaxHeapDown(int parent){
        int child1, child2;
        child1 = parent*2 + 1;
        child2 = child1 + 1;
        int bigger = child1;
        if( child2 < theData.size() && comparator.compare(theData.get(child1),theData.get(child2)) < 0 )
            bigger = child2;
        if( bigger< theData.size() && comparator.compare(theData.get(parent), theData.get(bigger)) < 0 ){
            swapData( parent, bigger);
            updateMaxHeapDown(bigger);
        }
        else
            return;

    }

    public AgeData find( AgeData data ){//this requires linear search that is used by the linked list since the elements are stored by their counter value
        int index = theData.indexOf(data);
        if( index == -1 )//when the desired element does not exist
            return null;
        return theData.get(index);//returning the element here since that is the desired bahaviour
    }

    /**
     * returns the number of people younger than a certain age
     * @param data age that is to be used for finding younger ones
     * @return number of poeple younger than the age
     */
    public int youngerThan( int data ){//all the elements need to be traversed since the the order depends on the number of people with the same age
        int count = 0;
        for( AgeData el: theData ){
            if( el.getAge().compareTo(data) < 0 )
                count += el.getCount();
        }
        return count;
    }

    /**
     * returns the number of people older than a ceratin age
     * @param data age younger than the ones that are to be be found older
     * @return number of people older than the age passed as a parameter to this method
     */
    public int olderThan( int data ){//all the elements need to be traversed here since the oreder depends on the number of people of the same age
        int count = 0;
        for( AgeData el: theData ){
            if( el.getAge().compareTo(data) > 0 )
                count += el.getCount();
        }
        return count;
    }

}
