// Leetcode 117	
// Populating Next Right Pointers in Each Node II
// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
/*
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/


class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}

public class Solution {
    public void connect(TreeLinkNode root) {
        connectHelper(root, null);
    }
    
    private void connectHelper(TreeLinkNode left, TreeLinkNode right) {
        if (left == null && right == null) {
            return;
        } else if (left == null) {
            //@assert right != null;
            connectHelper(right.left, right.right);
        } else if (right == null) {
            //@assert left != null;
            connectHelper(left.left, left.right);
        } else {
            //@assert (left != null && right != null);
            right.next = left.next;
            left.next = right;
            connectHelper(left.left, right.right);
            connectHelper(left.left, right.left);
            connectHelper(left.left, left.right);
        }
    }
}
