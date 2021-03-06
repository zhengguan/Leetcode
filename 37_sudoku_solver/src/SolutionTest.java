import static org.junit.Assert.*;

import org.junit.*;


public class SolutionTest {
	Solution s = new Solution();
	Board board;
	
	// GIVEN: an array of strings of the same length
	// RETURNS: a matrix(char[][]) representation
	private char[][] stringsToMatrix(String[] ss) {
		char[][] matrix = new char[ss.length][];
		for(int i = 0; i < ss.length; i++) {
			matrix[i] = ss[i].toCharArray();
		}
		return matrix;
	}
	
	@Before
	public void setUp() {
		String[] ss = new String[] {
				"53..7....",
				"6..195...",
				".98....6.",
				"8...6...3",
				"4..8.3..1",
				"7...2...6",
				".6....28.",
				"...419..5",
				"....8..79"
		};
		String[] ss2 = new String[] {
				"534678912",
				"672195348",
				"198342567",
				"859761423",
				"426853791",
				"713924856",
				"961537284",
				"287419635",
				"345286179"
		};
		String[] ss3= new String[] {
				"534678912",
				"672195348",
				"198342567",
				"859761423",
				"426853791",
				"713924856",
				"961537284",
				"287419635",
				"34528617."
		};
		char[][] board = stringsToMatrix(ss);
		this.board = new Board(board);
	}
	
	@Test
	public void testSquareNumber() {
		assertEquals(Board.squareNumber(0, 0), 0);
		assertEquals(Board.squareNumber(2, 2), 0);
		assertEquals(Board.squareNumber(2, 3), 1);
		assertEquals(Board.squareNumber(2, 3), 1);
	}
	
	@Test
	public void testSolveSudoku() {
		s.solveSudoku(board.board);
		for(int i = 0; i < board.board.length; i++) {
			for(int j = 0; j < board.board[0].length; j++) {
				System.out.print(board.board[i][j]);
			}
			System.out.println("");
		}
	}
	
	@Test
	public void testIsLegal() {
		assertTrue(board.isLegal(4, 0, 2));		
	}

}
