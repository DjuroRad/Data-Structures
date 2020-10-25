import gtu.automation_system_for_cargo_company.AutomationSystemForCargoCompany;
import gtu.automation_system_for_cargo_company.Administrator;
import gtu.automation_system_for_cargo_company.Customer;
import gtu.automation_system_for_cargo_company.Branch;
import gtu.automation_system_for_cargo_company.TransportationPersonnel;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This file is only used for making menus so that the program written
 * int the AutomationSystemForCargoCompany could be tested
 * It is not part of the package
 * but just a tool used for testing
 */
public class Test {
    String error = "Please enter a valid option";
    public void welcomeMenu(){
        System.out.println("Welcome to our Automation system for Cargo Companies");
        System.out.println("1) Make a system");
        System.out.println("2) Choose a system");
        System.out.println("3) Remove a system");
        System.out.println("4) Show system detailed");
        System.out.println("5) Exit");
    }
    public void systemCreationMenu(){
        System.out.println("1) Enter the name of system to add");
        System.out.println("2) Go back");
    }
    public void systemCreationMenuUserInput( ArrayList<AutomationSystemForCargoCompany> systems ){
        int choice;
        boolean exit = false;
        while(!exit) {
            systemCreationMenu();
            choice = getUserChoice();
            switch (choice) {
                case 1:
                    String name;
                    System.out.println("Enter the name of your system");
                    name = getUserInput();
                    if( systems.contains(new AutomationSystemForCargoCompany(name)))
                        System.out.println("Such system already exists, please try again");
                    else
                        systems.add(new AutomationSystemForCargoCompany(name));
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println(error);
            }
        }
    }
    public void chooseSystemMenu( ArrayList<AutomationSystemForCargoCompany> systems ) {
        if (systems.size() != 0) {
            System.out.println("Welcome to system selection menu: ");
            for (int i = 0; i < systems.size(); ++i)
                System.out.println((i + 1) + ") " + systems.get(i).toString());
            System.out.println(systems.size() + 1 + ") Go back");
        }
    }
    public void chooseSystemUser( ArrayList<AutomationSystemForCargoCompany> systems){
        int choice;
        boolean exit = false;
        while (!exit){
            if( systems.size() == 0 ) {
                System.out.println("You need to first make a system in order to be able to access one");
                exit = true;
            }
            else {
                chooseSystemMenu(systems);
                choice = getUserChoice();
                if (choice > 0 && choice <= systems.size() && systems.size() > 0) {
                    getIntoTheSystemUser(systems.get(choice-1));
                } else if (choice == systems.size() + 1)
                    exit = true;
                else
                    System.out.println(error);
            }
        }
    }
    public void getIntoTheSystemUser(AutomationSystemForCargoCompany system){
        boolean exit = false;
        int choice;
        while( !exit ){
            getIntoTheSystemMenu(system);
            choice = getUserChoice();
            switch (choice){
                case 1:
                    makeAdminUser(system);
                    break;
                case 2:
                    removeAdminUser(system);
                    break;
                case 3:
                    if( system.getAdministratorsSize()> 0 )
                        systemUserInterface(system);
                    else
                        System.out.println("System has no administrators, please make an administrator fist if you want to proceed");
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }
    public void systemUserInterface( AutomationSystemForCargoCompany system ){
        int choice;
        boolean exit = false;
        while( !exit ){
            systemUserInterfaceMenu(system);
            choice = getUserChoice();
            switch (choice){
                case 1:
                    AdministratorUserInterface(system);
                    break;
                case 2:
                    BranchEmployeeUserInterafce(system);
                    break;
                case 3:
                    TransportationPersonnelUserInterface(system);
                    break;
                case 4:
                    CustomerUserInterface(system);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println(error);
                    break;
            }
        }
    }
    private void CustomerUserInterface( AutomationSystemForCargoCompany system ){
        System.out.println("Enter your name");
        String name = getUserInput();
        System.out.println("Enter your surname");
        String surname= getUserInput();
        if( system.getCustomerByName(name,surname) != null )
            CustomerUserInterfaceMain(system.getCustomerByName(name,surname));
        else
            System.out.println("No such customer in the system");
    }
    private void CustomerUserInterfaceMain(Customer customer){
        boolean exit = false;
        while( !exit ) {

            System.out.println("1) Enter tracking number of the shipment you want to check out");
            System.out.println("2) Go back");
            int choice = getUserChoice();
            switch (choice){
                case 1:
                    System.out.println("Tracking Number: ");
                    System.out.println(customer.getInfoOfShipment(getUserInput()));
                    break;
                case 2:
                    exit = true;
                    break;
            }
        }
    }
    private void TransportationPersonnelUserInterface(AutomationSystemForCargoCompany system){
        String name, surname;
        System.out.println("Enter your name");
        name = getUserInput();
        System.out.println("Enter your surname");
        surname = getUserInput();
        if( system.getTPersonnelByName(name, surname) != null )
            TransportationPersonnelUserInterfaceMain(system.getTPersonnelByName(name,surname));
        else
            System.out.println("No transportation personnel with such name and surname exists");
    }
    private void TransportationPersonnelUserInterfaceMain( TransportationPersonnel tpersonnel ){
        boolean exit = false;
        while( !exit ){
            System.out.println("Welcome " + tpersonnel.getName() + " " + tpersonnel.getSurname());
            System.out.println("Which shipment do you want to set as delivered");
            System.out.println(tpersonnel.printShipments());
            System.out.println("0) Go back");
            int choice = getUserChoice();
            if( choice > 0 && choice <= tpersonnel.getShipmentssize() )
                tpersonnel.updateShipmentToDelivered(choice-1);
            else if( choice == 0 )
                exit = true;
            else
                System.out.println(error);
        }
    }
    private void BranchEmployeeUserInterafce(AutomationSystemForCargoCompany system){
        String name,surname, branch_name;
        int choice;
        do{
            System.out.println("Choose your branch");
            System.out.println(system.getBranchesString());
            System.out.println(system.getBranchesSize() + 1 + ") Go back");
            choice = getUserChoice();
            if( ! (choice > 0 && choice <= system.getBranchesSize() + 1 ) )
                System.out.println(error);
        }while( ! (choice > 0 && choice <= system.getBranchesSize() + 1 ) );
        if( choice != system.getBranchesSize() + 1 ){
            Branch branch = system.getBranchById(choice-1);
            System.out.println("Enter your name: ");
            name = getUserInput();
            System.out.println("Enter your surname: ");
            surname = getUserInput();
            if( branch.branchEmployeeExists(name,surname) ){
                Branch.BranchEmployee branch_employee = branch.getBranchEmployeeByName(name,surname);
                BranchEmployeeUserInterafceMain(branch_employee);
            }
            else
                System.out.println("Branch employee with such name and surname combination does not exist");

        }
        if( system.getBranchesSize() == 0 )
            System.out.println("No branch to select form");

    }
    private void BranchEmployeeUserInterafceMain(Branch.BranchEmployee branch_employee){
        boolean exit = false;
        while(!exit){
            System.out.println( branch_employee );
            System.out.println("What do you want to do?");
            System.out.println("1) Add shipment information");
            System.out.println("2) Remove shipment information");
            System.out.println("3) Update shipment status");
            System.out.println("4) Add Customer to the system");
            System.out.println("5) Remove Customer from the system");
            System.out.println("6) Go back");
            int choice = getUserChoice();
            switch ( choice ){
                case 1:
                    addShipment( branch_employee );
                    break;
                case 2:
                    removeShipment( branch_employee );
                    break;
                case 3:
                    updateShipmentStatus( branch_employee );
                    break;
                case 4:
                    addCustomer( branch_employee );
                    break;
                case 5:
                    removeCustomer( branch_employee );
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println(error);
            }
        }
    }
    private void addCustomer(Branch.BranchEmployee branch_employee ){
        String name,surname;
        System.out.println("Enter name");
        name = getUserInput();
        System.out.println("Enter surname");
        surname = getUserInput();
        try{
            branch_employee.addCustomer(name, surname);
        }
        catch (InvalidParameterException e ){
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
    private void removeCustomer(Branch.BranchEmployee branch_employee){
        int choice;
        if( branch_employee.getCustomersSize() > 0 ) {
            do {
                System.out.println(branch_employee.getCustomersString());
                System.out.println(branch_employee.getCustomersSize() + 1 + ") Go back");
                choice = getUserChoice();
                if (choice > 0 && choice <= branch_employee.getCustomersSize())
                    branch_employee.removeCustomer(choice-1);
                else if (choice != branch_employee.getCustomersSize() + 1)
                    System.out.println(error);
            } while (choice != branch_employee.getCustomersSize() + 1 && branch_employee.getCustomersSize() != 0);
        }
        else
            System.out.println("No Customers in the system");
    }
    private void updateShipmentStatus( Branch.BranchEmployee branch_employee ){

        boolean exit = false;
        while(!exit){
            System.out.println( branch_employee.getShipmentsString() );
            System.out.println( branch_employee.getShipmentsSize() + 1 + ") Go back");
            int choice = getUserChoice();
            if( choice > 0 && choice <= branch_employee.getShipmentsSize() ){
                System.out.println("1) Set arrived at the branch");
                System.out.println("2) Set left the branch");
                int choice_2 = getUserChoice();
                switch (choice_2){
                    case 1:
                        branch_employee.setStatusArrived(choice-1);
                        break;
                    case 2:
                        branch_employee.setStatusLeft(choice-1);
                        break;
                    default:
                        System.out.println(error);
                }
            }
            else if( choice == branch_employee.getShipmentsSize() + 1 )
                exit = true;
            else
                System.out.println(error);
        }
    }
    private void removeShipment(Branch.BranchEmployee branch_employee ){

        boolean exit = false;
        if( branch_employee.getShipmentsSize() > 0 ) {
            while (!exit) {
                System.out.println("Choose shipment to remove");
                System.out.println(branch_employee.getShipmentsString());
                System.out.println(branch_employee.getShipmentsSize()+ 1 + ") Go back");
                int choice = getUserChoice();
                if (choice > 0 && choice <= branch_employee.getShipmentsSize()) {
                    branch_employee.removeInfoAboutShipment(choice - 1);
                } else if (choice == branch_employee.getShipmentsSize() + 1)
                    exit = true;
                else
                    System.out.println(error);
            }
        }
        else
            System.out.println("There are no shipments in this branch");
    }
    private void addShipment(Branch.BranchEmployee branch_employee){
        String sender_name, sender_surname, reciever_name, reciever_surname, tracking_number;
        System.out.println("Enter sender name");
        sender_name = getUserInput();
        System.out.println("Enter sender surname");
        sender_surname = getUserInput();
        System.out.println("Enter reciever name");
        reciever_name = getUserInput();
        System.out.println("Enter reciever surname");
        reciever_surname = getUserInput();
        System.out.println("Enter a unique tracking number");
        tracking_number = getUserInput();
        try{
            branch_employee.addInfoAboutShipment( sender_name, sender_surname, reciever_name, reciever_surname, tracking_number );
        }
        catch (InvalidParameterException e ){
            System.out.println("Exceptoin caught: " + e.getMessage() );
        }
    }

    private void AdministratorUserInterface(AutomationSystemForCargoCompany system){
        System.out.println("Welcome to administrator user interface");
        boolean exit = false;
        int choice;
        while( !exit ){
            System.out.println("1) Login");
            System.out.println("2) Go Back");
            choice = getUserChoice();
            switch (choice){
                case 1:
                    String name,surname;
                    System.out.println("Enter your name");
                    name = getUserInput();
                    System.out.println("Enter your surname");
                    surname = getUserInput();
                    if( system.getAdministratorByName(name,surname)!=null )
                        AdministratorInterfaceMain( system.getAdministratorByName(name,surname) );
                    else
                        System.out.println("Name or Surname incorrect, please try again");
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println(error);
            }
        }
    }
    private void AdministratorInterfaceMain( Administrator admin ){
        boolean exit = false;
        int choice;
        while( !exit ){
            AdministratorInterfaceMainMenu(admin);
            choice = getUserChoice();
            switch (choice){
                case 1:
                    System.out.println("Enter new branch's name");
                    String branch_name = getUserInput();
                    try{
                        admin.addBranch(branch_name );
                    }
                    catch (InvalidParameterException e ){
                        System.out.println("Exception caught: " + e.getMessage() );
                    }
                    break;
                case 2:
                    removeBranch(admin);
                    break;
                case 3:
                    if( admin.getBranchesSize() != 0 ){
                        addBranchEmployee( admin );
                    }
                    else
                        System.out.println("To be able to add a branch employee you need to first make a branch");
                    break;
                case 4:
                    if( admin.getBranchesSize() > 0 ){
                        removeBranchEmployee( admin );
                    }
                    else
                        System.out.println("To be able to remove a branch employee you need to first make a branch");
                    break;
                case 5:
                    addTPersonnel( admin );
                    break;
                case 6:
                    removeTPersonnel( admin );
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println(error);
                    break;
            }
        }
    }
    private void addTPersonnel( Administrator admin ){
        String name, surname;
        System.out.println("Enter name: ");
        name = getUserInput();
        System.out.println("Enter surname");
        surname = getUserInput();
        try{
            if( admin.addTransportationPersonnel(name, surname) )
                System.out.println("Successfully added");
        }
        catch (InvalidParameterException e ){
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
    private void removeTPersonnel( Administrator admin ){
        boolean exit = false;
        while( !exit ){
            if( admin.getTPersonnelSize() > 0 ) {
                System.out.println("Welcome " + admin.getName() + " " + admin.getSurname());
                System.out.println("Select Personnel Employee you want to remove");

                System.out.println(admin.getTPersonnelString());

                System.out.println(admin.getTPersonnelSize() + 1 + ") Go back");
                int choice = getUserChoice();
                if( choice > 0 && choice <= admin.getTPersonnelSize() ) {
                    admin.removeTransportationPersonnel(choice - 1);
                    if( admin.getTPersonnelSize() == 0 ) {
                        System.out.println("No transportation personnel left");
                        exit = true;
                    }
                }
                else if( choice == admin.getTPersonnelSize() + 1 )
                    exit = true;
            }
            else {
                System.out.println("There is no personnel employees");
                exit = true;
            }
        }
    }
    private void removeBranchEmployee(Administrator admin){
        boolean exit = false;
        while( !exit ){
            int choice;
            System.out.println("Select a branch from which you want to remove an employee");
            System.out.println(admin.getBranchesString());
            System.out.println(admin.getBranchesSize()+1 + ") Go back");
            choice = getUserChoice();
            if( choice > 0 && choice <= admin.getBranchesSize() ) {
                if( admin.getBranchEmployeeSizeByIndex(choice-1) > 0 ){
                    removeBranchEmployee2( admin, choice-1 );
                    exit = true;
                }
                else
                    System.out.println("Branch does not contain any branch employees");
            }
            else if( choice == admin.getBranchesSize()+1 )
                exit = true;
        }
    }
    private void removeBranchEmployee2( Administrator admin, int branch_index ){
        boolean exit = false;
        int choice;
        while( !exit ) {
            System.out.println("Please choose which branch employee you want to remove");
            System.out.println(admin.getBranchEmployeesString(branch_index));
            System.out.println(admin.getBranchEmployeeSizeByIndex(branch_index) + 1 + ") Go back");
            choice = getUserChoice();
            if( choice > 0 && choice <= admin.getBranchEmployeeSizeByIndex(branch_index) ){
                admin.removeBranchEmployee( choice-1, branch_index);
                if( admin.getBranchEmployeeSizeByIndex(branch_index)==0) {
                    System.out.println("All branch employees removed");
                    exit = true;
                }
            }
            else if( choice == admin.getBranchEmployeeSizeByIndex(branch_index)+ 1 )
                exit = true;
            else
                System.out.println(error);
        }
    }
    private void addBranchEmployee( Administrator admin ){
        System.out.println("Select a branch to which you want to add an employee");
        System.out.println(admin.getBranchesString());
        System.out.println(admin.getBranchesSize()+1 + ") Go back");
        int choice;
        boolean exit = false;
        while( !exit ){
            choice = getUserChoice();
            if( choice > 0 && choice <= admin.getBranchesSize() ){
                String name, surname;
                System.out.println("Enter name of Branch Employee: ");
                name = getUserInput();
                System.out.println("Enter surname of Branch Employee: ");
                surname = getUserInput();

                try{
                    admin.addBranchEmployee(choice-1, name, surname);
                }
                catch ( InvalidParameterException e ){
                    System.out.println("Exception caught: " + e.getMessage());
                }
                exit = true;
            }
            else if( choice == admin.getBranchesSize()+1 )
                exit = true;

        }
    }
    private void removeBranch(Administrator admin){

        if( admin.getBranchesSize() > 0 ) {
            boolean exit = false;
            while (!exit) {
                System.out.println("Select a branch you want to remove from the system");
                System.out.println(admin.getBranchesString());
                System.out.println(admin.getBranchesSize() + 1 + ") Go back");
                int choice;
                choice = getUserChoice();
                if (choice > 0 && choice <= admin.getBranchesSize()) {
                    admin.removeBranch(choice-1);
                    if( admin.getBranchesSize() == 0 ) {
                        System.out.println("All branches removed");
                        exit = true;
                    }
                }
                else if( choice == admin.getBranchesSize()+1 )
                    exit = true;
                else
                    System.out.println(error);
            }
        }
        else
            System.out.println("System has no branches to be removed");
    }
    private void AdministratorInterfaceMainMenu( Administrator admin ){
        System.out.println("Welcome " + admin.getName() + " " + admin.getSurname() );
        System.out.println("What do you want to do?");
        System.out.println("1) Add a new Branch");
        System.out.println("2) Remove an existing Branch");
        System.out.println("3) Add a Branch Employee");
        System.out.println("4) Remove a Branch Employee");
        System.out.println("5) Add Transportation Personnel");
        System.out.println("6) Remove Transportation Personnel");
        System.out.println("7) Log out");
    }
    private void systemUserInterfaceMenu( AutomationSystemForCargoCompany system ){
        System.out.println("Welcome to \"" + system.getSystemName() + "\"" );
        System.out.println("Who are you?");
        System.out.println("1) Administrator");
        System.out.println("2) Branch Employee");
        System.out.println("3) Transporation Personnel");
        System.out.println("4) Customer");
        System.out.println("5) Go back");
    }
    public void makeAdminUser( AutomationSystemForCargoCompany system ){
        String name,surname;
        System.out.println("Enter admin's name: ");
        name = getUserInput();
        System.out.println("Enter admin's surname ");
        surname = getUserInput();

        try{
            system.addAdministrator(name,surname);
        }
        catch ( InvalidParameterException e ){
            System.out.println("Exception caught: " + e.getMessage() );
        }
    }
    public void removeAdminUser( AutomationSystemForCargoCompany system ){
        if( system.getAdministrators().size() > 0 ) {
            int choice;
            boolean exit = false;
            while (!exit) {
                System.out.println("Choose admin to remove");
                System.out.println(system.getAdministratorsString());
                System.out.println(system.getAdministratorsSize()+1 + ") Go back");
                choice = getUserChoice();
                if (choice > 0 && choice <= system.getAdministrators().size()) {
                    system.removeAdministrator(choice-1);
                    if( system.getAdministrators().size() == 0 ) {
                        System.out.println("All admins removed");
                        exit = true;
                    }
                }
                else if( choice == system.getAdministratorsSize()+1 )
                    exit = true;
                else
                    System.out.println(error);
            }
        }
        else
            System.out.println("System has no administrators to be removed");
    }
    public void getIntoTheSystemMenu(AutomationSystemForCargoCompany system){
        System.out.println("Welcome to \"" + system.getSystemName() + "\" system");
        System.out.println("1) Make an admin");
        System.out.println("2) Remove an admin");
        System.out.println("3) Get into the system's user interface");
        System.out.println("4) Go back");
    }
    public void removeSystemUser( ArrayList<AutomationSystemForCargoCompany> systems){
        int choice;
        boolean exit = false;
        while (!exit){
            if( systems.size() == 0 ) {
                System.out.println("You need to first make a system in order to be able to remove one");
                exit = true;
            }
            else {
                chooseSystemMenu(systems);
                choice = getUserChoice();
                if (choice > 0 && choice <= systems.size() && systems.size() > 0) {
                    systems.remove( systems.get(choice-1) );
                    if( systems.size()==0 ) {
                        System.out.println("All systems deleted, going back to main menu");
                        exit = true;
                    }
                } else if (choice == systems.size() + 1)
                    exit = true;
                else
                    System.out.println(error);
            }
        }
    }
    static int getUserChoice(){
        int to_return = -1;//-1 for an invalid input
        String input;
        Scanner scanner = new Scanner(System.in);
        do {
            input = scanner.next();
            if( !isNumeric(input) )
                System.out.println("Please enter a number");
        }while( !isNumeric(input) );
        return Integer.parseInt(input);
    }
    private static boolean isNumeric( String str ){
        try{
            Integer.parseInt(str);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    String getUserInput(){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while( input.length() == 0 )
            input = scanner.nextLine();
        return input;
    }
}

