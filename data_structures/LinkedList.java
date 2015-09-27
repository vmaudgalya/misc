/****************************************************************
* LinkedList (for problem-solving purposes, not an API)
* Implemented as a singly-linked list with head pointer.
* List does not support null elements.
*
* @author Varun Maudgalya
*****************************************************************/
public class LinkedList<Item extends Comparable<Item> {
  
  private int size;
  private Node head;

  public void add(Node n) {
    if (isEmpty()) {
      head = n;
    } else {
      Node current = head;
      while (current.hasNext()) {
        current = current.getNext();
      }
      current.setNext(n);
    }
    size++;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public int size() {
    return size;
  }

  public String toString() {
    StringBuilder builder = new StringBuilder("[ ");
    for (Node current = head; current != null; current = current.getNext()) {
      builder.append(current.toString() + " ");
    }
    builder.append("]");
    return builder.toString();
  }

  public void mergeSort() {
    head = mergeSort(head);
  }

  private Node mergeSort(Node head) {
    if (head == null || head.getNext() == null) {
      return head;
    }
    Node middle = getMiddle(head);
    Node secondHalf = middle.getNext();
    middle.setNext(null);
    return merge(mergeSort(head), mergeSort(secondHalf));
  }

  private Node merge(Node left, Node right) {
    if (left == null) {
      return right;
    } else if (right == null) {
      return left;
    }
    Node current = new Node();
    Node dummyHead = current;
    while (left != null && right != null) {
      if (less(left, right)) {
        current.setNext(left);
        left = left.getNext();
      } else {
        current.setNext(right);
        right = right.getNext();
      }
      current = current.getNext();
    }
    current.setNext((left == null ? right : left));
    return dummyHead.getNext();
  }

  private boolean less(Node left, Node right) {
    return (left.getData().compareTo(right.getData())) < 0;
  }

  private Node getMiddle(Node head) {
    Node slow = head;
    Node fast = slow.getNext();
    while (fast != null && fast.hasNext()) {
      slow = slow.getNext();
      fast = fast.getNext();
    }
    return slow;
  }

  public void kSizedReverse(int k) {
    head = kSizedReverse(head, k);
  }

  private Node kSizedReverse(Node current, int chunkSize) {
    return null;
  }

  public static void main(String[] args) {
    LinkedList<Integer> numbers = new LinkedList<Integer>();
    for (int i = 10; i >= 0; i--) {
      numbers.add(new Node(i));
    }
    System.out.println(numbers);
    numbers.mergeSort();
    System.out.println(numbers);

  }

}