public class Inversions {
  /**************
  * Time: O(n^2)
  * Space: O(1)
  **************/
  public static int countInversions(int[] numbers) {
    int inversions = 0;
    for (int i = 0; i < numbers.length-1; i++) {
      for (int j = i+1; j < numbers.length; j++) {
        if (numbers[i] > numbers[j]) {
          inversions++;
        }
      }
    }
    return inversions;
  }

  public static void main(String[] args) {
    int[] array = {1, 3, 2, 
                   4, 5, 8, 
                   6, 7    };
    System.out.println(countInversions(array)); // 3
  }

}