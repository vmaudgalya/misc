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
    nullCheck(data);
    nullCheck(newData);
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

  /******************************************
  * Iteratively finds the length of the list.
  * Time: O(n)
  * Space: O(1)
  *******************************************/
  public int ilength() {
    Node current = head;
    int count = 0;
    while (current != null) {
      current = current.next;
      count++;
    }
    return count;
  }

  /******************************************
  * Recursively finds the length of the list.
  * Time: O(n)
  * Space: O(1)
  *******************************************/
  public int rlength() {
    return rlength(head);
  }
  /* Recursive helper */
  private int rlength(Node n) {
    if (n == null) {
      return 0;
    } else {
      return 1 + rlength(n.next);
    }
  }

  /******************************************
  * Returns true if list contains the element
  * Iterative method
  * Time: O(n)
  * Space: O(1)
  *******************************************/
  public boolean icontains(Item data) {
    nullCheck(data);
    if (isEmpty()) {
      return false;
    }
    Node first = head;
    Node last = tail;
    while (first != last) {
      if (first.data == data || last.data == data) {
        return true;
      } else {
        first = first.next;
        last = last.prev;
      }
    }
    return false;
  }

  /******************************************
  * Returns true if list contains the element
  * Recursive method
  * Time: O(n)
  * Space: O(1)
  *******************************************/
  public boolean rcontains(Item data) {
    Node cur = head;
    return rcontains(cur, data);
  }

  private boolean rcontains(Node current, Item data) {
    if (current == null) {
      return false;
    }
    
    if (current.data == data) {
      return true;
    } else {
      return rcontains(current.next, data);
    }
  }

  /*************************************************************************************
  * Returns nth element from the end.
  * Note - this can be done more efficiently using the tail pointer,
  * however the purpose of this exercise is to be achieved using only the head pointer.
  * Time: O(n)
  * Space: O(1)
  *************************************************************************************/
  public Item nthFromEnd(int n) {
    if (n > size) {
      throw new java.util.NoSuchElementException("Element exceeds list size!");
    }
    Node behind = head;
    Node ahead = head;
    int count = 0;
    while (count < n) {
      ahead = ahead.next;
      count++;
    }
    while (ahead != null) {
      ahead = ahead.next;
      behind = behind.next;
    }
    return behind.data;
  }

  /******************
  * Deletes the list.
  * Time: O(1)
  * Space: O(1)
  *******************/
  public void delete() {
    head = null;
  }

  /*************************************************
  * Counts occurences of the given data in the list.
  * Time: O(n)
  * Space: O(1)
  **************************************************/
  public int countOccurences(Item data) {
    nullCheck(data);
    Node current = head;
    int count = 0;
    while (current != null) {
      if (current.data == data) {
        count++;
      }
      current = current.next;
    }
    return count;
  }


  /*****************************************
  * Returns true if the list contains a loop
  * Time: O(n)
  * Space: O(1)
  ******************************************/
  // public boolean hasLoop(Node n) {
  //   Node slow = n;
  //   Node fast = n;

  // }

  /***********************************
  * Returns element at provided index
  * Time: O(n)
  * Space: O(1)
  ************************************/
  public Item valueAt(int index) {
    if (head == null) {
      throw new java.util.NoSuchElementException("List is empty!");
    }
    if (index > size-1) {
      throw new ArrayIndexOutOfBoundsException("Index out of bounds");
    }
    int count = 0;
    Node current = head;
    while (count != index) {
      current = current.next;
      count++;
    }
    return current.data;
  }

  /**
  *
  *
  *
  */

  /***************************************
  * Prints the middle element of the list.
  * Time: O(n)
  * Space: O(1)
  ****************************************/
  public Item printMiddle() {
    Node slow = head;
    Node fast = head;
    while ((fast != null) && (fast.next != null)) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow.data;
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

  public boolean isPalindrome() {
    if(head == null) {
        return true;
    }
    Node p1 = head;
    Node p2 = head;
    Node p3 = p1.next;
    Node pre = p1;
    while(p2.next != null && p2.next.next != null) {
      p2 = p2.next.next;
      pre = p1;
      p1 = p3;
      p3 = p3.next;
      p1.next = pre;
    }
    // System.out.println(toString());

    if(p2.next == null) {
      p1 = p1.next;
    }
    
    while(p3 != null) {
      if(!p1.data.equals(p3.data)) {
        return false;
      }
      p1 = p1.next;
      p3 = p3.next;
    }
    // System.out.println(toString());

    return true;
  }

  /* For testing purposes */
  public static void main(String[] args) {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    // for (int i = 1; i <= 5; i++) {
    //   list.insert(i);  
    // }
    // System.out.println("Original: " + list);
    // list.reverse();
    // System.out.println("After reversing: " + list);
    // list.append(44);
    // System.out.println("Appended 44: " + list);
    // list.append(1, 43); // append 43 after 1
    // System.out.println("Appended 43 after 1: " + list);
    // list.append(62);
    // System.out.println("Appended 62: " + list);
    // System.out.println("Removing " + list.remove(43) + ".\n" + list);
    // System.out.println("Iteratively find length: " + list.ilength());
    // System.out.println("Recursively find length: " + list.rlength());
    // System.out.println("Does the list contain 5? " + list.contains(5));
    // System.out.println("Does the list contain 99? " + list.contains(99));
    list.append(1);
    list.append(2);
    list.append(3);
    list.append(2);
    list.append(1);
    System.out.println(list.isPalindrome());
    System.out.println(list.toString());

  }

}