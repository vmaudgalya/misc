public class ReverseNumber {
  

  /***
  * Time: O(n) where n is the number of digits
  * Space: O(1) in-place
  ***/
  public static int reverse(int n) { //2015 -> 5102
    if (n < 10) {
      return n;
    }
    int reversed = 0;
    while (n > 0) {
      int digit = (n >= 10 ? n % 10 : n);
      reversed = (reversed * 10) + digit;
      n /= 10;
    }
    return reversed;
  }

  public static void main(String[] args) {
    reverseTest();
  }

  public static void reverseTest() {
    assert reverse(2015) == 5102;
    assert reverse(0) == 0;
    assert reverse(1234567) == 7654321;
  }

}
