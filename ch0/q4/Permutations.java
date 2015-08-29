/************************************
* Print all permutations of a string.
*
* Time: O(n!)
* Space: O(n)
*************************************/

public class Permutations {

  public static void printPermutations(String s) {
    printPermutations(0, s, new boolean[s.length()], new StringBuilder());
  }

  private static void printPermutations(int position, String s, boolean[] used, StringBuilder sb) {
    if (position == s.length()) {
      System.out.println(sb.toString());
    } else {
      for (int i = 0; i < s.length(); i++) {
        if (used[i]) {
          continue;
        }
        used[i] = true;
        sb.append(s.charAt(i));
        printPermutations(position+1, s, used, sb);
        used[i] = false;
        sb.deleteCharAt(sb.length()-1);
      }
    }
  }

  public static void main(String[] args) {
    String s = "abc";
    printPermutations(s);
  }

}