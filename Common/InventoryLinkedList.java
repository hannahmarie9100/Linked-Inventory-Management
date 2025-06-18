// hannah ali - hma220003

package LinkedInventoryManagement.Common;

/**
 * InventoryLinkedList - a simple linked list implementation
 */
public class InventoryLinkedList<E extends Comparable<E>> {
    
    private Node<E> head;  // reference to the first node

    // returns the first element in the list
    public E getFirst() {
        if (head != null) {
            return head.getData();  // get data from the head node
        } else {
            return null;  // return null if list is empty
        }
    }

    // returns the last element in the list
    public E getLast() {
        if (head == null) {
            return null;  // list is empty, so no last element
        } else {
            Node<E> temp = head;
            while (temp.getNext() != null) {  // keep moving to the next node
                temp = temp.getNext();
            }
            return temp.getData();  // last node found
        }
    }

    // inserts a new element at a specific index
    public void insert(int index, E element) {
        Node<E> newNode = new Node<>(element);

        if (index == 0) {  // inserting at the start
            newNode.setNext(head);
            head = newNode;
        } else if (index > 0) {  // inserting at a specific index
            Node<E> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
                if (temp == null) {
                    throw new IndexOutOfBoundsException("Index is larger than list size.");
                }
            }
            newNode.setNext(temp.getNext());  // link new node
            temp.setNext(newNode);
        }
    }

    // removes and returns the element at a specific index
    public E remove(int index) {
        if (index < 0 || index >= getLength()) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        Node<E> temp = head;
        if (index == 0) {  // removing the first element
            head = head.getNext();
            return temp.getData();
        }

        for (int i = 0; i < index - 1; i++) {  // find the node before the one to remove
            temp = temp.getNext();
        }

        Node<E> temp2 = temp.getNext();  // the node to remove
        temp.setNext(temp2.getNext());  // bypass the removed node
        return temp2.getData();
    }

    // returns a string representation of the list
    public String toString() {
        StringBuilder string = new StringBuilder();
        Node<E> temp = head;
        while (temp != null) {
            string.append(temp.getData().toString()).append(" ");
            temp = temp.getNext();
        }
        return string.toString();
    }

    // checks if the list contains a specific element
    public boolean contains(E element) {
        Node<E> temp = head;
        while (temp != null) {
            if (temp.getData().equals(element)) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    // sets the element at a specific index to a new value
    public void setElement(int index, E element) {
        Node<E> temp = head;

        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }

        for (int i = 0; i < index; i++) {
            if (temp == null) {
                throw new IndexOutOfBoundsException("Index out of bounds.");
            }
            temp = temp.getNext();
        }

        temp.setData(element);  // set new data at the specified index
    }

    // gets the element at a specific index
    public E getElement(int index) {
        Node<E> temp = head;

        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }

        for (int i = 0; i < index; i++) {
            if (temp == null) {
                throw new IndexOutOfBoundsException("Index out of bounds.");
            }
            temp = temp.getNext();
        }

        return temp.getData();  // return the data at the specified index
    }

    // gets the length of the list
    public int getLength() {
        int size = 0;
        Node<E> temp = head;
        while (temp != null) {
            size++;
            temp = temp.getNext();
        }
        return size;
    }
}
