// Given an array of integers, find if the array contains any duplicates. 
// Your function should return true if any value appears at least twice 
// in the array, and it should return false if every element is distinct.

/******************************************************************************
* Traditional trade-off between time and space complexity. In this solution
* I have opted for the slower, constant space algorithm. The alterate solution
* would be to remember numbers we have seen using a HashMap, resulting in 
* one pass on the array, but linear space
*
* Time: O(nlog(n)) - the sort is the bottleneck
* Space: O(1)
******************************************************************************/

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        java.util.Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] == nums[i]) {
                return true;
            }
        }
        return false;
    }
}