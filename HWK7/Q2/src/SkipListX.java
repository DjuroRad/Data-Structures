import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Random;

public class SkipListX<T extends Comparable<T>>  implements SkipListInterface<T> {
    private int order = 0;
    private int minKey = 0;
    private int maxKey = 0;
    private SLNode<T> head = null;
    private int maxLevel = 0;
    private int size = 0;
    private int maxCap = 0;
    private static final double LOG2 = Math.log(2.0);
    private Random rand = null;

    @SuppressWarnings({"unchecked","rawtypes"})
    public SkipListX(int order) {
        if( order<2 )
            throw new InvalidParameterException("Use the basic skip list instead");
        this.order = order;
        maxKey = order-1;
        minKey = maxKey/2;
        size = 0;
        maxLevel = 0;
        maxCap = computeMaxCap();
        head = new SLNode(null, maxLevel, order);

        rand = new Random();
    }
    /**
     * searches for an element in the list and returns references of predecing nodes
     * @param target target to be found
     * @return array of predecing nodes(links)
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    private SLNode<T>[] search(T target){
        SLNode<T>[] pred = new SLNode[maxLevel];
        SLNode<T> current = head;

        for(int i = current.links.length-1; i>=0; --i){
            while(current.links[i] != null && current.links[i].data[current.links[i].size()-1].compareTo(target)<0)//has to be smaller than the last element to make an iteration since last element is the largest
                current = current.links[i];
            pred[i] = current;
        }
        return pred;
    }

    /**
     * computes the max cap with respect to maxLevel
     * @return new max cap
     */
    private int computeMaxCap(){
        return (int)Math.pow(2,maxLevel)-1;
    }
    @Override
    @SuppressWarnings({"unchecked","rawtypes"})
    public boolean remove(T target) {
        //maxLevel is not updated since we don't know if
        //some other node has the maxLevel! (it would slow thing down a lot if i were controling that every time giving removal linear complexity instead of logaritmic one
        SLNode<T> pred[] = search(target);
        if(pred[0].contains(target)){
            //case when item to be removed is in head
            if(pred[0].size() > minKey || pred[0].links[0] == null) {//when it contains more than minimum number of keys -> just remove OR head node is the only node left!
                --size;
                pred[0].remove(target);
                return true;
            }
            else{
                //idea is to swap this element with the smallest element in the next node which should be the
                //than continue normally
                T smallest = pred[0].links[0].data[0];
                pred[0].links[0].remove(smallest);//removing it now
                pred[0].add(smallest);//adding smallest to the head
                pred[0].remove(target);//removing the target
                pred[0].links[0].add(target);//adding target to the next node so that it could continue executing normally
            }
        }
        //after needed updates we can do the removal
        if(pred[0].links[0] != null && pred[0].links[0].contains(target)){//pred[0].links[0] != null checks when the element is out of bounds of this skip list
                --size;
            //case when it has more than minKey keys -> we can just remove it without any problem
            if(pred[0].links[0].size()>minKey)
                pred[0].links[0].remove(target);
            else if(pred[0].size()>minKey){
                //second case where preceding node has more than minimum number of key elements
                //we can borrow its largest element to the node whose element is to be removed
                T popped = pred[0].pop();
                pred[0].links[0].remove(target);
                pred[0].links[0].add(popped);
            }
            else{
                //case when both preceding node and target node have minimum number of key elements inside
                //we will remove target from the node
                //Than we will merge what is left in the preceding node( just add the remaining elements from the target node )
                //than we will relink so that nothing points to the node we removed( target node )
                pred[0].links[0].remove(target);//removing it
                for(int i = 0; i<pred[0].links[0].size(); ++i)//mergining it
                    pred[0].add(pred[0].links[0].data[i]);
                SLNode<T> temp = pred[0].links[0];
                for(int i = 0; i<temp.links.length; ++i) {//relinking so that preceding elements point to whatever targeted node was pointing to
                    pred[i].links[i] = temp.links[i];
                }
            }
            //when item to be removed in is not in the head node
            return true;
        }
        return false;
    }

    /**
     * inserts an elemnet in theskip list
     * @param data data to be inserted
     * @return true if insertion is  possible and false otherwise( always true )
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    @Override
    public boolean insert(T data) {
        if(data == null)
            throw new NullPointerException();
        ++size;
        SLNode<T> pred[] = search(data);
        if(size > maxCap){
            maxLevel++;
            maxCap = computeMaxCap();
            head.links = Arrays.copyOf(head.links, maxLevel);
            pred = Arrays.copyOf(pred, maxLevel);
            pred[maxLevel-1] = head;
        }
        //case 0 - when it is to be added at head
        //it's place is exactly at head
        if(pred[0] == head && head.size()>0 && pred[0].data[pred[0].size()-1].compareTo(data)>0){
            if(!pred[0].isFull()) {
                pred[0].add(data);
                return true;
            }
            //otherwise when head is full -> just pop the largest element, add the element to be added
            //and update element to be added to be the popped element
            T popped = pred[0].pop();
            pred[0].add(data);
            data = popped;
        }
        if(pred[0].links[0] == null){//first case when adding to the end( merging with respect to pred will need to occur )
            //two cases
            //when array is not full -> just add
            if(!pred[0].isFull()) {
                pred[0].add(data);
            }
            else{//when it is full -> we need to merge it
                //we know that the element to be inserted is the largest in the list since we are at the end...
                //so just make a new node with that element in it first
                SLNode<T> last_node = new SLNode<T>(data, logRandom(), order);
                for(int i = 0; i<minKey-1; ++i)//minKey-1 since data is already added meaning there is at least one element there
                    last_node.add(pred[0].pop());
                //now updating pred!
                for(int i = 0; i<last_node.links.length; ++i) {
                    last_node.links[i] = pred[i].links[i];
                    pred[i].links[i] = last_node;
                }
            }
            return true;
        }
        else if(pred[0].links[0] != null && !pred[0].links[0].isFull())//Second case when there is space in the following list
            pred[0].links[0].add(data);//just add it since there is space
        else{//third case when it is full and merging needs to occur
            //we are inserting somewhere inbetween right now
            //make a new node first
            //and add our data to it
            SLNode<T> new_node = new SLNode<T>(data, logRandom(), order);
            //than pop minKeys-1 elements from the preceding node so that new_node will have minimum number of keys
            for(int i = 0; i<minKey-1; ++i)
                new_node.add(pred[0].links[0].pop());
            //            //now we need to put the data at the right place
            //since it might not be sorted after this
            if(data.compareTo(pred[0].links[0].data[pred[0].links[0].size()-1]) < 0){
                T popped_el = pred[0].links[0].pop();
                new_node.remove(data);
                pred[0].links[0].add(data);
                new_node.add(popped_el);
            }
            //Now relink it
            //with respect to pred[0].links[0] and than with respect to preceding elements!
            int i;
            for(i = 0; i<new_node.links.length && i<pred[0].links[0].links.length; ++i){
                new_node.links[i] = pred[0].links[0].links[i];
                pred[0].links[0].links[i] = new_node;
            }
            //now we need to consider updating with respect to PRED
            //this is only when new_node's length is bigger than pred[0]
            while(i < new_node.links.length){
                new_node.links[i] = pred[i].links[i];
                pred[i].links[i] = new_node;
                ++i;
            }
        }
        return true;
    }
    /**
     * generating random level for nodes
     * @return random level
     */
    private int logRandom(){
        int r = rand.nextInt(maxCap);
        int k = (int)(Math.log(r+1)/LOG2);
        if(k > maxLevel - 1)
            k = maxLevel - 1;
        return maxLevel - k;
    }

    /**
     * returns the element it found and null otherwise
     * @param data data to be found
     * @return null if not in the list and the element otherwise
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    @Override
    public T find(T data) {
        SLNode<T> pred[] = search(data);
        if( pred[0].contains(data))
            return data;
        else if(pred[0].links[0]==null)
            return null;
        else if( pred[0].links[0].contains(data))
            return data;
        return null;
    }

    public int size(){
        return size;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SLNode<T> temp = head;
        while(temp != null){
            sb.append(temp);
            if(temp.links[0] != null)
                sb.append(" ---> ");
            temp = temp.links[0];
        }
        return sb.toString();
    }

    private static class SLNode<T extends Comparable<T>>{
        SLNode<T>[] links;
        T[] data;
        int i=0;
        int capacity;
        @SuppressWarnings({"unchecked","rawtypes"})
        public SLNode(T data, int level, int order){
            links = (SLNode<T>[])new SLNode[level];//links to other nodes
            this.data = (T[])new Comparable[order-1];//data stored
            this.data[i] = data;
            capacity = order - 1;
            if(data != null)
                i++;
        }

        /**
         * adds an element if there is space in the node
         * @param el data to be added to the array
         * @return true if capacity allows addition of this element
         */
        private boolean add(T el){
            if( isFull() )
                return false;
            //place it in the right order
            int place=i;

            for( place=i; place>=1 && (data[place-1] == null || el.compareTo(data[place-1])<0); --place)//if null it is for sure less( another way to express negative infinity )
                data[place] = data[place-1];
            data[place] = el;
            i++;//incerement the size

            return true;
        }

        /**
         * if there is no more space it is full
         * @return true if no space left in the array, false otherwise
         */
        public boolean isFull(){
            return i == capacity;
        }

        /**
         * pops the last element(removes it) and returns it
         * @return last element in the array
         */
        public T pop(){//removes an element
            if(!(i>1))
                throw new RuntimeException("Can't remove from a node that has less than 2 elements in it");
            --i;
            return data[i];
        }

        /**
         * removes a certain element from the array
         * @param data
         * @return
         */
        public T remove(T data){
            int j = 0;
            while(!(this.data[j].equals(data)))
                ++j;

            while(j+1 < i) {
                this.data[j] = this.data[j + 1];
                ++j;
            }
            --i;
            return data;
        }
        /**
         * returns size of this structure
         * @return number of elements in the node currently
         */
        public int size(){
            return i;
        }
        /**
         * checks if it contains an element in its array
         */
        public boolean contains(T target){
            for(int j = 0; j<i; ++j)
                if(target.equals(data[j]))
                    return true;
                return false;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<i; j++)
                sb.append(data[j] + " ");
            return sb.toString();
        }
    }
}
