import static org.junit.Assert.*;

import org.junit.*;
import java.util.*;

public class SolutionTest {
	Solution s = new Solution();
	List<Integer> l1;
	List<Integer> l2;
	
	@Before
	public void setUp() {
		l1 = new ArrayList<Integer>(Arrays.asList(1, 1, 2));
		l1 = new ArrayList<Integer>(Arrays.asList(1, 1, 1));
	}
	

	@Test
	public void test() {
		System.out.println(s.permuteUnique(new int[]{2, 1, 1}));
		System.out.println(s.permuteUnique(new int[]{1, 2, 1, 1, 1}));
	}

}
