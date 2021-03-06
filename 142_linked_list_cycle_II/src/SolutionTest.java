import static org.junit.Assert.*;

import org.junit.*;


public class SolutionTest {
	Solution s;
	ListNode[] ls;
	
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
	
	@Before
	public void setUp() {
		s = new Solution();
		ls = new ListNode[3];
		ls[0] = arrayToList(new int[]{1, 2, 3});
		ls[1] = arrayToList(new int[]{1, 2, 3, 4});
		ls[1].get(3).next = ls[1].get(0);
		
		ls[2] = arrayToList(new int[]{1, 2, 3, 4});
		ls[2].get(3).next = ls[2].get(1);
	}
	
	@Test
	public void test() {
		assertEquals(null, s.detectCycle(ls[0]));
		
		assertEquals(ls[1], s.detectCycle(ls[1]));
		
		assertEquals(ls[2].get(1), s.detectCycle(ls[2]));
	}

}
