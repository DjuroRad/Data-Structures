package LibraryAutomationSystem.gtu;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * system of the library
 */
public class LibrarySystem {
    // hasMap<Author, HashMap<Book_name, Set<Localtion> >
    /**
     * Admins' names and passowrds are stored in this map
     */
    private HashMap<String, String> admins;
    /**
     * users are stored in this HashSet
     */
    private HashSet<User> users;
    /**
     * authors are in here with their nested maps and sets as it was described in the pdf!
     */
    private HashMap<String/*author*/,HashMap<String/*book_name*/,HashSet<BookLocation/*locations*/> > > authors;
    /**
     * global locations of the book in oreder to avoid adding a book at a position where book already exists
     */
    private HashSet<BookLocation> locations;//to avoid multiple books at the same location

    /**
     * Constructor here requires admin's name and password sine otherwise it would not be possible to have a system
     * @param admin_name name of the admin
     * @param password password for that admin
     */
    public LibrarySystem(String admin_name, String password ){//at least one admin needs to be present when librarySystem is created
        admins = new HashMap<String, String>();
        authors = new HashMap<String/*author*/,HashMap<String/*book_name*/,HashSet<BookLocation/*locations*/> > >();
        users = new HashSet<User>();
        locations = new HashSet<BookLocation>();
        addAdmin( admin_name, password );
    }

    /**
     * used ot add an admin, administrator calls it within this package
     * @param name name of the admin to be added
     * @param password its password
     * @return returns true if there is not admin with such username already in the system
     */
    protected boolean addAdmin(String name, String password){
        if( !admins.containsKey(name) ){
            admins.put( name, password );
            return true;
        }
        return false;
    }

    /**
     * When admin logs in this mehtod is called
     * @param name name of the admin
     * @param password password for the admin
     * @return admin if it exists and null if it does not or the pw was incorrect
     */
    public Admin getAdmin( String name, String password ){
        if( admins.containsKey(name) && admins.get(name).equals(password) ) {
            return new Admin(name, password, this);
        }
        else {
            return null;
        }
    }

    /**
     * Searches using the author's name
     * @param author_name name of the author
     * @return string that showes detailed info
     * @throws NullPointerException when parameter passed in null
     */
    protected String searchByAuthorName( String author_name ){
        if( author_name == null )
            throw new NullPointerException("author_name passed is null");
        String books_info = "";
        if( authors.containsKey(author_name) ){
            HashMap<String/*titles*/,HashSet<BookLocation/*locations*/>> books = authors.get(author_name);
            Set<String> titles = books.keySet();//getting all the titles in a set
            for( String title: titles ){
                books_info += "Title: " + title;
                books_info += "\nLocations: ";
                HashSet<BookLocation> locations_ = books.get(title);
                for(BookLocation location: locations_ )//iterating through locations
                    books_info += location + "/ ";
                books_info += "\n\n";
            }
        }
        else
            throw new InvalidParameterException("Such author does not exist");
        return books_info;
    }

    /**
     * Searches book by its title
     * @param title name of the book
     * @return detailed info of the books with that title
     */
    protected String searchByBookTitle( String title ){
        if( title == null )
            throw new NullPointerException("Title passed is null");
        String book_info = "";
        for( String author_name: authors.keySet() ){
            for( String title_current: authors.get(author_name).keySet() ){
                if( title.equals(title_current) ){
                    book_info += "Author: " + author_name;
                    book_info += "\nLocation: ";
                    for( BookLocation location: authors.get(author_name).get(title) )
                        book_info += location + " - " + location.getStatus() + "/ ";
                }
            }
        }
        if( book_info.equals(""))
            book_info += "No such title";
        return book_info;
    }

    /**
     * adding a book ( admin calls this method )
     * @param author author's name
     * @param title book's title
     * @param location book's location
     * @param admin_name admin's name
     * @param admin_pw admin's password
     * @return true if added and false otherwise( false when there is a book at that place
     */
    protected boolean addBook(String author, String title, String location, String admin_name, String admin_pw){
        if( locations.contains(new BookLocation(location,Status.available)) )
            return false;
        locations.add(new BookLocation(location,Status.available));//the book is available in the beginning
        if( admins.containsKey(admin_name) && admins.get(admin_name).equals(admin_pw) ){//administrator verification
            HashMap<String, HashSet<BookLocation>> book = new HashMap<String, HashSet<BookLocation>>();
            HashSet<BookLocation> loc = new HashSet<BookLocation>();
            loc.add(new BookLocation(location, Status.available));
            book.put(title, loc);
            if( !authors.containsKey(author) )
                authors.put(author,book);
            else{
                if( !authors.get(author).containsKey(title) ){//there is an author but the title was missing
                    authors.get(author).put(title,loc);
                }
                else{
                    if( !authors.get(author).get(title).contains(new BookLocation(location, Status.available)) )
                        authors.get(author).get(title).add(new BookLocation(location,Status.available));
                }
            }
        }
        else {
            throw new InvalidParameterException("Administrator username or password incorrect: " + admins.containsKey(admin_name) + " " + admins.get(admin_name));
        }
        return true;
    }

    /**
     * Method for removing a book form the system
     * @param author name of the author of the book
     * @param title name of its title
     * @param admin_name admin's name
     * @param admin_pw admin's password
     * @return true if the book was in the system and false otherwise
     */
    protected boolean removeBook( String author, String title, String admin_name, String admin_pw ){
        if( authors.containsKey(author) ){
            if( authors.get(author).containsKey(title) ){
                //removing now
                //if it is present on more than one location we will remove the first location
                if( authors.get(author).get(title).size()>1 ){//removes the first element in this set( first location
                    locations.remove(authors.get(author).get(title).iterator().next());
                    authors.get(author).get(title).remove(authors.get(author).get(title).iterator().next());
                }//otherwise removes the title from the author's books
                else {//global location needs to be updated also
                    locations.remove(authors.get(author).get(title).iterator().next());
                    authors.remove(author);//since no books left from this author
                }
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    /**
     * Information about the book's status can be altered through here using admin's updateInfo method
     * @param author name of the author
     * @param title book's name
     * @param location location
     * @param new_status nwe status of the book
     * @param admin_name admin's name
     * @param admin_pw admin's password
     * @return returns true if book with that name at that position exists
     */
    protected boolean updateInformation(String author, String title, String location, Status new_status, String admin_name, String admin_pw ){//changes the status of a book
        if (authors.containsKey(author) && authors.get(author).containsKey(title) && authors.get(author).get(title).contains(new BookLocation(location, Status.available)) && locations.contains(new BookLocation(location,Status.available))) {
            HashSet<BookLocation> locs = authors.get(author).get(title);
            Iterator<BookLocation> iter = locs.iterator();
            while (iter.hasNext()) {
                BookLocation temp = iter.next();
                if (temp.getLocation().equals(location)) {
                    temp.setStatus(new_status);
                    break;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Adding a user to the system
     * @param name name of the user to be added
     */
    protected void addUser(String name){
        if( !users.contains(name) ) {
            users.add(new User(name, this));
        }
    }

    /**
     * Used to get the user from the system
     * @param name name of the user
     * @return user with provided name
     */
    public User getUser( String name ){
        User user_ = null;
        if( users.contains(new User(name,this)) ){
            for( User user: users) {
                if (user.getName().equals(name)) {
                    user_ = user;
                    break;
                }
            }
        }
        return user_;
    }

    /**
     * Method accessible through admin to remove a user from the system
     * @param name name of the user to be removed
     * @return true if user was in the system and false otherwise
     */
    protected boolean removeUser(String name){
        if( users.contains(new User(name,this)) ){
            users.remove(new User(name,this) );
            return true;
        }
        return false;
    }
}
