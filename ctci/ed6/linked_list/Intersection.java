public class Intersection {

  public static Node hasIntersection(final Node first, final Node second) {
    if (first == null || second == null) {
      return null; // Error
    }
    int firstLength = getLength(first);
    int secondLength = getLength(second);
    int difference = Math.abs(firstLength - secondLength);
    Node fpointer = first;
    Node spointer = second;
    if (secondLength < firstLength) {
      fpointer = advancePointer(fpointer, difference);
    } else if (firstLength < secondLength) {
      spointer = advancePointer(spointer, difference);
    }
    return compare(fpointer, spointer);
  }

  private static int getLength(final Node head) {
    int length = 0;
    for (Node n = head; n != null; n = n.next) {
      length++;
    }
    return length;
  }

  private static Node advancePointer(Node pointer, final int amount) {
    for (int i = 0; i < amount; i++) {
      pointer = pointer.next;
    }
    return pointer;
  }

  private static Node compare(Node fpointer, Node spointer) {
    while (fpointer != null) {
      if (fpointer == spointer) {
        return fpointer;
      }
      fpointer = fpointer.next;
      spointer = spointer.next;
    }
    return null;
  }

  public static void main(String[] args) {
    hasIntersectionTest();
  }

  public static void hasIntersectionTest() {
    Node firstList = new Node(34);
    Node firstListTail = firstList;
    for (int i = 32; i < 40; i++) {
      firstListTail.next = new Node(i);
      firstListTail = firstListTail.next;
    }
    Node secondList = new Node(408);
    secondList.next = new Node(172);
    secondList.next.next = firstListTail; // Intersection here
    assert hasIntersection(firstList, secondList) != null;

    firstList = new Node(183);
    firstList.next = new Node(1482);
    secondList = new Node(984);
    secondList = new Node(1384);
    assert hasIntersection(firstList, secondList) == null;

  }

}
