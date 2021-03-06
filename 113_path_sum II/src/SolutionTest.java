import static org.junit.Assert.*;

import org.junit.*;
import java.util.*;

public class SolutionTest {
	TreeNode[] ts = new TreeNode[8];
	Solution s = new Solution();
	
	@Before
	public void setUp() {
		for(int i = 0; i < 8; i++) {
			ts[i] = new TreeNode(i);			
		}
		for(int i = 0; i < 3; i++) {
			ts[i].left = ts[i * 2 + 1];
			ts[i].right = ts[(i + 1) * 2];
		}
		ts[4].val = 6;
	}
	
	private int sumOfList(List<Integer> l) {
		int sum = 0;
		for(Integer i : l) {
			sum += i;
		}
		return sum;
	}
	
	@Test
	public void test() {
		System.out.println(s.pathSum(ts[0], 7));
		assertEquals(sumOfList(s.pathSum(ts[0], 7).get(0)), 7);
	}

}
