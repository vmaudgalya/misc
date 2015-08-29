/****************************************************************
* SinglyLinkedList API
* Implemented as a singly-linked list with head pointer.
* List does not support null elements.
* @author Varun Maudgalya
*****************************************************************/
public class SinglyLinkedList<Item> {
  
  private Node head;
  private int size;


  private class Node {
    Item data;
    Node next;
  }

  /****************************
  * Add to the end of the list.
  * Time: O(n)
  * Space: O(1)
  *****************************/
  public void append(Item data) {
    if (isEmpty()) {
      head = new Node();
      head.data = data;
    } else {
      Node current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = new Node();
      current.next.data = data;
    }
    size++;
  }

  /******************************
  * Add to the front of the list.
  * Time: O(1)
  * Space: O(1)
  *******************************/
  public void insert(Item data) {
    if (isEmpty()) {
      head = new Node();
      head.data = data;
    } else {
      Node first = new Node();
      first.data = data;
      first.next = head;
      head = first;
    }
    size++;
  }

  /********************************
  * Removes first occurence of data
  * Time: O(n)
  * Space: O(1)
  *********************************/
  public Item remove(Item data) {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("The list is empty!");
    } else {
      if (head.data == data) {
        Item victim = head.data;
        head = head.next;
        size--;
        return victim;
      } else {
        Node current = head;
        while (current.next != null) {
          if (current.next.data == data) {
            Item victim = current.next.data;
            current.next = current.next.next;
            size--;
            return victim;
          }
          current = current.next;
        }
        return null;
      }
    }
  }

  /**********************************************
  * Returns the element that is nth from the end
  * Assumption that we are counting from 1
  * Time: O(n)
  * Space: O(1)
  ***********************************************/
  public Item nthFromEnd(int n) {
    if (n > size) {
      return null;
    }
    Node ahead = head;
    Node behind = head;
    for (int i = 0; i < n; i++) {
      ahead = ahead.next;
    }
    while (ahead != null) {
      behind = behind.next;
      ahead = ahead.next;
    }
    return behind.data;
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

  /***************************************
  * Returns true if list contains the data
  * Time: O(1)
  * Space: O(1)
  ***************************************/
  public boolean contains(Item data) {
    Node current = head;
    while (current != null) {
      if (current.data == data) {
        return true;
      } else {
        current = current.next;
      }
    }
    return false;
  }

  /***************************************
  * Returns the middle element of the list.
  * Time: O(n)
  * Space: O(1)
  ****************************************/
  public Item getMiddle() {
    Node slow = head;
    Node fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow.data;
  }

  /*****************
  * Deletes the list
  * Time: O(1)
  * Space: O(1)
  ******************/
  public void deleteList() {
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
  * Tortoise & Hare algorithm
  * Time: O(n)
  * Space: O(1)
  ******************************************/
  public boolean hasLoop() {
    Node slow = head;
    Node fast = head.next;
    while (slow != null && fast != null && fast.next != null) {
      if (slow == fast) {
        return true;
      } else {
        slow = slow.next;
        fast = fast.next;
      }
    }
    return false;
  }

  /*******************
  * Reverses the list
  * Time: O(n)
  * Space: O(1)
  *******************/
  public void reverse() {
    if (size == 1 || isEmpty()) {
      return;
    }
    Node current = head;
    Node next = head;
    Node previous = null;
    while (current != null) {
      next = current.next;
      current.next = previous;
      previous = current;
      current = next;
    }
    head = previous;
  }

  /******************************************
  * Returns true if the list is a palindrome.
  * Note: This currently modifies the list
  * Time: O(n)
  * Space: O(1)
  ******************************************/
  public boolean isPalindrome() {
    if (isEmpty() || size == 1) {
      return true;
    }
    Node slow = head;
    Node fast = head.next;
    while (slow != null && fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    Node previous = null;
    Node current = head;
    Node next = head;
    Node flag = slow.next;
    while (current != flag) {
      next = current.next;
      current.next = previous;
      previous = current;
      current = next;
    }
    if (fast == null){
      previous = previous.next;
    }
    while (previous != null && current != null) {
      if (previous.data != current.data) {
        return false;
      } else {
        previous = previous.next;
        current = current.next;
      }
    }
    return true;
  }

  /*****************
  * Prints the list.
  * Time: O(n)
  * Space: O(1)
  ******************/
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (isEmpty()) {
      sb.append("[]");
    } else {
      sb.append("[ ");
      for (Node cur = head; cur != null; cur = cur.next) {
        if (cur.next != null) {
          sb.append(cur.data + ", ");
        } else {
          sb.append(cur.data + " ]");
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
    SinglyLinkedList<String> animals = new SinglyLinkedList<String>();
    System.out.println("List is initially empty: " +  animals);
    animals.append("cat");
    animals.append("dog");
    animals.append("horse");
    animals.append("pigeon");
    animals.append("fish");
    animals.append("seagull");
    System.out.println("After adding some animals: " + animals);
    animals.reverse();
    System.out.println("Reversed the list: " + animals);
    System.out.println("Middle element: " + animals.getMiddle());
    System.out.println("Does the list contain pigeon? " + animals.contains("pigeon"));
    System.out.println("Does the list contain BMW? " + animals.contains("BMW"));
    System.out.println("The size of the list is: " + animals.size());
    System.out.println("2nd from end: " + animals.nthFromEnd(2));
    animals.deleteList();
    System.out.println("Deleted list: " + animals);
    palindromeTest(animals);
    

  }

  public static void palindromeTest(SinglyLinkedList<String> list) {
    list.append("A");
    list.append("B");
    list.append("C");
    System.out.println("Is the list " + list + " a palindrome? " + list.isPalindrome());
    list.deleteList();
    list.append("A");
    list.append("B");
    list.append("C");
    list.append("B");
    list.append("A");
    System.out.println("Is the list " + list + " a palindrome? " + list.isPalindrome());
    list.deleteList();
    list.append("A");
    list.append("B");
    list.append("C");
    list.append("C");
    list.append("B");
    list.append("A");
    System.out.println("Is the list " + list + " a palindrome? " + list.isPalindrome());
  }





}