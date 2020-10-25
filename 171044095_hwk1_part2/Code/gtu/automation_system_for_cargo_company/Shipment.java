package gtu.automation_system_for_cargo_company;


/**
 * enum status is used to indicate the status of the shipment
 * status can be: -Left the branch-, -Arrived to branch - or -Delivered-
 */

enum Status{ LEFT_THE_BRANCH, ARRIVED_TO_BRANCH, DELIVERED }
/**
 * Class shipment is used to store information of shipment
 */


public class Shipment implements PrintableInConsole{
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
    public Shipment(Customer sender, Customer reciever, String tracking_number, String branch_name ) {
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
        return (this.toString() + "| Class name: " + getClass().getSimpleName());
    }
    /**
     * implementing printDebuggingMode method of PrintableInConsodeInterface
     */
    public String showDebuggingMode(){
        return ( show() + "\n Class name: " + getClass().getName() + "\nHash Code: " + hashCode() );
    }
}
