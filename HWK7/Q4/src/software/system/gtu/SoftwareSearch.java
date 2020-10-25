package software.system.gtu;

public interface SoftwareSearch {
    /**
     * used for searching by name
     * @param name name of the software package
     */
    public String searchByName(String name);
    /**
     * used for searching with respect to quantity
     * @param quantity quantity of a software package
     */
    public String searchByQuantity(int quantity);
    /**
     * used for saerching with respect to price
     * @param price price of the software package
     */
    public String searchByPrice(int price);
}
