/***************************************
* A sorted array has been rotated so that 
* the elements might appear in the order 
* 3 4 5 6 7 1 2. How would you find the 
* minimum element?
*
* Time: O(log(n)) time (binary search variant)
* Space: O(log(n)) space
****************************************/
public class RotatedArray {

  public static int findMin(int[] a) {
    if (a == null || a.length == 0) {
      return -1;
    } else if (a.length == 1) {
      return a[0];
    }
    return findMin(a, 0, a.length-1);
  }

  public static int findMin(int[] a, int start, int end) {
    if (end-start == 1) {
      return a[end];
    } 
    int mid = (start+end)/2;
    if (a[start] > a[mid]) {
      return findMin(a, start, mid);
    } else {
      return findMin(a, mid, end);
    }
  }

  public static void main(String[] args) {
    int[] a = {6, 7, 0, 1, 2, 3, 4, 5};
    System.out.println(findMin(null));
  }


}