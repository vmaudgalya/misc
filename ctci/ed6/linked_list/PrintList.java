public class PrintList {

  public static void main(String[] args) {
    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    LinkedList<Integer> list = new LinkedList<Integer>();
    for (int number : numbers) {
      list.addLast(number);
    }
    printListIterative(list.head);
    printListRecursive(list.head);
  }

  public static void printListIterative(Node head) {
    for (Node current = head; current != null; current = current.next) {
      System.out.print(current.data + " ");
    }
    System.out.println();
  }

  public static void printListRecursive(Node head) {
    if (head == null) {
      System.out.println();
      return;
    }
    System.out.print(head.data + " ");
    printListRecursive(head.next);
  }

  public static void printReversedList(Node head) {
    if (head == null) {
      return;
    }
    printReversedList(head.next);
    System.out.print(head.data + " ");
  }

}
