import java.util.Arrays;
import java.util.Random;
public class SkipList<T extends Comparable<? super T>> implements SkipListInterface<T>{

    private SLNode<T> head;
    private int maxLevel;
    private int size;
    private int maxCap;
    /**Natural log of 2*/
    private static final double LOG2 = Math.log(2.0);
    private Random rand;
    private static final int MIN = Integer.MIN_VALUE;
    @SuppressWarnings({"unchecked", "rawtypes"})
    public SkipList() {
        rand = new Random();
        size = 0;
        maxLevel = 0;
        maxCap = computeMaxCap();
        head = new SLNode(null, maxLevel);
        //and other variables need to be initialized
    }

    @Override
    public boolean remove(T target) {
        SLNode<T>[] pred = search(target);
        if( pred[0].links[0].data.equals(target) ){
            --size;
            SLNode<T> temp = pred[0];
            for( int i = 0; i<temp.links.length; ++i ){
                /*if(pred[i].links[i] != null)
					pred[i].links[i] = pred[i].links[i].links[i];*/
                //this written beforehand
                pred[i].links[i] = temp.links[i];
            }
        }else
            return false;
        return true;
    }

    @Override
    public boolean insert(T data) {
        ++size;
        SLNode<T> pred[] = search(data);

        if(size > maxCap){
            maxLevel ++;
            maxCap = computeMaxCap();
            head.links = Arrays.copyOf(head.links, maxLevel);
            pred = Arrays.copyOf(pred,maxLevel);
            pred[maxLevel-1] = head;
        }

        SLNode<T> new_node = new SLNode<>(data,logRandom());
        for(int i = 0; i<new_node.links.length; ++i) {
            new_node.links[i] = pred[i].links[i];
            pred[i].links[i] = new_node;
        }
        pred[0].links[0] = new_node;
        //updating needed values here
        return true;
    }

    private int computeMaxCap(){
        return (int)Math.pow(2,maxLevel)-1;
    }
    /**
     * returns the value if it is inside of the Skip list, null otherwise, uses the search private method
     * @param data data to be searched
     * @return returns the data or null if not found
     */
    @Override
    public T find(T data) {
        SLNode<T>[] pred = search(data);
        if(pred[0].links[0] != null && pred[0].links[0].data.compareTo(data)==0)
            return data;
        else
            return null;
    }

    /**
     * searches for an element in the list and returns references of predecing nodes
     * @param target target to be found
     * @return array of predecing nodes(links)
     */
    @SuppressWarnings("unchecked")
    private SLNode<T>[] search(T target){
        SLNode<T>[] pred = new SLNode[maxLevel];
        SLNode<T> current = head;
        for(int i = current.links.length-1; i>=0; --i){
            while(current.links[i] != null && current.links[i].data.compareTo(target)<0)
                current = current.links[i];
            pred[i] = current;
        }
        return pred;
    }

    /**
     * generating random level for the new node to be inserted
     * @return returns the level of the new node
     */
    private int logRandom(){
        int r = rand.nextInt(maxCap);
        int k = (int)(Math.log(r+1)/LOG2);
        if(k > maxLevel - 1)
            k = maxLevel - 1;
        return maxLevel - k;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public String toString() {
        if(size == 0)
            return "Empty";
        StringBuilder sc = new StringBuilder();
        SLNode itr = head;
        sc.append("Head: " + maxLevel + "\n");
        int lineMaker = 0;

        for(int i = 0; i<head.links.length; ++i){
            SLNode<T> node = head;
            while(node!=null){
                if( node.links[i] != null)
                sc.append(node.links[i].data+ "|"+node.links[i].links.length + " ---> ");
                node = node.links[i];
            }
            sc.append("null\n");
        }

//        while(itr.links[0] != null){
//            itr = itr.links[0];
//            sc.append("-->" + itr.toString());
//            lineMaker++;
//            if(lineMaker==10){
//                sc.append("\n");
//                lineMaker = 0;
//            }
//        }
        return sc.toString();
    }

    private static class SLNode<T>{
        SLNode<T>[] links;
        T data;
        @SuppressWarnings("unchecked")
        public SLNode(T data, int m){
            links = (SLNode<T>[])new SLNode[m];//links to other nodes
            this.data = data;//data stored
        }

        @Override
        public String toString() {
            return(data.toString() + " |" + links.length + "|");
        }
    }
    public String printHead(){
        return head.data.toString();
    }
}
