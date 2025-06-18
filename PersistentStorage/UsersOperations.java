// hannah ali - hma220003

package LinkedInventoryManagement.PersistentStorage;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Security.User;

public class UsersOperations {

    private String fileName;                    // name of the users file
    private InventoryLinkedList<User> list;     // linked list to hold user objects

    // constructor to initialize the filename and linked list
    public UsersOperations(String fileName) {
        this.fileName = fileName;
        list = new InventoryLinkedList<>();
    }

    public InventoryLinkedList<User> readInventoryFile() {

        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);

            // read each line of the file and parse user details
            while(scan.hasNextLine()) {
                String curLine = scan.nextLine();
                String[] details = curLine.split(",");
                
                if (details.length >= 5) {
                    String username = details[2].trim();
                    String password = details[3].trim();
                    boolean isManager = Boolean.parseBoolean(details[4].trim());
                    
                    User user = new User(username, password, isManager);  // create user with username, password, and role
                    user.setFirstName(details[0].trim());                 // set first name
                    user.setLastName(details[1].trim());                  // set last name
                    
                    list.insert(list.getLength(), user);  // add user to the list
                }
            }

            scan.close();  // close scanner after reading

        } catch (Exception e) {
            System.out.println("error reading users file: " + e.getMessage());
        }

        return list;
    }

    public void writeToFile() {
        try (FileWriter writer = new FileWriter(fileName)) {

            // iterate through users and write each to file
            for(int i = 0; i < list.getLength(); i++) {
                User user = list.getElement(i);
                
                writer.write(user.getFirstName() + ", " +
                             user.getLastName() + ", " + 
                             user.getUsername() + ", " + 
                             user.getHashedPassword() + ", " +
                             user.getIsManager() + "\n");
            }

        } catch(Exception e) {
            System.out.println("error writing to users file: " + e.getMessage());
        }
    }

    public void updateUser(User user) {
        for(int i = 0; i < list.getLength(); i++) {
            // check if username matches to locate the correct user
            if(list.getElement(i).getUsername().equals(user.getUsername())) {
                list.setElement(i, user);  // replace old user with updated user
                break;
            }
        }

        writeToFile();  // save updated list to file
    }
}
