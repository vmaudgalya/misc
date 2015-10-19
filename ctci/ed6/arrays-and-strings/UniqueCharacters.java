import java.util.Map;
import java.util.HashMap;


public class UniqueCharacters {

  /***
  * Time: O(n) where n is the number of characters in the string
  * Space: O(n) where n is the number of characters in the string
  ***/
  public static boolean hasAllUnique1(String s) {
    if (s == null || s.equals("")) {
      return false;
    }
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    for (int i = 0; i < s.length(); i++) {
      if (map.containsKey(s.charAt(i))) {
        return false;
      } else {
        map.put(s.charAt(i), 1);
      }
    }
    return true;
  }

  /**
  * Time: O(n) where n is the number of characters 
  * (can be argued to be O(1) as it won't loop more than 256 times)
  * Space: O(1) constant space
  *
  **/
  public static boolean hasAllUnique2(String s) {
    if (s == null || s.equals("") || s.length() > 256) {
      return false;
    }
    boolean[] letters = new boolean[256]; // assuming extended ascii
    for (int i = 0; i < s.length(); i++) {
      int index = s.charAt(i);
      if (letters[index]) {
        return false;
      }
      letters[index] = true;
    }
    return true;
  }

  /** 
  * Time: O(n) where n is the number of characters
  * Space: O(1) constant space
  **/
  public static boolean hasAllUnique3(String s) {
    if (s == null || s.equals("")) {
      return false;
    }
    int bitVector = 0; // assuming lowercase characters a-z
    for (int i = 0; i < s.length(); i++) {
      int value = s.charAt(i) - 'a';
      if ((bitVector & (1 << value)) > 0) {
        return false;
      }
      bitVector |= (1 << value);
    }
    return true;
  }


  public static void main(String[] args) {
    hasAllUniqueTest();
  }

  public static void hasAllUniqueTest() {
    assert hasAllUnique("abcd") == true;
    assert hasAllUnique("abcda") == false;
    assert hasAllUnique(null) == false; // because its "bad" input
    assert hasAllUnique("") == false; // same reason as above
  }

}