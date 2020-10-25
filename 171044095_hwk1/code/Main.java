import gtu.automation_system_for_cargo_company.AutomationSystemForCargoCompany;
import gtu.automation_system_for_cargo_company.DynamicArray;
/**
 * This file is only used for making menus so that the program written
 * int the AutomationSystemForCargoCompany could be tested
 * It is not part of the package
 * but just a tool used for testing
 */
public class Main{
        public static void main(String[] args) {
            DynamicArray<AutomationSystemForCargoCompany> systems = new DynamicArray<AutomationSystemForCargoCompany>();
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
                        exit = true;
                        break;
                    default:
                        System.out.println("Please enter a valid input");
                }
            }
        }
}
