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

  public static Node removeDuplicatesInPlace(final Node head) {
    if (head == null || head.next == null) {
      return head;
    }
    Node current = head;
    while (current != null && current.next != null) {
      Node checker = current;
      while (checker.next != null) {
        if (current.data.compareTo(checker.next.data) == 0) {
          checker.next = checker.next.next;
        } else {
          checker = checker.next;
        }
      }
      current = current.next;
    }
    return head;
  }


  public static void main(String[] args) {
    removeDuplicatesTest();
  }

  public static void removeDuplicatesTest() {
    int[] numbers = {1, 2, 3, 4, 3, 2, 1};
    int[] uniqueNumbers = {1, 2, 3, 4};
    LinkedList<Integer> listOfDups = new LinkedList<Integer>();
    LinkedList<Integer> listOfUniques = new LinkedList<Integer>();
    for (final int number : numbers) {
      listOfDups.addLast(number);
    }
    for (final int number : uniqueNumbers) {
      listOfUniques.addLast(number);
    }
    listOfDups.head = removeDuplicatesInPlace(listOfDups.head);
    assert listOfUniques.equals(listOfDups);
  }


}
