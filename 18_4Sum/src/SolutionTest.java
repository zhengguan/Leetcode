import static org.junit.Assert.*;

import org.junit.*;
import java.util.*;

public class SolutionTest {
	Solution s;
	int[][] nums;
	int[] targets;
	
	@Before
	public void setUp() {
		s = new Solution();
		int size = 3;
		nums = new int[size][];
		targets = new int[size];
		
		nums[0] = new int[]{1,1,1,1};
		targets[0] = 4;
		
		nums[1] = new int[]{1, -1, 1, 2, 1};
		targets[1] = 3;
		
		nums[2] = new int[]{1, 0, -1, 0, -2, 2};
		targets[2] = 0;
	}
	
	private <T> boolean listOfListEquals(List<List<T>> lol1, List<List<T>> lol2) {
		return lol1.containsAll(lol2) && lol2.containsAll(lol1)
				&& lol1.size() == lol2.size();
	}
	
	private <T> List<List<T>> twoDimensionArrayToList(T[][] arr) {
		List<List<T>> result = new ArrayList<List<T>>();
		for (int i = 0; i < arr.length; i++) {
			result.add(Arrays.asList(arr[i]));
		}
		return result;
	}
	
	@Test
	public void test() {
		
		assert(listOfListEquals(
				twoDimensionArrayToList(new Integer[][]{{1,1,1,1}}),
				s.fourSum(nums[0], 4)));
		assert(listOfListEquals(
				twoDimensionArrayToList(new Integer[][]{{-1,1,1,2}}),
				s.fourSum(nums[1], 4)));
		assert(listOfListEquals(
				twoDimensionArrayToList(
						new Integer[][] {
								{-1,  0, 0, 1},
								{-2, -1, 1, 2},
								{-2,  0, 0, 2}}), 
				s.fourSum(nums[2], 0)));
		System.out.println(s.fourSum(nums[2], 0));
	}

}
