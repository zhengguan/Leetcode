import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	TreeNode t1;
	TreeNode t2;
	TreeNode t3;
	TreeNode t4;
	TreeNode t5;
	TreeNode t6;
	Solution s;
	
	private void reset() {
		this.t1 = new TreeNode(1);
		this.t2 = new TreeNode(2);
		this.t3 = new TreeNode(3);
		this.t4 = new TreeNode(1);
		this.t5 = new TreeNode(2);
		this.t6 = new TreeNode(3);
		this.t1.left = t2;
		this.t1.right = t3;
		this.t4.right = t5;
		this.t5.right = t6;
		this.s = new Solution();
	}

	@Test
	public void test() {
		reset();
		this.s.flatten(t3);
//		assertEquals(t3, t6);
		this.s.flatten(t1);
		assertEquals(t1, t4);
	}

}
