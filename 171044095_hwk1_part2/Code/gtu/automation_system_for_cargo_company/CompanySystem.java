package gtu.automation_system_for_cargo_company;

public interface CompanySystem{
    boolean addAdministrator( String name, String surname );
    boolean removeAdministrator( String name, String surname );
    String getSystemName();
}
