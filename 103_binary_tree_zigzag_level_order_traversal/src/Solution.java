// Leetcode 103	
// Binary Tree Zigzag Level Order Traversal
// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
/*
Given a binary tree, return the zigzag level order traversal of its nodes' 
values. (ie, from left to right, then right to left for the next level and 
alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]


 */

import java.util.*;



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    public String toString() {
    	return "[" + val + "," + 
    			(left == null ? "[]" : left.toString()) + "," + 
    			(right == null ? "[]" : right.toString()) +
    			"]";
    }
}

public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<TreeNode> forest = new ArrayList<TreeNode>();
        forest.add(root);
        zigzagLevelOrderOfForest(forest, result, false);
        return result;
    }
    
    /**
     * 
     * @param forest
     * @param values
     * @param fromBackward true iff the current level should be traversed from the backward 
     */
    // EFFECT: add all zigzag level order traversal of forest's nodes' values to values
    private void zigzagLevelOrderOfForest(List<TreeNode> forest, List<List<Integer>> values, 
    		boolean fromBackward) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        List<Integer> currLevelValues = new ArrayList<Integer>();
        for (TreeNode t : forest) {
            if (t != null) {
                children.add(t.left);
                children.add(t.right);
                currLevelValues.add(t.val);
            }
        }
        if (!currLevelValues.isEmpty()) {
            //@assert (children.isEmpty() == false);
        	if (fromBackward) {
        		Collections.reverse(currLevelValues);
        	}
            values.add(currLevelValues);            
            zigzagLevelOrderOfForest(children, values, !fromBackward);
        }
        return;
    }
}

/*
[0,
	[1,
		[3,[],[]],
		[4,[],[]]],
	[2,
		[5,[],[]],
		[]]]

*/