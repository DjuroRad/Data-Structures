package software.system.gtu;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Iterator;
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class SoftwareSystem implements SoftwareSearch{
    /**
     * this will hold the name and the password of the admin!
     */
    private SearchTree<Admin> admins;
    /**
     * stores the unique names of the users( like a username )
     */
    private SearchTree<User> users;//used for search by quantity and price
    /**
     * stores all the software packages to this system
     */
    private SearchTree<SoftwarePackage> softwares;//stored by name
    private HashSet<String> software_names;
    public SoftwareSystem(String admin_name, String admin_password, SearchTree<Admin> admin_class, SearchTree<User> user_class, SearchTree<SoftwarePackage> softwares_class){
        admins = admin_class;
        softwares = softwares_class;
        software_names = new HashSet<>();
        users = user_class;
        //in the begining putting an admin into the system so that the system could function properly( admin can add or remove an admin from the system
        admins.add(new Admin(admin_name,admin_password,this));
        //adding software packages specified in the homework
        softwares.add(new SoftwarePackage("Adobe Photoshop 6.0", 2, 100));
        software_names.add("Adobe Photoshop 6.0");
        softwares.add(new SoftwarePackage("Adobe Photoshop 6.2", 2, 100));
        software_names.add("Adobe Photoshop 6.2");
        softwares.add(new SoftwarePackage("Norton 4.5", 2, 200));
        software_names.add("Norton 4.5");
        softwares.add(new SoftwarePackage("Norton 5.5", 2, 200));
        software_names.add("Norton 5.5");
        softwares.add(new SoftwarePackage("Adobe Flash 3.3", 2, 100));
        software_names.add("Adobe Flash 3.3");
        softwares.add(new SoftwarePackage("Adobe Flash 4.0", 2, 100));
        software_names.add("Adobe Flash 4.0");
    }

    @Override
    public String searchByName(String name) {
        SoftwarePackage sp = softwares.find(new SoftwarePackage(name));
        if(sp != null)
            return sp.toString();
        else
            return "No software with such name";
    }

    /**
     * method is used to search by quantity of software
     * @param quantity quantity of a software package
     * @return string of all of packages with that quantity
     */
    @Override
    public String searchByQuantity(int quantity) {
        Iterator<String> i = software_names.iterator();
        String info = "";
        while(i.hasNext()){
            SoftwarePackage sp = softwares.find(new SoftwarePackage(i.next()));
            if(sp != null) {
                if(sp.getQuantity() == quantity)
                    info += sp.toString() + "\n";
            }
        }
        if(info.equals(""))
            System.out.println("No software with that quantity");
        return info;
    }

    /**
     * Searches by price
     * @param price price of the software package
     * @return string of all softwares with that price
     */
    @Override
    public String searchByPrice(int price) {
        Iterator<String> i = software_names.iterator();
        String info = "";
        while(i.hasNext()){
            SoftwarePackage sp = softwares.find(new SoftwarePackage(i.next()));
            if(sp != null) {
                if(sp.getPrice() == price)
                    info += sp.toString() + "\n";
            }
        }
        if(info.equals(""))
            System.out.println("No software with that price");
        return info;
    }

    /**
     * admin enters the system with a password and a name
     * @param name amins userame
     * @param password admin's password
     * @return null if information is incorrect
     */
    public Admin getAdmin(String name, String password){
        if(admins.contains(new Admin(name,password,this))) {
            if(admins.find(new Admin(name, password, this)).verifyAdmin(password))
                return admins.find(new Admin(name, password, this));
        }
        return null;
    }

    protected boolean removeAdmin(String name){
        Admin new_admin = new Admin(name, "", this);//just used for comparison purposes
        if(!admins.contains(new_admin))
            return false;
        admins.remove(new_admin);
        return true;
    }
    /**
     * Admin can be added into the system, this can be done through any of the admins
     * @param name name of th enew admin, has to be unique
     * @param password password of the admin
     * @return admin that has been added
     */
    protected boolean addAdmin(String name, String password){
        Admin new_admin=  new Admin(name, password, this);
        if(!admins.contains(new_admin)) {
            admins.add(new_admin);
            return true;
        }
        else
            return false;//admin is already inside of the system, another name should be assigned
    }

    /**
     * Protected method executed through admin to be able to add a new software to the system
     * @param name name of th software
     * @param quantity quantity of the software
     * @param price price of the new software
     * @return true if software with the same name doesn't exist and also true if it exists just adding to quantity instead here, false when price of qunatity is entered incorrectly
     */
    protected boolean addSoftwarePackage(String name, int quantity, int price){
        if(price < 1 || quantity < 1)
            return false;
        SoftwarePackage new_software = new SoftwarePackage(name, quantity, price);
        SoftwarePackage to_find = softwares.find(new_software);
        if(to_find!=null && to_find.getPrice()==price) {
             return addSoftwarePackage(name,quantity);
        }
        //not in the system so we can continue here
        softwares.add(new_software);
        //add the name of it also so that iteration would be possible for search by quantity and price
        software_names.add(name);
        return true;
    }

    /**
     * also called through admin
     * @param name name of the package
     * @param quantity number of elements to be added
     * @return true if the element exists in the system and false otherwise
     */
    protected boolean addSoftwarePackage(String name, int quantity){
        if(quantity<1)
            throw new InvalidParameterException();
        SoftwarePackage to_update = softwares.find(new SoftwarePackage(name, quantity, 0));
        if(to_update == null)
            return false;
        to_update.setQuantity(to_update.getQuantity() + quantity);//in this case adding number of
        return true;
    }

    protected boolean sellSoftwarePackage(String name){
        SoftwarePackage to_sell = new SoftwarePackage(name);
        SoftwarePackage to_update = softwares.find(to_sell);
        if(to_update != null)
            to_update.setQuantity(to_update.getQuantity()-1);
        else
            return false;
        if(to_update.getQuantity()==0) {
            softwares.remove(to_update);
            software_names.remove(name);
        }
        return true;
    }
    /**
     * method that changes the price of a software... done through admin again
     * @param name name of the software whose price is to be changed
     * @param price new price for this software
     * @return true if the software was in the system and false otherwise
     */
    protected boolean changeSoftwarePrice(String name, int price){
        if(price<1)
            throw new InvalidParameterException();
        SoftwarePackage sp = softwares.find(new SoftwarePackage(name,price));
        if( sp == null )
            return false;
        sp.setPrice(price);
        return true;
    }
    /**
     * adding a user to the system, Protected since only accessible thorugh admin!
     * @param name name of the user ot be added
     * @return if name is of the user is not take the user is added to the system
     */
    protected boolean addUser(String name){
        User new_user = new User(name,this);
        if(!users.contains(new_user)) {
            users.add(new_user);
            return true;
        }
        else
            return false;//admin is already inside of the system, another name should be assigned
    }

    /**
     * only executed through admin!
     * @param username name of the user to be removed
     * @return false is user is not in the system and true if it is
     */
    protected boolean removeUser(String username){
        User user = new User(username);
        if(users.contains(user)) {
            users.remove(user);//meaniing user is found in the system and removed
            return true;
        }
        else
            return false;//user is ont in the system
    }
    /**
     * users don't have password, are added by admins and can only search the system which is a public function
     * @param name name of the user
     * @return null if not in the system and the user otherwise
     */
    public User getUser(String name){
        if(users.contains(new User(name, this)))
            return users.find(new User(name, this));
        else
            return null;
    }
}
