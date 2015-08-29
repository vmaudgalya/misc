/**********************************************
* Implement an algorithm to determine if a 
* string has all unique characters. What if you
* can not use additional data structures?
*
* Time: O(n) 
* Space: O(n)
**********************************************/
import java.util.HashMap;

public class UniqueOne {

  public static void validateInput(String word) {
    if (word.equals("")) {
      throw new RuntimeException("Error: Empty strings are invalid input");
    } else if (word == null) {
      throw new RuntimeException("Error: Null strings are invalid input");
    }
  }


  public static boolean hasAllUnique(String word) {
    validateInput(word); // Assumed that null and empty strings are invalid.
    // Allows O(1) insert and search
    HashMap<Character, Integer> chars = new HashMap<Character, Integer>();
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (chars.containsKey(c)) {
        return false;
      }
      chars.put(c, null);
    }
    return true; //unable to find a duplicate
  }

  public static void main(String[] args) {
    String[] words = {"tuba", "firetruck"};
    for (String word : words) {
      System.out.println("Does " + word + " contain all unique characters? " + hasAllUnique(word));
    }
  }
}