// Leetcode 113
// Path Sum II
// https://leetcode.com/problems/path-sum-ii/

/*

Given a binary tree and a sum, find all root-to-leaf paths where each path's 
sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]

*/

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

 
 
 
public class Solution {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    
    // GIVEN: a tree and a value sum
    // EFFECT: add all root to leaf paths to result where each path's
    // sum equals the given sum
    // RETURNS: result
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	if(root == null) {
    		return result;
    	}
    	else {
    		helper(root, sum, new ArrayList<Integer>());
    		return result;
    	}
    }
    
    // GIVEN: a sub-tree sub of a tree root, value sum, a list of values of 
    // sub's predecessor
    // WHERE: root != null
    // EFFECT: for all leaf of sub whose sub to leaf path sums to sumLeft
    // add the root to leaf path to result
    private void helper(TreeNode root, int sumLeft, List<Integer> pred) {
        if(isLeaf(root)) {
        	if(root.val == sumLeft) {
                List<Integer> l = new ArrayList<Integer>();
                l.addAll(pred);
                l.add(root.val);
                result.add(l);
                return;
        	}
            return;
        }
        else {
            pred.add(root.val);
            
            if(root.left != null) {
            	helper(root.left, sumLeft - root.val, pred);
            }
            
            if(root.right != null) {
                helper(root.right, sumLeft - root.val, pred);            	
            }
            pred.remove(pred.size() - 1);
            return;
        }
    }
    
    // RETURNS: true iff the given node is a leaf
    private boolean isLeaf(TreeNode t) {
    	return (t != null) && (t.left == null) && (t.right == null);
    	
    }
}