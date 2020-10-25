package gtu.automation_system_for_cargo_company;

import java.security.InvalidParameterException;
import java.util.ArrayList;


/**
 * Administrator is a class the extends the user class
 * and is used to manipulate the system
 * by adding branches, branch employees and transporatation personel
 */
public class Administrator extends User{
    private ArrayList<Branch> branches;
    private ArrayList<TransportationPersonnel> transportation_personnel;
    private AutomationSystemForCargoCompany system;
    /**
     * No paramter constructor wiill assign
     * name as UNDEFINED_NAME_ADMIN and surname as UNDEFINED_SURNAME_ADMIN
     */
    public Administrator(AutomationSystemForCargoCompany system ){
        this( "UNDEFINED_NAME_ADMIN", "UNDEFINED_SURNAME_ADMIN", system );
    }

    /**
     * Tw paramter consturctor that calls the three paramter consturctor of the User with enum type of ADMINISTRATOR
     * @param name - name of admin
     * @param surname - surname of admin
     */
    public Administrator( String name, String surname, AutomationSystemForCargoCompany system ){
        super( name, surname, UserType.ADMINISTRATOR );
        branches = system.getBranches();
        transportation_personnel = system.getTransportationPersonnel();
        this.system = system;
    }

    /**
     * Administrators are able to add branches to the system
     * @param name - name of the branch, type is String
     * @return true if added, false if the system already exists
     */
    public boolean addBranch( String name ){
        if( !branches.contains( new Branch(name, system.getShipments(), system.getCustomers()) ))
            return branches.add( new Branch(name, system.getShipments(), system.getCustomers() ));
        else
            throw new InvalidParameterException("Branch you are trying to add already exists");
    }
    /**
     * Remove a branch from the system
     * @param name - name of the branch to be removed
     * @return true if branch exists, false otherwise
     */
    public boolean removeBranch( String name ){
        return branches.remove( new Branch( name, system.getShipments(), system.getCustomers() ) );
    }

    /**
     * Removes a branch by providing index to the branch to be removced
     * @param i int - index of the branch
     */
    public void removeBranch( int i ){
        if( i>=0 && i<branches.size() )
            branches.remove(i);
        else
            throw new InvalidParameterException("Index paramter for removing branches out of bounds");
    }
    /**
     * This function returns branches as a String
     * which can be used to be shown as a choice menu
     * this is used to accomplish better overview of the
     * things admin can manipulate
     * @return returns all the branches basic info
     */
    public String getBranchesString(){
        String to_return = "";
        for(int i = 0; i<branches.size(); ++i)
            to_return += i+1 + ") " + branches.get(i).show() + "\n";
        return to_return;
    }

    /**
     * Administrator is also able to see the number of branches
     * that were made so far
     * @return number of branches in the system
     */
    public int getBranchesSize(){
        return branches.size();
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
            try{
                return (branches.get(branch_index).addBranchemployee( employee_name, employee_surname ) );
            }
            catch ( InvalidParameterException e ){
                throw new InvalidParameterException("Branch employee already in the system");
            }
        }
        else
            throw new InvalidParameterException("Branch you are trying to add your employee to does not exist");
    }

    /**
     * adds a branch employee with index to the branch its name and surname
     * @param i index to the branch to which we want to add
     * @param name name of th employee
     * @param surname surname of the employee
     * @return true if added, throws exception when index out of bounds
     */
    public boolean addBranchEmployee( int i, String name, String surname ){
        if( i >= 0 && i < branches.size() )
            return addBranchEmployee( branches.get(i).getBranchName(), name, surname );
        else
            throw new InvalidParameterException("Branch index out of bounds");
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
            return (branches.get(branch_index).removeBranchemployee( employee_name, employee_surname ) );
        }
        else
            return false;
    }

    /**
     * Branch employees removed by using the index instead of name
     * @param i index parameter of the branch from which the emplyee is to be removed
     * @param name name of the employee
     * @param surname surname of the employee
     * @return true if removed, throws an exception if index is out of bounds
     */
    public boolean removeBranchEmployee( int i, String name, String surname ){
        if( i>=0 && i<branches.size() )
            return removeBranchEmployee(branches.get(i).getBranchName(), name, surname);
        else
            throw new InvalidParameterException("Branch index from which you are trying to remove the branch employe is out of bounds for branches of the system");
    }

    /**
     * Removes a branch employee by providing index of the branch and the index of th employee at that branch
     * @param emp_i index of th employee
     * @param brc_i index of the branch
     * @return true if removed, throws an exception when any of these two indexes are out of bounds
     */
    public boolean removeBranchEmployee( int emp_i, int brc_i ){
        return removeBranchEmployee( brc_i, branches.get(brc_i).getBranchEmployeeNameByI(emp_i), branches.get(brc_i).getBranchEmployeeSurnameByI(emp_i));
    }
    /**
     * Returns the number of employees in a certain branch by the index of the branch they are in
     * @param i index of the branch at which we want to see number of branch employees
     * @return int, number of branch employees
     */
    public int getBranchEmployeeSizeByIndex( int i ){
        if( i>=0 && i<branches.size())
            return branches.get(i).getBranchEmployeesSize();
        else
            throw new InvalidParameterException("Invalid index parameter for branches in getBranchEmployeeSizeByIndex method");
    }

    /**
     * Gives a nicely formatted string of branch employees in a certain branch
     * @param i index of the branch
     * @return string of employees of that branch
     */
    public String getBranchEmployeesString( int i ){
        if( i>= 0 && i<branches.size() )
            return branches.get(i).getBranchEmployeesString();
        else
            throw new InvalidParameterException("Invalid index parameter for branches in getBranchEmployeesString method");
    }
    /**
     * Administrator can add Transportation Personnel to the system
     * @param name name of the Transportationn Personnel
     * @param surname surnname of the Transportationn Personnel
     * @return return false if the personnel already exists in the system, false otherwise
     */
    public boolean addTransportationPersonnel( String name, String surname ){
        if( !transportation_personnel.contains( new TransportationPersonnel(name, surname, system.getShipments()) ))
            return transportation_personnel.add( new TransportationPersonnel(name, surname, system.getShipments()) );
        else
            throw new InvalidParameterException("Transportation personnel already exists");
    }

    /**
     * Transportation Personnel can also be removed though administrator
     * @param name name of the personnel to be removed
     * @param surname surname of the personnel to be removed
     * @return returns true if the perosnnel is removed( if it existed in the system it will be removed ), false otherwise
     */
    public boolean removeTransportationPersonnel( String name, String surname ){
        return transportation_personnel.remove( new TransportationPersonnel(name, surname, system.getShipments()) );
    }

    /**
     * Used for making selection menus and similar.
     * Provides better overview for the Administrator using the system
     * @return nicely formated string of all the TransportationPersonnel elements
     */
    public String getPersonnelString(){
        String to_return = "";
        for( int i = 0; i<transportation_personnel.size(); ++i )
            to_return += i+1 + ") " + transportation_personnel.get(i).show() + "\n";
        return to_return;
    }

    /**
     * Gives the possiblity to check how many transportation personnel
     * elements are in the system
     * @return number of transportation personnle in the system
     */
    public int getPersonnelSize(){
        return transportation_personnel.size();
    }
    /**
     * Transportation Personnel can also be removed though administrator by providing index of the personnel to be removed
     * @param i - index of the array where the personnel is
     * @return will return false if ined provided is out of bounds
     */
    public boolean removeTransportationPersonnel( int i ){
        if( i >= 0 && i<transportation_personnel.size() ) {
            if (transportation_personnel.remove(i) != null)
                return true;
        }
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
        for( int i = 0; i<branches.size() && (index == -1); ++i )
            if( branches.get(i).getBranchName().equals(branch_name) )
                index = i;

        return index;
    }

    /**
     * Returns the number of personnel in the system
     * @return number of perosnnel in the system
     */
    public int getTPersonnelSize(){
        return transportation_personnel.size();
    }

    /**
     * Nicely formatted string of t personnel in the system
     * @return String
     */
    public String getTPersonnelString(){
        String to_return = "";
        for( int i = 0; i<transportation_personnel.size(); ++i )
            to_return += i+1 + ") " + transportation_personnel.get(i).show() + "\n";
        return to_return;
    }

    /**
     * overrides the printDetailed class to show that it inherits from another class
     */
    @Override
    public String showDetailed(){
        return (this.toString() + "| Class name: " + getClass().getSimpleName() + "| Parent Class name: " + super.getClass().getSimpleName() );
    }
    /**
     * Shows more info than the basic user
     */
    @Override
    public String showDebuggingMode(){
        String to_return = this.toString();
        to_return += ("\nFull class name: " + getClass().getName() );
        to_return += ("\nFull parent class name: " + super.getClass().getName() );
        to_return += ("\nHash code: " + hashCode() );
        return to_return;
    }
}//Administrator class ends here

