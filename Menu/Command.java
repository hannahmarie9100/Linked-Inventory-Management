// hannah ali - hma220003

package LinkedInventoryManagement.Menu;

import java.lang.reflect.Constructor;

import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * Command - Abstract class to represent commands within the inventory management system.
 */
public abstract class Command {
    protected ProductCatalog productCatalog;  // product catalog reference
    protected User loggedOnUser;              // current logged-in user

    // constructor for command with dependencies
    public Command(ProductCatalog productCatalog, User loggedOnUser) {
        this.productCatalog = productCatalog;
        this.loggedOnUser = loggedOnUser;
    }

    
    //CreateCommandDynamically - creates a specific command instance based on class name
    public static Command CreateCommandDynamically(ProductCatalog productCatalog, User user, String commandClassName) {
        Command command = null;
        String packageName = "LinkedInventoryManagement.Menu";

        try {
            // get the class using reflection
            Class<?> commandClass = Class.forName(packageName + "." + commandClassName);

            // get the constructor that matches the expected parameters
            Constructor<?> constructor = commandClass.getConstructor(ProductCatalog.class, User.class);

            // create a new instance of the command
            command = (Command) constructor.newInstance(productCatalog, user);
        } catch (Exception e) {
            System.out.println("Error creating command dynamically: " + e.getMessage());
        }

        return command;
    }

    // abstract execute method that each command subclass must implement
    public abstract void Execute();
}
