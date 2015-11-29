public class ReverseList {

  public static Node reverseListIterative(final Node head) {
    Node previous = null;
    Node current = head;
    while (current != null) {
      Node next = current.next;
      current.next = previous;
      previous = current;
      current = next;
    }
    return previous;
  }

  public static Node reverseListRecursive(Node head) {
    if (head == null || head.next == null) {
      return head;
    }
    Node reversedHead = reverseListRecursive(head.next);
    head.next.next = head; // Next's next will point to me
    head.next = null; // My next will point to null (handles tail needing to point to null)
    return reversedHead;
  }

  public static void main(String[] args) {
    reverseListTest();
  }

  public static void reverseListTest() {
    int[] numbers = {1, 2, 3, 4, 5};
    LinkedList<Integer> list = new LinkedList<Integer>();
    LinkedList<Integer> reversedList = new LinkedList<Integer>();
    for (int i = 0, j = numbers.length; i < numbers.length & j > 0; i++) {
      list.addLast(numbers[i]);
      reversedList.addLast(numbers[j]);
      j++;
    }
    assert reverseListIterative(list.head).equals(reversedList.head);
    assert reverseListRecursive(list.head).equals(reversedList.head);
  }
}
