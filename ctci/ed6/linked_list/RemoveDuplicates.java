import java.util.Set;
import java.util.HashSet;

public class RemoveDuplicates {

  public static Node removeDuplicates(final Node head) {
    if (head == null || head.next == null) {
      return head;
    }
    Set<Integer> set = new HashSet<Integer>();
    set.add(head.data);
    Node current = head;
    while (current.next != null) {
      if (set.contains(current.next.data)) {
        current.next = current.next.next;
      } else {
        set.add(current.next.data);
        current = current.next;
      }
    }
    return head;
  }

  public static Node removeDuplicatesInPlace(Node head) {
    return null;
  }


  public static void main(String[] args) {
    removeDuplicatesTest();
  }

  public static void removeDuplicatesTest() {
    final int[] numbers = {1, 2, 3, 4, 3, 2, 1};
    final int[] uniqueNumbers = {1, 2, 3, 4};
    final LinkedList<Integer> listOfDups = new LinkedList<Integer>();
    final LinkedList<Integer> listOfUniques = new LinkedList<Integer>();
    for (final int number : numbers) {
      listOfDups.addLast(number);
    }
    for (final int number : uniqueNumbers) {
      listOfUniques.addLast(number);
    }
    listOfDups.head = removeDuplicates(listOfDups.head);
    assert listOfUniques.equals(listOfDups);
  }


}
