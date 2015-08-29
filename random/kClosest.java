/*************************************
* Given a sorted array, and an index, 
* find the k elements closest to the 
* number at index n.
*
* Time: O(k)
* Space: O(1)
*************************************/
public class kClosest {

  public static void printKClosest(int[] array, int index, int K) {
    validateInput(array, index, K);
    if (array.length == 0) {
      return;
    }
    if (index == 0) {
      for (int i = 1; i <= K; i++) {
        System.out.print(array[i] +  (i != K ? ", " : "\n"));
      }
    } else if (index == array.length-1) {
      for (int i = array.length-2; i >= array.length-1-K; i--) {
        System.out.print(array[i] +  (i != K ? ", " : "\n"));
      }
    } else {
      int left = index-1;
      int right = index+1;
      int count = 0;
      while (count != K) {
        String delim = (count != K-1 ? ", " : "\n");
        if (left < 0) {
          System.out.print(array[right++] + delim);
        } else if (right > array.length-1) {
          System.out.print(array[left--] + delim);
        } else if (difference(array[index], array[left]) < difference(array[index], array[right])) {
          System.out.print(array[left--] + delim);
        } else {
          System.out.print(array[right++] + delim);
        }
        count++;
      }
    }
  }

  private static int difference(int a, int b) {
    return Math.abs(a-b);
  }

  private static void validateInput(int[] array, int index, int K) {
    if (array == null) {
      throw new NullPointerException("Null array not allowed.");
    } 
    if (index < 0 || index > array.length-1) {
      throw new ArrayIndexOutOfBoundsException("index " + index + " invalid.");
    }
    if (K > array.length) {
      throw new RuntimeException("K " + K + " invalid. Exceeds array capacity.");
    }
  }

  public static void main(String[] args) {
    int[] testArray = {6, 7, 2, 8, 3, 5, 1, 9, 5, 3};
    java.util.Arrays.sort(testArray);
    System.out.println("Sorted array: " + java.util.Arrays.toString(testArray));
    int index = 4;
    int K = 5;
    System.out.println("Printing " + K + " closest numbers to index " 
                        + index + " (Number = " + testArray[index] + ")");
    printKClosest(testArray, 4, 5);
  }

}