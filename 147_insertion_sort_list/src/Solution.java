// Leetcode 147	
// Insertion Sort List
// https://leetcode.com/problems/insertion-sort-list/

// Sort a linked list using insertion sort.

import tester.Tester;

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    // EFFECT: sort the given list
    // RETURNS: a sorted version of the given list
    public ListNode insertionSortList2(ListNode head) {
        ListNode curr = head;
        while(curr != null) {
            ListNode min = curr;
            ListNode tmp = curr;
            while(tmp != null) {
                if(tmp.val < min.val) {
                    min = tmp;
                }
                tmp = tmp.next;
            }
            swap(curr, min);
            curr = curr.next;
        }
        return head;
    }
    
    // EFFECT: swap the value of the two given ListNodes
    public void swap(ListNode node1, ListNode node2) {
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }
    
    public ListNode insertionSortList(ListNode head) {
        ListNode header = new ListNode(0);
        header.next = head;
        ListNode prev = header;
        while(prev.next != null) 
        //@loop_invariant prev != null;
        {
            ListNode prevOfMin = this.minOfPrevInList(prev);
            swapListNode(prev, prevOfMin);
            prev = prev.next;
        }
        return header.next;
    }
    
    // GIVEN: a list with at least two nodes
    // RETURNS: the previous listnode of the one with minimum value after the first one
    // (i.e. don't need to compare the first one with other node)
    public ListNode minOfPrevInList(ListNode l) {
        ListNode prevOfMin = l;
        ListNode prev = l;
        while(prev.next != null) {
            if(prev.next.val < prevOfMin.next.val) {
                prevOfMin = prev;
            }
            prev = prev.next;
        }
        return prevOfMin;
    }
    
    // GIVEN: two ListNode p, q which guarantee to have next node
    // EFFECT: swap their next node
    public void swapListNode(ListNode p, ListNode q) {
        ListNode tmp = p.next;
        p.next = q.next;
        q.next = tmp;
        
        p = p.next;
        q = q.next;
        
        tmp = p.next;
        p.next = q.next;
        q.next = tmp;
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	// RETURSN: a corresponding list of the given array
	ListNode arrayToList(int[] nums) {
		ListNode headerNode = new ListNode(0);
		ListNode p = headerNode;
		for(int i = 0; i < nums.length; i++) {
			p.next = new ListNode(nums[i]);
			p = p.next;
		}
		return headerNode.next;
	}
	
	// RETURNS: true iff the given list is sorted
	boolean isSortedList(ListNode l) {
		if(l == null) {
			return true;
		}
		else {
			while(l.next != null) {
				if(l.val > l.next.val) {
					return false;
				}
				l = l.next;
			}
			return true;
		}
	}
	
	// a checker for insertionSortList()
	boolean insertionSortListChecker(int[] nums) {
		ListNode l = this.arrayToList(nums);
		ListNode sortedList = s.insertionSortList(l);
		return this.isSortedList(sortedList);
	}
	
	// tests for method insertionSortList()
	boolean testInsertionSortList(Tester t) {
		return 
		t.checkExpect(this.insertionSortListChecker(new int[]{2, 1, 3})) &&
		t.checkExpect(this.insertionSortListChecker(new int[]{}));
	}
}