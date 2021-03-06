// Leetcode 142	
// Linked List Cycle II
// https://leetcode.com/problems/linked-list-cycle-ii/


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
    
    // RETURNS: the ith node in the list
    public ListNode get(int i) {
    	if (i == 0) {
    		return this;
    	}
    	else if (i > 0 && next != null) {
    		return this.next.get(i -1);    				
    	}
    	else {
    		throw new RuntimeException("ListNode: get from null");
    	}
    }
}

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if ((head == null) || (head.next == null)) return null;

        ListNode hare = head;
        ListNode tortoise = head;
        while (true) 
        //@loop_invariant (hare != null);
        //@loop_invariant (tortoise != null);
        {
            if (hare.next == null || hare.next.next == null) return null;
            hare = hare.next.next;
            tortoise = tortoise.next;
            if (hare == tortoise) break;

        }
        //@assert hare == tortoise;
        
        hare = head;
        while (hare != tortoise) {
            hare = hare.next;
            tortoise = tortoise.next;
        }
        return hare;
    }
}