package software.system.gtu;

/**
 * specifies what admin is supposed to do
 * names of methods say what it is supposed to do
 */
public interface AdminInterface {
    public boolean verifyAdmin(String password);
    public boolean addUser(String name);
    public boolean removeUser(String name);
    public boolean addAdmin(String name, String password);
    public boolean removeAdmin(String name);
    public boolean addSoftwarePackage(String name, int quantity, int price);
    public boolean updateQunatity(String name, int quantity);
    public boolean updatePrice(String name, int quantity);
    public boolean sellSoftwarePackage(String name);
}
