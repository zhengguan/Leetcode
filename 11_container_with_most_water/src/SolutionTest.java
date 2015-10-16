import static org.junit.Assert.*;

import org.junit.*;


public class SolutionTest {
	Solution s;
	int[][] nums;
	
	@Before
	public void setUp() {
		s = new Solution();
		nums = new int[3][];
		nums[0] = new int[]{2,1,2};
		nums[1] = new int[]{2,1,3};
		nums[2] = new int[]{3,2,1};
	}

	@Test
	public void test() {
		assertEquals(4, s.maxArea(nums[0]));
		assertEquals(4, s.maxArea(nums[1]));
		assertEquals(2, s.maxArea(nums[2]));
	}

}
