package software.system.gtu;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Class is used just to show capabilities of this system
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class SoftwareSystemMenuDriven extends SoftwareSystem {
    private String name;
    public SoftwareSystemMenuDriven(String name, String admin_name, String admin_password, SearchTree<Admin> admin_class, SearchTree<User> user_class, SearchTree<SoftwarePackage> softwares_class) {
        super(admin_name, admin_password, admin_class, user_class, softwares_class);
        this.name = name;
    }
    @SuppressWarnings({"unchecked","rawtypes"})
    public void startSystem(){
        int choice=-1;
        while(choice != 2) {
            System.out.println("Welcome to: " + name);
            System.out.println("Who are you?");
            choice = choiceMenu(new String[]{"User", "Admin"});
            User current_user = LogInMenu(choice, new String[]{"User", "Admin"});
            if(choice == 0 && current_user != null) {
                System.out.println("User UI");
                userUI(current_user);
            }
            else if(choice == 1 && current_user != null){
                System.out.println("Admin UI");
                Admin admin = (Admin)current_user;
                adminUI(admin);
            }
            else if(choice != 2)
                System.out.println("Login was not successful");
        }
    }
    private void userUI(User user){
        System.out.println("User: " + user.getName());
        String options[] = new String[]{"Search system by software name", "Search system by software quantity", "Search system by software price"};
        int choice = -1;
        while( choice != options.length ) {
            choice = choiceMenu(options);
            userExc(user, choice);
        }
    }
    private void adminUI(Admin admin){
        System.out.println("Welcome " + admin.getName());
        String[] options =new String[]{"Add admin", "Add User", "Remove user", "Add software package", "Sell software package", "Update quantity", "Update price", "Remove admin", "Search as user"};
        int choice = -1;
        while( choice != options.length ) {
            choice = choiceMenu(options);
            adminExc(admin, choice);
        }
    }
    private void adminExc(Admin admin, int choice){
        Scanner scanner = new Scanner(System.in);
        switch (choice){
            case 0: {
                System.out.println("Enter new admin's name");
                String admin_name = scanner.nextLine();
                System.out.println("Enter new admin's pw");
                String pw = scanner.nextLine();
                if (admin.addAdmin(admin_name, pw))
                    System.out.println("Admin successfully added");
                else
                    System.out.println("Not valid, please try some other name");
            }
                break;
            case 1: {
                System.out.println("Enter name of the user to be added");
                if (admin.addUser(scanner.nextLine()))
                    System.out.println("User added");
                else
                    System.out.println("Not valid, please try some other name");
            }
                break;
            case 2: {
                System.out.println("Enter name of the user to be removed");
                if (admin.removeUser(scanner.nextLine()))
                    System.out.println("User removed");
                else
                    System.out.println("Not valid, please try some other name");
            }
                break;
            case 3: {
                System.out.println("Enter name of the package to be added");
                String s_name = scanner.nextLine();

                try {
                    System.out.println("Enter quantity to be added");
                    int quantity = parseInt(scanner.nextLine());
                    System.out.println("Enter software's price");
                    int price = parseInt(scanner.nextLine());
                    if (admin.addSoftwarePackage(s_name, quantity, price))
                        System.out.println("Software added");
                    else
                        System.out.println("Entered information is not valid, please try again");
                } catch (Exception e) {
                    System.out.println("Quantity/Price should be a valid integer number");
                }
            }
                break;
            case 4: {
                System.out.println("Enter name of the package to be sold");
                if(admin.sellSoftwarePackage(scanner.nextLine()))
                    System.out.println("Software Package sold");
                else
                    System.out.println("Not in stock");
            }
                break;
            case 5: {
                try {
                    System.out.println("Enter name of the package whose quantity is to be updated");
                    String s_name = scanner.nextLine();
                    System.out.println("Enter quantity to be added");
                    int quantity = parseInt(scanner.nextLine());
                    if( admin.updateQunatity(s_name, quantity) )
                        System.out.println("Quantity updated");
                    else
                        System.out.println("Invalid name");
                } catch (Exception e) {
                    System.out.println("Invalid information. Please try again");
                }
            }
                break;
            case 6: {
                try {
                    System.out.println("Enter name of the package whose price is to be updated");
                    String s_name = scanner.nextLine();
                    System.out.println("Enter new price");
                    int price = parseInt(scanner.nextLine());
                    if( admin.updatePrice(s_name, price) )
                        System.out.println("Price updated");
                    else
                        System.out.println("Invalid name");
                }
                catch (Exception e){
                    System.out.println("Invalid information. Please try again");
                }
            }
                break;
            case 7:
                System.out.println("Enter the name of admin to be removed");
                if(admin.removeAdmin(scanner.nextLine()))
                    System.out.println("Admin removed");
                else
                    System.out.println("Admin name invalid");
                break;
            case 8:
                userUI(admin);
                break;
            case 9:
                System.out.println("Exiting..");
                break;
            default:
                System.out.println("Invalid input");
        }
    }
    private void userExc(User user, int choice){
        Scanner scanner = new Scanner(System.in);
        switch (choice){
            case 0:
                System.out.println("Enter name");
                System.out.println(user.searchByName(scanner.nextLine()));
                break;
            case 1:
                System.out.println("Enter quantity");
                try {
                    System.out.println(user.searchByQuantity(parseInt(scanner.nextLine())));
                }
                catch (Exception e) {
                    System.out.println("Invalid input");
                }
                break;
            case 2:
                System.out.println("Enter price");
                try{
                    System.out.println(user.searchByPrice(parseInt(scanner.nextLine())));
                }
                catch (Exception e){
                    System.out.println("Invalid input");
                }
                break;
            case 3:
                System.out.println("Exit");
        }
    }
    private User LogInMenu(int choice, String[] str){
        Scanner scanner = new Scanner(System.in);
        switch (choice){
            case 0:
                System.out.println("Enter your username");
                return getUser(scanner.nextLine());
            case 1:
                System.out.println("Enter your username");
                String admin_name = scanner.nextLine();
                System.out.println("Enter your password");
                String pw = scanner.nextLine();
                return getAdmin(admin_name, pw);
        }
        return null;//when some other choice is there return null immediately
    }
    private int choiceMenu(String[] str){
        for(int i = 0; i<str.length; ++i)
            System.out.println(i + ")" + str[i]);
        System.out.println(str.length + ")"  + "Exit");
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        do{
            String choices = scanner.nextLine();
            try{
                choice = parseInt(choices);
                if(choice < 0 || choice>str.length )
                    System.out.println("Try again");
            }
            catch (Exception e){
                System.out.println("Exception caught " + e.getMessage());
            }

        }while(choice<0 || choice>str.length);
        return choice;
    }
}
