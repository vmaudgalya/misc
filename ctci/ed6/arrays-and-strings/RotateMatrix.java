public class RotateMatrix {

  public static int[][] rotateRight(int[][] array) {
    if (array == null) {
      return null;
    }
    int[][] rotated = new int[array.length][array.length];
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        rotated[j][array.length-1-i] = array[i][j];
      }
    }
    return rotated;
  }

  public static int[][] rotateRightInPlace() {
    return null;
  }

  public static void main(String[] args) {
    rotateRightTest();
  }

  public static void rotateRightTest() {
    int[][] testArray = {{1, 2, 3}, 
                         {4, 5, 6}, 
                         {7, 8, 9}};
    int[][] rotatedRight = {{7, 4, 1}, 
                            {8, 5, 2}, 
                            {9, 6, 3}};
    assert java.util.Arrays.deepEquals(rotateRight(testArray), rotatedRight);
  }

}