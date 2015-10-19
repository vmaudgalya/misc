/****************************************
* Reverse a string.
* 
* O(n) time
* O(n) space
* If we are actually reversing the String
*****************************************/
public class ReverseTwo {
  
  public static String reverse(String word) {
    char[] letters = word.toCharArray();
    int start = 0;
    int end = letters.length-1;
    while (start != end) {
      swap(letters, start++, end--);
    }
    return new String(letters);
  }

  public static void swap(char[] letters, int a, int b) {
    char temp = letters[a];
    letters[a] = letters[b];
    letters[b] = temp;
  }

  public static void main(String[] args) {
    String word = "Hello, and welcome!";
    System.out.println("Original string: " + word);
    System.out.println("After reversing: " + reverse(word));
  }

}