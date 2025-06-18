// hannah ali - hma220003

package LinkedInventoryManagement.Common;

// node class to be used in linked list
public class Node<E> {
    E data;           // stores the actual data for this node
    Node<E> next;     // reference to the next node in the list

    // constructor to initialize node with data
    Node(E data) {
        this.data = data;
        this.next = null;  // initially, next is set to null
    }

    // returns the data stored in this node
    public E getData() {
        return data;
    }

    // returns the next node
    public Node<E> getNext() {
        return next;
    }

    // sets the data for this node
    public void setData(E data) {
        this.data = data;
    }

    // sets the reference to the next node
    public void setNext(Node<E> next) {
        this.next = next;
    }
}
