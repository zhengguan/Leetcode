import static org.junit.Assert.*;

import org.junit.*;


public class SolutionTest {
	Solution s;

	@Before 
	public void setUp() {
		s = new Solution();
	}
	
	@Test
	public void test() {
		assertEquals(5, s.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
		assertEquals(2, s.findKthLargest(new int[]{3,2,1,5,6,4,5,2}, 6));
		assertEquals(3, s.findKthLargest(new int[]{3,3,1}, 2));
	}

}
