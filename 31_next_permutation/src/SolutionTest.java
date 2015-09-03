import static org.junit.Assert.*;

import org.junit.*;


public class SolutionTest {
	Solution s = new Solution();
	int[][] nums;
	
	@Before
	public void setUp() {
		nums = new int[6][];
		nums[0] = new int[]{1, 2, 3};
		nums[1] = new int[]{1, 3, 2};
		nums[2] = new int[]{3, 2, 1};
		nums[3] = new int[]{1, 2, 3};
		nums[4] = new int[]{1, 1, 5};
		nums[5] = new int[]{1, 5, 1};
	}

	@Test
	public void test() {
		s.nextPermutation(nums[0]);
		assertArrayEquals(nums[1], nums[0]);
		s.nextPermutation(nums[2]);
		assertArrayEquals(nums[3], nums[2]);
		s.nextPermutation(nums[4]);
		assertArrayEquals(nums[5], nums[4]);
	}

}
