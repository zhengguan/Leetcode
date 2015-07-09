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
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> traversal = new ArrayList<Integer>();
//        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        Stack<TreeNode> queue = new Stack<TreeNode>();
        queue.push(root);
        while(queue.isEmpty() == false) {
            TreeNode node = queue.pop();

            if(node == null) {
                continue;
            }
            else {
                traversal.add(node.val);
                queue.push(node.right);
                queue.push(node.left);
            }
        }
        return traversal;
    }
}


// REPRESENTS: a queue
class Stack<T> {
    ArrayList<T> list = new ArrayList<T>();
    
    Stack() {}
    
    // EFFECT: add an element t to the top of this Stack 
    public void push(T t) {
    	this.list.add(t);
    }
    
    // RETURNS: the top element of this Stack
    public T top() {
    	return this.list.get(list.size() - 1);
    }
    
    // WHERE: this Stack should be non-empty
    // EFFECT: pop the top element of this Stack
    // RETURNS: the top element
    public T pop() {
    	T t = this.list.get(list.size() - 1);
    	this.list.remove(list.size() - 1);
    	return t;
    }
    
    // RETURNS: true iff this Stack is empty
    public boolean isEmpty() {
    	return this.list.size() == 0;
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
	
	// testss for method inorderTraversal()
	boolean testInorderTraversal(Tester t) {
		TreeNode tn1 = new TreeNode(1);
		TreeNode tn2 = new TreeNode(2);
		TreeNode tn3 = new TreeNode(3);
		TreeNode tn4 = new TreeNode(4);
		tn1.left = tn2;
		tn1.right = tn3;
		tn3.left = tn4;
		return 
		t.checkExpect(s.inorderTraversal(tn2.left), new ArrayList<Integer>()) &&
		t.checkExpect(s.inorderTraversal(tn1.left), 
				this.arrayToList(new int[]{2})) &&
		t.checkExpect(s.inorderTraversal(tn1), 
				this.arrayToList(new int[]{2, 1, 3}));
	}
}