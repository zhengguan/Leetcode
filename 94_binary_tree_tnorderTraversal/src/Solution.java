// Leetcode 94	
// Binary Tree Inorder Traversal
// https:// leetcode.com/problems/binary-tree-inorder-traversal/

// Given a binary tree, return the inorder traversal of its nodes' values.
// 
// For example:
// Given binary tree {1,#,2,3},
//    1
//     \
//      2
//     /
//    3
// return [1,3,2].
// 
// Note: Recursive solution is trivial, could you do it iteratively?
// 
// confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
	
import java.util.*;

import tester.Tester;

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
	// RETURNS: a list that represents the inorder traversal of the given tree
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> traversal = new ArrayList<Integer>();
    	this.inorderTraversalHelper(traversal, root);
    	return traversal;
    }
    
    // EFFECT: traverse the Tree root and add all value into traversal
    public void inorderTraversalHelper(List<Integer> traversal, 
    		TreeNode root) {
    	if(root == null) {
    		return;
    	}
    	else {
    		this.inorderTraversalHelper(traversal, root.left);
    		traversal.add(root.val);
    		this.inorderTraversalHelper(traversal, root.right);
    	}
    }  
}

class SolutionIter {
	// RETURNS: a list that represents the inorder traversal of the given tree
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> traversal = new ArrayList<Integer>();
    	this.inorderTraversalHelper(traversal, root);
    	return traversal;
    }
	
    // EFFECT: traverse the Tree root and add all TreeNode value into traversal
    public void inorderTraversalHelper(List<Integer> traversal, 
    		TreeNode root) {
    	if(root == null) {
    		return;
    	}
    	else {
    		TreeNode p = root;
    		Stack<TreeNode> stack = new Stack<TreeNode>();
    		while(!stack.empty() || p != null) 
    		//@loop_invariant p represents  	
    		{
    			while(p != null) {
    				stack.push(p);
    				p = p.left;
    			}
    			p = stack.pop();
    			traversal.add(p.val);
    			p = p.right;
    		}
    		return;
    	}
    }
}


class SolutionExamples {
	SolutionIter s = new SolutionIter();	
	
	// RETURSN: a corresponding list of the given array
	List<Integer> arrayToList(int[] array) { 
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list;
	}
	
	// testss for method inorderTraversal()
	boolean testInorderTraversal(Tester t) {
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
		t.checkExpect(s.inorderTraversal(tn2.left), new ArrayList<Integer>()) &&
		t.checkExpect(s.inorderTraversal(tn1.left), 
				this.arrayToList(new int[]{2})) &&
		t.checkExpect(s.inorderTraversal(tn1), 
				this.arrayToList(new int[]{2, 1, 4, 3, 5}));
	}
}