//hannah ali - hma220003

package LinkedInventoryManagement;

import LinkedInventoryManagement.Menu.*;
import LinkedInventoryManagement.PersistentStorage.MenuListOperations;

import java.util.Scanner;

import LinkedInventoryManagement.Common.*;
// import LinkedInventoryManagement.Product.*;
import LinkedInventoryManagement.Security.*;

/**
 * Hello world!
 */
public class Main 
{
    public static void main( String[] args )
    {
        // //THIS IS JUST AN EXAMPLE ON HOW TO AUTHENTICATE AND CREATE A COMMAND DYNAMICALLY. 
        // //REMOVE CODE AND CHANGE AS NEEDED.

        // //Authenticate the user and get a user object back
        // User user = SecurityOperations.AuthenticateUser("admin", "admin"); 
        
        // ProductCatalog  productCatalog = new ProductCatalog(); 
        
        // //TODO: You will have to read the information below from the MenuList.dat file instead of hardcoding it here.
        // String          commandClassName    = "AddProductCommand"; 
        // int             optionNumber        = 1; 
        // String          description         = "Add Product"; 
        // Boolean         isRestricted        = true; 

        // Command dynamicCommand = Command.CreateCommandDynamically(productCatalog, user, commandClassName); 

        // System.out.println("The command concrete type is " + dynamicCommand.getClass().getSimpleName());
        
        // //Create all MenuItems and add them to the MenuList. Array list usage is NOT permitted.
        // MenuItem menuItem = new MenuItem(dynamicCommand, optionNumber, description, isRestricted); 

        // MenuList menuList = new MenuList("Main Menu"); 

        // menuList.AddMenuItem(menuItem);

        // //Example of using a Singleton class to create and use a scanner object
        // ScannerFactory.GetScannerInstance().nextLine();
        // //Use the ScannerFactory as many times as you like throughout your project. 


        // //Close the Scanner at the end of your program as follows. 
        // ScannerFactory.CloseScannerInstance();

        Scanner scan = ScannerFactory.GetScannerInstance();

        User user = null;
        while(user == null)
        {
            System.out.println("Enter username: ");
            String username = scan.nextLine();
            System.out.println("Enter password: ");
            String pass = scan.nextLine();

            user = SecurityOperations.AuthenticateUser(username, pass);

            if(user == null)
            {
                System.out.println("Invalid username or password!");
                System.out.print("Press enter to continue or type 'Exit' to exit: ");
                String choice = scan.nextLine().trim();
                if (choice.equalsIgnoreCase("Exit")) {
                    scan.close();
                    System.exit(0);
            }
        }

    }
    System.out.println();
    System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
    System.out.println();
    // ProductCatalog pc = new ProductCatalog();
    MenuListOperations menuListOperations = new MenuListOperations("MenuList.dat");

    InventoryLinkedList<MenuItem> menuItems = menuListOperations.readMenuFile();
    MenuList menuList = new MenuList("Inventory Management System Menu");



    for(int i = 0; i < menuItems.getLength(); i++)
    {
        menuList.AddMenuItem(menuItems.getElement(i));
    }

    // boolean running = true;
    while(true)
    {
        menuList.StartMenu(user);

        // System.out.println("Enter your selection: ");
        // int choice = scan.nextInt();

        // // if(choice == menuItems.getLength() - 1) //user chose exit
        // // { 
        // //     running = false;
        // //     break;
        // // }

        // if(choice > 0 && choice <= menuItems.getLength())
        // {
        //     MenuItem chosenItem = menuItems.getElement(choice-1);
        //     Command command = chosenItem.getCommand();
        //     command.Execute();
        // }
    }

    // scan.close();

    }
}
