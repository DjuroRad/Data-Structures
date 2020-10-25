package gtu.graph;
/**
 * Edge class for implementing graphs. Vertices are represented by the type int
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class Edge<T extends Comparable> extends List2D.Node<T>{
    //Data Fields
    /**
     * Source vertex for an edge
     */
    Vertex<T> row;//source
    /**
     * Destination vertex for an edge
     */
    Vertex<T> column;//dest
    public Edge( Vertex<T> row, Vertex<T> col){
        this.row = row;
        this.column = col;
    }
    //Constructors
    /**
     * Construct an edge from source to dest and sets weight to 1.0
     * @param row The source vertex for the edge
     * @param col The destination vertex for the edge
     */
    public Edge(T row, T col){
        this.row = new Vertex<T>(row);
        this.column = new Vertex<T>(col);
    }

    //Methods
    /**
     * Compares two edges for equality. Edges are equal if their source and destination vertices are the same.
     *    Weight is not considered.
     * @param e The edge we are comparing to.
     */
    public boolean equals(Edge e){
        return (this.row.vertex.equals(e.row.vertex) && this.row.vertex.equals(e.row.vertex) );
    }

    /**
     * Getter for destination vertex
     * @return The destination vertex of this edge
     */
    public T getDest(){
        return column.vertex;
    }

    /**
     * Getter for source vertex
     * @return The source vertex of this edge
     */
    public T getSource(){
        return row.vertex;
    }

    /**
     * Return the hash code for an edge. The hash code depends only on the source and destination
     * @return The hash code for this edge
     */
    public int hashCode(){
        return row.vertex.hashCode() + column.vertex.hashCode();
    }

    /**
     * String representation of edge
     */
    public String toString(){
        return "Source: " + row.vertex + ", Destination: " + column.vertex;
    }
}
