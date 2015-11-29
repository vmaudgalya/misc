public class LoopDetection {


  public static void main(String[] args) {
    loopDetectionTest();
  }

  public static void loopDetectionTest() {
    Node head = new Node(281);
    head.next = new Node(194);
    head.next.next = new Node(491);
    head.next.next.next = new Node(1938);
    head.next.next.next.next = head.next.next; // Loop
    assert hasLoop(head) != null;
  }
}
