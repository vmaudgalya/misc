public class CheckPermutation {

  /*
  * Time: O(n) where n is the length of one of the strings
  * Space: O(1) constant space
  */
  public static boolean isPermutation(String first, String second) {
    if (first == null || second == null || first.equals("") 
      || second.equals("") || first.length() != second.length()) {
      return false;
    }

    int[] charCount = new int[256]; // Assumed extended ASCII
    for (int i = 0; i < first.length(); i++) {
      charCount[first.charAt(i)]++;
      charCount[second.charAt(i)]--;
    }
    for (int count : charCount) {
      if (count != 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    isPermutationTest();
  }

  public static void isPermutationTest() {
    assert isPermutation("abc", "acb") == true;
    assert isPermutation("tacocat", "totacac") == true;
    assert isPermutation("", null) == false;
    assert isPermutation("abcc", "acbb") == false;
  }
}