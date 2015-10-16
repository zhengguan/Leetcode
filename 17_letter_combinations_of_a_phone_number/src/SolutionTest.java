import static org.junit.Assert.*;


import org.junit.*;

import java.util.*;




public class SolutionTest {
	Solution s;
	
	@Before
	public void setUp() {
		s = new Solution();
	}

	// GIVEN: two Collections each of which has no duplicate elements
	// RETURNS: true iff the two collections contain the same set of elements
	public <E> boolean collectionSetEqual(Collection<E> c1, Collection<E> c2) {
		return c1.size() == c2.size() 
				&& c1.containsAll(c2)
				&& c2.containsAll(c1);
	}
	
	@Test
	public void test() {
		String s1 = "23";
		List<String> combinations1 = new ArrayList<String>(
				Arrays.asList(
						new String[]{"ad", "ae", "af", "bd", 
								"be", "bf", "cd", "ce", "cf"}));
		assertTrue(collectionSetEqual(combinations1, s.letterCombinations(s1)));
	}

}
