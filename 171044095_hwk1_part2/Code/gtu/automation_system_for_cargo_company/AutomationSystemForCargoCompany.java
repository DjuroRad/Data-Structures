package gtu.automation_system_for_cargo_company;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * The AutomationSystemForCargoCompany program is used to help cargo companies
 * to automate and organize their system. They can create as many of these systems as needed.
 * Each of them will contain users such as administrators, branch employees, transporatation personnel and customers.
 *
 * @author Djuro Radusinovic 171044095
 * @version 1.0
 * @since 2020-02-21
 */
public class AutomationSystemForCargoCompany implements PrintableInConsole, CompanySystem{

    private String system_name;
    private ArrayList<Administrator> administrators = new ArrayList<>();
    private ArrayList <Branch> branches = new ArrayList<Branch>();
    private ArrayList <Shipment> shipments = new ArrayList<Shipment>();//holds all the shipments in the system
    private ArrayList <TransportationPersonnel> transportation_personnel = new ArrayList<TransportationPersonnel>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();// I've put it here since they were part of the branch beforehand, but in the homework it is said that branch employees add customers to the system, NOT THE BRANCH
    /**
     * A no paramter constructoer that makes a system
     * with a default name of UNDEFINED_NAME_SYSTEM
     */
    public AutomationSystemForCargoCompany(){
        this( "UNDEFINED_NAME_SYSTEM" );
    }

    /**
     * One parameter constructor with type String
     * @param system_name It indicated the name system will be given
     */
    public AutomationSystemForCargoCompany( String system_name ){
        setSystemName(system_name);
    }

    /**
     * Setter of the system's name
     * @param system_name type String - indicates the name of the system
     */
    public void setSystemName(String system_name) {
        this.system_name = system_name;
    }

    /**
     * Overriden toString method - it prints the name of the system
     * @return type String
     */
    @Override
    public String toString() {
        return system_name;
    }

    /**
     * Two systems are the same if their names are the same( System does not allow two systems with the same name to avoid ambiguity )
     * @param obj - will be casted to AutomationSystemForCargoCompany type, otherwise return false
     * @return returns boolean
     */
    @Override
    public boolean equals(Object obj) {
        if( this == obj )
            return true;
        if( !(obj instanceof AutomationSystemForCargoCompany) )
            return false;
        AutomationSystemForCargoCompany obj_sys = (AutomationSystemForCargoCompany)obj;
        return this.system_name.equals(obj_sys.getSystemName() );
    }

    /**
     * Getter for system's name
     * @return type is String
     */
    public String getSystemName() {
        return system_name;
    }

    /**
     * Getter for the administrators of the system
     * @return type of ArrayList since administrators are stored in the array that is manipulated so that is dynamic
     */
    public ArrayList<Administrator> getAdministrators() {
        return administrators;
    }

    /**
     * getter for branches of the system to be able to access employees of the branch and the shipments of the each branch
     * @return type of ArrayList since branches are stored in the array that is manipulated so that is dynamic
     */
    public ArrayList<Branch> getBranches() {
        return branches;
    }

    /**
     * getter for shipements of the system to be able to access shpipments
     * @return type of ArrayList since shipments are stored in the array that is manipulated so that is dynamic
     */
    public ArrayList<Shipment> getShipments() {
        return shipments;
    }

    /**
     * getter for Transportation Personnel of the system to be able to access employees of the branch and the shipments of the each branch
     * @return type of ArrayList since branches are stored in the array that is manipulated so that is dynamic
     */
    public ArrayList<TransportationPersonnel> getTransportationPersonnel() {
        return transportation_personnel;
    }

    /**
     * Whoever is in control of the system is able to add adminstrators to it
     * System's user interface cannot be reached before the administrator is added
     * since it is the one who is able to control a certain system's users
     * @param name - Name of the administrator to be added
     * @param surname - Surname of the administrator to be added
     * @return true if the admin is added and false if it is not added( The same administrator cannot be added twice
     */
    public boolean addAdministrator( String name, String surname ){
        if( !administrators.contains( new Administrator(name,surname, this) ))
            return administrators.add( new Administrator(name,surname, this) );
        else
            throw new InvalidParameterException("This admin already exists");
    }

    /**
     * Whoever in charge of the system can remove an administrator from it*
     * @param admin - Administrator is the type as it is a public class
     * @return returns true if administrator is successfully remove and false otherwise
     */
    public boolean removeAdministrator( Administrator admin ){
        return administrators.remove( admin );
    }

    /**
     * Another function for removing an administrator by providing index to the ArrayList of administrators
     * @param i throws an exception if i out of bounds
     * @return true if removed, otherwise throws an exception
     */
    public boolean removeAdministrator( int i ){
        if( i >= 0 && i < administrators.size() ) {
            administrators.remove(i);
            return true;
        }
        else
            throw new InvalidParameterException();//throws an exception when i is out of bounds
    }
    /**
     * Main thing the head of the system gotta do is add an admin
     * When it adds an admin, other things should be done by the admin
     * This can be used when removing an admin from the system so that the head
     * of the system could choose
     * @return STring of the admins
     */
    public String getAdministratorsString(){
        String to_return = "";
        for( int i = 0; i<administrators.size(); ++i )
            to_return += i+1 + ") " + administrators.get(i).show() + "\n";
        return to_return;
    }

    /**
     * Head of the system can see the size of the administrators also
     * @return number of administrators
     */
    public int getAdministratorsSize(){
        return administrators.size();
    }
    /**
     * Another way to remove an administrator is by its name and surname
     * @param name - Admin's name
     * @param surname - Admin's surname
     * @return returns true if administrator is successfully remove and false otherwise
     */
    public boolean removeAdministrator( String name, String surname ){
        return removeAdministrator( new Administrator(name, surname, this) );
    }

    /**
     * Shows information about branches
     * @return nicely formatted string of branches
     */
    public String getBranchesString(){
        String to_return = "";
        for( int i = 0; i<branches.size(); ++i )
            to_return += i + 1 + ") " + branches.get(i).show() + "\n";
        return to_return;
    }

    /**
     * Gets the number of branches in the system
     * @return Number of branches
     */
    public int getBranchesSize(){
        return branches.size();
    }

    /**
     * Method enables to get a branch by its index
     * @param i throws an exception if i is out of bounds
     * @return return the branch if found
     */
    public Branch getBranchById( int i ){
        if( i >= 0 && i < branches.size() )
            return branches.get(i);
        else
            throw new InvalidParameterException("Invalid paramter to branches ArrayList in getBranchesById method");
    }
    /**
     * Returns the admnistrator by its name and surname
     * @param name - name of the admin
     * @param surname surname fo the admin
     * @return Administrator type, null if it doesn't exist
     */
    public Administrator getAdministratorByName( String name, String surname ){
        int i =administrators.indexOf(new Administrator(name,surname, this));
        if( i != -1 )
            return administrators.get(i);
        else
            return null;
    }

    /**
     * TransporatatinPersonnel returned if it exists, returns null on failure
     * @param name name of Personnel
     * @param surname surname of Personnel
     * @return Personnel reference if exists, false otherwise
     */
    public TransportationPersonnel getTPersonnelByName( String name, String surname ){
        int i = transportation_personnel.indexOf(new TransportationPersonnel(name, surname, shipments ));
        if( i != -1 )
            return transportation_personnel.get(i);
        else
            return null;
    }

    /**
     * Customer to get by its name and surname. Can be used for logging in where it would return null on failure
     * @param name name of customer
     * @param surname surname of customer
     * @return Customer reference if it exists, null otherwise
     */
    public Customer getCustomerByName( String name, String surname ){
        return customers.get( customers.indexOf(new Customer(name, surname, shipments)) );
    }

    public String getSystem_name() {
        return system_name;
    }

    public ArrayList<TransportationPersonnel> getTransportation_personnel() {
        return transportation_personnel;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    /**
     * implementing print method of PrintableInConsodeInterface
     */
    public String show(){
        return this.toString();
    }
    /**
     * implementing printDetailed method of PrintableInConsodeInterface
     */
    public String showDetailed(){
        String to_return = "";
        ArrayList<User> users = new ArrayList<User>( administrators.size() + transportation_personnel.size() + customers.size());
        to_return += show() + "\n| Class name: " + getClass().getSimpleName();
        to_return += "\nUsers: \n";

        for( int i = 0; i<administrators.size(); ++i)
            users.add( administrators.get(i) );

        for( int i = 0; i<customers.size(); ++i)
            users.add( customers.get(i) );

        for( int i = 0; i<transportation_personnel.size(); ++i)
            users.add( transportation_personnel.get(i) );

        for( int i = 0; i<users.size(); ++i)
            to_return += users.get(i).show()+"\n";

        to_return += "\nBranches of this system: \n";
        for( int i = 0; i<branches.size(); ++i ){
            to_return += branches.get(i).showDetailed() + "\n";
        }
        return to_return;
    }
    /**
     * implementing printDebuggingMode method of PrintableInConsodeInterface
     */
    public String showDebuggingMode(){
        String to_return = "";
        to_return += show() + "| Class name: " + getClass().getSimpleName() + "\n";

        to_return += "Users: ";
        for( int i = 0; i<administrators.size(); ++i){
            to_return += administrators.get(i).showDebuggingMode();
        }
        for( int i = 0; i<customers.size(); ++i){
            to_return += customers.get(i).showDebuggingMode();
        }
        for( int i = 0; i<transportation_personnel.size(); ++i){
            to_return += transportation_personnel.get(i).showDebuggingMode() + "\n";
        }
        to_return += "Branches of this system: ";
        for( int i = 0; i<branches.size(); ++i ){
            to_return += branches.get(i).showDebuggingMode();
        }
        return to_return;
    }
}
