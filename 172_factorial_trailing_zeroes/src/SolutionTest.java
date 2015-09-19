import static org.junit.Assert.*;

import org.junit.*;


public class SolutionTest {
	Solution s;
	
	@Before
	public void setUp() {
		s = new Solution();
	}
	
	@Test
	public void test() {
		assertEquals(0, s.trailingZeroes(4));
		assertEquals(1, s.trailingZeroes(5));
		assertEquals(2, s.trailingZeroes(10));
		assertEquals(2, s.trailingZeroes(14));
		assertEquals(6, s.trailingZeroes(25));
	}

}
