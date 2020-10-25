import gtu.automation_system_for_cargo_company.AutomationSystemForCargoCompany;
import java.util.ArrayList;
/**
 * This file is only used for making menus so that the program written
 * int the AutomationSystemForCargoCompany could be tested
 * It is not part of the package
 * but just a tool used for testing
 */
public class Main{
    public static void main(String[] args) {
        ArrayList<AutomationSystemForCargoCompany> systems = new ArrayList<AutomationSystemForCargoCompany>();
        Test menus = new Test();
        boolean exit = false;
        int choice;
        while( !exit ){
            menus.welcomeMenu();
            choice = menus.getUserChoice();
            switch ( choice ){
                case 1:
                    menus.systemCreationMenuUserInput(systems);
                    break;
                case 2:
                    menus.chooseSystemUser(systems);
                    break;
                case 3:
                    menus.removeSystemUser(systems);
                    break;
                case 4:
                    for( int i = 0; i<systems.size(); ++i )
                        System.out.println(i+1+") " + systems.get(i).getSystemName());
                    System.out.println("Press any non-valid option to go back" );
                    int choice2 = menus.getUserChoice();
                    if( choice2> 0 && choice2<=systems.size())
                        System.out.println( systems.get(choice2-1).showDetailed() );
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter a valid input");
            }
        }
    }
}

