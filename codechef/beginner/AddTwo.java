// Shivam is the youngest programmer in the world, he is just 12 years old. 
// Shivam is learning programming and today he is writing his first program.
// Program is very simple, Given two integers A and B, write a program to add these two numbers. 

// Input
// The first line contains an integer T, total number of test cases. Then follow T lines, each line contains two Integers A and B.
// Output

// Add A and B and display it.
// Constraints11
// 1 ≤ T ≤ 1000
// 1 ≤ A,B ≤ 10000
// Example

// Input
// 3 
// 1 2
// 100 200
// 10 40

// Output
// 3
// 300
// 50

import java.util.Scanner;

public class AddTwo {

  public static void main(String[] args) {
    processInput(new Scanner(System.in));
  }

  private static void processInput(Scanner in) {
    short cases = in.nextShort();
    while (cases-- > 0) {
      short first = in.nextShort();
      short second = in.nextShort();
      System.out.println(first + second);
    }
  }
}