package gtu.graph;
import java.util.*;

/**
 * Interface to specify a GraphInterface ADT. A graph is a set of vertices and a set of edges.
 *    Vertices are represented by integers from 0 to n - 1
 *    Edges are ordered pairs of vertices.
 * Each implementation of the GraphInterface interface should provide a constructor that specifies the number of vertices
 * and whether or not the graph is directed.
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public interface GraphInterface<T extends Comparable>{
    //Interface Methods
    /**
     * Return the number of vertices
     * @return The number of vertices
     */
    int getNumV();

    /**
     * Determine whether this is a directed graph
     * @return True if this is a directed graph
     */
    boolean isDirected();

    /**
     * Insert a new edge into the graph
     * @param e The new edge
     */
    void insert(Edge<T> e);

    /**
     * Determine whether an edge exists
     * @param source The source vertex
     * @param dest The destination vertex
     * @return true if there is an edge from source to dest
     */
    boolean isEdge(T source, T dest);

    /**
     * Get edge between two vertices
     * @param source The source vertex
     * @param dest The destination vertex
     * @return The Edge between these two vertices,
     *         or an Edge with a weight of
     *         Double.POSITIVE_INFINITY if there is no edge
     */
    Edge<T> getEdge(T source, T dest);

    /**
     * Deletion of an individual edge.
     * @param e edge to be deleted
     */
    public void delete(Edge<T> e);

    /**
     * inserts an individual vertex
     * @param vertex vertex to be inserted
     * @return returns true if it didn't exist and false otherwise
     */
    public boolean insert_v(T vertex);

    /**
     * removes an individual vertex
     * @param vertex vertex to be removed
     * @return true if vertex existed and false otherwise
     */
    public boolean remove_v(T vertex);

    /**
     * performs a breath first search of the graph
     * @return string representation of the grap bfs traversed
     */
    public String BFS();

    /**
     * performs a depth first search of the graph
     * @return string representation of the graph DFS traversed
     */
    public String DFS();
    /**
     * Return an iterator to the edges connected to a given vertex
     * @param source The source vertex
     * @return An Iterator<Edge> to the vertices connected to source
     */
    Iterator<Edge<T>> edgeIterator(T source);
}
