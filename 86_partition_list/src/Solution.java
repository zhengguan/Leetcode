import java.util.Objects;

// Leetcode 86	
// Partition List
// https://leetcode.com/problems/partition-list/

// Given a linked list and a value x, partition it such that all nodes less than 
// x come before nodes greater than or equal to x.
// 
// You should preserve the original relative order of the nodes in each of the two 
// partitions.
// 
// For example,
// Given 1->4->3->2->5->2 and x = 3,
// return 1->2->2->4->3->5.



class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    
    public boolean equals(Object o) {
    	if(! (o instanceof ListNode)) {
    		return false;
    	}
    	ListNode that = (ListNode) o;
    	return (that != null) && (this.val == that.val)
    		   && (((this.next == null) && (that.next == null)) 
    		       || ((this.next != null) && (this.next.equals(that.next))));

    }
    
    public int hashCode() {
    	return Objects.hash(val, this.next.hashCode());
    }
    
    public String toString() {
    	if(next == null) {
    		return val + "";
    	}
    	else {
    		return val + "  " + next.toString();
    	}
    }
}

public class Solution {
    public ListNode partition(ListNode head, int x) {
    	if(head == null || head.next == null) {
    		return head;
    	}
    	else {
    		ListNode l1 = new ListNode(0);
    		ListNode curr1 = l1;
    		ListNode l2 = new ListNode(0);
    		ListNode curr2 = l2;
    		
    		ListNode t = head;
    		while(t != null) 
    		/*@loop_invariant l1 contains all nodes in the sub list segment
    		   [head, curr) of the original list that less than x 
    		 @*/
        	/*@loop_invariant l1 contains all nodes in sub list segment
     		   [head, curr) of the original list that greater than or equal 
     		   to x 
     		 @*/
    		{
    			if(t.val < x) {
    				curr1.next = t;
    				curr1 = t;
    			}
    			else {
    				curr2.next = t;
    				curr2 = t;    				
    			}
    			t = t.next;
    		}
    		curr1.next = l2.next;
    		curr2.next = null;
    		return l1.next;
    	}
    }
}