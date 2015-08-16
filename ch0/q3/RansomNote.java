/**
* O(n) space where n is the number of words in the magazine 
* O(n) time where n is the number of words in the magazine
*/

import java.util.HashMap;

public class RansomNote {
  public static boolean containsNote(String magazine, String note) {
    HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
    String[] words = magazine.split(" ");
    for (String word : words) {
      if (wordCount.containsKey(word)) {
        wordCount.put(word, wordCount.get(word) + 1);
      } else {
        wordCount.put(word, 1);
      }
    }
    words = note.split(" ");
    for (String word : words) {
      if (wordCount.containsKey(word)) {
        int count = wordCount.get(word);
        if (count >= 2) {
          wordCount.put(word, wordCount.get(word) - 1);
        } else if (count == 1) {
          wordCount.remove(word);
        }
      } else {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String note = "cubed marshmallows are good";
    String magazine = "in a study of marshmallows it was found that when cubed it became good and we are happy to say etc.";
    System.out.println(containsNote(magazine, note));
  }
}