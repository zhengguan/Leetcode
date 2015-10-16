import static org.junit.Assert.*;

import org.junit.*;


public class SolutionTest {
	Solution s;
	ListNode[] lists;
	
	private ListNode arrayToList(int... nums) {
		ListNode head = null;
		ListNode curr = null;
		for (int i : nums) {
			if (head == null) {
				head = new ListNode(i);
				curr = head;
			} else {
				curr.next = new ListNode(i);
				curr = curr.next;
			}
		}
		return head;
	}

	@Before
	public void setUp() {
		s = new Solution();
		lists = new ListNode[3];
		lists[0] = this.arrayToList();
		lists[1] = this.arrayToList(1,2);
		lists[2] = this.arrayToList(1,2,3);
	}
	
	@Test
	public void test() {
		for (ListNode l : lists) {
			if (l != null) {
				System.out.println(s.sortedListToBST(l));
			}
		}
	}

}
