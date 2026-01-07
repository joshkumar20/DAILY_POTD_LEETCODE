// Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.

// Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.

// Note that you need to maximize the answer before taking the mod and not after taking it.

 

// Example 1:


// Input: root = [1,2,3,4,5,6]
// Output: 110
// Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
// Example 2:


// Input: root = [1,null,2,3,4,null,null,5,6]
// Output: 90
// Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
 

// Constraints:

// The number of nodes in the tree is in the range [2, 5 * 104].
// 1 <= Node.val <= 104

class Solution {
    long maxProduct = 0;
    long totalSum = 0;
    final int MOD = 1000000007;

    public int maxProduct(TreeNode root) {
        // Step 1: get total sum
        totalSum = dfsTotal(root);

        // Step 2: compute max product using subtree sums
        dfsSubtree(root);

        return (int)(maxProduct % MOD);
    }

    // Get total tree sum
    private long dfsTotal(TreeNode node) {
        if (node == null) return 0;
        return node.val + dfsTotal(node.left) + dfsTotal(node.right);
    }

    // Compute subtree sums and update max product
    private long dfsSubtree(TreeNode node) {
        if (node == null) return 0;

        long left = dfsSubtree(node.left);
        long right = dfsSubtree(node.right);

        long subSum = node.val + left + right;

        // possible product when cutting above this subtree
        long product = subSum * (totalSum - subSum);

        maxProduct = Math.max(maxProduct, product);

        return subSum;
    }
}


 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }