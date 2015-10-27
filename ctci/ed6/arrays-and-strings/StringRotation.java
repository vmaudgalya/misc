public class StringRotation {
  
  private static boolean isSubstring(String first, String second) {
    if (first.length() > second.length()) {
      return first.indexOf(second) > 0;
    } else {
      return second.indexOf(first) > 0;
    }
  }

  /**
  * Time: O(n)
  * Space: O(n)
  *****/
  public static boolean isRotation(String s1, String s2) {
    if (s1 == null || s2 == null || s1.equals("") || s2.equals("")) {
      return false; // Invalid input
    }
    StringBuilder sb = new StringBuilder(s1.length()*2);
    sb.append(s1).append(s1);
    return isSubstring(s2, sb.toString());
  }

  public static void main(String[] args) {
    isRotationTest();
  }

  public static void isRotationTest() {
    assert isRotation("waterbottle", "erbottlewat") == true;
    assert isRotation("abc", "cba") == false;
    assert isRotation(null, "a") == false;
  }





}