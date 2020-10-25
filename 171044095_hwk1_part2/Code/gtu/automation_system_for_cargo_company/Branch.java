package gtu.automation_system_for_cargo_company;

import java.security.InvalidParameterException;
import java.util.ArrayList;


/**
 * Branch stores its Employees that work in that class and Shipments from that branch
 */
public class Branch implements PrintableInConsole{
    private ArrayList<BranchEmployee> branch_employees = new ArrayList<BranchEmployee>();
    private ArrayList<Customer> customers;
    private ArrayList<Shipment> shipments_branch = new ArrayList<Shipment>();
    private ArrayList<Shipment> shipments;
    private String branch_name;

    /**
     * No param consturctor, sets name of the branch to UNDEFINED_NAME_BRANCH
     */
    public Branch( ArrayList<Shipment> shipments, ArrayList<Customer> customers ){
        this( "UNDEFINED_NAME_BRANCH", shipments, customers );
    }

    /**
     * One paramter consturctor
     * @param branch_name - name of the Branch
     */
    public Branch( String branch_name, ArrayList<Shipment> shipments, ArrayList<Customer> customers ){
        this.branch_name = branch_name;
        this.shipments = shipments;
        this.customers = customers;
    }

    /**
     * Returns the name of the branch
     * @return String type
     */
    public String getBranchName(){
        return this.branch_name;
    }

    /**
     * getter of the name of employee at an index
     * @param i index of the employee
     * @return returns the String of employee's name
     */
    public String getBranchEmployeeNameByI( int i ){
        if( i >= 0 && i <branch_employees.size() )
            return branch_employees.get(i).getName();
        else
            throw new InvalidParameterException("Index out of bounds");
    }
    /**
     * getter of the surname of employee at an index
     * @param i index of the employee
     * @return returns the String of employee's surname
     */

    public String getBranchEmployeeSurnameByI( int i ){
        if( i>=0 && i<branch_employees.size() )
            return branch_employees.get(i).getSurname();
        else
            throw new InvalidParameterException("Index out of bounds");
    }
    /**
     * Getter for the number of employees in the branch
     * @return number of employees in the branch, int
     */
    public int getBranchEmployeesSize(){
        return branch_employees.size();
    }

    /**
     * Nicely formatted string of all employee working in that branch
     * @return String
     */
    public String getBranchEmployeesString(){
        String to_return = "";
        for( int i = 0; i<branch_employees.size(); ++i )
            to_return += i+1 + ") " + branch_employees.get(i).show() + "\n";
        return to_return;
    }

    /**
     * Whoever can access the branch(Administrator or the head of the system)
     * can add a branch employee to it
     * @param name - name of the Branch employee to be added
     * @param surname - surname of the Branch employee to be added
     * @return true if added, false if already exists in the system
     */
    public boolean addBranchemployee( String name, String surname ){
        BranchEmployee branch_employee = new BranchEmployee( name, surname );
        if( !branch_employees.contains( branch_employee ) )
            return branch_employees.add( branch_employee );
        else
            throw new InvalidParameterException("Branch employee already exists in this branch");
    }

    /**
     * Whoever can access the branch(Administrator or the head of the system)
     * can remove a branch employee from it
     * @param name - name of branch employee to remove
     * @param surname - surname of branch employee to remove
     * @return true if removed, false if not found
     */
    public boolean removeBranchemployee( String name, String surname ){
        return branch_employees.remove( new BranchEmployee( name, surname ) );
    }

    /**
     * returns if there is a branch employee with ceratin name and surname in the branch
     * @param name name of the branch employee
     * @param surname surname of the branch employee
     * @return true if employee is in the branch, false otherwise
     */
    public boolean branchEmployeeExists( String name, String surname ){
        return branch_employees.contains( new BranchEmployee( name, surname ));
    }

    /**
     * gets a branch employee with its name and surname
     * @param name - name of employee
     * @param surname - surname of employe
     * @return - type is BranchEmployee, returns null if not found
     */
    public BranchEmployee getBranchEmployeeByName( String name, String surname ){//returns the branch employee if it exists by its name and surname
        return branch_employees.get( branch_employees.indexOf(new BranchEmployee(name, surname)) );
    }

    /**
     * Branch Employee can add and remove Customers to the system
     * Add and remove shipments for a branch, update status of the Shipment sent from that branch
     * It is an inner class of Branch since it is only responsible for functionality of a branch, would not exist without a branch
     */
    public class BranchEmployee extends User{
        /**
         * no parameter constructor,sets
         * name as UNDEFINED_NAME_BRANCH_EMPLOYEE, sets surname as UNDEFINED_SURNAME_BRANCH_EMPLOYEE
         */
        public BranchEmployee(){
            this( "UNDEFINED_NAME_BRANCH_EMPLOYEE", "UNDEFINED_SURNAME_BRANCH_EMPLOYEE" );
        }

        /**
         * Two parameter constructor, sets type to BRANCH_EMPLOYEE
         * @param name - name of the branch employee
         * @param surname - surname of the branch employee
         */
        public BranchEmployee( String name, String surname ){
            super( name, surname, UserType.BRANCH_EMPLOYEE );
        }

        /**
         * Through this method, info of the shipment can be updated
         * @param name_sender String
         * @param surname_sender String
         * @param name_reciever String
         * @param surname_reciever String
         * @param tracking_number String
         * @return true if tracking_number is unique, false otherwise( also sender and reciever need to be in the system, otherwise will return false )
         */
        public boolean addInfoAboutShipment( String name_sender, String surname_sender, String name_reciever, String surname_reciever, String tracking_number ){
            Customer sender = new Customer( name_sender, surname_sender,shipments );
            Customer reciever = new Customer( name_reciever, surname_reciever, shipments );
            if( customers.contains(sender) && customers.contains(reciever) ) {
                Shipment shipment = new Shipment( sender, reciever, tracking_number, getBranchName() );
                if( !shipments.contains(shipment) )
                    return shipments_branch.add(shipment) && shipments.add(shipment);
                else
                    throw new InvalidParameterException("Shipment already in the system");
            }
            else
                throw new InvalidParameterException("Either sender or reciever is not in the system");
        }

        /**
         * Used to remove the shipment
         * @param shipment Shipment to remove
         * @return ture if removed, false otherwise
         */
        public boolean removeInfoAboutShipment( Shipment shipment ){
            return removeInfoAboutShipment( shipment.getTrackingNumber() );
        }

        /**
         * Removes shipment by probidin index to it
         * @param i index of the shipment
         * @return true if the shipment is removed and false otherwise
         */
        public boolean removeInfoAboutShipment( int i ){
            if( i >= 0 && i < shipments_branch.size() )
                return removeInfoAboutShipment( shipments_branch.get(i).getTrackingNumber() );
            else
                return false;
        }

        /**
         * Every other removeShipment function is calling this one in the end
         * @param tracking_number - tracking number of the shipment to be removed
         * @return true if the branch is in charge of that shipment and it exists, false otherwise
         */
        public boolean removeInfoAboutShipment( String tracking_number ) {
            Shipment shipment = getShipmentByTrackingNumber(tracking_number);
            if (shipment.getBranchName().equals(getBranchName())){
                shipments.remove(shipment);
                shipments_branch.remove(shipment);
                return true;
            }
            else
                return false;
        }

        /**
         * Used to get the string of all the shipments branch employee is able to manipulate
         * @return nicely formatted string of all the shipments in the branch employee works in
         */
        public String getShipmentsString(){
            String to_remove = "";
            for( int i = 0; i<shipments_branch.size(); ++i )
                to_remove += i+1 + ") " + shipments_branch.get(i).show() + "\n";
            return to_remove;
        }

        /**
         * Customer is allowed to see the number of shipements sent from that branch
         * @return Number of shipments from that branch
         */
        public int getShipmentsSize(){
            return shipments_branch.size();
        }
        /**
         * Used to show the customers since the branch employee is able to manipulate them
         * @return nicely formated string of all the customers in the system
         */
        public String getCustomersString(){
            String to_return = "";
            for( int i = 0; i<customers.size(); ++i )
                to_return += i+1+") " + customers.get(i).show() + "\n";
            return to_return;
        }

        /**
         * Branch employee is allowed to see the number of customers in the system
         * @return Number of customers
         */
        public int getCustomersSize(){
            return customers.size();
        }
        /**
         * adds a customer to the system using its name and surname
         * @param name - name of the customer
         * @param surname - surname of the customer
         * @return true if the customer is added, false otherwise
         */
        public boolean addCustomer( String name, String surname ){
            if( !customers.contains( new Customer(name, surname, shipments)) )
                return customers.add( new Customer( name, surname, shipments ) );
            else
                throw new InvalidParameterException("Customer already in the system");
        }

        /**
         * remove the Customer from the system though branch employee
         * @param name - name of the customer
         * @param surname - surname of the customer
         * @return true if customer exists, false otherwise
         */
        public boolean removeCustomer( String name, String surname ){
            if( customers.contains( new Customer(name, surname, shipments) ) )
                return customers.remove( new Customer(name, surname, shipments) );
            else
                return false;
        }

        /**
         * Removes customer but using the index of the place in the array it is stored in
         * @param i - index of the array
         * @return - true if removed false otherwise
         */
        public boolean removeCustomer( int i ){
            if( customers.remove(i) != null )
                return true;

            return false;
        }
        /**
         * Sets the status of the shipment to arrived by providing index of the array
         * @param i - index of the shpment whose status is to be changed
         */
        public void setStatusArrived( int i ){
            shipments_branch.get(i).setStatus(Status.ARRIVED_TO_BRANCH);
        }

        /**
         * Sets the status of the shipment to left the branch by providing index of the array
         * @param i - index of the place it is stored in the array
         */
        public void setStatusLeft( int i ){
            shipments_branch.get(i).setStatus( Status.LEFT_THE_BRANCH );
        }
        /**
         * implementing print method of PrintableInConsodeInterface
         */
        @Override
        public String show(){
            return this.toString();
        }
        /**
         * implementing printDetailed method of PrintableInConsodeInterface
         */
        @Override
        public String showDetailed(){
            return (this.toString() + "| Class name: " + getClass().getSimpleName() + "| Parent class name: " + getClass().getSuperclass().getSimpleName() );
        }
        /**
         *implementing showDebugging method of PrintableInConsode Interface
         */
        @Override
        public String showDebuggingMode(){
            String to_return = "";
            to_return += this.toString();
            to_return += " Class name: " + getClass().getName() + "\n Parent Class name: " + getClass().getSuperclass().getName();
            to_return += "Hash code: " + hashCode();

            return to_return;
        }
    }

    /**
     * returns the name of the branch
     * @return String Branch_Name (branch)
     */
    @Override
    public String toString() {
        return branch_name + " (branch)";
    }

    /**
     * Two branches are equal if their names are equal
     * @param obj Branch instance, castted to Branch type
     * @return true if names are the same
     */
    @Override
    public boolean equals(Object obj) {
        if( this == obj )
            return true;
        if( !(obj instanceof Branch) )
            return false;
        return branch_name.equals( ((Branch)obj).getBranchName() );
    }

    /**
     * gives shipment by its tracking number
     * @param tracking_number string
     * @return shipment
     */
    private Shipment getShipmentByTrackingNumber( String tracking_number ){
        for( int i = 0; i<shipments.size(); ++i )
            if( shipments.get(i).getTrackingNumber().equals(tracking_number) )
                return shipments.get(i);

        return null;
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
        to_return += (show() + "| Class name: \n" + getClass().getSimpleName());
        to_return += ("Branch Employees of this branch: \n");
        for( int i = 0; i<branch_employees.size(); ++i ){
            to_return += branch_employees.get(i).showDetailed() + "\n";
        }
        to_return += ("Shipments of this branch:\n");
        for( int i = 0; i<shipments_branch.size(); ++i)
           to_return += shipments_branch.get(i).showDetailed() + "\n";
        return to_return;
    }
    /**
     * implementing printDebuggingMode method of PrintableInConsodeInterface
     */
    public String showDebuggingMode() {
        String to_return = "";
        to_return += show() + "| Class name: " + getClass().getName() + "| Superclass name: " + getClass().getSuperclass().getName();
        to_return += ("Branch Employees of this branch: ");
        for (int i = 0; i < branch_employees.size(); ++i) {
            to_return += branch_employees.get(i).showDebuggingMode();
        }
        to_return += ("Shipments of this branch");
        for (int i = 0; i < shipments_branch.size(); ++i)
            to_return += shipments_branch.get(i).showDebuggingMode();
        return to_return;
    }
}