// hannah ali - hma220003

package LinkedInventoryManagement.Menu;

import java.util.Scanner;

import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * AddProductCommand
 */
public class AddProductCommand extends Command {
    private ProductCatalog productCatalog;

    // constructor with parameters
    public AddProductCommand(ProductCatalog productCatalog, User loggedOnUser) {
        super(productCatalog, loggedOnUser);
        this.productCatalog = productCatalog;
    }

    @Override
    public void Execute() {
        Scanner scan = new Scanner(System.in);  // remove scan.close() later if using shared Scanner

        try {
            // prompt for product details
            System.out.println("Enter product ID:");
            int id = Integer.parseInt(scan.nextLine());
            System.out.println("Enter product name:");
            String name = scan.nextLine();
            System.out.println("Enter product cost:");
            double cost = Double.parseDouble(scan.nextLine());
            System.out.println("Enter product quantity:");
            int quantity = Integer.parseInt(scan.nextLine());
            System.out.println("Enter product margin (%):");
            int margin = Integer.parseInt(scan.nextLine());

            // create new product and add/update it in the catalog
            Product newProd = new Product(id, name, cost, quantity, margin);
            productCatalog.AddUpdateProduct(newProd);

            System.out.println("Product added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers for ID, cost, quantity, and margin.");
        }
    }
}
