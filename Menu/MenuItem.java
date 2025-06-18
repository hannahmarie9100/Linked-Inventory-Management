// hannah ali - hma220003

package LinkedInventoryManagement.Menu;

/**
 * MenuItem - represents an item in the menu with associated command, description, and restriction level
 */
public class MenuItem implements Comparable<MenuItem> {
    private Command command;
    private int optionNumber;
    private String description;
    private boolean isRestricted;

    // constructor to initialize menu item
    public MenuItem(Command command, int optionNumber, String description, boolean isRestricted) {
        this.command = command;
        this.optionNumber = optionNumber;
        this.description = description;
        this.isRestricted = isRestricted;
    }

    @Override
    public int compareTo(MenuItem menuItemToCompare) {
        return Integer.compare(this.optionNumber, menuItemToCompare.optionNumber);
    }

    // getters
    public Command getCommand() {
        return command;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsRestricted() {
        return isRestricted;
    }

    // override toString for easy display of menu item information
    @Override
    public String toString() {
        return String.format("Option %d: %s (Restricted: %s)", optionNumber, description, isRestricted);
    }
}
