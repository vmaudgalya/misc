/***************************************************************
* O(nlog(n)) time 
* [Worst case is O(n^2) but probability of that case is 1/(n!)]
* O(n) space
****************************************************************/
import java.util.Arrays;

public class UniqueTwo {

  public static void validateInput(String word) {
    if (word.equals("")) {
      throw new RuntimeException("Error: Empty strings are invalid input");
    } else if (word == null) {
      throw new RuntimeException("Error: Null strings are invalid input");
    }
  }

  public static boolean hasAllUnique(String word) {
    validateInput(word); // Assumed that null and empty strings are invalid.
    char[] letters = word.toCharArray();
    Arrays.sort(letters); // bottleneck operation, O(nlog(n)) time, in place quicksort
    for (int i = 1; i < letters.length; i++) {
      if (letters[i-1] == letters[i]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String[] words = {"tuba", "firetruck"};
    for (String word : words) {
      System.out.println("Does " + word + " contain all unique characters? " + hasAllUnique(word));
    }
  }
  
}