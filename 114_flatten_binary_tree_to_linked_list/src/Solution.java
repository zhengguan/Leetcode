// 	Leetcode 114	
// Flatten Binary Tree to Linked List
// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

// Given a binary tree, flatten it to a linked list in-place.
// 
// For example,
// Given
// 
//          1
//         / \
//        2   5
//       / \   \
//      3   4   6
//      
// The flattened tree should look like:
//    1
//     \
//      2
//       \
//        3
//         \
//          4
//           \
//            5
//             \
//              6


import tester.Tester;

import java.util.*; 

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
//	boolean equals(TreeNode that) {
//		return (that != null) && (this.val == that.val) &&
//				(this.left.equals(that.left)) &&
//				(this.right.equals(that.right));
//	}
}

public class Solution {       
	public void flatten(TreeNode root) {
		this.flattenRecur(root);
	}
	
	// EFFECT: flatten the given tree in place
	// RETURNS: the last node of the flattened tree
	public TreeNode flattenRecur(TreeNode root) {
		if(root == null) {
			return null;
		}
		else {
			TreeNode lchild = root.left;
			TreeNode rchild = root.right;
			root.left = null;
			root.right = null;
			TreeNode rear = root;
			TreeNode lrear = this.flattenRecur(lchild);
			TreeNode rrear = this.flattenRecur(rchild);	
			if (lrear != null) {
				rear.right = lchild;
				rear = lrear;
			}
			if(rrear != null) {
				rear.right = rchild;
				rear = rrear;
			}
			return rear;
			
		}
	}
}

// test for flatten
class SolutionExamples {
	TreeNode t1;
	TreeNode t2;
	TreeNode t3;
	TreeNode t4;
	TreeNode t5;
	TreeNode t6;
	Solution s;
	
	private void reset() {
		this.t1 = new TreeNode(1);
		this.t2 = new TreeNode(2);
		this.t3 = new TreeNode(3);
		this.t4 = new TreeNode(1);
		this.t5 = new TreeNode(2);
		this.t6 = new TreeNode(3);
		this.t1.left = t2;
		this.t1.right = t3;
		this.t4.right = t5;
		this.t5.right = t6;
		this.s = new Solution();
	}
	
	// test for flatten
	void testFlatten(Tester t) {
		reset();
		s.flatten(t3);
		t.checkExpect(t3, t6);	
		s.flatten(t1);
		t.checkExpect(t1, t4);
	}
}
 