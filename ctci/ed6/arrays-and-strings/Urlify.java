public class Urlify {
  
  /***
  * Time: O(n) where n is the number of characters in the string
  * Space: O(n) but the approach will be O(1) in-place if input 
  * is in the form of a character array.
  ***/
  public static String toUrl(String str, int trueLength) {
    if (str == null || str.equals("") || trueLength < 3) {
      return str;
    }
    int last = str.length()-1;
    char[] characters = str.toCharArray();
    for (int tail = trueLength-1; tail >= 0; tail--) {
      if (characters[tail] != ' ') {
        characters[last--] = characters[tail];
      } else {
        characters[last--] = '0';
        characters[last--] = '2';
        characters[last--] = '%';
      }
    }
    return new String(characters);
  }

  public static void toUrlTest() {
    assert toUrl("Mr John Smith    ", 13).equals("Mr%20John%20Smith");
  }

  public static void main(String[] args) {
    toUrlTest();
  }

}