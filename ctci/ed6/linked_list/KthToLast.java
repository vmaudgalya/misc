public class KthToLast {

  public static int kthToLast(Node head, int k) {
    if (k < 0 || head == null) {
      return Integer.MAX_VALUE;
    }
    Node fast = head;
    while (k-- > 0) {
      fast = fast.next;
    }
    Node slow = head;
    while (fast != null) {
      slow = slow.next;
      fast = fast.next;
    }
    return slow.data;
  }

  public static void main(String[] args) {
    kthToLastTest();
  }

  public static void kthToLastTest() {
    int[] numbers = {4, 2, 6, 1, 33, 44, 71, 23, 9};
    int thirdToLast = 71;
    LinkedList<Integer> list = new LinkedList<Integer>();
    for (final int number : numbers) {
      list.addLast(number);
    }
    int result = kthToLast(list.head, 3);
    assert result == thirdToLast;
  }

}
