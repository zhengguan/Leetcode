import static org.junit.Assert.*;

import org.junit.*;


public class SolutionTest {
	TreeNode[] nodes;
	Solution s;
	
	@Before
	public void setUp() {
		s = new Solution();
		
		int size = 7;
		nodes = new TreeNode[size];
		for(int i = 0; i < size; i++) {
			nodes[i] = new TreeNode(i);
		}
		
		for(int i = 0; i < size / 2; i++) {
			nodes[i].left = nodes[i * 2 + 1];
			nodes[i].right = nodes[(i + 1) * 2];
		}
	}
	
	@Test
	public void test() {
		assertEquals(s.sumNumbers(nodes[0]), 78);
		assertEquals(s.sumNumbers(nodes[1]), 27);
	}
}
