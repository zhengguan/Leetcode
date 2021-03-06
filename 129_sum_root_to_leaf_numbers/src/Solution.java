// Leetcode 129	
// Sum Root to Leaf Numbers
// https://leetcode.com/problems/sum-root-to-leaf-numbers/

/*

Given a binary tree containing digits from 0-9 only, each root-to-leaf path 
could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.

*/



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public int sumNumbers(TreeNode root) {
        if(root == null) {
            return 0;
        }
        else {
            return helper(root, 0);
        }
    }
    
    /**
     * 
     * @param sub a sub-tree of a tree root
     * WHERE: (sub != null)
     * @param sumOfPrev sum of predecessors of sub
     * @return sum of all root-to-leaf paths(start from root, not sub) whose 
     * leaves are contained in sub
     */
    private int helper(TreeNode sub, int sumOfPrev) {
    	if(isLeaf(sub)) {
    		return sumOfPrev * 10+ sub.val;
    	}
    	else {
    		//@assert (sub.left != null) || (sub.right != null);
    		int result = 0;
    		sumOfPrev = sumOfPrev * 10 + sub.val;
    		if(sub.left != null) {
    			result += helper(sub.left, sumOfPrev);
    		}
    		if(sub.right != null) {
    			result += helper(sub.right, sumOfPrev);
    		}
    		return result;
    	}
    }
    
    /**
     * 
     * @param t represents a tree
     * WHERE: (t != null)
     * @return true iff t is a tree node
     */
    private boolean isLeaf(TreeNode t) {
    	return (t.left == null) && (t.right == null);
    }
}