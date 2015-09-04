import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	
	private ListNode arrayToList(int[] nums) {
		ListNode head = new ListNode(0);
		ListNode t = head;
		for(int i = 0; i < nums.length; i++) 
		//@loop_invariant 0 <= i && i <= nums.length
		{
			t.next = new ListNode(nums[i]);
			t = t.next;			
		}		
		return head.next;
	}

	@Test
	public void test() {
		Solution s = new Solution();
		ListNode l1 = arrayToList(new int[]{1, 4, 3, 2, 5, 2});
		ListNode l2 = arrayToList(new int[]{1, 2, 2, 4, 3, 5});
		assertEquals(arrayToList(new int[]{1}), arrayToList(new int[]{1}));
		assertEquals(arrayToList(new int[]{1, 2}), arrayToList(new int[]{1, 2}));
		assertEquals(s.partition(l1, 3), l2);
		
	}

}