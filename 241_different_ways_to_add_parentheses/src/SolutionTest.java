import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import java.util.*;

public class SolutionTest {

	@Test
	public void testDiffWaysToCompute() {
//		fail("Not yet implemented");
		Solution s = new Solution();
		
		List<Integer> l1 = new ArrayList<Integer>(Arrays.asList(0, 2));
		assertEquals(true, l1.containsAll(s.diffWaysToCompute("2-1-1")));
		
		List<Integer> l2 = 
				new ArrayList<Integer>(
						Arrays.asList(-34, -14, -10, -10, 10));
		assertTrue(l2.containsAll(s.diffWaysToCompute("2*3-4*5")));
	}
	

}
