/****************************************************************
* DoublyLinkedList API
* Implemented as a doubly-linked list with head and tail pointers
* for efficient operations.
* @author Varun Maudgalya
*****************************************************************/
public class DoublyLinkedList<Item> {
  
  private Node head;
  private Node tail;
  private int size;

  private class Node {
    Item data;
    Node next; // No getters or setters because inner class
    Node prev; // is invisible to client and is only used internally.
  }

  /******************************
  * Add to the front of the list.
  * Time: O(1)
  * Space: O(1)
  *******************************/
  public void insert(Item data) {
    nullCheck(data);
    if (isEmpty()) {
      head = new Node();
      head.data = data;
      tail = head;
    } else {
      Node first = new Node();
      first.data = data;
      first.next = head;
      head.prev = first;
      head = first;
    }
    size++;
  }

  /****************************
  * Add to the end of the list.
  * Time: O(1)
  * Space: O(1)
  *****************************/
  public void append(Item data) {
    nullCheck(data);
    if (isEmpty()) {
      head = new Node();
      head.data = data;
      tail = head;
    } else {
      Node last = new Node();
      last.data = data;
      last.prev = tail;
      tail.next = last;
      tail = last;
    }
    size++;
  }

  /**
  * Add after first occurence of specified element in the list.
  * @param data - the specified element to add after
  * @param newData - the data to add
  * Time: O(n)
  * Space: O(1)
  */
  public void append(Item data, Item newData) {
    if (data == tail.data) {
      append(newData);
    } else {
      Node current = head;
      while (current.data != data) {
        current = current.next;
      }
      Node element = new Node();
      element.data = newData;
      element.next = current.next;
      current.next.prev = element;
      element.prev = current;
      current.next = element;
    }
    size++;
  }

  /********************************
  * Removes first occurence of data
  * Time: O(n)
  * Space: O(1)
  *********************************/
  public Item remove(Item data) {
    emptyCheck();
    if (head.data == data) {
      Node victim = head;
      head.next.prev = null;
      head = head.next;
      return victim.data;
    } else {
      Node current = head.next;    
      while (current.data != data) {
        current = current.next;
      }
      current.next.prev = current.prev;
      current.prev.next = current.next;
      current.prev = null;
      current.next = null;
      return current.data;
    }
  }

  /*******************
  * Reverses the list.
  * Time: O(n)
  * Space: O(1)
  *******************/
  public void reverse() {
    if (isEmpty()) {
      return;
    }
    Node cur = head;
    Node nxt = cur.next;
    cur.prev = cur.next;
    cur.next = null;
    cur = nxt;
    nxt = cur.next;
    while (nxt != null) {
      cur.next = cur.prev;
      cur.prev = nxt;
      cur = nxt;
      nxt = cur.next;
    }
    cur.next = cur.prev;
    cur.prev = null;
    tail = head;
    head = cur;
  }

  /***********************************
  * Returns true if the list is empty.
  * Time: O(1)
  * Space: O(1)
  ***********************************/
  public boolean isEmpty() {
    return head == null;
  }

  /******************************
  * Returns the size of the list.
  * Time: O(1)
  * Space: O(1)
  *******************************/
  public int size() {
    return size;
  }

  /*****************
  * Prints the list.
  * O(n) Time
  * O(1) Space
  ******************/
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (isEmpty()) {
      sb.append("[]");
    } else {
      sb.append("[");
      for (Node cur = head; (cur != null); cur = cur.next) {
        if (cur != tail) {
          sb.append(cur.data + ", ");
        } else {
          sb.append(cur.data + "]");
        }
      }
    }
    return sb.toString();
  }

  /* Helper Methods */
  private void nullCheck(Item data) {
    if (data == null) {
      throw new NullPointerException("Null items are not allowed in the list.");
    }
  }

  private void emptyCheck() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("Cannot remove from empty list!");
    }
  }

  /* For testing purposes */
  public static void main(String[] args) {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    for (int i = 1; i <= 5; i++) {
      list.insert(i);  
    }
    System.out.println("Original: " + list);
    list.reverse();
    System.out.println("After reversing: " + list);
    list.append(44);
    System.out.println("Appended 44: " + list);
    list.append(1, 43); // append 43 after 1
    System.out.println("Appended 43 after 1: " + list);
    list.append(62);
    System.out.println("Appended 62: " + list);
    System.out.println("Removing " + list.remove(43) + ".\n" + list);
  }

}