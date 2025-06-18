// hannah ali - hma220003

package LinkedInventoryManagement.PersistentStorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Menu.Command;
import LinkedInventoryManagement.Menu.MenuItem;

/**
 * menulistoperations: this class manages reading, writing, and updating operations 
 * for the menulist.dat file.
 */
public class MenuListOperations {

    private String fileName;                    // name of the menu list file
    private InventoryLinkedList<MenuItem> list; // linked list to hold menu items

    // constructor initializes the file name and linked list for menu items
    public MenuListOperations(String fileName) {
        this.fileName = fileName;
        list = new InventoryLinkedList<>();
    }


    public InventoryLinkedList<MenuItem> readMenuFile() {
        
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(",");
                if(details.length >= 3) {
                    String desc = details[0].trim();  // description of the menu item
                    boolean isRestricted = Boolean.parseBoolean(details[1].trim());  // restriction flag
                    String commandName = details[2].trim();  // command class name
                    
                    // dynamically create command using the command name
                    Command command = createCommand(commandName);
                    if(command != null) {
                        list.insert(list.getLength(), new MenuItem(command, 0, desc, isRestricted));
                    } else {
                        System.out.println("error: command creation failed for " + commandName);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("error reading menu list file: " + e.getMessage());
        }

        return list;
    }

    public void writeToFile() {
        try (FileWriter writer = new FileWriter(fileName)) {

            // iterate through menu items and write each to file
            for(int i = 0; i < list.getLength(); i++) {
                MenuItem item = list.getElement(i);
                writer.write(item.getDescription() + ", " +
                             item.getIsRestricted() + ", " +
                             item.getCommand().getClass().getSimpleName() + "\n");
            }

        } catch(IOException e) {
            System.out.println("error writing to menu list file: " + e.getMessage());
        }
    }


    public void updateMenuItem(MenuItem item) {
        for(int i = 0; i < list.getLength(); i++) {
            // match based on command class name to find the correct menu item
            if(list.getElement(i).getCommand().getClass().getSimpleName().equals(item.getCommand().getClass().getSimpleName())) {
                list.setElement(i, item);  // replace old item with updated item
                break;
            }
        }

        writeToFile();  // save updated list to file
    }

    
    private Command createCommand(String commandName) {
        try {
            String fullName = "LinkedInventoryManagement.Menu." + commandName;  // full path of command class
            Class<?> commandClass = Class.forName(fullName);

            if(Command.class.isAssignableFrom(commandClass)) {
                return (Command) commandClass.getDeclaredConstructor().newInstance();
            } 
        } catch(Exception e) {
            System.out.println("error creating command for " + commandName + ": " + e.getMessage());
        }

        return null;  // return null if command creation fails
    }
}
