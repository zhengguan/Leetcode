// 148
// Sort List
// Sort a linked list in O(n log n) time using constant space complexity.
// https://leetcode.com/problems/sort-list/


/**
 * Definition for singly-linked list.
 */
import tester.Tester;
import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    
    // GIVEN: a list(without head node)
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        else {
            return sortListHelper(head, null);
        }
    }    
    
    // GIVEN: two node of a list start and end
    // WHERE: the order of start in the list less than or equal to end
    // RETURNS: the sorted list of nodes between start(inclusive) and 
    // end(exclusive)
    ListNode sortListHelper(ListNode start, ListNode end) {
        if(start == end) {
            return start;
        }
        else {            
            ListNode le = new ListNode(0); //node whose value less than or equal to start
            ListNode letLast = le;
            ListNode gt = new ListNode(0);
            ListNode gtLast = gt;
            ListNode node = start.next; //
            while(node != end) {
                if(node.val <= start.val) {
                    letLast.next = node;
                    letLast = letLast.next;
                }
                else {
                    gtLast.next = node;
                    gtLast = gtLast.next;
                }
                node = node.next;
            }
            letLast.next = start;
            gtLast.next = end;
            le.next = sortListHelper(le.next, letLast.next);
            gt.next = sortListHelper(gt.next, gtLast.next);
            start.next = gt.next;
            return le.next;
        }
    }
    
    void swap(ListNode l1, ListNode l2) {
        int tmp = l1.val;
        l1.val = l2.val;
        l2.val = tmp;
    }
    
    void printList(ListNode head) {
        ListNode node = head;
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
        return;        
    }
}


class SolutionExamples {
    Solution s = new Solution();
    
    boolean isSorted(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        ListNode pre = head;
        ListNode curr = pre.next;
        while(curr != null) {
            if(curr.val < pre.val) {
                return false;
            }
            pre = curr;
            curr = curr.next;
        }
        return true;
    }
    
    ListNode convertArrayToList(int[] nums) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for(int i = 0; i < nums.length; i++) {
            curr.next = new ListNode(nums[i]);
            curr = curr.next;
        }
        return head.next;
    }
    
    boolean testSortList(Tester t) {
//        ListNode head = this.convertArrayToList(new int[] {1, 2});
//        ListNode head = this.convertArrayToList(new int[] {2, 1});
//        ListNode head = this.convertArrayToList(new int[] {4, 3});
        ListNode head = this.convertArrayToList(new int[] {4,19,14,5,-3,1,8,5,11,15,-5});
//        s.sortList(head);
        s.printList(s.sortList(head));
        return
        t.checkExpect(this.isSorted(s.sortList(head)), true);
    }
}


class Solution2 {
    // GIVEN: a list(without head node)
    public ListNode sortList(ListNode head) {
        ArrayList<Integer> nums = convertListToArrayList(head);
        Collections.sort(nums);
        return convertArrayListToList(nums);
    }
   
    
    ArrayList<Integer> convertListToArrayList(ListNode head) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        ListNode node = head;
        while(node != null) {
            nums.add(node.val);
            node = node.next;
        }
        return nums;       
    }
    
    ListNode convertArrayListToList(ArrayList<Integer> nums) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for(int i = 0; i < nums.size(); i++) {
            curr.next = new ListNode(nums.get(i));
            curr = curr.next;
        }
        return head.next;
    }
    
    void printList(ListNode head) {
        ListNode node = head;
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
        return;        
    }    
}

class Solution2Examples {
    Solution2 s = new Solution2();
    
    boolean isSorted(ListNode head) {
        if(head.next == null || head.next.next == null) {
            return true;
        }
        ListNode pre = head.next;
        ListNode curr = pre.next;
        while(curr != null) {
            if(curr.val < pre.val) {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }
    
    ListNode convertArrayToList(int[] nums) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for(int i = 0; i < nums.length; i++) {
            curr.next = new ListNode(nums[i]);
            curr = curr.next;
        }
        return head.next;
    }
    
    boolean testSortList(Tester t) {
//        ListNode head = this.convertArrayToList(new int[] {1, 2});
        ListNode head = this.convertArrayToList(new int[] {4,19,14,5,-3,1,8,5,11,15,-5});
        s.printList(s.sortList(head));
//        ListNode head = this.convertArrayToList(new int[] {3, 2, 1, 4});
//        s.printList(head);
        return 
        t.checkExpect(this.isSorted(s.sortList(head)), true);
    }
}