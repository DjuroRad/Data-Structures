import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
@SuppressWarnings({"unchecked","rawtypes"})
public class Test {
    static Random rand = new Random();
    public static void insertTree(SearchTree<Integer> tree[] ){
        //[0] -> 10 000 els
        for(int i = 0; i<10000; ++i)
            tree[0].add(rand.nextInt()%10000+1);
        //[0] -> 10 000 els
        for(int i = 0; i<20000; ++i)
            tree[1].add(rand.nextInt()%20000+1);
        //[0] -> 10 000 els
        for(int i = 0; i<40000; ++i)
            tree[2].add(rand.nextInt()%40000+1);
        //[0] -> 10 000 els
        for(int i = 0; i<80000; ++i)
            tree[3].add(rand.nextInt()%80000+1);
        //verifying now that is inserted correctly
        System.out.println("First tree");
        System.out.println(tree[0]);
        System.out.println("Second tree");
        System.out.println(tree[1]);
        System.out.println("Third tree");
        System.out.println(tree[2]);
        System.out.println("Fourth tree");
        System.out.println(tree[3]);
        System.out.println();
    }
    public static void insertTree(TreeSet<Integer> tree[] ){
        //[0] -> 10 000 els
        for(int i = 0; i<10000; ++i)
            tree[0].add(rand.nextInt()%10000+1);
        //[0] -> 10 000 els
        for(int i = 0; i<20000; ++i)
            tree[1].add(rand.nextInt()%20000+1);
        //[0] -> 10 000 els
        for(int i = 0; i<40000; ++i)
            tree[2].add(rand.nextInt()%40000+1);
        //[0] -> 10 000 els
        for(int i = 0; i<80000; ++i)
            tree[3].add(rand.nextInt()%80000+1);
        //verifying now that is inserted correctly
        System.out.println("First tree");
        System.out.println(tree[0]);
        System.out.println("Second tree");
        System.out.println(tree[1]);
        System.out.println("Third tree");
        System.out.println(tree[2]);
        System.out.println("Fourth tree");
        System.out.println(tree[3]);
        System.out.println();
    }
    public static void insertList(SkipListInterface<Integer> list[] ){
        //[0] -> 10 000 els
        for(int i = 0; i<10000; ++i)
            list[0].insert(rand.nextInt()%10000+1);
        //[0] -> 10 000 els
        for(int i = 0; i<20000; ++i)
            list[1].insert(rand.nextInt()%20000+1);
        //[0] -> 10 000 els
        for(int i = 0; i<40000; ++i)
            list[2].insert(rand.nextInt()%40000+1);
        //[0] -> 10 000 els
        for(int i = 0; i<80000; ++i)
            list[3].insert(rand.nextInt()%80000+1);
        //verifying now that is inserted correctly
        System.out.println("First list");
        System.out.println(list[0]);
        System.out.println("Second list");
        System.out.println(list[1]);
        System.out.println("Third list");
        System.out.println(list[2]);
        System.out.println("Fourth list");
        System.out.println(list[3]);
        System.out.println();
    }
    public static int[] getRandArr(int n){
        int[] arr = new int[n];
        for(int i = 0; i<n/4; ++i)
            arr[i] = rand.nextInt()%10000+1;
        for(int i = n/4; i<n/2; ++i)
            arr[i] = rand.nextInt()%20000+1;
        for(int i = n/2; i<(n*3)/4; ++i)
            arr[i] = rand.nextInt()%40000+1;
        for(int i = (n*3)/4; i<n; ++i)
            arr[i] = rand.nextInt()%80000+1;
        return arr;
    }
    public static void insertTree(SearchTree<Integer> tree[], int arr[]){
        long ex_time[] = new long[4];
        //n is random number of els
        long start, end;
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[0].add(arr[i]);
        }
        end = System.nanoTime();
        ex_time[0] = end-start;
        System.out.println("Time taken for '10000' is: " + (end-start) +  " Avg is: " + ex_time[0]/arr.length);
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[1].add(arr[i]);
        }
        end = System.nanoTime();
        ex_time[1] = end-start;
        System.out.println("Time taken for '20000' is: " + (end-start) +  " Avg is: " + ex_time[1]/arr.length + " | Difference with the '10000' one is: " + (ex_time[1] - ex_time[0]));
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[2].add(arr[i]);
        }
        end = System.nanoTime();
        ex_time[2] = end-start;
        System.out.println("Time taken for '40000' is: " + (end-start)+  " Avg is: " + ex_time[2]/arr.length +" | Difference with the '20000' one is: " + (ex_time[2] - ex_time[1]));

        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[3].add(arr[i]);
        }
        end = System.nanoTime();
        ex_time[3] = end-start;
        System.out.println("Time taken for '80000' is: " + (end-start)+ " Avg is: " + ex_time[3]/arr.length +" | Difference with the '40000' one is: " + (ex_time[3] - ex_time[2]));
    }
    public static void deleteTree(SearchTree<Integer> tree[], int arr[]){
        long ex_time[] = new long[4];
        //n is random number of els
        long start, end;
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[0].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[0] = end-start;
        System.out.println("Time taken for '10000' is: " + (end-start) +  " Avg is: " + ex_time[0]/arr.length);
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[1].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[1] = end-start;
        System.out.println("Time taken for '20000' is: " + (end-start) +  " Avg is: " + ex_time[1]/arr.length + " | Difference with the '10000' one is: " + (ex_time[1] - ex_time[0]));
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[2].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[2] = end-start;
        System.out.println("Time taken for '40000' is: " + (end-start)+  " Avg is: " + ex_time[2]/arr.length +" | Difference with the '20000' one is: " + (ex_time[2] - ex_time[1]));

        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[3].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[3] = end-start;
        System.out.println("Time taken for '80000' is: " + (end-start)+ " Avg is: " + ex_time[3]/arr.length +" | Difference with the '40000' one is: " + (ex_time[3] - ex_time[2]));
    }
    public static void insertTree(TreeSet<Integer> tree[], int arr[]){
        long ex_time[] = new long[4];
        //n is random number of els
        long start, end;
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[0].add(arr[i]);
        }
        end = System.nanoTime();
        ex_time[0] = end-start;
        System.out.println("Time taken for '10000' is: " + (end-start)+ " Avg is: " + ex_time[0]/arr.length);
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[1].add(arr[i]);
        }
        end = System.nanoTime();
        ex_time[1] = end-start;
        System.out.println("Time taken for '20000' is: " + (end-start) + " Avg is: " + ex_time[1]/arr.length+ " | Difference with the '10000' one is: " + (ex_time[1] - ex_time[0]));
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[2].add(arr[i]);
        }
        end = System.nanoTime();
        ex_time[2] = end-start;
        System.out.println("Time taken for '40000' is: " + (end-start)+ " Avg is: " + ex_time[2]/arr.length+ " | Difference with the '20000' one is: " + (ex_time[2] - ex_time[1]));

        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[3].add(arr[i]);
        }
        end = System.nanoTime();
        ex_time[3] = end-start;
        System.out.println("Time taken for '80000' is: " + (end-start)+ " Avg is: " + ex_time[3]/arr.length+ " | Difference with the '40000' one is: " + (ex_time[3] - ex_time[2]));
    }
    public static void deleteTree(TreeSet<Integer> tree[], int arr[]){
        long ex_time[] = new long[4];
        //n is random number of els
        long start, end;
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[0].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[0] = end-start;
        System.out.println("Time taken for '10000' is: " + (end-start)+ " Avg is: " + ex_time[0]/arr.length);
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[1].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[1] = end-start;
        System.out.println("Time taken for '20000' is: " + (end-start) + " Avg is: " + ex_time[1]/arr.length+ " | Difference with the '10000' one is: " + (ex_time[1] - ex_time[0]));
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[2].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[2] = end-start;
        System.out.println("Time taken for '40000' is: " + (end-start)+ " Avg is: " + ex_time[2]/arr.length+ " | Difference with the '20000' one is: " + (ex_time[2] - ex_time[1]));

        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            tree[3].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[3] = end-start;
        System.out.println("Time taken for '80000' is: " + (end-start)+ " Avg is: " + ex_time[3]/arr.length+ " | Difference with the '40000' one is: " + (ex_time[3] - ex_time[2]));
    }
    public static void insertList(SkipListInterface<Integer> list[], int arr[]){
        long ex_time[] = new long[4];
        //n is random number of els
        long start, end;
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[0].insert(arr[i]);
        }
        end = System.nanoTime();
        ex_time[0] = end-start;
        System.out.println("Time taken for '10000' is: " + (end-start) + " Avg is: " + ex_time[0]/arr.length );
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[1].insert(arr[i]);
        }
        end = System.nanoTime();
        ex_time[1] = end-start;
        System.out.println("Time taken for '20000' is: " + (end-start) + " Avg is: " + ex_time[1]/arr.length+ " | Difference with the '10000' one is: " + (ex_time[1] - ex_time[0]));
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[2].insert(arr[i]);
        }
        end = System.nanoTime();
        ex_time[2] = end-start;
        System.out.println("Time taken for '40000' is: " + (end-start)+ " Avg is: " + ex_time[2]/arr.length+ " | Difference with the '20000' one is: " + (ex_time[2] - ex_time[1]));

        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[3].insert(arr[i]);
        }
        end = System.nanoTime();
        ex_time[3] = end-start;
        System.out.println("Time taken for '80000' is: " + (end-start)+ " Avg is: " + ex_time[3]/arr.length+ " | Difference with the '40000' one is: " + (ex_time[3] - ex_time[2]));
    }
    public static void deleteList(SkipListInterface<Integer> list[], int arr[]){
        long ex_time[] = new long[4];
        //n is random number of els
        long start, end;
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[0].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[0] = end-start;
        System.out.println("Time taken for '10000' is: " + (end-start) + " Avg is: " + ex_time[0]/arr.length );
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[1].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[1] = end-start;
        System.out.println("Time taken for '20000' is: " + (end-start) + " Avg is: " + ex_time[1]/arr.length+ " | Difference with the '10000' one is: " + (ex_time[1] - ex_time[0]));
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[2].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[2] = end-start;
        System.out.println("Time taken for '40000' is: " + (end-start)+ " Avg is: " + ex_time[2]/arr.length+ " | Difference with the '20000' one is: " + (ex_time[2] - ex_time[1]));

        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[3].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[3] = end-start;
        System.out.println("Time taken for '80000' is: " + (end-start)+ " Avg is: " + ex_time[3]/arr.length+ " | Difference with the '40000' one is: " + (ex_time[3] - ex_time[2]));
    }
    public static void deleteList(ConcurrentSkipListSet<Integer> list[], int arr[]){
        long ex_time[] = new long[4];
        //n is random number of els
        long start, end;
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[0].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[0] = end-start;
        System.out.println("Time taken for '10000' is: " + (end-start) + " Avg is: " + ex_time[0]/arr.length);
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[1].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[1] = end-start;
        System.out.println("Time taken for '20000' is: " + (end-start) + " Avg is: " + ex_time[1]/arr.length + " | Difference with the '10000' one is: " + (ex_time[1] - ex_time[0]));
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[2].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[2] = end-start;
        System.out.println("Time taken for '40000' is: " + (end-start)+ " Avg is: " + ex_time[2]/arr.length + " | Difference with the '20000' one is: " + (ex_time[2] - ex_time[1]));

        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[3].remove(arr[i]);
        }
        end = System.nanoTime();
        ex_time[3] = end-start;
        System.out.println("Time taken for '80000' is: " + (end-start) + " Avg is: " + ex_time[3]/arr.length + " | Difference with the '40000' one is: " + (ex_time[3] - ex_time[2]));
    }
    public static void insertList(ConcurrentSkipListSet<Integer> list[], int arr[]){
        long ex_time[] = new long[4];
        //n is random number of els
        long start, end;
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[0].add(arr[i]);
        }
        end = System.nanoTime();
        ex_time[0] = end-start;
        System.out.println("Time taken for '10000' is: " + (end-start) + " Avg is: " + ex_time[0]/arr.length);
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[1].add(arr[i]);
        }
        end = System.nanoTime();
        ex_time[1] = end-start;
        System.out.println("Time taken for '20000' is: " + (end-start) + " Avg is: " + ex_time[1]/arr.length + " | Difference with the '10000' one is: " + (ex_time[1] - ex_time[0]));
        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[2].add(arr[i]);
        }
        end = System.nanoTime();
        ex_time[2] = end-start;
        System.out.println("Time taken for '40000' is: " + (end-start)+ " Avg is: " + ex_time[2]/arr.length + " | Difference with the '20000' one is: " + (ex_time[2] - ex_time[1]));

        start = System.nanoTime();
        for(int i = 0; i<arr.length; ++i){
            list[3].add(arr[i]);
        }
        end = System.nanoTime();
        ex_time[3] = end-start;
        System.out.println("Time taken for '80000' is: " + (end-start) + " Avg is: " + ex_time[3]/arr.length + " | Difference with the '40000' one is: " + (ex_time[3] - ex_time[2]));
    }
    public static void insertListJava(ConcurrentSkipListSet<Integer> list[] ){
        //[0] -> 10 000 els
        for(int i = 0; i<10000; ++i)
            list[0].add(rand.nextInt()%10000+1);
        //[0] -> 10 000 els
        for(int i = 0; i<20000; ++i)
            list[1].add(rand.nextInt()%20000+1);
        //[0] -> 10 000 els
        for(int i = 0; i<40000; ++i)
            list[2].add(rand.nextInt()%40000+1);
        //[0] -> 10 000 els
        for(int i = 0; i<80000; ++i)
            list[3].add(rand.nextInt()%80000+1);
        //verifying now that is inserted correctly
        System.out.println("First list");
        System.out.println(list[0]);
        System.out.println("Second list");
        System.out.println(list[1]);
        System.out.println("Third list");
        System.out.println(list[2]);
        System.out.println("Fourth list");
        System.out.println(list[3]);
        System.out.println();
    }
    public static void main(String[] args) {
        BinarySearchTree<Integer>[] binaryTree = new BinarySearchTree[4];
        RedBlackTree<Integer>[] redBlackTree= new RedBlackTree[4];
        SkipList<Integer>[] skipList = new SkipList[4];
        SkipListX<Integer>[] skipListX = new SkipListX[4];
        ConcurrentSkipListSet<Integer>[] skipListC = new ConcurrentSkipListSet[4];
        TreeSet<Integer>[] redBlackTreejava = new TreeSet[4];
        Btree<Integer>[] btree = new Btree[4];
        //b tree
        for(int i=0; i<4; ++i) {
            binaryTree[i] = new BinarySearchTree<>();
            redBlackTree[i] = new RedBlackTree<>();
            redBlackTreejava[i] = new TreeSet<>();
            skipListX[i] = new SkipListX<Integer>(5);
            skipListC[i] = new ConcurrentSkipListSet<>();
            skipList[i] = new SkipList<Integer>();
            btree[i] = new Btree<Integer>();
        }
        System.out.println("Binary tree");
        insertTree(binaryTree);
        System.out.println("Book's Red-Black trees:");
        insertTree(redBlackTree);
        System.out.println("Book's b tree");
        insertTree(btree);
        System.out.println("Java's red-black tree");
        insertTree(redBlackTreejava);
        System.out.println("Homework's skip list");
        insertList(skipListX);
        System.out.println("Java's skip list set");
        insertListJava(skipListC);
        System.out.println("Book's skip list");
        insertList(skipList);

        int[] arr = getRandArr(10);
        System.out.println("Inserting 10 random els\n");
        System.out.println("Book's skip list");
        insertList(skipList, arr);
        System.out.println("Question 2's skip list");
        insertList(skipListX, arr);
        System.out.println("Java's skip list");
        insertList(skipListC, arr);
        System.out.println("Binary search tree");
        insertTree(binaryTree, arr);
        System.out.println("Red blakc tree");
        insertTree(redBlackTree, arr);
        System.out.println("Java's red black tree");
        insertTree(redBlackTreejava, arr);
        System.out.println("Book's B tree");
        insertTree(btree,arr);

        //removing
        System.out.println("\nRemoving 10 random els\n");
        System.out.println("Book's skip list");
        deleteList(skipList, arr);
        System.out.println("Question 2's skip list");
        deleteList(skipListX, arr);
        System.out.println("Java's skip list");
        deleteList(skipListC, arr);
        System.out.println("Binary search tree");
        deleteTree(binaryTree, arr);
        System.out.println("Red black tree");
        deleteTree(redBlackTree, arr);
        System.out.println("Java's red black tree");
        deleteTree(redBlackTreejava, arr);
    }
}
