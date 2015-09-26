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

public class MaxDepth {

/********************************
* Recursive implementation (DFS)
* Time: O(n)
* Space: O(n)
*********************************/
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = 1 + maxDepth(root.left);
    int right = 1 + maxDepth(root.right);
    return (left < right ? right : left);
  }

/*******************************
* Iterative implementation (BFS)
* Time: O(n)
* Space: O(n)
********************************/
  public int iterativeMaxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.offer(root);
    int depth = 0;
    while (!q.isEmpty()) {
      depth++;
      int N = q.size();
      for (int i = 0; i < N; i++) {
        TreeNode current = q.poll();
        if (current.left != null) {
          q.offer(current.left);
        }
        if (current.right != null) {
          q.offer(current.right);
        }
      }
    }
    return depth;
  }
}