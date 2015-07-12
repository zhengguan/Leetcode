// Leetcode 145	
// Binary Tree Postorder Traversal
// Given a binary tree, return the postorder traversal of its nodes' values.
// https:// leetcode.com/problems/binary-tree-postorder-traversal/		
// 
// For example:
// Given binary tree {1,#,2,3},
//    1
//     \
//      2
//     /
//    3
// return [3,2,1].
// 
// Note: Recursive solution is trivial, could you do it iteratively?

import tester.Tester;

import java.util.*;

/**
 * Definition for a binary tree node.
 */

// Definition 1:
// A Tree is either:
// 	-- null
// 	-- (Int Tree Tree)

// Definition 2:
// A Tree is either:
// 	-- (Int null null)
// 	-- (Int Tree null)
// 	-- (Int null Tree)
// 	-- (Int Tree Tree)

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    public String toString() {
    	return Integer.toString(val) + this.left.toString() + this.right.toString();
    }
}
 
public class Solution {
    List<Integer> treeTraversal; 
    
    public List<Integer> postorderTraversal(TreeNode root) {
    	treeTraversal = new ArrayList<Integer>();
        postorderTraversalRecur(root);
//        postorderTraversalIter(root);
        return treeTraversal;
    }
   

    public void postorderTraversalRecur(TreeNode root) {
    	if(root == null) {
    		return;
    	}
    	else {
    		postorderTraversalRecur(root.left);
    		postorderTraversalRecur(root.right);
    		treeTraversal.add(root.val);
    		return;
    	}
    }

    public void swap(int[] nums, int i, int j) {
    	int tmp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = tmp;
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
	
	// tests for method postorderTraversal()
	boolean testPostorderTraversal(Tester t) {
		TreeNode[] tns = new TreeNode[8];
		for(int i = 0; i < 8; i++) {
			tns[i] = new TreeNode(i + 1);
		}
		for(int i = 1; i < 4; i++) {
			tns[i].left = tns[i * 2];
			tns[i].right = tns[i * 2 + 1];
		}		
		System.out.println(tns);
		return
		t.checkExpect(s.postorderTraversal(tns[6].left), 
				this.arrayToList(new int[] {})) &&
		t.checkExpect(s.postorderTraversal(tns[5]), 
				this.arrayToList(new int[] {6})) &&
		t.checkExpect(s.postorderTraversal(tns[0]), 
				this.arrayToList(new int[] {4, 5, 2, 6, 7, 3, 1}));
	}
}