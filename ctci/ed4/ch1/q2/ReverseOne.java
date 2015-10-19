/********************************************
* Reverse a string.
*
* Time: O(n) time
* Space: In-place
* If we are just printing the reversed string
*********************************************/
public class ReverseOne {
  
  public static void reverse(String word) {
    if (word == null || word.equals("")) {
      return;
    }
    for (int i = word.length()-1; i >= 0; i--) {
      System.out.print(word.charAt(i));
    }
  }

  public static void main(String[] args) {
    String word = "Hello, and welcome!";
    System.out.println("Original string: " + word);
    System.out.print("After reversing: ");
    reverse(word);
    System.out.println();
  }
}