import java.util.Iterator;
import java.util.LinkedList;

public class Graph implements GraphInterface{
    private LinkedList<Edge>[] edges;
    private int numV;
    private boolean is_directed;

    @SuppressWarnings({"rawtypes","unchecked"})
    public Graph(int numV, boolean is_directed) {
        this.numV = numV;
        this.is_directed = is_directed;
        edges = new LinkedList[numV];
        for(int i = 0; i < numV; i++){
            edges[i] = new LinkedList<Edge>();
        }
    }

    public boolean isEdge(int source, int dest){
        return edges[source].contains(new Edge(source, dest));
    }

    public void insert(Edge edge){
        edges[edge.getSource()].add(edge);
        if(!isDirected()){
            edges[edge.getDest()].add(new Edge(edge.getDest(),
                    edge.getSource(),
                    edge.getWeight()));
        }
    }

    public Edge getEdge(int source, int dest){
        Edge target = new Edge(source, dest, Integer.MAX_VALUE);
        for(Edge edge : edges[source]){
            if(edge.equals(target))
                return edge; //Desired edge found; return it
        }
        //Assert: All edges for source checked.
        return target; //Desired edge not found.
    }

    public int getNumV() {
        return numV;
    }
    public boolean isDirected() {
        return is_directed;
    }
    public Iterator<Edge> edgeIterator(int source){
        return edges[source].iterator();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int end = edges.length;
        for(int i=0; i < end; i++){
            sb.append("Node " + i + "-->\n");
            for(Edge e : edges[i]){
                sb.append("\tnode: " + e.getDest() + ", weight: " + e.getWeight() + "\n");
            }
        }
        return sb.toString();
    }
}
