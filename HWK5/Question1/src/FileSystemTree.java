import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class FileSystemTree {

    /**
     * holds the root directory of this FileSystemTree
     */
    private FileNode root;

    /**
     * one parameter constructor for the FileSystemTree
     * @param root_directory_name name of the root directory
     * @throws InvalidParameterException if the null is passed as a string
     */
    public FileSystemTree(String root_directory_name) {
        if( root_directory_name == null )
            throw new InvalidParameterException();
        root = new FileNode(root_directory_name, false);//making a root directory
    }

    /**
     * returns true if directory is added and false otherwise
     * @param path path to the element to be added
     * @return returns true if directory is added and false otherwise
     * @throws InvalidParameterException when passed path in null
     */
    public boolean addDir(String path){
        if( path == null )
            throw new InvalidParameterException();
        return root.addFileDir( path.split("/"), false );
    }

    /**
     * returns true if the file is added and false otherwise
     * @param path path to the element to be added
     * @return true if the file is added and false otherwise
     * @throws InvalidParameterException when path passed is null
     */
    public boolean addFile(String path){
        if( path == null )
            throw new InvalidParameterException();
        return root.addFileDir( path.split("/"), true );
    }

    /**
     * this class is used to store the file or the directory of the instances of FIleSystemTree
     */
    protected static class FileNode{
        boolean file;//indicates if this node is file, if it is false it is a directory
        private String name;//name of the file or the directory
        private LinkedList<FileNode> files;//One of the children contains the files( null if it is a file )
        private LinkedList<FileNode> folders;//contains the folders inside that folder/file( if it is a file it will be null )

        /**
         * two paramteer constrcutor for this node
         * @param name name of the file
         * @param file type of the file( true for file and false for directory )
         */
        public FileNode(String name, boolean file) {
            this.file = file;
            this.name = name;
            if( !file ) {
                files = new LinkedList<FileNode>();
                folders = new LinkedList<FileNode>();
            }
            else
                files = folders = null;

        }

        /**
         * simple setter
         * @param name name of the file
         */
        protected void setName(String name) {
            this.name = name;
        }

        /**
         * basic getter
         * @return name of the file
         */
        protected String getName() {
            return name;
        }

        /**
         * adds a new child to this node
         * @param dir_name name of the directory to be added
         * @return returns true if it is added
         */
        protected boolean addDir(String dir_name){
            return folders.add(new FileNode(dir_name, false));//adding a folder to the file system
        }

        /**
         * adds a file/firectory to the FileNode
         * @param folder_arr string that contains the path of the file/directory to be added
         * @param file indicates if the file to be added is file or a directory
         * @return true if the file is added and false otherwise
         */
        private boolean addFileDir( String folder_arr[], boolean file ){
            FileNode root_node = this;
            if(!root_node.name.equals(folder_arr[0]))
                return false;
            for( int i = 1; i<folder_arr.length; ++i ) {//from since 0th element is the root and is controlled beforehand
                //this is for searching a folder it is going to be added to
                if( i!=folder_arr.length-1 ) {
                    Iterator<FileNode> iter = root_node.folders.iterator();
                    boolean found = false;
                    while (iter.hasNext() && !found) {
                        root_node = iter.next();
                        if (root_node.getName().equals(folder_arr[i]))//when the folder is found
                            found = true;
                    }
                    if(!found)//when the system does not contain the folder that is to be added to the system
                        return false;
                }
                else{//this is for when the wanted folder is found and the addition is to be performed
                    if(file)
                        return root_node.addFile(folder_arr[i]);
                    return root_node.addDir(folder_arr[i]);
                }
            }
            return false;//when the end of the string is reached and nothing is returned
        }
        /**
         * adds a child to this node( adds a file )
         * @param file_name name of the file to be added
         * @return true if added and false otherwise
         */
        protected boolean addFile( String file_name ){
            return files.add(new FileNode(file_name, true));
        }

        /**
         * checks if the fileNode instance is file or not
         * @return true if it is a file and false otherwise
         */
        protected boolean isFile(){
            return file;
        }

        /**
         * two files are the same if their names are the same( can't contain two elements that are the same in the file
         * @param obj object to be compared with
         * @return true if the names are the same and false otherwise
         */
        @Override
        public boolean equals(Object obj) {
            return (((FileNode)obj).getName()).equals(this.name);
        }

        /**
         * public method that prints the FileSystem( it calls a private method that prints it in the desired manner )
         */
        public void printFileSystem(){
            printFileSystem(this,0);
        }

        /**
         * this method is called within printFileSystem() public no parameter method
         * @param node file system's root node that is to be printted
         * @param depth indicates the depth of the tree for better overview of the tree
         */
        private void printFileSystem(FileNode node, int depth){
            for( int i = 0; i<depth; ++i )
                System.out.print(" ");
            System.out.print("|");
            System.out.print(node.name);

            Iterator<FileNode> i = node.folders.iterator();
            while(i.hasNext()) {
                System.out.println();
                printFileSystem(i.next(), depth + 1);
            }
            i = node.files.iterator();
            while(i.hasNext()){
                System.out.println();
                for( int it = 0; it<depth+1; ++it )
                    System.out.print(" ");
                System.out.print("|");
                System.out.print(i.next().name);
            }
        }

        /**
         * gives the string that has the name of the file and its type - directory or file
         * @return
         */
        @Override
        public String toString() {
            String str = "";
            if( file )
                str += "file - ";
            else
                str += "dir - ";
            return str += name;
        }
    }

    /**
     * calls private printFileSystem that is contained within the FileNode
     */
    public void printFileSystem(){
        root.printFileSystem();
    }

    /**
     * method to search the entire file system for a directory or a file including the given
     * search characters in its name. The search characters will be given as the parameter of the
     * method
     * @param str characters to be searched for
     */
    public void search(String str){
        search(root, str, root.name + "/");
    }

    /**
     * private helper method used for searching
     * @param temp current file node
     * @param str characters to be serached for
     * @param path string to be output at the momemnt found( being appended at every step
     */
    private void search( FileNode temp, String str, String path ){
        Iterator<FileNode> i = temp.files.iterator();//first printing all the files
        while(i.hasNext()){
            FileNode curr = i.next();
            if( curr.name.contains(str) )
                System.out.println("file - " + path + curr.name);
        }
        i = temp.folders.iterator();//then recursively printing all the folders
        while( i.hasNext() ){
            FileNode curr = i.next();
            if( curr.name.contains(str))
                System.out.println("dir - " + path + curr.name);
            search(curr, str, path + curr.name+"/");
        }
    }

    /**
     * removes the element given the desired path
     * @param path path of the file to be removed
     * @throws InvalidParameterException when null is passed as a paramter
     */
    public void remove( String path ){
        if( path == null )
            throw new InvalidParameterException();
        String paths[] = path.split("/");
        if( root.name.equals( paths[0] ) )
            remove( paths, root );
    }

    /**
     * private method that executes the desired functionality
     * @param path path of the element to be removed
     * @param root_node root node of the file system from which the file/directory is to be removed
     */
    private void remove( String path[], FileNode root_node ){
        for( int i = 1; i<path.length; ++i ){
            if( i!=path.length-1){
                Iterator<FileNode> it = root_node.folders.iterator();
                boolean found = false;
                while(it.hasNext() && !found ){
                    root_node = it.next();
                    if( root_node.name.equals(path[i]) )
                        found = true;
                }
                if( !found )//meaning the path is not valid if the folder wasn't found here
                    return;
            }
            else{
                remove( path[path.length-1], root_node );
            }
        }
    }

    /**
     * helper method that is exectued when the desired path is reached, it will give the opportunity to remove the right file if it's name has been misspelled
     * @param file name of the file to be removed
     * @param node node of FileNode structure that should contain this file/directory
     */
    private void remove( String file, FileNode node ){
        Iterator<FileNode> i = node.folders.iterator();
        boolean removed = false;
        while( i.hasNext() && !removed ){
            FileNode to_remove = i.next();
            if( to_remove.name.equals(file) ) {
                i.remove();
                removed = true;
            }
        }
        if( !removed ){
            i = node.files.iterator();
            while( i.hasNext() && !removed ){
                FileNode to_remove = i.next();
                if( to_remove.name.equals(file) ) {
                    i.remove();
                    removed = true;
                }
            }
        }
        if( !removed ){//should let the user choose right now
            System.out.println("File/Folder you wanted to remove is not found");
            System.out.println("Please choose folder/file to be removed from your destination");
            i = node.folders.iterator();
            while(i.hasNext())
                System.out.println(i.next());
            i = node.files.iterator();
            while(i.hasNext())
                System.out.println(i.next());

            Scanner scanner= new Scanner(System.in);
            System.out.println("Do you want to remove some other directory or file?\n 1-yes\n 0-no");
            int input = scanner.nextInt();//removing the new line
            scanner.nextLine();
            if( input == 1 ){
                System.out.println("Enter the name of the folder you want to remove");
                file = scanner.nextLine();
                remove( file, node );
            }
        }
    }
}
