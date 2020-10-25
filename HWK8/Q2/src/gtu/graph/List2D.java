package gtu.graph;

import java.security.InvalidParameterException;
@SuppressWarnings({"rawtypes","unchecked"})
public class List2D<T extends Comparable> {
    /**
     * row vertices
     */
    private Vertex<T> v_rows;//rows that would contain vertices( SOURCE )
    /**
     * column vertices
     */
    private Vertex<T> v_cols;//vertices in the rows( DESTINATION )
    /**
     * number of vertices
     */
    private int v_num;//represents the number of vertices

    /**
     * no parameter consturctor
     */
    public List2D() {
        v_num = 0;
        v_rows = new Vertex<T>();
        v_cols = new Vertex<T>();
    }

    /**
     * returns corresponding vertex
     * @param source Source vertex
     * @return reference to the desired vertex and null otherwise if it does not exist
     */
    protected Vertex<T> getVertex(T source){//iterator for vertices in the row list since they are the source
        Vertex<T> temp = v_rows;
        while(temp!=null && !temp.vertex.equals(source))
            temp = (Vertex<T>) temp.down;

        return temp;
    }
    public int getNumVertices() {
        return v_num;
    }
    /**
     * use very carefully within the package
     * @return row represented by this vertex
     */
    protected Vertex<T> getStartRow(){
        return v_rows;
    }

    /**
     * inserts a vertex to a 2D list( can think of it as a grid
     * @param vertex vertex to be inserted(generic type)
     * @return true if it didn't exist and false otherwise
     */
    public boolean insertVertex(T vertex){
        if(v_num == 0){
            v_rows.vertex = vertex;
            v_cols.vertex = vertex;
            v_num++;
            return true;
        }
        if(containsVertex(vertex)) {//can not have two same vertices
            return false;
        }
        Node<T> prev_col = v_cols;
        Node<T> prev_row = v_rows;
        while( prev_col.next != null && ((Vertex)prev_col.next).vertex.compareTo(vertex)<0 ){
            prev_col = prev_col.next;
            prev_row = prev_row.down;
        }
        //node will be added
        Vertex<T> new_vertex_col = new Vertex<>(vertex);
        Vertex<T> new_vertex_row = new Vertex<>(vertex);
        //added to head
        if(prev_col == v_cols && vertex.compareTo(v_cols.vertex)<0){
            new_vertex_col.next = v_cols;
            v_cols.prev = new_vertex_col;
            new_vertex_row.down = v_rows;
            v_rows.up = new_vertex_row;
            v_cols = new_vertex_col;
            v_rows = new_vertex_row;
        }
        else {//if it is added to the end or middle
            new_vertex_col.next = prev_col.next;
            new_vertex_row.down = prev_row.down;

            prev_col.next = new_vertex_col;
            prev_row.down = new_vertex_row;

            new_vertex_col.prev = prev_col;
            new_vertex_row.up = prev_row;//doubly linking them
        }
        v_num++;
        return true;
    }

    /**
     * inserts a new edge( connection between to vertices )
     * @param row_v row vertex
     * @param col_v column vertex
     * @return true if it exists and false otherwise
     */
    public boolean insertEdge(T row_v, T col_v){//column is destination, row is source
        if(v_num==0)
            throw new InvalidParameterException("Can't add a new edge if a vertex does not yet exist");
        Vertex<T> col_temp = v_cols;
        Vertex<T> row_temp = v_rows;
        while( !col_temp.vertex.equals(col_v) ) {
                col_temp = (Vertex) col_temp.next;
                if(col_temp == null )//indicates if these vertices exist here
                    return false;
        }
        while( !row_temp.vertex.equals(row_v) ){
            row_temp = (Vertex) row_temp.down;
            if(row_temp == null)//that vertex does not exist in this grpah it means
                return false;
        }
        //found where the edge is to be added
        //now iterating column down and row next ot link the edge
        //find preceding col and row and link them where needed
        Node prev_edge_row = col_temp;
        while(prev_edge_row.down != null && ((Edge)prev_edge_row.down).row.vertex.compareTo(row_v)<0 ){
            prev_edge_row= prev_edge_row.down;
        }
        Node prev_edge_col = row_temp;
        while(prev_edge_col.next != null && ((Edge)(prev_edge_col .next)).column.vertex.compareTo(col_v)<0 ){
            prev_edge_col = prev_edge_col.next;
        }
        if( prev_edge_row.down!=null && ((Edge)prev_edge_row.down).column.vertex.compareTo(col_v)==0 && ((Edge)prev_edge_row.down).row.vertex.compareTo(row_v)==0 ) {
            return false;//meaningn this edge already exists!!!
        }

        Edge new_edge = new Edge(row_temp, col_temp);

        //now connecting them in all four directions!
        //up,down,left,right
        if(prev_edge_col != row_temp) {
            new_edge.next = prev_edge_col.next;
            prev_edge_col.next = new_edge;
            new_edge.prev = prev_edge_col;
            if (prev_edge_col.next != null)
                prev_edge_col.next.prev = new_edge;
        }
        else if(prev_edge_col.next != null){
            prev_edge_col.next.prev = new_edge;
            new_edge.next = prev_edge_col.next;
            prev_edge_col.next = new_edge;
            //since this is a vertex we do not connect   new_edge.prev = prev_edge_col
        }
        else//when it is a vertex with no edges
            prev_edge_col.next = new_edge;

        if(prev_edge_row != col_temp) {//when not a vertex
            new_edge.down = prev_edge_row.down;
            prev_edge_row.down = new_edge;
            new_edge.prev = prev_edge_row;
            if (prev_edge_row.down != null)
                prev_edge_row.down.up = new_edge;
        }
        else if(prev_edge_row.down!=null){//vertex but contains other edges
            new_edge.down = prev_edge_row.down;
            prev_edge_row.down.up = new_edge;
            prev_edge_row.down = new_edge;
        }
        else
            prev_edge_row.down = new_edge;

        return true;//we know vertices exist since it didn't go till the end( condition in the while loop would terminate the method )
    }

    /**
     * can remove a connection in 2D structure(edge)
     * @param row vertex source
     * @param col vertex destination
     * @return true if exists and false otherwise
     */
    public boolean removeEdge(T row, T col){
        Node prev_col = prevCol(row, col);
        Node prev_row = prevRow( row, col );
        if(prev_col == null || prev_row == null)
            return false;
        //otherwise we know that this edge exists and we can remove it by relinking these nodes
        if(prev_col.next.next!=null)
            prev_col.next.next.prev = prev_col.next.prev;
        prev_col.next = prev_col.next.next;


        if(prev_row.down.down!=null)
            prev_row.down.down.up = prev_row.down.up;
        prev_row.down = prev_row.down.down;
        return true;
    }

    /**
     * method for getting a prev column of some target connection in the ist
      * @param row row vertex
     * @param col column vertex
     * @return previous node or null if this connection does not exist
     */
    private Node prevCol(T row, T col){
        Node prev_col = v_rows;
        while(!((Vertex)prev_col).vertex.equals(row)){
            prev_col = prev_col.down;//i know vertices are placed down
        }//row is found after this
        //searching for its column now
        while( prev_col!=null && prev_col.next!=null && !((Edge)prev_col.next).column.vertex.equals(col) )
            prev_col = prev_col.next;
        if(prev_col!=null && prev_col.next==null)
            return null;
        return prev_col;//returns null when not successfull( meaning this edge does not exist
    }

    /**
     * same as for prev col but now it is for row
     * @param row vertex
     * @param col vertex
     * @return node or null
     */
    private Node prevRow(T row, T col){
        Node prev_row = v_cols;
        while(!((Vertex)prev_row).vertex.equals(col)){
            prev_row = prev_row.next;//i know vertices are placed down
        }//col is found after this
        //searching for its row now
        while( prev_row!=null && prev_row.down!=null && !((Edge)prev_row.down).row.vertex.equals(row) )
            prev_row = prev_row.down;
        if(prev_row!=null && prev_row.down==null)
            return null;
        return prev_row;//returns null when not successfull( meaning this edge does not exist
    }

    /**
     * used by graph, not public-works within the package!
     * @param source row vertex
     * @param destination column  vertex
     * @return reference to wanted edge or null if it doesn't exist
     */
    protected Edge<T> getEdge(T source, T destination){
        Node<T> prev_col = prevCol(source, destination);
        if(prev_col==null)
            return null;
        return (Edge<T>)prev_col.next;
    }

    /**
     * checks if a connection in this 2d list exists
     * @param source row
     * @param dest column
     * @return true if there is 'connection'
     */
    public boolean elementExists(T source, T dest){
        return prevCol(source,dest) != null;
    }

    /**
     * method for removing a vertex from 2d list grid
     * @param v Vertex to be removed
     * @return true if it exists and false otherwise
     */
    public boolean removeVertex(T v){
        Vertex vertex_col = v_cols;
        Vertex vertex_row = v_rows;
        //finding vertices in both rows and columns(2 linked lists preset
        while(vertex_col!=null && !vertex_col.vertex.equals(v))
            vertex_col = (Vertex)vertex_col.next;
        while(vertex_row!=null && !vertex_row.vertex.equals(v))
            vertex_row = (Vertex)vertex_row.down;

        //checking if that vertex actually exist
        if(vertex_col==null || vertex_row==null)
            return false;//true for any othercase...

        //removing the edges and their links now also
        removeEdgeCol(vertex_col);
        removeEdgeRow(vertex_row);

        v_num--;
        if(vertex_col==v_cols) {
            v_cols = (Vertex<T>) v_cols.next;
            v_rows = (Vertex<T>) v_rows.down;
        }
        //relinking only after all edges are removed
        if(vertex_col.prev!=null)
            vertex_col.prev.next = vertex_col.next;
        if(vertex_col.next!=null)
            vertex_col.next.prev = vertex_col.prev;
        if(vertex_row.up!=null)
            vertex_row.up.down = vertex_row.down;
        if(vertex_row.down!=null)
            vertex_row.down.up = vertex_row.up;

        return true;
    }

    /**
     * removes all the edge of the passed column vertex
     * @param v column vertex
     */
    @SuppressWarnings({"rawtypes","unchecked"})
    private void removeEdgeCol(Vertex<T> v){
        Edge<T> temp = (Edge<T>)v.down;
        while(temp!=null) {
            Edge<T> temp2 = (Edge<T>)temp.down;
            removeEdge(temp.row.vertex, temp.column.vertex);
            temp = temp2;
        }
    }
    /**
     * removes all the edge of the passed row vertex
     * @param v row vertex
     */
    private void removeEdgeRow(Vertex v){
        Edge<T> temp = (Edge<T>)v.next;
        while(temp!=null) {
            Edge<T> temp2 = (Edge<T>)temp.next;
            removeEdge(temp.row.vertex, temp.column.vertex);
            temp = temp2;
        }
    }

    /**
     * checks if vertex exists
     * @param vertex
     * @return
     */
    @SuppressWarnings({"rawtypes","unchecked"})
    public boolean containsVertex(T vertex){
        Vertex<T> temp = v_cols;//it is enough to check just one of them
        while(temp!=null){
            if(temp.vertex.equals(vertex))
                return true;
            temp = (Vertex)temp.next;
        }
        return false;
    }

    /**
     * provides a vertex and returns reference of it in the row
     * @param source
     * @return
     */
    @SuppressWarnings({"rawtypes","unchecked"})
    protected Vertex<T> getRowEquiv(T source){
        Vertex<T> temp = v_rows;
        while (temp != null && !temp.vertex.equals(source))
            temp = (Vertex<T>)temp.down;
        return temp;
    }
    protected static class Node<T extends Comparable>{
        Node up;
        Node down;
        Node next;//right
        Node prev;//left
        public Node() {
            this(null, null, null, null);
        }
        public Node(Node up, Node down, Node next, Node prev) {
            this.up = up;
            this.down = down;
            this.next = next;
            this.prev = prev;
        }
    }

    @SuppressWarnings({"rawtypes","unchecked"})
    @Override
    public String toString() {
        if(v_num==0)
            return "Empty";
        Vertex<T> t_rows = v_rows;//rows that would contain vertices
        Vertex<T> t_cols = v_cols;//vertices in the rows
        String str = "\t";
        while(t_cols!=null){
            str+=t_cols.vertex + "\t";
            t_cols = (Vertex<T>)t_cols.next;//i know vertices point to vertices this way in columns
        }
        str+="\n";
        while(t_rows!=null){
            str+=t_rows.vertex + "\t";
            Edge<T> t_edge = (Edge<T>)t_rows.next;
            while(t_edge!=null) {
                str += t_edge.row.vertex + "" + t_edge.column.vertex + "\t";
                t_edge = (Edge)t_edge.next;
            }
            str+="\n";
            t_rows = (Vertex<T>)t_rows.down;
        }
        return str;
    }
}
