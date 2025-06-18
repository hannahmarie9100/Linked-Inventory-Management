// hannah ali - hma220003

package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * DisplayInventoryCommand - Command to display all inventory items
 */
public class DisplayInventoryCommand extends Command {
    
    // constructor to initialize with product catalog and logged-on user
    public DisplayInventoryCommand(ProductCatalog productCatalog, User loggedOnUser) {
        super(productCatalog, loggedOnUser);
    }

    @Override
    public void Execute() {
        // print header for inventory display
        System.out.println("Id  Name  Cost  Quantity  Retail Price");
        System.out.println("---------------------------------------");

        // display inventory list from product catalog
        String inventoryList = productCatalog.PrintInventoryList();
        System.out.println(inventoryList);
    }
}
