import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Maze {
    private Graph graph;
    private String lines[];
    private int grid_original[][];
    private int grid[][];//traversed grid

    /**
     * vertices found in the maze are stored here
     */
    private ArrayList<Vertex> vertices;

        /**
     * one paramter constructors
     * @param file file from which the maze will be read
     */
    public Maze(String file) {
        //read the file and make a graph from it
        BufferedReader br = null;
        try {
            int n = 0;
            br = new BufferedReader(new FileReader(file));
            while(br.readLine()!=null)
                ++n;
            lines = new String[n];
            String line;
            br = new BufferedReader(new FileReader(file));
            int i = 0;
            do{
                line = br.readLine();
                if(line!=null) {
                    lines[i] = line;
                    ++i;
                }
            }while(line!=null);

            grid = new int[n][lines[0].length()];
            grid_original = new int[n][lines[0].length()];

            int j,k;
            j = k = 0;

            for(String str:lines){
                char chars[] = str.toCharArray();
                for(char c:chars) {
                    grid[j][k] = c - '0';
                    grid_original[j][k] = c - '0';
                    ++k;
                }
                ++j;k=0;
            }
            getVertices();//finds the vertices in the maze
            getGraphRepresentation();//constructs a graph from the data given
            shortestPathDjikstra();//finding the shortest path
        }
        catch (IOException fnfe){
            fnfe.printStackTrace();
        }
    }

    /**
     * returns the graph
     * @return
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * shows the shortest path as a console output
     */
    public void showPath(){
        printGridOrg();
    }

    /**
     * returns the input file as an array of strings
     * @return
     */
    public String[] getLines() {
        return lines;
    }
    /**
     * solves the maze
     */
    private void shortestPathDjikstra(){
        int[] pred=new int[vertices.size()];
        int[] dist = new int[vertices.size()];
        int start = 0;
        shortestPathDjikstra(start, pred, dist);

        int v = vertices.size()-1;
        while(v!=0){
            Vertex vertex = vertices.get(v);
            grid_original[vertex.row][vertex.col] = 4;//4 is path point
            v = pred[v];
        }
        grid_original[0][0] = 4;//4 is path point
    }

    private void shortestPathDjikstra(int start, int[] pred, int[] dist){
        start = 0;
        //graph present
        boolean[] visited = new boolean[vertices.size()];
        LinkedList<Integer> to_visit = new LinkedList<>();//vertices to be visited
        for(int i = 0; i<dist.length; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[start] = 0;//start id

        for(int i=0; i<visited.length;++i)
            visited[i] = false;//none of them are visited in the beginning, using this array we can check if a vertex has been visited

        for(Vertex v: vertices)
            to_visit.add(v.id);//adding all the vertices to here
        //until all the vertices are visited
        int prev_vertex = start;
        while(!to_visit.isEmpty()){
            Iterator<Edge> i = graph.edgeIterator(prev_vertex);
            while(i.hasNext()){
                Edge edge = i.next();
                //assign weights to non visited vertices
                if(!visited[edge.getDest()]){
                    int temp_dist = dist[prev_vertex] + edge.getWeight();
                    if(temp_dist<dist[edge.getDest()]) {//update distance if new distance is smaller than before
                        dist[edge.getDest()] = temp_dist;
                        pred[edge.getDest()] = prev_vertex;
                    }
                }
            }
            to_visit.remove((Integer)prev_vertex);//we can now safetly remove it
            visited[prev_vertex] = true;
            //now find the new prev vertex
            //it has to be non visited and have the smallest distance from target
            int min_dist=0;//min dis from current vertex
            Iterator<Integer> it = to_visit.iterator();//here non visited ones are placed
            if(it.hasNext()) {//assume this
                prev_vertex = it.next();
                min_dist = dist[prev_vertex];
            }
            while(it.hasNext()){
                int temp_vertex = it.next();
                if(min_dist>dist[temp_vertex]){
                    min_dist = dist[temp_vertex];
                    prev_vertex = temp_vertex;
                }
            }
        }

    }

    /**
     * converts the int[][] matrix maze to a graph structure
     * @return
     */
    private Graph getGraphRepresentation(){
        graph = new Graph(vertices.size(), true);
        Iterator<Vertex> it = vertices.iterator();
        while (it.hasNext()){
            Vertex current_vertex = it.next();
            //four times need( up, down, left, right connection is possible for each node
            //can it go right
            int temp[][] = grid;
            grid = grid_original;//to temprarily work for this funciton since grid is changed
            if(rightPossible(current_vertex.row, current_vertex.col))
                findRightEdge(current_vertex);
            if(leftPossible(current_vertex.row, current_vertex.col))
                findLeftEdge(current_vertex);
            if(downPossible(current_vertex.row, current_vertex.col))
                findDownEdge(current_vertex);
            if(upPossible(current_vertex.row, current_vertex.col))
                findUpEdge(current_vertex);


            grid = temp;//go back to previous state
        }

        return graph;
    }
    private void findRightEdge(Vertex current_vertex){
        //find the one shortest on the right
        Iterator<Vertex> i = vertices.iterator();
        Vertex dest=null;
        Vertex min_dist=null;
        while(i.hasNext()){
            dest = i.next();
            if(current_vertex.col<dest.col && current_vertex.row==dest.row){
                if(min_dist == null) min_dist = dest;
                else if(min_dist.col>dest.col) min_dist = dest;
            }
        }
        graph.insert(new Edge(current_vertex.id, min_dist.id, min_dist.col-current_vertex.col));
    }
    private void findLeftEdge(Vertex current_vertex){
        //find the one shortest on the right
        Iterator<Vertex> i = vertices.iterator();
        Vertex dest=null;
        Vertex min_dist=null;
        while(i.hasNext()){
            dest = i.next();
            if(current_vertex.col>dest.col && current_vertex.row==dest.row){
                if(min_dist == null) min_dist = dest;
                else if(min_dist.col<dest.col) min_dist = dest;
            }
        }
        graph.insert(new Edge(current_vertex.id, min_dist.id, current_vertex.col - min_dist.col));
    }
    private void findUpEdge(Vertex current_vertex){
        //find the one shortest on the right
        Iterator<Vertex> i = vertices.iterator();
        Vertex dest=null;
        Vertex min_dist=null;
        while(i.hasNext()){
            dest = i.next();
            if(current_vertex.row>dest.row && current_vertex.col==dest.col){
                if(min_dist == null) min_dist = dest;
                else if(min_dist.row<dest.row) min_dist = dest;
            }
        }
        graph.insert(new Edge(current_vertex.id, min_dist.id, current_vertex.row - min_dist.row));
    }
    private void findDownEdge(Vertex current_vertex){
        //find the one shortest on the right
        Iterator<Vertex> i = vertices.iterator();
        Vertex dest=null;
        Vertex min_dist=null;
        while(i.hasNext()){
            dest = i.next();
            if(current_vertex.row<dest.row && current_vertex.col==dest.col){
                if(min_dist == null) min_dist = dest;
                else if(min_dist.row>dest.row) min_dist = dest;
            }
        }
        graph.insert(new Edge(current_vertex.id, min_dist.id, min_dist.row-current_vertex.row));
    }

    /**
     * gets the vertices from the maze array
     */
    private void getVertices(){
        vertices = new ArrayList<>();
        boolean found = false;
        //first find start vertex in the potentatly 1st row
        Vertex start = new Vertex();
        for(int i =0; i<grid.length &&!found; ++i){
            for(int j = 0; j<grid[i].length&&!found; ++j) {
                if (grid[i][j] == 0) {
                    vertices.add(new Vertex(i, j, vertices.size()));
                    grid[i][j] = -1;//-1 indicates a vertex
                    getVertices(i,j);
                    found = true;
                }
            }
        }
        vertices.add(new Vertex(grid.length-1, grid[0].length-1, vertices.size()));//end vertex
    }
    private void getVertices(int i, int j){
        if(rightPossible(i,j)) {
            grid[i][j+1] = 2;
            searchRight(i, j + 1);
        }
        if(leftPossible(i,j)) {
            grid[i][j-1] = 2;
            searchLeft(i, j - 1);
        }
        if(upPossible(i,j)) {
            grid[i-1][j] = 2;
            searchUp(i - 1, j);
        }
        if(downPossible(i,j)) {
            grid[i+1][j] = 2;
            searchDown(i + 1, j);
        }
    }
    private void searchRight(int i, int j){
        while(rightPossible(i,j) && !upPossible(i,j) && !downPossible(i,j)) {
            j = j + 1;
            grid[i][j] = 2;//mark path already visited
        }
        //check if cornered
        if(upPossible(i,j) || downPossible(i,j)) {
            vertices.add(new Vertex(i, j,vertices.size()));
            grid[i][j] = -1;//mark as a vertex
            getVertices(i,j);//do it again for the new place
        }
        else if(j==grid[0].length-1 || grid[i][j+1] == 1) {//means it is cornered
            vertices.add(new Vertex(i, j, vertices.size()));
            grid[i][j] = -1;
        }
        //else it is already saerched
    }
    private void searchLeft(int i, int j){
        while(leftPossible(i,j) && !upPossible(i,j) && !downPossible(i,j)) {
            j = j - 1;
            grid[i][j] = 2;//mark path visited
        }
        //check if cornered
        if(upPossible(i,j) || downPossible(i,j)) {
            vertices.add(new Vertex(i, j,vertices.size()));
            grid[i][j] = -1;//mark as a vertex
            getVertices(i,j);//do it again for the new place
        }
        else if(j==0 || grid[i][j-1] == 1) {//means it is cornered
            vertices.add(new Vertex(i, j,vertices.size()));
            grid[i][j] = -1;
        }
        //else it is already saerched
    }
    private void searchUp(int i, int j){
        while(upPossible(i,j) && !leftPossible(i,j) && !rightPossible(i,j)) {
            i = i - 1;
            grid[i][j] = 2;//mark path visited
        }
        //check if cornered
        if(leftPossible(i,j) || rightPossible(i,j)) {
            vertices.add(new Vertex(i, j,vertices.size()));
            grid[i][j] = -1;//mark as a vertex
            getVertices(i,j);//do it again for the new place
        }
        else if(i==0 || grid[i-1][j] == 1) {//means it is cornered
            vertices.add(new Vertex(i, j,vertices.size()));
            grid[i][j] = -1;
        }
        //else it is already saerched
    }
    private void searchDown(int i, int j){
        while(downPossible(i,j) && !leftPossible(i,j) && !rightPossible(i,j)) {
            i = i + 1;
            grid[i][j] = 2;//mark path visited
        }
        //check if cornered
        if(leftPossible(i,j) || rightPossible(i,j)) {
            vertices.add(new Vertex(i, j,vertices.size()));
            grid[i][j] = -1;//mark as a vertex
            getVertices(i,j);//do it again for the new place
        }
        else if(i==grid.length || grid[i-1][j] == 1) {//means it is cornered
            vertices.add(new Vertex(i, j, vertices.size()));
            grid[i][j] = -1;
        }
        //else it is already saerched
    }

    private boolean rightPossible(int i, int j){
        return (j+1<grid[0].length) && (grid[i][j+1] == 0);
    }
    private boolean leftPossible(int i, int j){
        return (j-1>=0) && (grid[i][j-1] == 0);
    }
    private boolean upPossible(int i, int j){
        return (i-1>=0) && (grid[i-1][j] == 0);
    }
    private boolean downPossible(int i, int j){
        return (i+1<grid.length) && (grid[i+1][j] == 0);
    }
    //modified grid( grid after saerch is performed
    private void printGrid(){
        for(int[] r: grid) {
            for (int m : r) {
                if(m!=-1 && m!=2)
                    System.out.print("n" + " ");
                else if(m==2)
                    System.out.print("-" + " ");
                else
                    System.out.print("v" + " ");

            }
            System.out.println();
        }
    }
    private void printGridOrg(){
        for(int[] r: grid_original) {
            for (int m : r) {
                if(m==4)
                    System.out.print("x" + " ");
                else
                    System.out.print(m + " ");

            }
            System.out.println();
        }
    }
    /**
     * class to store a vertex( its id, row and column )
     * it is private, used only for constructing the maze
     */
    private static class Vertex{
        private int row;
        private int col;
        int id;
        public Vertex() {
            this(0,0,-1);
        }
        public Vertex(int row, int col, int id) {
            this.row = row;
            this.col = col;
            this.id = id;
        }
        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        @Override
        public String toString() {
            return "ID: " + id + "| r: " + row + ", c: " + col;
        }
    }


}

