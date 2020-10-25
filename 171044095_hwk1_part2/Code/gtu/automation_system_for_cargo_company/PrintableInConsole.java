package gtu.automation_system_for_cargo_company;

/**
 * Printable in console interface is used to show
 * as much information as possible about a certain Object
 */
public interface PrintableInConsole {
    /**
     * This method should provide
     * similar results to toString function
     */
    String show();

    /**
     * This method should provide more information
     * about the object if more informaiton is there in the system
     */
    String showDetailed();

    /**
     * This method prints everything the other
     * two functions print but also the hasCode of the function
     * and the exact class name as it is in the program
     */
    String showDebuggingMode();
}
