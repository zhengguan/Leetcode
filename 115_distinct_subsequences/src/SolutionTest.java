import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void test() {
		Solution s = new Solution();
		System.out.println(s.numDistinct("aabb", "ab"));
		assertEquals(s.numDistinct("aabb", "ab"), 4);
		assertEquals(s.numDistinct("rabbbit", "rabbit"), 3);
	}

}
