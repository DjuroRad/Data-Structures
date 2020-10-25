import gtu.graph.Edge;
import gtu.graph.Graph;

import java.util.Iterator;
public class Test {
    public static void main(String[] args) {
        Graph<Character> graph = new Graph<>();
        graph.insert_v('A');
        graph.insert_v('E');
        graph.insert_v('D');
        System.out.println(graph);
        graph.insert(new Edge<Character>('A','E'));
        graph.insert(new Edge<Character>('A','D'));
        graph.insert(new Edge<Character>('A','A'));
        graph.insert(new Edge<Character>('D','D'));
        graph.insert(new Edge<Character>('D','A'));
        graph.insert(new Edge<Character>('D','E'));
        graph.insert(new Edge<Character>('E','D'));
        graph.insert(new Edge<Character>('E','E'));
        graph.insert(new Edge<Character>('E','A'));
        graph.insert(new Edge<Character>('A','A'));
        graph.insert(new Edge<Character>('E','E'));
        graph.insert(new Edge<Character>('A','E'));
        System.out.println(graph);
        System.out.println("Using iterator now to iterate through this graph's edges with source 'A' ");
        Iterator<Edge<Character>> it = graph.edgeIterator('A');
        while(it.hasNext())
            System.out.println("Edge is " + it.next() + "\n");
        System.out.println("Using breath first search traverasal is performed in the following order: ");
        System.out.println(graph.BFS());
        System.out.println("Depth first traversal ");
        System.out.println(graph.DFS());
        System.out.println("Now removing AD edge and trying it again(BFS), should be AED now");
        graph.delete(new Edge<Character>('A', 'D'));
        System.out.println(graph);
        System.out.println(graph.BFS());
        graph.delete(new Edge<Character>('E','E'));
        System.out.println(graph);
        graph.delete(new Edge<Character>('D','E'));
        System.out.println(graph);
        graph.delete(new Edge<Character>('A','A'));
        graph.delete(new Edge<Character>('E','E'));
        graph.delete(new Edge<Character>('A','A'));
        System.out.println("Removing vertex A");
        graph.remove_v('A');
        System.out.println("Tried to remove 'A' again even though it is not a vertex anymore");
        graph.remove_v('A');
        System.out.println(graph);

        Graph<Character> hwk_graph = new Graph<>();
        hwk_graph.insert_v('A');
        hwk_graph.insert_v('B');
        hwk_graph.insert_v('C');
        hwk_graph.insert_v('D');
        hwk_graph.insert_v('E');
        hwk_graph.insert(new Edge<Character>('A', 'B'));
        hwk_graph.insert(new Edge<Character>('B', 'A'));
        hwk_graph.insert(new Edge<Character>('B', 'E'));
        hwk_graph.insert(new Edge<Character>('D', 'A'));
        hwk_graph.insert(new Edge<Character>('E', 'A'));
        hwk_graph.insert(new Edge<Character>('E', 'C'));
        hwk_graph.insert(new Edge<Character>('E', 'D'));
        System.out.println("Constructing homework pdf's graph and printing it");
        System.out.println(hwk_graph);
        System.out.println("Performing BFS for it(final order printted) - ABECD should be printted");
        System.out.println(hwk_graph.BFS());
        System.out.println("Performing DFS for it(final order printted) - CDEBA should be printted");
        System.out.println(hwk_graph.DFS());

        System.out.println("Constructing a undirectional graph");
        Graph<Integer> un_graph = new Graph<>(false);
        un_graph.insert_v(5);
        un_graph.insert_v(2);
        un_graph.insert_v(4);
        un_graph.insert_v(7);
        un_graph.insert(new Edge<Integer>(5,2));
        un_graph.insert(new Edge<Integer>(4,7));
        System.out.println("Inserting edges 5,2 and 4,7");
        System.out.println(un_graph);
        System.out.println("Checking if edge 7,4 exists");
        if(un_graph.isEdge(7,4))
            System.out.println("7,4 is edge");
        else
            System.out.println("7,4 is not an edge");
        System.out.println("Removing an edge (2,5)");
        un_graph.delete(new Edge<>(2,5));
        System.out.println(un_graph);
        System.out.println("Removing vertex 7");
        un_graph.remove_v(7);
        System.out.println(un_graph);
        System.out.println("Checks if it is directed");
        if(un_graph.isDirected())
            System.out.println("It is directed");
        else
            System.out.println("It is not directed");
    }
}

