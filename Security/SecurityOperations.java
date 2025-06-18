// hannah ali - hma220003

package LinkedInventoryManagement.Security;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import LinkedInventoryManagement.Common.ScannerFactory;

/**
 * securityoperations: handles user authentication, adding new users, removing users,
 * changing passwords, and hashing passwords for the inventory management system.
 */
public class SecurityOperations {

    public static User AuthenticateUser(String username, String password) {
        User authenticatedUser = null;

        // check for admin credentials
        if ((username.compareToIgnoreCase("admin") == 0) &&
            (GetPasswordHash(password).compareToIgnoreCase("58c536ed8facc2c2a293a18a48e3e120") == 0)) {
            authenticatedUser = new User(username, GetPasswordHash(password), true);
            authenticatedUser.setFirstName("Admin");
            authenticatedUser.setLastName("");
        } else {
            // look up the user in the users.dat file
            String hashedPassword = GetPasswordHash(password);

            Scanner scan = ScannerFactory.GetScannerInstance();

            while (scan.hasNextLine()) {
                String curLine = scan.nextLine();

                // if hashed password is found, set the authenticated user object
                if (curLine.contains(hashedPassword)) {
                    if (curLine.contains("true")) {
                        authenticatedUser = new User(username, hashedPassword, true);
                    } else {
                        authenticatedUser = new User(username, hashedPassword, false);
                    }
                }
            }
            ScannerFactory.CloseScannerInstance(); // closing scanner
        }
        return authenticatedUser;
    }

    public static void AddNewUser(User newUser) {
        try {
            FileWriter fileWriter = new FileWriter("Users.dat", true);
            String hashedPassword = GetPasswordHash(newUser.getHashedPassword());

            fileWriter.write(newUser.getFirstName() + ", ");
            fileWriter.write(newUser.getLastName() + ", ");
            fileWriter.write(newUser.getUsername() + ", ");
            fileWriter.write(hashedPassword + ", ");
            fileWriter.write(newUser.getIsManager() + "\n");

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong in AddNewUser function." + e);
        }
    }

    public static void RemoveUser(String userName) {
        File origFile = new File("Users.dat");
        File tempFile = new File("UserTemp.dat");

        try {
            Scanner scan = new Scanner(origFile);
            FileWriter writer = new FileWriter(tempFile);

            while (scan.hasNextLine()) {
                String cur = scan.nextLine();
                String[] user = cur.split(",");

                if (!user[2].trim().equalsIgnoreCase(userName.trim())) {
                    writer.write(cur + "\n");
                }
            }

            scan.close();
            writer.close();
            origFile.delete();
            tempFile.renameTo(origFile); // renaming file

        } catch (IOException e) {
            System.out.println("Error in RemoveUser: " + e);
        }
    }

    public static void ChangePassword(String username, String currentPassword, String newPassword) {
        File origFile = new File("Users.dat");
        File tempFile = new File("UserTemp.dat");

        try {
            Scanner scan = new Scanner(origFile);
            FileWriter writer = new FileWriter(tempFile);

            while (scan.hasNextLine()) {
                String cur = scan.nextLine();
                String[] user = cur.split(",");

                if (user[2].equalsIgnoreCase(username) && user[3].equalsIgnoreCase(currentPassword)) {
                    String newLine = user[0] + ", " + user[1] + ", " + user[2] + ", " + newPassword + ", " + user[4] + "\n";
                    writer.write(newLine + "\n");
                } else {
                    writer.write(cur + "\n");
                }
            }

            scan.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error in ChangePassword: " + e);
        }
    }

    public static String GetPasswordHash(String password) {
        String generatedPassword = null;

        try {
            byte[] salt = new byte[] {12, -12, 65, 61, 2, -6, -90, 12, 4, -7, -87, 2, 34, -102, 3, 115};
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedPassword;
    }
}
