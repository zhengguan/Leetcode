import static org.junit.Assert.*;

import org.junit.*;
import java.util.*;

public class SolutionTest {
	Solution s;
	
	@Before
	public void setUp() {
		s = new Solution();
	}
	
	@Test
	public void testSet() {
		Set<Character> set1 = new HashSet<Character>();
		Set<Character> set2 = new HashSet<Character>();
		set1.add('g');
		set1.add('r');
		set2.add('r');
		set2.add('g');
		assertTrue(set1.equals(set2));
	}

	@Test
	public void test() {
		assertTrue(s.isScramble("gr", "rg"));
		assertTrue(s.isScramble("eat", "eat"));
		assertTrue(s.isScramble("great", "rgeat"));
		assertTrue(s.isScramble("great", "rgtae"));
		System.out.println(s.isScramble("ccabcbabcbabbbbcbb",
										"bbbbabccccbbbabcba"));	
	}

}
