import static org.junit.Assert.*;

import org.junit.*;


public class SolutionTest {
	Solution s;
	TreeNode[] trees;
	
	@Before 
	public void setUp() {
		s = new Solution();
		trees = new TreeNode[3];
		
		int size = 6;
		TreeNode[] nodes = new TreeNode[size];
		for (int i = 0; i < size; i++) {
			nodes[i] = new TreeNode(i);
		}
		nodes[0].left = nodes[1];
		nodes[0].right = nodes[2];
		nodes[1].right = nodes[3];
		nodes[2].right=  nodes[4];
		nodes[4].right = nodes[5];
		
		trees[0] = nodes[0];
		
		nodes = new TreeNode[size];
		for (int i = 0; i < size; i++) {
			nodes[i] = new TreeNode(i);
		}
		for (int i = 0; i < size; i++) {
			if (i * 2 + 1 < size) {
				nodes[i].left = nodes[i * 2 + 1];
			}
			if (i * 2 + 2 < size) {
				nodes[i].right = nodes[i * 2 + 2];
			}
		}
		trees[1] = nodes[0];
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
		System.out.println(s.zigzagLevelOrder(trees[0]));
		System.out.println(s.zigzagLevelOrder(trees[1]));
		System.out.println(trees[1]);
	}

}
