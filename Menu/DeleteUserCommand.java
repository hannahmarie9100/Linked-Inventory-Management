// hannah ali - hma220003

package LinkedInventoryManagement.Menu;

import java.util.Scanner;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;

/**
 * DeleteUserCommand - Command to delete a user by username
 */
public class DeleteUserCommand extends Command {

    // constructor with necessary fields
    public DeleteUserCommand(ProductCatalog productCatalog, User loggedOnUser) {
        super(productCatalog, loggedOnUser);
    }

    @Override
    public void Execute() {
        Scanner scan = new Scanner(System.in);  // avoid closing if using shared scanner

        try {
            // prompt for username to delete
            System.out.println("Enter username of user to delete: ");
            String username = scan.nextLine().trim();

            // attempt to remove the user
            boolean success = SecurityOperations.RemoveUser(username);
            if (success) {
                System.out.println("User '" + username + "' deleted successfully.");
            } else {
                System.out.println("User '" + username + "' does not exist.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }
}
