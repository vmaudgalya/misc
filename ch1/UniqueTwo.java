import java.util.Arrays;

public class UniqueTwo {
  
  public static void main(String[] args) {
    String word = args[0];
    System.out.println(hasAllUnique(word));
  }

  public static boolean hasAllUnique(String word) {
    if (word == null || word.equals("")) {
      return false; // assumption that invalid strings aren't accepted
    }
    char[] letters = word.toCharArray();
    Arrays.sort(letters); // bottleneck operation, O(nlog(n)) time, in place quicksort
    for (int i = 1; i < letters.length; i++) {
      if (letters[i-1] == letters[i]) {
        return false;
      }
    }
    return true;
  }
}