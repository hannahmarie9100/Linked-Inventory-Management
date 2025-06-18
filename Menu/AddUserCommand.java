// hannah ali - hma220003

package LinkedInventoryManagement.Menu;

import java.util.Scanner;

import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;

/**
 * AddUserCommand - Command to add a new user to the system
 */
public class AddUserCommand extends Command {

    // constructor with necessary fields
    public AddUserCommand(ProductCatalog productCatalog, User loggedOnUser) {
        super(productCatalog, loggedOnUser);
        this.productCatalog = productCatalog;
    }

    @Override
    public void Execute() {
        Scanner scan = new Scanner(System.in);  // avoid closing if using a shared scanner

        try {
            // get user details from input
            System.out.println("Enter new user's first name: ");
            String firstname = scan.nextLine();

            System.out.println("Enter new user's last name: ");
            String lastname = scan.nextLine();

            System.out.println("Enter new user's username:");
            String username = scan.nextLine();

            System.out.println("Enter new user's password:");
            String password = scan.nextLine();

            System.out.println("Is the user a manager? (true/false):");
            boolean isManager = scan.nextLine().trim().equalsIgnoreCase("true");

            // create new user object with hashed password
            User newUser = new User(username, SecurityOperations.GetPasswordHash(password), isManager);
            newUser.setFirstName(firstname);
            newUser.setLastName(lastname);

            // add new user to the system
            SecurityOperations.AddNewUser(newUser);

            System.out.println("User added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }
}
