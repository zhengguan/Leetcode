import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

public class SolutionTest {

	@Test
	public void test() {
		Solution s = new Solution();
		System.out.println(s.subsetsWithDup(new int[]{2,1,2}));
		System.out.println(s.subsetsWithDup(new int[]{2,2,2}));
		ArrayList<Integer> l1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
		ArrayList<Integer> l2 = new ArrayList<Integer>(Arrays.asList(2, 1, 3));
		System.out.println(l1.containsAll(l2));
	}

}
