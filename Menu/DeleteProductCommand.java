// hannah ali - hma220003

package LinkedInventoryManagement.Menu;

import java.util.Scanner;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * DeleteProductCommand - Command to delete a product by its ID
 */
public class DeleteProductCommand extends Command {

    // constructor with product catalog and logged-on user
    public DeleteProductCommand(ProductCatalog productCatalog, User loggedOnUser) {
        super(productCatalog, loggedOnUser);
    }

    @Override
    public void Execute() {
        Scanner scan = new Scanner(System.in);  // avoid closing if using shared scanner

        try {
            // prompt for product ID to delete
            System.out.println("Enter the Product ID to delete:");
            int productId = scan.nextInt();
            scan.nextLine();  // consume newline character

            // create a temporary product object to locate the product by ID
            Product tempProduct = new Product(productId, "", 0.0, 0, 0);

            // check if the product exists
            if (productCatalog.FindProduct(tempProduct)) {
                productCatalog.RemoveProduct(tempProduct);
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Product with ID " + productId + " does not exist.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }
}
