public class Partition {

  public static Node partition(Node node, int pivot) {
    if (node == null || node.next == null) {
      retun node;
    }
    Node head = node;
    Node tail = node;

    while (node != null) {
      Node next = node.next;
      if ((Integer) node.data < pivot) {
        node.next = head;
        head = node;
      } else {
        tail.next = node;
        tail = node;
      }
      node = next;
    }

    tail.next = null; // remove cycles
    return head; // return modified head
  }

  public static void main(String[] args) {
    partitionTest();
  }

  public static void partitionTest() {
    int[] numbers = {22, 40, 101, 43, 25, 16, 8, 99, 1};
    LinkedList<Integer> list = new LinkedList<Integer>();
    for (final int number : numbers) {
      list.addLast(number);
    }
    int pivot = 43;
    list.head = partition(list.head, pivot);
    boolean lessThan = true;
    for (final int number : list) {
      if (number >= pivot) {
        lessThan = false;
      }
      if (number < pivot && !lessThan) {
        System.out.println("Failure to partition around " + pivot + ": " + list);
        return;
      }
    }
    System.out.println("Partition succeeded around " + pivot + ": " + list);
  }
}
