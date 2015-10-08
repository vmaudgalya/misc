/***************************************
* @author Varun Maudgalya
* @brief My StringBuilder implementation
****************************************/

public class MyStringBuilder {
  private char[] data;
  private int size;

  public MyStringBuilder() {
    data = new char[100];
    size = 0;
  }

  public MyStringBuilder append(String s) {
    if (s == null || s.equals("")) {
      return this;
    }
    add(s.toCharArray());
    return this;
  }

  public MyStringBuilder remove(String s) {
    if (s == null || s.equals("")) {
      return this;
    }
    remove(s.toCharArray());
    return this;
  }

  private void add(char[] characters) {
    while (characters.length >= data.length) {
      resize(data.length*2);
    }
    for (int i = 0; i < characters.length; i++) {
      data[size++] = characters[i];
    }
  }

  private void remove(char[] characters) {
    for (int i = 0; i < size; i++) {
      if (data[i] == characters[0]) {
        int victimSize = 1;
        for (int j = i+1; j < i+characters.length && j < size; j++) {
          if (data[j] != characters[j-i]) {
            break;
          } else {
            victimSize++;
          }
        }
        if (victimSize == characters.length) {
          for (int k = i; k < i+characters.length; k++) {
            data[k] = '\0';
          }
        }
        i += characters.length-1;
        size -= characters.length;
      }
    }
  }

  private void resize(int newLength) {
    char[] resized = new char[newLength];
    for (int i = 0; i < size; i++) {
      resized[i] = data[i];
    }
    data = resized;
  }

  public String toString() {
    if (size == 0) {
      return "";
    }
    return new String(data);
  }

  public int size() {
    return size;
  }

  public static void main(String[] args) {
    MyStringBuilder sb = new MyStringBuilder();
    sb.append("adb").append("catcat").append("efgc");
    System.out.println(sb.toString());
    System.out.println("The size is " + sb.size());
    System.out.println(sb.remove("cat"));
    System.out.println("The size is " + sb.size());
  }
}