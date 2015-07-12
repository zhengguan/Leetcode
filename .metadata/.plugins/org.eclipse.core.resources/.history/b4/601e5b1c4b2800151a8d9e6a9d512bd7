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
    
    // This is wrong !!!!
//    public void postorderTraversalIter(TreeNode root) {   	
//    	if(root == null) {
//    		return;
//    	}
//    	else {
//    		// What's the meaning of stack ???
//        	ArrayList<TreeNode> stack = new ArrayList<TreeNode>();
//    		stack.add(root);   		
//    		ArrayList<Integer> modes = new ArrayList<Integer>();
//    		modes.add(0);
//    		while(!stack.isEmpty()) {
//    			TreeNode node = stack.get(stack.size()-1);
//    			if(node == null) {
//    				continue;
//    			}
//    			else {
//    				
//    			}
//    		}
//    		return;
//    	}
//    }
  
    // GIVEN: a array of three value 0, 1, 2 corresponding to three colors
    // red, white, blue
    // EFFECT: sort the array in order of red, white, blue
    public void sortColors(int[] nums) {
        int start = partition(nums, 0, nums.length, 0);
        start = partition(nums, start, nums.length, 1);  
        return;
    }
    
    // GIVEN: an array, a range [start, end), a value
    // WHERE: 0 <= start <= end <= nums.length
    // RETURNS: \result in the range
    // EFFECT: partition the array into two parts, such that 
    // all elements in nums[start, \result) equal to val,
    // all elements in nums[\result, end) greater than val
    public int partition(int[] nums, int start, int end, int val) {
    	int i = start, j = end; 
    	while(i < j) 
    	//@loop_invariant start <= i <= j <= end;
    	//@loop_invariant all elements in nums[start, i) equal to val
    	//@loop_invariant all elements in nums[j, end) greater than val
    	{
    		if(nums[i] == val) {
    			i++;
    		}
    		else {
    			swap(nums, i, j - 1);
    			j--;
    		}
    	}
    	//@assert i == j;
    	return i;
    }
    
    public void swap(int[] nums, int i, int j) {
    	int tmp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = tmp;
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	// tests for method postorderTraversal()
	boolean testPostorderTraversal(Tester t) {
		TreeNode[] tns = new TreeNode[8];
		for(int i = 0; i < 8; i++) {
			tns[i] = new TreeNode(i);
		}
		for(int i = 1; i < 4; i++) {
			tns[i].left = tns[i * 2];
			tns[i].right = tns[i * 2 + 1];
		}		
		System.out.println(s.postorderTraversal(tns[6].left));
		System.out.println(s.postorderTraversal(tns[6]));
		System.out.println(s.postorderTraversal(tns[1]));
		return
		true;
	}
}