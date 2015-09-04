import static org.junit.Assert.*;

import org.junit.*;
import java.util.*;

public class SolutionTest {
	private boolean sameListOfList(List<List<Integer>> lol1, List<List<Integer>> lol2) {
		return lol1.containsAll(lol2) 
				&& lol2.containsAll(lol1);
	}

	@Test
	public void test() {
		Solution s = new Solution();
		
		ArrayList<List<Integer>> l = new ArrayList<List<Integer>>();
		l.add(Arrays.asList(1, 2, 4));
		assertTrue(sameListOfList(s.combinationSum3(3, 7), l));
		
		ArrayList<List<Integer>> l1 = new ArrayList<List<Integer>>();
		l1.add(Arrays.asList(1, 2, 6));
		l1.add(Arrays.asList(1, 3, 5));
		l1.add(Arrays.asList(2, 3, 4));
		System.out.println(s.combinationSum3(3, 9));
		assertTrue(sameListOfList(s.combinationSum3(3, 9), l1));
	}

}