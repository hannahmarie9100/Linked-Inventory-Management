// hannah ali - hma220003

package LinkedInventoryManagement.Menu;

import java.util.Scanner;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * DisplayProductInfoCommand - Command to display information about a specific product by ID
 */
public class DisplayProductInfoCommand extends Command {

    // constructor with necessary fields
    public DisplayProductInfoCommand(ProductCatalog productCatalog, User loggedOnUser) {
        super(productCatalog, loggedOnUser);
    }

    @Override
    public void Execute() {
        Scanner scan = new Scanner(System.in);  // avoid closing if using shared scanner

        try {
            // prompt for product ID to display
            System.out.println("Enter the Product ID to display:");
            int productId = scan.nextInt();
            scan.nextLine();  // consume newline character

            // create a temporary product to locate the actual product in the catalog
            Product tempProduct = new Product(productId, "", 0.0, 0, 0);

            // retrieve the product information if it exists
            Product foundProduct = productCatalog.GetProduct(tempProduct);  // assuming a method like GetProduct exists

            if (foundProduct != null) {
                System.out.println("Id  Name        Cost     Quantity  Retail");
                System.out.println("-----------------------------------------");
                System.out.printf("%d   %s   $%.2f   %d       $%.2f\n",
                        foundProduct.getId(),
                        foundProduct.getName(),
                        foundProduct.getCost(),
                        foundProduct.getQuantity(),
                        foundProduct.getCost() + (foundProduct.getMargin() * foundProduct.getCost() / 100));
            } else {
                System.out.println("Product with ID " + productId + " not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid Product ID.");
        }
    }
}
