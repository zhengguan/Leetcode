// Leetcode 236	
// Lowest Common Ancestor of a Binary Tree
// https:// leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

// Given a binary tree, find the lowest common ancestor (LCA) of two given nodes 
// in the tree.
// 
// According to the definition of LCA on Wikipedia: ¡°The lowest common ancestor 
// is defined between two nodes v and w as the lowest node in T that has both 
// v and w as descendants (where we allow a node to be a descendant of itself).¡±
// 
//         _______3______
//        /              \
//     ___5__          ___1__
//    /      \        /      \
//    6      _2       0       8
//          /  \
//          7   4
// For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another 
// example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of 
// itself according to the LCA definition.

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
 
// RETPRESENTS: whether a node is the ancestor of the other two nodes
// if so set IsAncestor.ancestor to the node, and mark the two boolean true,
// else set the corresponding flag IsAncestor.isAncestorOfNode1 and 
// IsAncestor.isAncestorOfNode2
class IsAncestor {
	boolean isAncestorOfNode1;
	boolean isAncestorOfNode2;
	TreeNode ancestor;
	
	IsAncestor(boolean isAncestorOfNode1, boolean isAncestorOfNode2, 
			TreeNode ancestor) {
		this.isAncestorOfNode1 = isAncestorOfNode1;
		this.isAncestorOfNode2 = isAncestorOfNode2;
		this.ancestor = ancestor;
	}
}

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return this.lCAHelper(root, p, q).ancestor;
    }
    
    // GIVEN: a Tree root and two TreeNode p, q in root
    // RETURNS: if root contains the two TreeNode return the lowest common 
    // ancestor in IsAncestor, otherwise set the corresponding falg 
    // IsAncestor.isAncestorOfNode1 and IsAncestor.isAncestorOfNode2
    // TERMINATION: the height of the Tree root becomes smaller
    public IsAncestor lCAHelper(TreeNode root, TreeNode p, TreeNode q) {
    	if(root == null) {
    		return new IsAncestor(false, false, null);
    	}
    	else {    		
    		IsAncestor lchildResult = lCAHelper(root.left, p, q);
    		IsAncestor rchildResult = lCAHelper(root.right, p, q);
    		if(lchildResult.ancestor != null) {
    			return lchildResult;
    		}
    		if(rchildResult.ancestor != null) {
    			return rchildResult;
    		}
    		lchildResult.isAncestorOfNode1 = lchildResult.isAncestorOfNode1 ||
    				rchildResult.isAncestorOfNode1 || (root == p);
    		lchildResult.isAncestorOfNode2 = lchildResult.isAncestorOfNode2 ||
    				rchildResult.isAncestorOfNode2 || (root == q);
    		if(lchildResult.isAncestorOfNode1 && lchildResult.isAncestorOfNode2) {
    			lchildResult.ancestor = root;
    		}
    		return lchildResult;
    	}
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	// tests for method lowestCommonAncestor()
	boolean testLowestCommonAncestor(Tester t) {
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
		t.checkExpect(s.lowestCommonAncestor(tn1, tn4, tn4), tn4) &&
		t.checkExpect(s.lowestCommonAncestor(tn1, tn3, tn5), tn3) &&
		t.checkExpect(s.lowestCommonAncestor(tn1, tn5, tn3), tn3) &&
		t.checkExpect(s.lowestCommonAncestor(tn1, tn2, tn5), tn1);
	}
}