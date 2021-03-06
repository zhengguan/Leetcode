import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s;
	ListNode l;
	
	/**
	 * 
	 * @param nums is a non-empty array
	 * @return a corresponding ListNode of the given array
	 */
	private ListNode arrayToListNode(int[] nums) {
		ListNode head = null;
		for(int i = nums.length; i > 0; i--) 
		//@loop_invariant list head contains elements of nums[i, nums.length)
		{
			ListNode tmp = new ListNode(nums[i-1]);
			tmp.next = head;
			head = tmp;
		}
		return head;
	}
	
	private void reset() {
		l = arrayToListNode(new int[]{1, 2, 3, 4 ,5});
		s = new Solution();
	}
	
	

	@Test
	public void test() {
//		fail("Not yet implemented");
		reset();
		
		assertTrue(l.equals(arrayToListNode(new int[]{1, 2, 3, 4 ,5})));
		assertTrue(s.reverseBetween(l, 2, 4).equals(
				arrayToListNode(new int[]{1, 4, 3, 2, 5})));
		reset();
	}
}
