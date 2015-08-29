/****************************************
* Remove duplicate characters in a string
* without using any additional buffer.
* 
* Time: O(n^2) TODO: improve time complexity
* Space: O(1)
*****************************************/
public class Duplicates {
  
  public static String removeDuplicates(String word) {
    char[] letters = word.toCharArray();
    for (int i = 0; i < letters.length; i++) {
      char toCompare = letters[i];
      for (int j = i+1; j < letters.length; j++) {
        if (toCompare == letters[j]) {
          letters[j] = '\0';
        }
      }
    }
    return new String(letters);
  }

  public static void main(String[] args) {
    String[] words = {"abcda", "penny", "AabBcCdzz", "123451234512"};
    for (String word : words) {
      System.out.println(removeDuplicates(word));  
    }
  }
}