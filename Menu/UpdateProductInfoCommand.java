// hannah ali - hma220003

package LinkedInventoryManagement.Menu;

import java.util.Scanner;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * UpdateProductInfoCommand - Command to update information about a specific product
 */
public class UpdateProductInfoCommand extends Command {

    // constructor with necessary fields
    public UpdateProductInfoCommand(ProductCatalog productCatalog, User loggedOnUser) {
        super(productCatalog, loggedOnUser);
    }

    @Override
    public void Execute() {
        Scanner scan = new Scanner(System.in);  // avoid closing if shared

        try {
            // prompt for product ID
            System.out.println("Enter the Product ID to update:");
            int productId = scan.nextInt();
            scan.nextLine();  // clearing newline

            // find product by ID
            Product tempProduct = new Product(productId, null, 0, 0, 0);
            boolean finding = productCatalog.FindProduct(tempProduct);

            if (finding != false) {
                // prompt for updated information
                System.out.println("Enter new product name:");
                String newName = scan.nextLine();

                System.out.println("Enter new product cost:");
                double newCost = scan.nextDouble();

                System.out.println("Enter new product quantity:");
                int newQuantity = scan.nextInt();

                System.out.println("Enter new product margin:");
                int newMargin = scan.nextInt();

                // set updated values
                tempProduct.setName(newName);
                tempProduct.setCost(newCost);
                tempProduct.setQuantity(newQuantity);
                tempProduct.setMargin(newMargin);

                // update product in catalog
                productCatalog.AddUpdateProduct(tempProduct);
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product with ID " + productId + " not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
    }
}
