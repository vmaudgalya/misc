// Your program is to use the brute-force approach in order to find the Answer to Life, the Universe, and Everything. 
// More precisely... rewrite small numbers from input to output. 
// Stop processing input after reading in the number 42. All numbers at input are integers of one or two digits.

// Input:
// 1
// 2
// 88
// 42
// 99

// Output:
// 1
// 2
// 88

import java.util.Scanner;

public class AnswerToLife {
  private static final int ANSWER;
  static {
    ANSWER = 42;
  }

  private static void readInput(Scanner in) {
    int number = in.nextInt();
    while (number != ANSWER) {
      System.out.println(number);
      number = in.nextInt();
    }
  }

  public static void main(String[] args) {
    readInput(new Scanner(System.in));
  }
}