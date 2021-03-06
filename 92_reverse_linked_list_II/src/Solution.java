// Leetcode 92	
// Reverse Linked List II
// https://leetcode.com/problems/reverse-linked-list-ii/

// Reverse a linked list from position m to n. Do it in-place and in one-pass.
// 
// For example:
// Given 1->2->3->4->5->NULL, m = 2 and n = 4,
// 
// return 1->4->3->2->5->NULL.
// 
// Note:
// Given m, n satisfy the following condition:
// 1 �� m �� n �� length of list.



import java.util.*;



class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    
    boolean equals(ListNode that) {
    	if (that == null) {
    		return false;
    	}
    	if (this.next == null) {
    		return this.val == that.val && this.next == that.next;
    	}
    	else {
    		return this.val == that.val && this.next.equals(that.next);
    	}
    }
    
    public String toString() {
    	if(this.next == null) {
    		return val + "";
    	}
    	else {
        	return val + " " + this.next.toString();    		
    	}  	
    }
}

public class Solution {
	/**
	 * 
	 * @param head
	 * @param m
	 * @param n 
	 * WHERE: 1 <= m && m <= n && n <= \length(head)
	 * @return a list like head but with sublist that contains mth to 
	 * nth(start counting from 1) nodes reversed
	 */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int i;
        ListNode prev = new ListNode(0);
        prev.next = head;
        ListNode curr = head;
        head = prev;
        
    	for(i = 1; i < m; i++) 
    	//@loop_invariant curr is the ith node of the original list
    	{
    		prev =  curr;
    		curr = curr.next;
    	}
    	
    	ListNode end = curr;
    	for(; i < n; i++) {
    		end = end.next;
    	}
    	
    	while(curr != end) {
    		prev.next = curr.next;
    		curr.next = end.next;
    		end.next = curr;
    		curr = prev.next;
    	}    	    	
    	return head.next;
    }
}