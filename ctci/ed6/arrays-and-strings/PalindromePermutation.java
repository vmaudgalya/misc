public class PalindromePermutation {

  /****
  * Time: O(n)
  * Space: O(1) in-place
  *****/
  public static boolean isPermutation(char[] str) {
    if (str == null) {
      return false;
    }
    java.util.Arrays.sort(str);
    int count = 0;
    int maxOdds = 0;
    for (int i = 1; i < str.length; i++) {
      if (str[i] == str[i-1]) {
        count++;
      } else {
        if (count % 2 != 1) {
          maxOdds++;
          if (maxOdds > 1) {
            return false;
          }
        }
        count = 0;
      }
    }
    if(str[str.length-1] == str[str.length-2]) {
      if (count % 2 != 1) {
        maxOdds++;
        if (maxOdds > 1) {
          return false;
        }
      }
    }
    return true;
  }

  /****************************
  * Time: O(n)
  * Space: O(1) constant space
  *****************************/
  public static boolean isPermutation(String str) {
    if (str == null || str.equals("")) {
      return false;
    }
    char[] characters = new char[256];
    for (int i = 0; i < str.length(); i++) {
      characters[str.charAt(i)]++;
    }
    int count = 0;
    for (char c : characters) {
      if (!(c == 0 || c % 2 == 0)) {
        if (count == 0) {
          count++;
        } else {
          return false; // allow only one character to occur once
        }
      }
    }
    return true;
  }
  
  public static void main(String[] args) {
    isPermutationTest();
  }

  public static void isPermutationTest() {
    String disguisedPalindrome = "tacocatt";
    assert isPermutation(disguisedPalindrome) == false;
    disguisedPalindrome = "tacocat";
    assert isPermutation(disguisedPalindrome) == true;
    disguisedPalindrome = "aaa";
    assert isPermutation(disguisedPalindrome) == true;
  }

}