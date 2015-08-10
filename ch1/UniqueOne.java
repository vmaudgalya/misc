import java.util.HashMap;

public class UniqueOne {

  public static boolean hasAllUnique(String word) {
    if (word == null || word.equals("")) {
      return false; //based on assumption that invalid words are not accepted
    }
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
    String word = args[0];
    System.out.println(hasAllUnique(word));
    // duplicateCharacters = false
    // abc = true
  }

}