public class PalindromePermutation {

  // public static boolean isPermutation(String str) {
  //   char[] letters = str.toCharArray();
  //   java.util.Arrays.sort(letters);
  //   int count = 0;
  //   for (int i = 1; i < letters.length; i++) {
  //     if (letters[i] == letters[i-1]) {
  //       count++;
  //     } else {
  //       if (cantBeMatched(count)) {
  //         return false;
  //       }
  //       count = 0;
  //     }
  //   }
  //   if (letters[letters.length-1] != letters[letters.length-2]) {
  //     return false;
  //   }
  //   return true;
  // }

  // private static boolean cantBeMatched(int count) {
  //   if (!(count == 0 || count == 1 || (count % 2 == 1))) {
  //     return true;
  //   }
  //   return false;
  // }

  public static boolean isPermutation(String str) {
    char[] characters = new char[256];
    for (int i = 0; i < str.length(); i++) {
      characters[str.charAt(i)]++;
    }
    int count = 0;
    for (char c : characters) {
      if (!(c == 0 || c % 2 == 0)) {
        if (count == 0) {
          count++;
        } else {
          return false; // allow only one character to occur once
        }
      }
    }
    return true;
  }
  
  public static void main(String[] args) {
    isPermutationTest();
  }

  public static void isPermutationTest() {
    assert isPermutation("acoca") == true;
  }

}