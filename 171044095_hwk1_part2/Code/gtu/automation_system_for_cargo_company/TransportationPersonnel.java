package gtu.automation_system_for_cargo_company;

import java.util.ArrayList;

/**
 * Transportation Personnel is used to mark a certain
 * shipment as delivered
 */
public class TransportationPersonnel extends User{
    ArrayList<Shipment> shipments;
    /**
     * No parameter constructor will set
     * name as UNDEFINED_NAME_TRANSPORTATION_PERSONNEL and surname as UNDEFINED_SURNAME_TRANSPORTATION_PERSONNEL
     */
    public TransportationPersonnel( ArrayList<Shipment> shipments ){
        this( "UNDEFINED_NAME_TRANSPORTATION_PERSONNEL", "UNDEFINED_SURNAME_TRANSPORTATION_PERSONNEL", shipments);
    }

    /**
     * Two paramter constructor calls user's constructor and
     * sets the type to TRANSPORTATION_PERSONNEL
     * @param name - name of the personnel
     * @param surname - surname of the personnel
     */
    public TransportationPersonnel( String name, String surname, ArrayList<Shipment> shipments ){
        super( name, surname, UserType.TRANSPORTATION_PERSONNEL );
        this.shipments = shipments;
    }

    /**
     * Method used to update the shipment's status to delivered
     * @param tracking_number - String type
     * @return - return true if the shipment with such tracking number exists, false otherwise
     */
    public boolean updateShipmentStatusToDelivered( String tracking_number ){
        if( getShipmentByTrackingNumber( tracking_number) != null ) {
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
        if( i >= 0 && i <shipments.size() ) {
            shipments.get(i).setStatus(Status.DELIVERED);
            return true;
        }
        else
            return false;
    }

    public Shipment getShipmentByTrackingNumber( String tracking_number ){
        for( int i = 0; i<shipments.size(); ++i )
            if( shipments.get(i).getTrackingNumber().equals(tracking_number) )
                return shipments.get(i);

        return null;
    }
    /**
     * This method is used to print shipments since personnel is able to manipulate their status
     */
    public String printShipments(){//Since transportation personnel is able to manipulate shipments, it should also be able to see them instead of only using the tracking number
        String to_return = "";
        for( int i = 0; i<shipments.size(); ++i )
            to_return += "1) " + shipments.get(i).toString() + "\n";
        return to_return;
    }
    /**
     * just a getter for the number of the shipments in the array of shipments
     * @return Int type
     */
    public int getShipmentssize(){
        return shipments.size();
    }
    /**
     * implementing printDetailed method of PrintableInConsodeInterface
     */
    @Override
    public String showDetailed(){
        return (this.toString() + "| Class name: " + getClass().getSimpleName());
    }

    /**
     * Implementing showDebugginMode from printableInConsole Interface
     */
    @Override
    public String showDebuggingMode() {
        return show() + "\n Class name: " + getClass().getName() + "\n Upper class name: " + getClass().getSuperclass().getName() + "\n Hash Code: " + hashCode();
    }
}//Transportation personnel class ends here

