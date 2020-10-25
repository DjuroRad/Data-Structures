package LibraryAutomationSystem.gtu;

import java.security.InvalidParameterException;

/**
 * Separate class was made for book location so that i could store status of each book there
 */
public class BookLocation {
    /**
     * locatoin of the book ( is unique for each book for the whole system
     */
    private String location;
    /**
     * status of the book( can be altered by admin )
     */
    private Status status;

    /**
     * Location of the book's constructor, made protected since i want it to be accessible only within the package
     * @param location location of the book in string
     * @param status it's status
     */
    protected BookLocation(String location, Status status ){
        this.location = location;
        this.status = status;
    }

    /**
     * setter for the book's location, available withing the package( publicly available through admin
     * @param location locatoin of the book
     */
    protected void setLocation(String location) {
        this.location = location;
    }

    /**
     * setter for the status of the book
     * @param status new status for this book
     */
    protected void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Overloaded equals method because Set's and Map's contains methods depend on it
     * @param obj object to be compared with( exception if the type is not the same
     * @return true if locations are the same and false otherwise
     */
    @Override
    public boolean equals(Object obj) {//if the location is the same than they are the same
        BookLocation loc = (BookLocation)obj;
        if(loc == null )
            throw new InvalidParameterException("Object no of type BookLocation");
        return this.location.equals(loc.location);
    }

    /**
     * Same reason as for the equals method( contains uses it)
     * @return has code of the location string
     */
    @Override
    public int hashCode() {
        return location.hashCode();
    }

    /**
     * getter for locatoin of the book
     * @return gets the location of the book
     */
    public String getLocation() {
        return location;
    }

    /**
     * getter for the status of the book
     * @return status of the booik
     */
    public Status getStatus() {
        return status;
    }

    /**
     * prints the status location of the book
     * @return
     */
    @Override
    public String toString() {
        return location;
    }

}
