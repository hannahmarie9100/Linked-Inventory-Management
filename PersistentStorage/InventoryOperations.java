// hannah ali - hma220003

package LinkedInventoryManagement.PersistentStorage;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Product.Product;


public class InventoryOperations {

    private String fileName;                     // Name of the inventory file
    private InventoryLinkedList<Product> list;    // Linked list to hold products

    // Constructor to initialize the file name and linked list
    public InventoryOperations(String fileName) {
        this.fileName = fileName;
        list = new InventoryLinkedList<>();
    }

    public InventoryLinkedList<Product> readInventoryFile() {

        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);

            // Read each line of the file and parse product details
            while(scan.hasNextLine()) {
                String curLine = scan.nextLine();
                String[] details = curLine.split(",");
                if (details.length >= 5) {
                    int id = Integer.parseInt(details[0].trim());
                    String name = details[1].trim();
                    double cost = Double.parseDouble(details[2].trim());
                    int quantity = Integer.parseInt(details[3].trim());
                    int margin = Integer.parseInt(details[4].trim());

                    // Insert product into linked list
                    list.insert(list.getLength(), new Product(id, name, cost, quantity, margin));
                }
            }
            scan.close();  // Close the scanner after reading

        } catch (Exception e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }

        return list;
    }

    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter(fileName);  // Open FileWriter in overwrite mode

            // Write each product's details to the file in CSV format
            for(int i = 0; i < list.getLength(); i++) {
                Product prod = (Product) list.getElement(i);
                writer.write(prod.getId() + "," +
                             prod.getName() + "," +
                             prod.getCost() + "," +
                             prod.getQuantity() + "," +
                             prod.getMargin() + "\n");
            }

            writer.close();  // Close writer after saving

        } catch (Exception e) {
            System.out.println("Error writing to inventory file: " + e.getMessage());
        }
    }

    public void updateProduct(Product prod) {
        // Iterate through list to find product with matching ID
        for(int i = 0; i < list.getLength(); i++) {
            if(list.getElement(i).getId() == prod.getId()) {
                list.setElement(i, prod);  // Update product in list
                break;
            }
        }

        // Save updated list to file
        writeToFile();
    }
}
