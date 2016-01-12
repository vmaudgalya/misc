public class RotateMatrix {

  /**
  * Naive solution
  * Time: O(n^2)
  * Space: O(n^2)
  */
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

  public static int[][] rotateLeft(int[][] array) {
    if (array == null) {
      return null;
    }
    int[][] rotated = new int[array.length][array[0].length];
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        rotated[array.length-1-j][i] = array[i][j];
      }
    }
    return rotated;
  }

  /**
  * Optimal solution
  * Time: O(n^2)
  * Space: O(1) in place
  */
  public static int[][] rotateRightInPlace(int[][] array) {
    int N = array.length;
    for (int layer = 0; layer < array.length/2; layer++) {
      int first = layer;
      int last = N - 1 - layer;
      for (int i = first; i < last; i++) {
        int offset = i - first;
        int top = array[first][i]; // save top
        array[first][i] = array[last - offset][first]; // left -> top
        array[last - offset][first] = array[last][last - offset]; // bottom -> left
        array[last][last - offset] = array[i][last]; // right -> bottom
        array[i][last] = top; // top -> right
      }
    }
    return array;
  }

  public static int[][] rotateLeftInPlace(int[][] array) {
    int N = array.length;
    for (int layer = 0; layer < N / 2; layer++) {
      int first = layer;
      int last = N - 1 - layer;
      for (int i = first; i < last; i++) {
        int offset = i - first;
        int top = array[first][i]; // save top
        array[first][i] = array[i][last]; //right -> top
        array[i][last] = array[last][last-offset]; //bottom -> right
        array[last][last-offset] = array[last - offset][first]; // left -> bottom
        array[last - offset][first] = top; //top -> left
      }
    }
    return array;
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
    int[][] original = {{1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}};
    assert java.util.Arrays.deepEquals(rotateRightInPlace(testArray), rotatedRight);
    assert java.util.Arrays.deepEquals(rotateLeftInPlace(testArray), original);
  }

}