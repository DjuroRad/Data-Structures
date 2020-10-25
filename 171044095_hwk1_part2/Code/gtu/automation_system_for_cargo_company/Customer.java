package gtu.automation_system_for_cargo_company;

import java.util.ArrayList;

/**
 * Customer is see the sender and reciever of a certain shipment by entering its tracking number
 * it also extends from User
 */
public class Customer extends User{
    private ArrayList<Shipment> shipments;
    /**
     * gets information of shipment by using its tracking number
     * @param tracking_number - unique set of character and/or number for every shipment
     * @return - returns String in format of ----Sender----Reciever----Tracking Number ----
     */
    public String getInfoOfShipment( String tracking_number ){
        Shipment shipment = getShipmentByTrackingNumber(tracking_number);
        if( shipment != null )
            try {
                return getShipmentByTrackingNumber(tracking_number).toString();
            }
            catch (Exception e){
                return "NullPointerException called";
            }
        else
            return "Tracking number not valid";

    }
    /**
     * Returns the shipment by its tracking number
     * @param tracking_number type String
     * @return Shipment with corresponding tracking number
     */
    private Shipment getShipmentByTrackingNumber( String tracking_number ){
        for( int i = 0; i<shipments.size(); ++i )
            if( shipments.get(i).getTrackingNumber().equals(tracking_number) )
                return shipments.get(i);

        return null;
    }
    /**
     * Two paramter consturctor, sets type to CUSTOMER
     * @param name - name of the Customer
     * @param surname - surname of the Customer
     */
    public Customer( String name, String surname, ArrayList<Shipment> shipments){
        super( name, surname, UserType.CUSTOMER );
        this.shipments = shipments;
    }

    /**
     * overrides the printDetailed class to show that it inherits from another class
     */
    @Override
    public String showDetailed(){
        return (this.toString() + "| Class name: " + getClass().getSimpleName() + "| Parent Class name: " + getClass().getSuperclass().getSimpleName() );
    }
    /**
     * Shows more info than the basic user
     */
    @Override
    public String showDebuggingMode(){
        String to_return = (this.toString());
        to_return += ("\nFull class name: " + getClass().getSuperclass().getName() );
        to_return += ("\nFull parent class name: " + getClass().getSuperclass().getName() );
        to_return += ("\nHash code: " + hashCode() );
        return to_return;
    }
}

