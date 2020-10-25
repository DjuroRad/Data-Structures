package LibraryAutomationSystem.gtu;

import java.security.InvalidParameterException;

public class User{
    /**
     * system where user belongs
     */
    LibrarySystem librarySystem;
    /**
     * name of the user
     */
    String name;

    /**
     *protected since only should be access from the system
     * @param name name of the user
     * @param librarySystem system user is in
     */
    protected User(String name, LibrarySystem librarySystem){
        this.name = name;
        this.librarySystem = librarySystem;
    }

    /**
     * Searching by using books title
     * @param title
     * @return
     */
    public String searchByBookTitle( String title ){
        return librarySystem.searchByBookTitle(title);
    }

    /**
     * Search using author's name
     * @param author_name name of the author
     * @return retur detailed info of the book in a string
     */
    public String searchByAuthorName( String author_name ){
        return librarySystem.searchByAuthorName(author_name);
    }

    /**
     * used for comparing two user( are same if names are the same )
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        User user = (User)obj;
        if( user == null )
            throw new InvalidParameterException();
        return user.name.equals(this.name);
    }

    /**
     * return's the hash code of its name
     * @return name's hash code
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * gettor for the user's name
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * returns name
     * @return name of the user
     */
    @Override
    public String toString() {
        return name;
    }

}