public class OneAway {

  /***
  * Time: O(n)
  * Space: O(1) in-place
  *****/
  public static boolean isOneAway(String a, String b) {
    if (a == null || b == null || a.equals("") || 
      b.equals("") || (Math.abs(a.length() - b.length()) > 1)) {
      return false;
    }
    int length = (a.length() > b.length() ? b.length() : a.length());
    int edits = 0;
    for (int i = 0, j = 0; i < length; i++) {
      if (a.charAt(i) != b.charAt(j)) {
        if (edits == 0) {
          edits++;
        } else {
          return false;
        }
        if (a.length() > b.length()) {
          i++; // advance past the extra character, if there is one
        } else if (a.length() < b.length()) {
          j++;
        }
      }
      j++;
    }
    return true;
  }

  public static void main(String[] args) {
    isOneAwayTest();
  }

  public static void isOneAwayTest() {
    assert isOneAway("pale", "bale") == true;
    assert isOneAway("pale", "ale") == true;
    assert isOneAway("pale", "ple") == true;
    assert isOneAway("pale", "bal") == false;
    assert isOneAway("pale", null) == false;
    assert isOneAway("pales", "pale") == true;
    assert isOneAway("pale", "bake") == false;
  }

}