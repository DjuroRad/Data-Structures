package gtu.automation_system_for_cargo_company;

/**
 * The AutomationSystemForCargoCompany program is used to help cargo companies
 * to automate and organize their system. They can create as many of these systems as needed.
 * Each of them will contain users such as administrators, branch employees, transporatation personnel and customers.
 *
 * @author Djuro Radusinovic 171044095
 * @version 1.0
 * @since 2020-02-21
 */
public class AutomationSystemForCargoCompany {

    private String system_name;
    private DynamicArray <Administrator> administrators = new DynamicArray<Administrator>();
    private DynamicArray <Branch> branches = new DynamicArray<Branch>();
    private DynamicArray <Shipment> shipments = new DynamicArray<Shipment>();//holds all the shipments in the system
    private DynamicArray <TransportationPersonnel> transportation_personnel = new DynamicArray<TransportationPersonnel>();
    private DynamicArray<Customer> customers = new DynamicArray<Customer>();// I've put it here since they were part of the branch beforehand, but in the homework it is said that branch employees add customers to the system, NOT THE BRANCH

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
     * @return type of DynamicArray since administrators are stored in the array that is manipulated so that is dynamic
     */
    public DynamicArray<Administrator> getAdministrators() {
        return administrators;
    }

    /**
     * getter for branches of the system to be able to access employees of the branch and the shipments of the each branch
     * @return type of DynamicArray since branches are stored in the array that is manipulated so that is dynamic
     */
    public DynamicArray<Branch> getBranches() {
        return branches;
    }

    /**
     * getter for shipements of the system to be able to access shpipments
     * @return type of DynamicArray since shipments are stored in the array that is manipulated so that is dynamic
     */
    public DynamicArray<Shipment> getShipments() {
        return shipments;
    }

    /**
     * getter for Transportation Personnel of the system to be able to access employees of the branch and the shipments of the each branch
     * @return type of DynamicArray since branches are stored in the array that is manipulated so that is dynamic
     */
    public DynamicArray<TransportationPersonnel> getTransportationPersonnel() {
        return transportation_personnel;
    }

    /**
     * enum status is used to indicate the status of the shipment
     * status can be: -Left the branch-, -Arrived to branch - or -Delivered-
     */
    private enum Status{ LEFT_THE_BRANCH, ARRIVED_TO_BRANCH, DELIVERED }

    /**
     * UserType indicated the type of the user.
     * Administrator, Branch Employee, Transoprtation Personnel and Customer
     * all extend for the class user and have their type
     * according to their position in the system
     */
    private enum UserType{ UNDEFINED_USER, ADMINISTRATOR, BRANCH_EMPLOYEE, TRANSPORTATION_PERSONNEL, CUSTOMER}

    /**
     * Whoever is in control of the system is able to add adminstrators to it
     * System's user interface cannot be reached before the administrator is added
     * since it is the one who is able to control a certain system's users
     * @param name - Name of the administrator to be added
     * @param surname - Surname of the administrator to be added
     * @return true if the admin is added and false if it is not added( The same administrator cannot be added twice
     */
    public boolean addAdministrator( String name, String surname ){
           return administrators.insert( new Administrator(name,surname) );
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
     * Another way to remove an administrator is by its name and surname
     * @param name - Admin's name
     * @param surname - Admin's surname
     * @return returns true if administrator is successfully remove and false otherwise
     */
    public boolean removeAdministrator( String name, String surname ){
        return removeAdministrator( new Administrator(name, surname) );
    }

    /**
     * User class is used for implementing basic functions of every user
     * such as setting name, getting name, and their types
     * It is private withing this system
     */
    private class User{
        private UserType type;
        private String name;
        private String surname;

        /**
         * No parameter constructor for user
         * it give default name as NAME_UNDEFINED, surname as SURNAME_UNDEFINED, type as UNDEFINED_USER
         */
        public User(){
            this( "NAME_UNDEFINED", "SURNAME_UNDEFINED", UserType.UNDEFINED_USER );
        }

        /**
         * Three parameter constructor for user
         * @param name - name of the user
         * @param surname - surname of the user
         * @param type - type of the user
         */
        public User( String name, String surname, UserType type ){
            this.name = name;
            this.surname = surname;
            this.type = type;
        }

        /**
         * gets the name of the user
         * @return String type
         */
        public String getName(){
            return name;
        }

        /**
         * gets the surname of the user
         * @return String type
         */
        public String getSurname(){
            return surname;
        }

        /**
         * gets the type of the user
         * @return Usertype type
         */
        public UserType getType(){ return type; }

        /**
         * Sets the name of the user
         * @param name type String
         */
        public void setName( String name ){
            this.name = name;
        }

        /**
         * Sets the surname of the user
         * @param surname type String
         */
        public void setSurname( String surname ){
            this.surname = surname;
        }

        /**
         * Sets the type of the user
         * @param type UserType enum
         */
        public void setUserType( UserType type ){
            this.type = type;
        }

        /**
         * Used to check if two instaces of User are the same
         * They are the same if their name, surname and type are the same
         * @param o will be casted to User type
         * @return boolean, true if their name, surname and type are the same, false otherwise
         */
        @Override
        public boolean equals( Object o ){
            if( o == this )
                return true;
            if( !(o instanceof User ) )
                return false;
            User o_user = (User)o;
            return ( o_user.getName().equals(this.name)
                    && o_user.getSurname().equals(this.surname)
                    && o_user.getType().equals(this.type) );
        }

        /**
         * Used to print user's info
         * if format of Name|Surname|Type
         * @return type String
         */
        @Override
        public String toString() {
            String to_return = "";
            to_return += "Name: " + name + "| Surname: " + surname + "| Type: ";
            switch (type){
                case CUSTOMER:
                    to_return += "Customer";
                    break;
                case ADMINISTRATOR:
                    to_return += "Administrator";
                    break;
                case BRANCH_EMPLOYEE:
                    to_return += "Branch Employee";
                    break;
                case TRANSPORTATION_PERSONNEL:
                    to_return += "Transportation Personnel";
                    break;
            }
            return to_return;
        }
    }//User class ends here

    /**
     * Administrator is a class the extends the user class
     * and is used to manipulate the system
     * by adding branches, branch employees and transporatation personel
     */
    public class Administrator extends User{
        /**
         * No paramter constructor wiill assign
         * name as UNDEFINED_NAME_ADMIN and surname as UNDEFINED_SURNAME_ADMIN
         */
        public Administrator(){
            this( "UNDEFINED_NAME_ADMIN", "UNDEFINED_SURNAME_ADMIN" );
        }

        /**
         * Two paramter consturctor that calls the three paramter consturctor of the User with enum type of ADMINISTRATOR
         * @param name - name of admin
         * @param surname - surname of admin
         */
        public Administrator( String name, String surname ){
            super( name, surname, UserType.ADMINISTRATOR );
        }

        /**
         * Administrators are able to add branches to the system
         * @param name - name of the branch, type is String
         * @return true if added, false if the system already exists
         */
        public boolean addBranch( String name ){
                return branches.insert( new Branch(name) );
        }

        /**
         * Remove a branch from the system
         * @param name - name of the branch to be removed
         * @return true if branch exists, false otherwise
         */
        public boolean removeBranch( String name ){
            return branches.remove( new Branch( name ) );
        }

        /**
         * Administrator is able to add a branch employee to branches of the system
         * @param branch_name - name of the branch employee will be added to - type String
         * @param employee_name - name of the employee to add - type String
         * @param employee_surname - surname of the employee to be added - type String
         * @return true if branch exists
         */
        public boolean addBranchEmployee( String branch_name, String employee_name, String employee_surname ){
            int branch_index = BranchExists(branch_name);
            if( branch_index != -1 ) {
                return (branches.get(branch_index).addBranchemployee( employee_name, employee_surname ) );
            }
            else
                return false;
        }

        /**
         * Branch employees can be removed thought administrator
         * @param branch_name - type is String
         * @param employee_name - type is String
         * @param employee_surname - type is String
         * @return true if the branch exists and an employee with name and surname given exists in that branch
         */
        public boolean removeBranchEmployee( String branch_name, String employee_name, String employee_surname ){
            int branch_index = BranchExists(branch_name);
            if( branch_index != -1 ){
//                return branches.get(branch_index).getBranchEmployees().remove( new Branch.BranchEmployee( employee_name, employee_surname ));
                return (branches.get(branch_index).removeBranchemployee( employee_name, employee_surname ) );
            }
            else
                return false;
        }

        /**
         * Administrator can add Transportation Personnel to the system
         * @param name name of the Transportationn Personnel
         * @param surname surnname of the Transportationn Personnel
         * @return return false if the personnel already exists in the system, false otherwise
         */
        public boolean addTransportationPersonnel( String name, String surname ){
            return transportation_personnel.insert( new TransportationPersonnel(name, surname) );
        }

        /**
         * Transportation Personnel can also be removed though administrator
         * @param name name of the personnel to be removed
         * @param surname surname of the personnel to be removed
         * @return returns true if the perosnnel is removed( if it existed in the system it will be removed ), false otherwise
         */
        public boolean removeTransportationPersonnel( String name, String surname ){
            return transportation_personnel.remove( new TransportationPersonnel(name, surname) );
        }

        /**
         * Transportation Personnel can also be removed though administrator by providing index of the personnel to be removed
         * @param i - index of the array where the personnel is
         * @return will return false if ined provided is out of bounds
         */
        public boolean removeTransportationPersonnel( int i ){
            if( i >= 0 && i<transportation_personnel.Size() )
                return transportation_personnel.remove( i );
            else
                return false;
        }

        /**
         * Checks if a branch exists in the system
         * It is private withing the system
         * @param branch_name - name of the branch
         * @return return -1 if it does not exist and the index of where the branch is in the array otherwise
         */
        private int BranchExists( String branch_name ){
            int index = -1;
            for( int i = 0; i<branches.Size() && (index == -1); ++i )
                if( branches.get(i).getBranchName().equals(branch_name) )
                    index = i;

            return index;
        }

        /**
         * getter for transporatation personnel is also present here since administrator is able to manipulate
         * the transportation array. This way is can use it to see the number of the personnel in the system
         * or print them to the screen and similar
         * @return DyanmicArray in which personnel is stored
         */
        public DynamicArray<TransportationPersonnel> getTransportationPersonnel(){//exists in here also because admin is also in charge of the personnel
            return transportation_personnel;
        }
    }//Administrator class ends here

    /**
     * Transportation Personnel is used to mark a certain
     * shipment as delivered
     */
    public class TransportationPersonnel extends User{
        /**
         * No parameter constructor will set
         * name as UNDEFINED_NAME_TRANSPORTATION_PERSONNEL and surname as UNDEFINED_SURNAME_TRANSPORTATION_PERSONNEL
         */
        public TransportationPersonnel(){
            this( "UNDEFINED_NAME_TRANSPORTATION_PERSONNEL", "UNDEFINED_SURNAME_TRANSPORTATION_PERSONNEL");
        }

        /**
         * Two paramter constructor calls user's constructor and
         * sets the type to TRANSPORTATION_PERSONNEL
         * @param name - name of the personnel
         * @param surname - surname of the personnel
         */
        public TransportationPersonnel( String name, String surname ){
            super( name, surname, UserType.TRANSPORTATION_PERSONNEL );
        }

        /**
         * Method used to update the shipment's status to delivered
         * @param tracking_number - String type
         * @return - return true if the shipment with such tracking number exists, false otherwise
         */
        public boolean updateShipmentStatusToDelivered( String tracking_number ){
            if( getShipmentByTrackingNumber(tracking_number) != null ) {
                getShipmentByTrackingNumber(tracking_number).setStatus(Status.DELIVERED);
                return true;
            }
            else
                return false;
        }

        /**
         * Method used to update the shipment's status to delivered
         * @param i - int type
         * @return - returns true of i is not out of bounds, false otherwise
         */
        public boolean updateShipmentToDelivered( int i ){
            if( i >= 0 && i <shipments.Size() ) {
                shipments.get(i).setStatus(Status.DELIVERED);
                return true;
            }
            else
                return false;
        }

        /**
         * This method is used to print shipments since personnel is able to manipulate their status
         */
        public void printShipments(){//Since transportation personnel is able to manipulate shipments, it should also be able to see them instead of only using the tracking number
            System.out.println(shipments);
        }

        /**
         * just a getter for the number of the shipments in the array of shipments
         * @return Int type
         */
        public int getShipmentsSize(){
            return shipments.Size();
        }
    }//Transportation personnel class ends here

    /**
     * Customer is see the sender and reciever of a certain shipment by entering its tracking number
     * it also extends from User
     */
    public class Customer extends User{
        /**
         * gets information of shipment by using its tracking number
         * @param tracking_number - unique set of character and/or number for every shipment
         * @return - returns String in format of ----Sender----Reciever----Tracking Number ----
         */
        public String getInfoOfShipment( String tracking_number ){
            Shipment shipment = getShipmentByTrackingNumber(tracking_number);
            if( shipment != null )
                return getShipmentByTrackingNumber(tracking_number).toString();
            else
                return "Tracking number not valid";

        }

        /**
         * Two paramter consturctor, sets type to CUSTOMER
         * @param name - name of the Customer
         * @param surname - surname of the Customer
         */
        public Customer( String name, String surname ){
            super( name, surname, UserType.CUSTOMER );
        }
    }

    /**
     * Branch stores its Employees that work in that class and Shipments from that branch
     */
    public class Branch{
        private DynamicArray<BranchEmployee> branch_employees = new DynamicArray<BranchEmployee>();
        private DynamicArray<Shipment> shipments_branch = new DynamicArray<Shipment>();
        private String branch_name;

        /**
         * No param consturctor, sets name of the branch to UNDEFINED_NAME_BRANCH
         */
        public Branch(){
            this( "UNDEFINED_NAME_BRANCH" );
        }

        /**
         * One paramter consturctor
         * @param branch_name - name of the Branch
         */
        public Branch( String branch_name ){
            this.branch_name = branch_name;
        }

        /**
         * Returns the name of the branch
         * @return String type
         */
        public String getBranchName(){
            return this.branch_name;
        }

        /**
         * Returns array of employees working in that branch
         * @return type DyanmicArray
         */
        public DynamicArray<BranchEmployee> getBranchEmployees(){
            return this.branch_employees;
        }

        /**
         * returns the shipments that are made in that branch
         * @return returns the shipments array of that branch
         */
        public DynamicArray<Shipment> getShipmentsBranch(){
            return this.shipments_branch;
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
            return branch_employees.insert( branch_employee );
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
            return branch_employees.elementExists( new BranchEmployee( name, surname ));
        }

        /**
         * gets a branch employee with its name and surname
         * @param name - name of employee
         * @param surname - surname of employe
         * @return - type is BranchEmployee, returns null if not found
         */
        public BranchEmployee getBranchEmployeeByName( String name, String surname ){//returns the branch employee if it exists by its name and surname
            return branch_employees.getElement( new BranchEmployee(name, surname) );
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
                Customer sender = new Customer( name_sender, surname_sender );
                Customer reciever = new Customer( name_reciever, surname_reciever );
                if( customers.elementExists(sender) && customers.elementExists(reciever) ) {
                    Shipment shipment = new Shipment( sender, reciever, tracking_number, getBranchName() );
                    if( shipments.insert(shipment) )
                        return shipments_branch.insert(shipment);
                    else
                        return false;
                }
                else
                    return false;
            }

            /**
             * Branch Employee is manipulating the shipments of the branch so the getter of its array
             * is provided for more flexibility
             * @return array in which shipments of that branch are stored
             */
            public DynamicArray<Shipment> getShipmentsBranch(){//can get shipments through Branch Employee since
                return shipments_branch;
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
                if( i >= 0 && i < shipments_branch.Size() )
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
             * adds a customer to the system using its name and surname
             * @param name - name of the customer
             * @param surname - surname of the customer
             * @return true if the customer is added, false otherwise
             */
            public boolean addCustomer( String name, String surname ){
                return customers.insert( new Customer( name, surname ) );
            }

            /**
             * remove the Customer from the system though branch employee
             * @param name - name of the customer
             * @param surname - surname of the customer
             * @return true if customer exists, false otherwise
             */
            public boolean removeCustomer( String name, String surname ){
                return customers.remove( new Customer(name, surname) );
            }

            /**
             * Removes customer but using the index of the place in the array it is stored in
             * @param i - index of the array
             * @return - true if removed false otherwise
             */
            public boolean removeCustomer( int i ){
                return customers.remove(i);
            }

            /**
             * used to print customer of the system branch employee is able to manipulate
             */
            public void printCustomers(){
                System.out.println(customers);
            }

            /**
             * Size of the array customers are in
             * @return int, size of the array
             */
            public int getCustomersSize(){
                return customers.Size();
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
    }

    /**
     * Class shipment is used to store information of shipment
     */
    private class Shipment{
        private Customer sender;
        private Customer reciever;
        private String tracking_number;
        private Status status;
        private String branch_name;

        /**
         * 4 param constructor
         * @param sender - type Customer
         * @param reciever - type Customer
         * @param tracking_number - type String
         * @param branch_name - type String
         */
        public Shipment( Customer sender, Customer reciever, String tracking_number, String branch_name ) {
            this.sender = sender;
            this.reciever = reciever;
            this.tracking_number = tracking_number;
            this.branch_name = branch_name;
            status = Status.ARRIVED_TO_BRANCH;
        }

        /**
         * Returns if the shipment is delivered
         * @return booelan type
         */
        public boolean isDelivered(){
            if( status == Status.DELIVERED )
                return true;
            else
                return false;
        }

        /**
         * Returns the sender of the shipment
         * @return Customer type
         */
        public Customer getSender() {
            return sender;
        }

        /**
         * Sets the sender of the shipment
         * @param sender Customer type
         */
        public void setSender(Customer sender) {
            this.sender = sender;
        }

        /**
         * returns the Customer that is to recieve the shipment
         * @return Customer type
         */
        public Customer getReciever() {
            return reciever;
        }

        /**
         * sets the Reciever of the shipment
         * @param reciever Customer
         */
        public void setReciever(Customer reciever) {
            this.reciever = reciever;
        }

        /**
         * returns the Tracking Number of the shipment
         * @return String
         */
        public String getTrackingNumber() {
            return tracking_number;
        }

        /**
         * Sets tracking number of the shipment
         * @param tracking_number - String
         */
        public void setTrackingNumber(String tracking_number) {
            this.tracking_number = tracking_number;
        }

        /**
         * return Status of the shipment
         * @return Status type
         */
        public Status getStatus() {
            return status;
        }

        /**
         * sets the status of the shipment
         * @param status Status type
         */
        public void setStatus(Status status) {
            this.status = status;
        }

        /**
         * Two shipments are equal is their tracking numbers are equal
         * @param o casted to Shipment
         * @return true if tracking number is the same, false otherwise
         */
        @Override
        public boolean equals( Object o ){//shipments are equals to each other if and only if their tracking number is the same
            if( this == o )
                return true;
            if( !(o instanceof Shipment) )
                return false;
            Shipment o_shipment = (Shipment)o;
            return o_shipment.getTrackingNumber().equals(this.tracking_number);
        }

        /**
         * returns the branch name
         * @return String type
         */
        public String getBranchName() {
            return branch_name;
        }

        /**
         * Returns the Info about the shipment
         * @return ----Sender----Reciever----Tracking NUmber----Status ----
         */
        @Override
        public String toString() {
            String status = "";
            switch (this.status){
                case DELIVERED:
                    status = "Delivered";
                    break;
                case ARRIVED_TO_BRANCH:
                    status = "Arrived to branch";
                    break;
                case LEFT_THE_BRANCH:
                    status = "Left the branch";
                    break;
            }
            return "---- Sender: " + sender +
                    "---- Reciever: " + reciever +
                    "---- Tracking number: " + tracking_number +
                    "---- Status: " + status + " ----";
        }
    }

    /**
     * Returns the shipment by its tracking number
     * @param tracking_number type String
     * @return Shipment with corresponding tracking number
     */
    private Shipment getShipmentByTrackingNumber( String tracking_number ){
        for( int i = 0; i<shipments.Size(); ++i )
            if( shipments.get(i).getTrackingNumber().equals(tracking_number) )
                return shipments.get(i);

        return null;
    }

    /**
     * Returns the admnistrator by its name and surname
     * @param name - name of the admin
     * @param surname surname fo the admin
     * @return Administrator type, null if it doesn't exist
     */
    public AutomationSystemForCargoCompany.Administrator getAdministratorByName( String name, String surname ){
        return administrators.getElement( new Administrator(name,surname) );
    }

    /**
     * TransporatatinPersonnel returned
     * @param name name of Personnel
     * @param surname surname of Personnel
     * @return Personnel reference if exists, false otherwise
     */
    public TransportationPersonnel getTPersonnelByName( String name, String surname ){
        return transportation_personnel.getElement( new TransportationPersonnel(name, surname ));
    }

    /**
     * Customer to get by its name and surname
     * @param name name of customer
     * @param surname surname of customer
     * @return Customer reference if it exists, null otherwise
     */
    public Customer getCustomerByName( String name, String surname ){
        return customers.getElement( new Customer(name, surname) );
    }

}
