package gtu.graph;
import java.util.*;
@SuppressWarnings({"rawtypes","unchecked"})
public class Graph<T extends Comparable> implements GraphInterface<T>{
    /**
     * graph is stored in this data structure
     */
    List2D<T> graph_list;
    /**
     * indicates if graph is directed or not
     */
    boolean is_directed;

    /**
     * no parameter constructor making a graph which is not directed
     */
    public Graph() {
        this(true);//makes a graph that is not directed
    }

    /**
     * on paramter constructor indicating if the graph will be directed or not
     * @param is_directed directed or not(boolean)
     */
    public Graph(boolean is_directed) {
        this.is_directed = is_directed;
        graph_list = new List2D<T>();
    }

    /**
     * Return the number of vertices
     * @return The number of vertices
     */
    @Override
    public int getNumV() {
        return graph_list.getNumVertices();
    }

    /**
     * Determine whether this is a directed graph
     *
     * @return True if this is a directed graph
     */
    @Override
    public boolean isDirected() {
        return is_directed;
    }

    /**
     * Insert a new edge into the graph
     *
     * @param e The new edge
     */
    @Override
    public void insert(Edge<T> e) {
        graph_list.insertEdge(e.row.vertex, e.column.vertex);
        if(!is_directed)
            graph_list.insertEdge(e.column.vertex, e.row.vertex);
    }

    /**
     * Determine whether an edge exists
     *
     * @param source The source vertex
     * @param dest   The destination vertex
     * @return true if there is an edge from source to dest
     */
    @Override
    public boolean isEdge(T source, T dest) {
        return graph_list.elementExists(source,dest);
    }

    /**
     * Get edge between two vertices
     *
     * @param source The source vertex
     * @param dest   The destination vertex
     * @return The Edge between these two vertices,
     * or null if it does not exist
     */
    @Override
    public Edge<T> getEdge(T source, T dest) {
        return graph_list.getEdge(source,dest);
    }

    /**
     * Deletion of an individual edge.
     *
     * @param e edge to be deleted
     */
    @Override
    public void delete(Edge<T> e) {
        graph_list.removeEdge(e.row.vertex, e.column.vertex);
        if(!is_directed)
            graph_list.removeEdge(e.column.vertex, e.row.vertex);
    }

    /**
     * inserts an individual vertex
     *
     * @param vertex vertex to be inserted
     * @return returns true if it didn't exist and false otherwise
     */
    @Override
    public boolean insert_v(T vertex) {
        return graph_list.insertVertex(vertex);
    }

    /**
     * removes an individual vertex
     *
     * @param vertex vertex to be removed
     * @return true if vertex existed and false otherwise
     */
    @Override
    public boolean remove_v(T vertex) {
        return graph_list.removeVertex(vertex);
    }

    /**
     * performs a breath first search of the graph
     *
     * @return string representation of the grap bfs traversed
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public String BFS() {
        String str = "";
        Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();
        Queue<Vertex<T>> visited = new LinkedList<Vertex<T>>();

        Vertex<T> v = graph_list.getStartRow();
        visited.offer(v);
        while(!visited.isEmpty()) {
            v = visited.poll();
            queue.offer(v);
            Iterator<Edge<T>> edges = edgeIterator(v.vertex);
            while (edges.hasNext()) {
                Edge<T> edge = edges.next();
                if (!visited.contains(edge.column) && !queue.contains(edge.column))//column is the destination, not possible to use array here asin the book since the type is generic
                    visited.add(edge.column);
            }
        }
        for(Vertex<T> vertex: queue)
            str += vertex + " ";
        return str;
    }

    /**
     * performs a depth first search of the graph
     *
     * @return string representation of the graph DFS traversed
     */
    @Override
    public String DFS() {
        Queue<T> queue = new LinkedList<>();
        DFS_helper(graph_list.getStartRow(), new Stack<T>(), queue);
        String str = "";
        for(T el: queue)
            str += el + " ";
        return str;
    }
    private void DFS_helper(Vertex<T> v, Stack<T> discovered, Queue<T> finished_order) {
        if(v == null)
            return;

        if(v.next==null) {
            DFS_helper((Vertex<T>) v.down, discovered, finished_order);
            return;//just continue executing and don't add it since it is not sure if this vertex will exist
        }

        discovered.add(v.vertex);//if it has edges here ---> add it, otherwise it will just continue, checked beforehand anyways and returned if it doesn't
        Iterator<Edge<T>> it = edgeIterator(discovered.peek());

        while (it.hasNext()){
            Vertex<T> dest_v = it.next().column;
            if( it.hasNext() && dest_v.vertex.equals(v.vertex) )//no need to add the same element again, only its edges are examined
                DFS_helper(graph_list.getRowEquiv(it.next().column.vertex), discovered, finished_order);
            if(!discovered.contains(dest_v.vertex) && !finished_order.contains(dest_v.vertex)) {//calling recursive call for verices that have not yet been visited
                if(graph_list.getRowEquiv(dest_v.vertex).next==null) {
                    discovered.add(dest_v.vertex);
                    finished_order.add(discovered.pop());//add it to finished order now
                }
                else
                    DFS_helper(graph_list.getRowEquiv(dest_v.vertex), discovered, finished_order);
            }
        }
        finished_order.add(discovered.pop());//after iterationg through all the edges is finished, we are sure that all the vertices have been visited and can add them to the finished ones

    }
    @Override
    public String toString() {
        return graph_list.toString();
    }

    /**
     * Return an iterator to the edges connected to a given vertex
     *
     * @param source The source vertex
     * @return An Iterator<Edge> to the vertices connected to source
     */
    @Override
    public Iterator<Edge<T>> edgeIterator(T source) {
        return new Iter<Edge<T>>(source);
    }

    /**
     * class used for iterating
     * @param <Edge>
     */
    private class Iter<Edge> implements Iterator{
        /**
         * current node
         */
        private List2D.Node<T> current;
        /**
         * next node
         */
        private List2D.Node<T> next;

        public Iter(T source) {
            Vertex<T> target_v = graph_list.getVertex(source);
            if(target_v!=null)
                current = next = target_v.next;
            else
                current = next = null;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return next != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Object next() {
            if(!hasNext())
                throw new NoSuchElementException();
            current = next;
            next = next.next;
            return (Edge)current;
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.
         * <p>
         * The behavior of an iterator is unspecified if the underlying collection
         * is modified while the iteration is in progress in any way other than by
         * calling this method, unless an overriding class has specified a
         * concurrent modification policy.
         * <p>
         * The behavior of an iterator is unspecified if this method is called
         * after a call to the {@link #forEachRemaining forEachRemaining} method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
