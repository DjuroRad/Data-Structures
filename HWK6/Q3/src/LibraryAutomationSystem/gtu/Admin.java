package LibraryAutomationSystem.gtu;

public class Admin{
    private String name;
    /**
     * its password
     */
    private String password;
    /**
     * system it manipulates
     */
    private LibrarySystem librarySystem;
    protected Admin( String name, String password, LibrarySystem librarySystem){//admin can be accessed only from within the librarySystem
        this.password = password;
        this.name = name;
        this.librarySystem = librarySystem;
    }

    /**
     * Adds a book to the system it manipulates
     * @param author name of the author
     * @param title book name
     * @param location location name( unique )
     * @return if added true( false otherwise )
     */
    public boolean addBook(String author, String title, String location){
        return librarySystem.addBook(author, title, location, name, password);
    }

    /**
     * Removes a book from the system
     * @param author author name
     * @param title book name
     * @return true if found, false otherwise
     */
    public boolean deleteBook(String author, String title){
        return librarySystem.removeBook(author,title,name, password);
    }

    /**
     * Used to alter the status of the book
     * @param author author name
     * @param title book name
     * @param location location name
     * @param new_status new status
     * @return true if book is in the system and false otherwise
     */
    public boolean updateInformation(String author, String title, String location, Status new_status ){
        return librarySystem.updateInformation(author, title, location, new_status, name, password);
    }

    /**
     * adds a user to eht eh system
     * @param name name of the user to be added
     */
    public void addUser( String name ){//administrator can also add a user to the librarySystem
        librarySystem.addUser(name);
    }

    /**
     * Comparing two admins
     * @param obj has to be Admin object
     * @return true if the names are the same and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Admin admin = (Admin) obj;
        return admin.name.equals(this.name);
    }

    /**
     * return's admin name's hash code
     * @return
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * adding an admin to the system
     * @param name admin's name
     * @param password its password
     * @return
     */
    public boolean addAdmin(String name, String password ){
        return librarySystem.addAdmin(name, password);
    }

    /**
     * removes a user form the system if the user exists in the system
     * @param name name of the user to be removed
     * @return true if user was in the system and false otherwise
     */
    public boolean removeUser(String name){
        return librarySystem.removeUser(name);
    }

}
