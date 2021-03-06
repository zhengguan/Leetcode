import java.util.ArrayList;
import java.util.List;

// Leetcode 144	
// Binary Tree Preorder Traversal
// https://leetcode.com/problems/binary-tree-preorder-traversal/

// Given a binary tree, return the preorder traversal of its nodes' values.
// 
// For example:
// Given binary tree {1,#,2,3},
//    1
//     \
//      2
//     /
//    3
// return [1,2,3].

import tester.Tester;

import java.util.*;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
//        return this.preorderTraversalRecur(root);
    	return this.preorderTraversalIter(root);
        
    }
    
    ArrayList<Integer> traversal = new ArrayList<Integer>();
    public List<Integer> preorderTraversalRecur(TreeNode root) {
    	if(root == null) {
    		return this.traversal;
    	}
    	else {
    		this.traversal.add(root.val);
    		this.preorderTraversalRecur(root.left);
    		this.preorderTraversalRecur(root.right);
    		return this.traversal;
    	}
    }
    
    public List<Integer> preorderTraversalIter(TreeNode root) {
    	ArrayList<Integer> traversal = new ArrayList<Integer>();
    	if(root == null) {
    		return traversal;
    	}
    	else {
    		Stack<TreeNode> stack = new Stack<TreeNode>();
    		TreeNode node = root;
    		while(!stack.isEmpty() || node != null) 
    		//@loop_invariant TreeNode in stack are those whose right child 
    		// hasn't been visited
    		//@loop_invariant node is the tree to be visited
    		{
    			while(node != null) {
    				stack.add(node);
    				traversal.add(node.val);
    				node = node.left;
    			}
    			node = stack.pop();
    			node = node.right;
    		}
    		return traversal;
    	}
    }
    
}

class SolutionExamples {
	Solution s = new Solution();
	
	
	// RETURSN: a corresponding list of the given array
	List<Integer> arrayToList(int[] array) { 
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list;
	}
	
	// tests for method preorderTraversal()
	boolean testPreorderTraversal(Tester t) {
		TreeNode tn1 = new TreeNode(1);
		TreeNode tn2 = new TreeNode(2);
		TreeNode tn3 = new TreeNode(3);
		TreeNode tn4 = new TreeNode(4);
		TreeNode tn5 = new TreeNode(5);
		tn1.left = tn2;
		tn1.right = tn3;
		tn3.left = tn4;
		tn3.right = tn5;
		return
		t.checkExpect(s.preorderTraversal(tn2.left), 
				this.arrayToList(new int[]{})) &&
		t.checkExpect(s.preorderTraversal(tn2), 
				this.arrayToList(new int[]{2})) &&
		t.checkExpect(s.preorderTraversal(tn1), 
				this.arrayToList(new int[]{1, 2, 3, 4, 5}));		
	}
}