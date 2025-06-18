// hannah ali - hma220003

package LinkedInventoryManagement.Menu;

import java.util.Scanner;
import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Security.User;

/**
 * MenuList - handles the display and management of menu options
 */
public class MenuList {

    private String menuHeader;
    private InventoryLinkedList<MenuItem> menuItems;
    private int exitChoice;

    // constructor to initialize menu with a header
    public MenuList(String menuHeader) {
        this.menuHeader = menuHeader;
        menuItems = new InventoryLinkedList<>();
        System.out.println(menuHeader);
    }

    // method to add a menu item to the list
    public void AddMenuItem(MenuItem menuItem) {
        menuItems.insert(menuItems.getLength(), menuItem);
        exitChoice = menuItems.getLength() + 1;  // update exit choice whenever a new item is added
    }

    // start displaying the menu and handle user input
    public void StartMenu(User user) {
        Scanner scan = ScannerFactory.GetScannerInstance();
        int choice = 0;

        while (choice != exitChoice) {
            System.out.println("\n" + menuHeader);
            System.out.println("----------------------------------------");

            // display menu items based on user permissions
            for (int i = 0; i < menuItems.getLength(); i++) {
                MenuItem item = menuItems.getElement(i);
                if (!item.getIsRestricted() || (item.getIsRestricted() && user.getIsManager())) {
                    System.out.println((i + 1) + ". " + item.getDescription());
                }
            }

            System.out.println(exitChoice + ". Exit");

            System.out.print("Enter your selection: ");
            try {
                choice = scan.nextInt();
                scan.nextLine();  // clear newline character

                if (choice == exitChoice) {
                    System.out.println("Exiting the menu.");
                } else if (choice > 0 && choice <= menuItems.getLength()) {
                    MenuItem menuChoice = menuItems.getElement(choice - 1);
                    menuChoice.getCommand().Execute();
                } else {
                    System.out.println("Invalid choice. Please select a valid menu option.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scan.nextLine();  // clear invalid input
            }
            System.out.println();
        }
    }

    public int getExitChoice() {
        return exitChoice;
    }
}
