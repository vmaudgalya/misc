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
        return victim;
      } else {
        Node current = head;
        while (current.next != null) {
          if (current.next.data == data) {
            Item victim = current.next.data;
            current.next = current.next.next;
            return victim;
          }
          current = current.next;
        }
        return null;
      }
    }
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





}