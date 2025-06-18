// hannah ali - hma220003

package LinkedInventoryManagement.Menu;

import java.util.Scanner;

import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;

/**
 * ChangePasswordCommand - Command to allow a user to change their password
 */
public class ChangePasswordCommand extends Command {
    private User loggedOnUser;  // reference to the user currently logged in

    // constructor with necessary fields
    public ChangePasswordCommand(ProductCatalog productCatalog, User loggedOnUser) {
        super(productCatalog, loggedOnUser);
        this.loggedOnUser = loggedOnUser;
    }

    @Override
    public void Execute() {
        Scanner scan = new Scanner(System.in);  // dont closing if using shared scanner

        try {
            // prompt for current password
            System.out.println("Enter current password: ");
            String curPass = scan.nextLine().trim();

            // check if current password is correct
            if (loggedOnUser.getHashedPassword().equals(SecurityOperations.GetPasswordHash(curPass))) {
                // prompt for new password
                System.out.println("Enter new password: ");
                String newPass = scan.nextLine();

                // change password using SecurityOperations
                SecurityOperations.ChangePassword(loggedOnUser.getUsername(), curPass, newPass);
                System.out.println("Password changed successfully!");
            } else {
                System.out.println("Incorrect password.");
            }
        } catch (Exception e) {
            System.out.println("Error changing password: " + e.getMessage());
        }
    }
}
