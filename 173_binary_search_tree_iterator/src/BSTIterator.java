// Leetcode 173	
// Binary Search Tree Iterator
// https://leetcode.com/problems/binary-search-tree-iterator/

// Implement an iterator over a binary search tree (BST). Your iterator will 
// be initialized with the root node of a BST.
// 
// Calling next() will return the next smallest number in the BST.
// 
// Note: next() and hasNext() should run in average O(1) time and uses O(h) 
// memory, where h is the height of the tree.

import tester.Tester;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class BSTIterator {
	Stack<TreeNode> stack;
	TreeNode node;
	
    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        node = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (!stack.isEmpty()) || (node != null);
    }

    /** @return the next smallest number */
    public int next() {
        while (node != null) {
        	stack.push(node);
        	node = node.left;
        }
        node = stack.pop();
        int val = node.val;
        node = node.right;
        return val;
    }
    
    // the process of the iterator traversal is like an in-order
    // traversal of the tree, so I wrote the inorder traversal first
    private void inOrderTraversal(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	else {
    		Stack<TreeNode> stack = new Stack<TreeNode>();
    		TreeNode node = root;
    		while (!stack.isEmpty() || node != null) 
    		//@loop_invariant stack contains all of the parents of current tree
    		//@loop_invariant is the current node to be processed  
    		{
    			while (node != null) {
    				stack.push(node);
    				node = node.left;
    			}
    			node = stack.pop();
    			/* process(node); */
    			node = node.right;
    		}
    		return;
    	}
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

class BSTIteratorExamples {
	private BSTIterator itr;
	private TreeNode root;
	private TreeNode[] nodes;
	
	private void reset() {
		int[] nums = new int[]{4, 2, 6, 1, 3, 5, 7};
		int len = 7;
		nodes = new TreeNode[len];
		for (int i = 0; i < len; i++) {
			nodes[i] = new TreeNode(nums[i]);
		}
		for (int i = 0; i < len / 2; i++) {
			nodes[i].left = nodes[i * 2 + 1];
			nodes[i].right = nodes[(i + 1) * 2];
		}
		root = nodes[0];
		itr = new BSTIterator(root);
		return;
	}
	
	// tests for class BSTIterator
	void testBSTIterator(Tester t) {
		reset();
		for (int i = 0; i < nodes.length; i++) {
			t.checkExpect(itr.hasNext(), true);
			int val = itr.next();
			t.checkExpect(val == i + 1, true, "iterate on node" + val);
		}
		t.checkExpect(itr.hasNext(), false);
		reset();
	}
}