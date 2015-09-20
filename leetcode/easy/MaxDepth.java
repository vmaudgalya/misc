// Given a binary tree, find its maximum depth.

// The maximum depth is the number of nodes 
// along the longest path from the root node down to the farthest leaf node.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


/************
* Time: O(n)
* Space: O(n)
*************/
public class MaxDepth {
  public int maxDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    int left = 1 + maxDepth(root.left);
    int right = 1 + maxDepth(root.right);
    return (left < right ? right : left);
  }
}