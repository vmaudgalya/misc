public class DeleteMiddle {

  public static void deleteMiddle(Node victim) {
    if (victim == null || victim.next == null) {
      victim = null;
      return;
    }
    victim.data = victim.next.data;
    victim.next = victim.next.next;
  }

  public static void main(String[] args) {
    deleteMiddleTest();
  }

  public static void deleteMiddleTest() {
    int[] numbers = {1, 2, 3, 4, 5};
    LinkedList<Integer> list = new LinkedList<Integer>();
    for (final int number : numbers) {
      list.addLast(number);
    }
    deleteMiddle(list.head.next.next);
    assert list.size() == numbers.length-1;
    assert list.head.next.next != 3;
  }
}
