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
//		fail("Not yet implemented");
		assertEquals(1, s.trap(new int[]{2, 1, 2}));
		assertEquals(1, s.trap(new int[]{3, 1, 2}));
		assertEquals(1, s.trap(new int[]{2, 1, 3}));
		assertEquals(6, s.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
	}

}
