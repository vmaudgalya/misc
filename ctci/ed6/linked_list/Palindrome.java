import java.util.Deque;
import java.util.ArrayDeque;

public class Palindrome {

  public static boolean isPalindrome(Node head) {
    if (head == null) {
      return false; // error
    } else if (head.next == null) {
      return true;
    }
    int size = getSize(head);
    Deque<Integer> stack = new ArrayDeque<Integer>();
    Node current = head;
    for (int i = 0; i < size / 2; i++) {
      stack.push(current.data);
      current = current.next;
    }
    if (size % 2 != 0) {
      current = current.next; // Skip the middle if list has odd length
    }
    for (int i = 0; i < size / 2; i++) {
      if (stack.pop() != current.data) {
        return false;
      }
      current = current.next;
    }
    return true;
  }

  private static int getSize(final Node head) {
    int count = 0;
    Node current = head;
    while (current != null) {
      count++;
      current = current.next;
    }
    return count;
  }

  public static void main(String[] args) {
    palindromeTest();
  }

  public static void palindromeTest() {
    LinkedList<Integer> realPalindrome = new LinkedList<Integer>();
    LinkedList<Integer> fakePalindrome = new LinkedList<Integer>();
    int[] realPalindromeNumbers = {0, 1, 2, 3, 2, 1, 0};
    int[] fakePalindromeNumbers = {0, 4, 2, 3, 2, 1, 0};
    for (int i = 0; i < realPalindromeNumbers.length; i++) {
      realPalindrome.addLast(realPalindromeNumbers[i]);
      fakePalindrome.addLast(fakePalindromeNumbers[i]);
    }
    assert isPalindrome(realPalindrome.head) == true;
    assert isPalindrome(fakePalindrome.head) == false;

  }
}
