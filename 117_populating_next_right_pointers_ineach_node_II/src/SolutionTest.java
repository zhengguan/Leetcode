import static org.junit.Assert.*;

import org.junit.*;

import java.util.*;

public class SolutionTest {
	Solution s;
	TreeLinkNode t;
	TreeLinkNode[] nodes;
	
	// GIVEN: an array that only contains bit digits or '#' that
	// represents the serialization of a binary tree
	// RETURNS: the corresponding binary tree 
	public TreeLinkNode serializedTreeToBinaryTree(char[] cs) {
		if (cs == null || cs.length == 0) {
			return null;
		}
		
		List<TreeLinkNode> currLayer = new ArrayList<TreeLinkNode>();
		TreeLinkNode root = charToNode(cs[0]);
		currLayer.add(root);
		for (int i = 1; i < cs.length; i++) {
			List<TreeLinkNode> nextLayer = new ArrayList<TreeLinkNode>();
			for (TreeLinkNode node : currLayer) {
				TreeLinkNode left = charToNode(cs[i]);
				TreeLinkNode right = charToNode(cs[i]);
				node.left = left;
				node.right = right;				
				if (left != null) {
					nextLayer.add(left);
				}
				if (right != null) {
					nextLayer.add(right);
				}
			}
			currLayer = nextLayer;
		}
		return root;
	}
	
	private TreeLinkNode charToNode(char c) {
		if (c == '#') return null;
		else return new TreeLinkNode((int) (c - '0'));
	}
	
    public void print(String s, TreeLinkNode node) {
    	if (node == null) {
    		System.out.println(s);
    	} else {
    		System.out.println(s + node.val);
    		print("  " + s, node.left);
    		print("  " + s, node.right);
    	}    	
    }
	
	@Before
	public void setUp() {
		s = new Solution();
		int size = 7;
		nodes = new TreeLinkNode[size];
		for (int i = 0; i < 7; i++) {
			nodes[i] = new TreeLinkNode(i); 
		}
		for (int i = 0; i * 2 + 2 < 7; i++) {
			 nodes[i].left = nodes[i * 2 + 1];
			 nodes[i].right = nodes[i * 2 + 2];
		}
		nodes[1].right = null;
		nodes[2].left = null;
		t = nodes[0];
	}

	@Test
	public void test() {
		print("--", serializedTreeToBinaryTree("0241#3151#6#8".toCharArray()));
		
		s.connect(t);
		assertFalse(nodes[3].next == nodes[4]);
		assertEquals(nodes[2], nodes[1].next);
		assertEquals(nodes[6], nodes[3].next);
		
		
	}

}
