// Given an array nums, write a function to move all 0's
// to the end of it while maintaining the relative order of the non-zero elements.

// For example, given nums = [0, 1, 0, 3, 12], after
// calling your function, nums should be [1, 3, 12, 0, 0].

// Note:
// You must do this in-place without making a copy of the array.
// Minimize the total number of operations.

/************
* Time: O(n)
* Space: O(1)
*************/
public class MoveZeroes {
  public void moveZeroes(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return;
    }
    int insertionIndex = 0;
    for (int number : nums) {
      if (number != 0) {
        nums[insertionIndex++] = number;
      }
    }
    for (int i = insertionIndex; i < nums.length; i++) {
      nums[i] = 0;
    }
  }
}