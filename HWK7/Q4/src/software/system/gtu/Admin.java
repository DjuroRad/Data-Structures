package software.system.gtu;

/**
 * Implements admin interface and extends user - check those two to find out more
 */
public class Admin extends User implements AdminInterface{
    private String password;
    public Admin(String name, String password, SoftwareSystem system) {
        super(name, system);
        this.password = password;
    }

    @Override
    public boolean verifyAdmin(String password) {
        return this.password.equals(password);
    }

    public boolean removeAdmin(String name){
        if(!this.name.equals(name))
            return system.removeAdmin(name);
        return false;
    }
    @Override
    public boolean addUser(String name) {
        return system.addUser(name);
    }

    @Override
    public boolean removeUser(String name) {
        return system.removeUser(name);
    }

    @Override
    public boolean addAdmin(String name, String password) {
        return system.addAdmin(name, password);
    }

    public boolean addSoftwarePackage(String name, int quantity, int price){
        return system.addSoftwarePackage(name, quantity, price);
    }

    public boolean updateQunatity(String name, int quantity){
        return system.addSoftwarePackage(name, quantity);
    }
    public boolean updatePrice(String name, int quantity){
        return system.changeSoftwarePrice(name, quantity);
    }
    public boolean sellSoftwarePackage(String name){
        return system.sellSoftwarePackage(name);
    }
}
