// hannah ali - hma220003

package LinkedInventoryManagement.Product;

import LinkedInventoryManagement.Common.InventoryLinkedList;

public class ProductCatalog {

    private InventoryLinkedList<Product> list;  // linked list to store products

    // constructor initializes the product list
    public ProductCatalog() {
        list = new InventoryLinkedList<>();
    }

    public void AddUpdateProduct(Product product) {
        boolean found = false;

        // search for the product by id
        for (int i = 0; i < list.getLength(); i++) {
            Product curIndex = list.getElement(i);
            if (curIndex.getId() == product.getId()) { // if product found, update it
                curIndex.setName(product.getName());
                curIndex.setCost(product.getCost());
                curIndex.setQuantity(product.getQuantity());
                curIndex.setMargin(product.getMargin());
                found = true;
                break;
            }
        }

        // if product not found, add it to the list
        if (!found) {
            list.insert(list.getLength(), product);
        }
    }

    
    public boolean RemoveProduct(Product product) {
        boolean isSuccess = false;

        // search and remove product by id
        for (int i = 0; i < list.getLength(); i++) {
            if (list.getElement(i).getId() == product.getId()) {
                list.remove(i);
                isSuccess = true;
                break;
            }
        }

        return isSuccess;
    }

    public boolean FindProduct(Product product) {
        boolean isSuccess = false;

        // search for product by id
        for (int i = 0; i < list.getLength(); i++) {
            if (list.getElement(i).getId() == product.getId()) {
                isSuccess = true;
                break;
            }
        }

        return isSuccess;
    }

    public String PrintProductInformation(Product product) {
        String productInformation = "";

        // find and format product details
        for (int i = 0; i < list.getLength(); i++) {
            Product curProd = list.getElement(i);
            if (curProd.getId() == product.getId()) {
                double price = curProd.getCost() + (curProd.getMargin() * (curProd.getCost() / 100));
                productInformation += curProd.getId() + "\t" +
                                      curProd.getName() + "\t" +
                                      curProd.getCost() + "\t" +
                                      curProd.getQuantity() + "\t" +
                                      price + "\n";
                break;
            }
        }

        return productInformation.isEmpty() ? null : productInformation;
    }

    public String PrintInventoryList() {
        StringBuilder inventoryInformation = new StringBuilder();

        // loop through products and format their details
        for (int i = 0; i < list.getLength(); i++) {
            Product cur = list.getElement(i);
            inventoryInformation.append("id: ").append(cur.getId()).append(", name: ").append(cur.getName())
                                .append(", cost: ").append(cur.getCost()).append(", quantity: ")
                                .append(cur.getQuantity()).append(", margin: ").append(cur.getMargin()).append("\n");
        }

        return inventoryInformation.toString();
    }
}
