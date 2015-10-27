import java.util.List;
import java.util.ArrayList;

public class ZeroMatrix {
  

  public static int[][] zeroMatrix(int[][] matrix) {
    if (matrix == null) {
      return null;
    }
    int rows = matrix.length;
    int columns = matrix[0].length;
    int[] rowNums = new int[rows];
    int[] colNums = new int[columns];
    initializeArray(rowNums);
    initializeArray(colNums);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (matrix[i][j] == 0) {
          rowNums[i] = i;
          colNums[j] = j;
        }
      }
    }

    for (int row : rowNums) {
      if (row != -1) {
        for (int i = 0; i < matrix.length; i++) {
          matrix[row][i] = 0;
        }
      }
    }
    for (int col : colNums) {
      if (col != -1) {
        for (int i = 0; i < matrix.length; i++) {
          matrix[i][col] = 0;
        }
      }
    }

    return matrix;
  }

  public static void main(String[] args) {
    int[][] matrix = {{0, 2, 1, 8}, 
                      {6, 4, 6, 1}, 
                      {7, 5, 9, 5},
                      {6, 1, 1, 0}};
    String[] rows = java.util.Arrays.deepToString(zeroMatrix(matrix)).split("], ");
    for (String row : rows) {
      System.out.println(row);
    }
  }

  private static void initializeArray(int[] a) {
    for (int i = 0; i < a.length; i++) {
      a[i] = -1;
    }
  }

}