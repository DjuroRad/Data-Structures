package gtu.automation_system_for_cargo_company;


/**
 * UserType indicated the type of the user.
 * Administrator, Branch Employee, Transoprtation Personnel and Customer
 * all extend for the class user and have their type
 * according to their position in the system
 */
enum UserType {UNDEFINED_USER, ADMINISTRATOR, BRANCH_EMPLOYEE, TRANSPORTATION_PERSONNEL, CUSTOMER,}

/**
 * User class is used for implementing basic functions of every user
 * such as setting name, getting name, and their types
 * It is private withing this system
 */
public class User implements PrintableInConsole{
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
    /**
     * implementing print method of PrintableInConsodeInterface
     */
    public String show(){
        return this.toString();
    }
    /**
     * implementing printDetailed method of PrintableInConsodeInterface
     */
    public String showDetailed() {
        String to_return = "";
        to_return += (this.toString() + "| Class name: " + getClass().getSimpleName());
        return to_return;
    }
    /**
     * implementing printDebuggingMode method of PrintableInConsodeInterface
     */
    public String showDebuggingMode(){
        return show() + "\nClass name: " + getClass().getName() + "\nHash Code: " + hashCode();
    }
}//User class ends here

