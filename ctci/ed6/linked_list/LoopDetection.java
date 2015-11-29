public class LoopDetection {

  public static Node hasLoop(Node head) {
    if (head == null || head.next == null) {
      return null;
    }
    Node slow = head;
    Node fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) { // Cycle detected
        slow = head;
        while (slow != fast) {
          slow = slow.next;
          fast = fast.next;
        }
        return slow; // Pointing to the node at start of cycle
      }
    }
    return null; // Reached end of the list
  }


  public static void main(String[] args) {
    loopDetectionTest();
  }

  public static void loopDetectionTest() {
    Node head = new Node(281);
    head.next = new Node(194);
    head.next.next = new Node(491);
    head.next.next.next = new Node(1938);
    head.next.next.next.next = head.next.next; // Loop
    assert hasLoop(head).data == 491;
  }
}
