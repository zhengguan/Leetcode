import static org.junit.Assert.*;

import org.junit.*;


public class SolutionTest {
	Solution s = new Solution();
	int[] nums = new int[]{1, 2, 3, 4, 2};
	
	@Before
	public void setUp() {
		
	}

	@Test
	public void test() {
		assertTrue(s.containsNearbyDuplicate(nums, 3));
		assertTrue(s.containsNearbyDuplicate(nums, 8));
		assertFalse(s.containsNearbyDuplicate(nums, 2));
	}

}
