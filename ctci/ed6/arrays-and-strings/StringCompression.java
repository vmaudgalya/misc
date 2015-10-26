public class StringCompression {

  public static String compress(String str) {
    if (str == null || str.length() <= 2) {
      return str;
    }
    StringBuilder builder = new StringBuilder();
    char previous = str.charAt(0);
    int count = 1;
    for (int i = 1; i < str.length(); i++) {
      char current = str.charAt(i);
      if (current != previous) {
        builder.append(previous).append(count);
        if (builder.length() >= str.length()) {
          return str;
        }
        count = 1;
        previous = current;
      } else {
        count++;
      }
      if (i == str.length() - 1 && count != 1) {
        builder.append(current).append(count);
      }
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    compressTest();
  }

  public static void compressTest() {
    assert compress("aa").equals("aa");
    assert compress("a").equals("a");
    assert compress("ab").equals("ab");
    assert compress("aaa").equals("a3");
    assert compress("aabcccccaaa").equals("a2b1c5a3");
    assert compress(null) == null;
    assert compress("").equals("");
  }

}