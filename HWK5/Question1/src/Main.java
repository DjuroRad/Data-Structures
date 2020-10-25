public class Main {
    public static void main(String[] args) {
        FileSystemTree file_syste = new FileSystemTree("root");
        file_syste.addDir("root/dir1");
        file_syste.addFile("root/file.txt");
        file_syste.addDir("root/dir1/dir2");
        file_syste.addDir("root/dir1/dir2/dir23");
        file_syste.addFile("root/dir1/dir2/file2.txt");
        System.out.println("Printing the system after adding 2 files and 3 directories to it:");
        file_syste.printFileSystem();

        System.out.println();
        System.out.println("Trying to remove a file/directory in the correct but where the desired element to be removed does not exist");
        file_syste.remove("root/dir1/dir2/sdth");
        System.out.println("After removing chosen 'dir23' the fileSystem is ");
        file_syste.printFileSystem();
        System.out.println();
        System.out.println("Trying to search for a file/directory that does not exist( \"sth\" ) passed as a parameter( nothing should be output)");
        file_syste.search("sth");
        System.out.println("After executing search for \"sth\" ");
        System.out.println("Now searching for \"fi\"(should find 2 files) ");
        file_syste.search("fi");
        System.out.println();
        FileSystemTree myFileSystem = new FileSystemTree("root");
        //Add directories and files using paths
        System.out.println("Making a new tree that is equivalent to the example given in the homework, should work the same way");
        myFileSystem.addDir("root/first_directory");
        myFileSystem.addDir("root/second_directory");
        myFileSystem.addFile("root/first_directory/new_file.txt");
        myFileSystem.addDir("root/second_directory/new_directory");
        myFileSystem.addFile("root/second_directory/new_directory/new_file.doc");
        System.out.println("Printing the newly created system");
        myFileSystem.printFileSystem();
        System.out.println();
        System.out.println("Searching for new in our tree");
        myFileSystem.search("new");
        //Remove files or directories
        System.out.println("Removing new_file and new_directory");
        myFileSystem.remove("root/first_directory/new_file.txt");
        myFileSystem.remove("root/second_directory/new_directory");
        myFileSystem.printFileSystem();
    }
}
