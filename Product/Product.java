// hannah ali - hma220003

package LinkedInventoryManagement.Product;

public class Product implements Comparable<Product> {

    private int id;             // unique id for the product
    private String name;        // name of the product
    private double cost;        // cost price of the product
    private int quantity;       // quantity available in inventory
    private int margin;         // profit margin percentage

    public Product(int id, String name, double cost, int quantity, int margin) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.margin = margin;
    }

    // getters to access the fields

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMargin() {
        return margin;
    }

    // setters to modify the fields

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    @Override
    public int compareTo(Product productToCompare) {
        return Integer.compare(this.id, productToCompare.id);
    }
}
