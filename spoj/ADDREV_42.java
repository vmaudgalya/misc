/**********************************
* @author Varun Maudgalya
* @mail vmaudgalya@gmail.com
* @problemName ADDREV
* @problemId 42
* @verdict Accepted
* @runtime 0.28
* @judge http://www.spoj.com/
* @category ad-hoc
* @level easy
* @date 09/27/2015
* @time O(n)
* @space O(1)
***********************************/

import java.util.Scanner;
public class ADDREV_42 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    short cases = in.nextShort();
    while (cases-- > 0) {
      int a = reverse(in.nextInt());
      int b = reverse(in.nextInt());
      System.out.println(reverse(a+b));
    }
  }

  private static int reverse(int number) {
    int reversed = 0;
    while (number > 0) {
      reversed = reversed * 10 + (number % 10);
      number /= 10;
    }
    return reversed;
  }
}