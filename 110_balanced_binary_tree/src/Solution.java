// Leetcode 110
// Balanced Binary Tree
// https://leetcode.com/problems/balanced-binary-tree/

// Given a binary tree, determine if it is height-balanced.
// 
// For this problem, a height-balanced binary tree is defined 
// as a binary tree in which the depth of the two subtrees of 
// every node never differ by more than 1.

import tester.Tester;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

// RETRESENTS: the state of a binary tree
class TreeState {
	int height; // height of the tree
	boolean isBalanced; // whether the tree is balanced

	TreeState(int height, boolean isBalanced) {
		this.height = height;
		this.isBalanced = isBalanced;
	}
}

public class Solution {
	public boolean isBalanced(TreeNode root) {
		return checkTreeState(root).isBalanced;
	}

	private TreeState checkTreeState(TreeNode root) {
		if (root == null) {
			return new TreeState(0, true);
		} else {
			TreeState lState = checkTreeState(root.left);
			TreeState rState = checkTreeState(root.right);
			int height = 1 + Math.max(lState.height, rState.height);
			if (Math.abs(lState.height - rState.height) <= 1
					&& lState.isBalanced && rState.isBalanced) {
				return new TreeState(height, true);
			} else {
				return new TreeState(height, false);
			}
		}
	}
}

class SolutionExamples {
	Solution s;
	TreeNode[] nodes;
	
	private void reset() {
		s = new Solution();
		int len = 5;
		nodes = new TreeNode[len];
		for (int i = 0; i < len; i++) {
			nodes[i] = new TreeNode(i);
		}
		nodes[0].right = nodes[1];
		nodes[1].left = nodes[2];
		nodes[1].right = nodes[3];
		nodes[3].right = nodes[4];
	}
	
	// tests for method isBalanced
	boolean testIsBalanced(Tester t) {
		reset();
		return
		t.checkExpect(s.isBalanced(null), true) &&
		t.checkExpect(s.isBalanced(nodes[4]), true) &&
		t.checkExpect(s.isBalanced(nodes[3]), true) &&
		t.checkExpect(s.isBalanced(nodes[1]), true) &&
		t.checkExpect(s.isBalanced(nodes[0]), false);
	}
}