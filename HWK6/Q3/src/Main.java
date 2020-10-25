import LibraryAutomationSystem.gtu.Admin;
import LibraryAutomationSystem.gtu.LibrarySystem;
import LibraryAutomationSystem.gtu.Status;
import LibraryAutomationSystem.gtu.User;

import java.security.InvalidParameterException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Creating a system with admin names 'djuro' with pw 'djuro123' ");
        LibrarySystem librarySystem = new LibrarySystem("djuro", "djuro123");//when making a librarySystem this admin must be initialized!
        System.out.println("Getting the admin form the system by providing admin's username and password correctly");
        Admin admin = librarySystem.getAdmin("djuro", "djuro123");
        System.out.println("Admin adds a book with author 'Mesa' with title 'Dervis' and location of 'c15r23.2234' ");
        admin.addBook("Mesa", "Dervis", "c15r23.2234");
        System.out.println("Admin adds user named 'marija' to the system");
        admin.addUser("marija");
        System.out.println("Getting a user from the system by providing its name");
        User user = librarySystem.getUser("marija");
        System.out.println("User searches a book using its title 'Dervis' ");
        System.out.println(user.searchByBookTitle("Dervis"));
        System.out.println("User searches a book using its author's name 'Mesa' ");
        System.out.println(user.searchByAuthorName("Mesa"));
        System.out.println("Admin deletes a book from author 'Mesa' with the title 'Dervis' ");
        admin.deleteBook("Mesa", "Dervis");
        System.out.println("User tries to access using author's name");
        try{
            System.out.println(user.searchByAuthorName("Mesa"));
        }
        catch (InvalidParameterException e){
            System.out.println("Exception cought with a message: " + e.getMessage());
        }
        System.out.println("User tries to access using name of the title: " + user.searchByBookTitle("Dervis"));

        System.out.println("Admin adds a book with author 'Mesa' and name 'Dervis' at 'c1r1' ");
        admin.addBook("Mesa", "Dervis", "c1r1");
        System.out.println("Admin adds a book with author 'Mesa' and name 'Tvrdjava' at 'c1r2' ");
        admin.addBook("Mesa", "Tvrdjava", "c1r2");
        System.out.println("Admin changes the status of 'Dervis' at 'c1r1' to not available");
        admin.updateInformation("Mesa", "Dervis", "c1r1", Status.not_available );
        System.out.println("Admins tries to add a book at location 'c1r1' which should not be possible( name is 'Ako Znam' with the same author");
        admin.addBook("Mesa", "Ako Znam", "c1r2");
        System.out.println("User tries to find the book at that location that should not have been added");
        System.out.println("Searching for 'Ako Znam' returns: " + user.searchByBookTitle("Ako Znam"));
        System.out.println("Admin adds again book called 'Dervis' from 'Mesa' but now at position 'c4r4' ");
        admin.addBook("Mesa", "Dervis", "c4r4");
        System.out.println("User searches 'Dervis': " + user.searchByBookTitle("Dervis"));
        System.out.println("Now searches for all the books from 'Mesa': " + "\n" + user.searchByAuthorName("Mesa"));
        System.out.println("Admin 'djuro' adds a new admin called 'maka' with password 'marija1' ");
        admin.addAdmin("maka", "marija1");
        System.out.println("Getting 'maka' admin from the system by loggin in with password 'marija1' ");
        Admin marija = librarySystem.getAdmin("maka", "marija1");
        System.out.println("maka is taken from the system: " + (marija != null));
        System.out.println("Maka removes user with a name marija");
        marija.removeUser("marija");
        User new_user = librarySystem.getUser("marija");
        if( new_user == null )
            System.out.println("Marija was successfully removed form the system" );

    }

}
