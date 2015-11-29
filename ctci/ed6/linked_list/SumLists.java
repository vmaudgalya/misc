public class SumLists {

  public static Node sumLists(Node first, Node second) {
    if (first == null || second == null) {
      return;
    }
    int multiplier = 1;
    int firstNum = 0;
    int secondNum = 0;
    Node firstRunner = first;
    Node secondRunner = second;
    while (firstRunner != null) {
      firstNum += (firstRunner.data * multiplier);
      secondNum += (secondRunner.data * multiplier);
      multiplier *= 10;
      firstRunner = firstRunner.next;
      secondRunner = secondRunner.next;
    }
    int total = firstNum + secondNum;
    Node dummy = new Node();
    Node sum = dummy;
    while (total > 0) {
      sum.next = new Node(total % 10);
      sum = sum.next;
      total /= 10;
    }
    return dummy.next;
  }

  public static void main(String[] args) {
    sumListsTest();
  }

  public static void sumListsTest() {
    LinkedList<Integer> firstNumber = new LinkedList<Integer>();
    LinkedList<Integer> secondNumber = new LinkedList<Integer>();
    LinkedList<Integer> expectedResult = new LinkedList<Integer>();
    int[] firstNumDigits = {7, 1, 6};
    int[] secondNumDigits = {5, 9, 2};
    int[] expectedDigits = {2, 1, 9};
    for (int i = 0; i < firstNumDigits.length; i++) {
      firstNumber.addLast(firstNumDigits[i]);
      secondNumber.addLast(secondNumDigits[i]);
      expectedResult.addLast(expectedDigits[i]);
    }
    LinkedList<Integer> result = new LinkedList<Integer>();
    result.head = sumLists(firstNumber.head, secondNumber.head);
    Node resultChecker = result.head;
    Node expectedChecker = expectedResult.head;
    while (resultChecker != null && expectedChecker != null) {
      assert expectedChecker.data.equals(resultChecker.data);
      expectedChecker = expectedChecker.next;
      resultChecker = resultChecker.next;
    }
  }
}
