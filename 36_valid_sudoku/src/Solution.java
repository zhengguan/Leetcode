// Leetcode 36
// Valid Sudoku
// https://leetcode.com/problems/valid-sudoku/

// Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
// 
// The Sudoku board could be partially filled, where empty cells are filled 
// with the character '.'.
// 
// 
// A partially filled sudoku which is valid.
// 
// Note:
// A valid Sudoku board (partially filled) is not necessarily solvable. Only 
// the filled cells need to be validated.
import tester.Tester;

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i++) {
            if(!this.isValidRow(board, i)) {
                return false;
            }
        }
        for(int j = 0; j < 9; j++) {
            if(!this.isValidCol(board, j)) {
                return false;
            }
        }
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
            	if(!this.isValidSubBox(board, i * 3, j * 3)) {
            		return false;
            	}
            }
        }
        return true;
    }
    
    // RETURNS: true iff the ith row of the board is vald
    public boolean isValidRow(char[][] board, int i) {
        int[] mark = new int[9 + 1];
        for(int j = 0; j < board[i].length; j++) {
        	int value = this.gridValue(board, i, j);
        	if(value == -1) {
        		continue;
        	}
            if(mark[value] == 1) {
                return false;
            }
            mark[value] = 1;
        }
        return true;
    }
    
    // RETURNS: true iff the jth col of the board is valid
    public boolean isValidCol(char[][] board, int j) {
        int[] mark = new int[9 + 1];
        for(int i = 0; i < board.length; i++) {
        	int value = this.gridValue(board, i, j);
        	if(value == -1) {
        		continue;
        	}
            if(mark[value] == 1) {
                return false;
            }
            mark[value] = 1;
        }
        return true;
    }
    
    // RETURNS: true iff the sub-box on the board that starts at
    // (i, j) is valid
    public boolean isValidSubBox(char[][] board, int i, int j) {
        int[] mark = new int[9 + 1];
        for(int k = 0; k < 9; k++) {
        	int value = this.gridValue(board, i + k / 3, j + k % 3);
        	if(value == -1) {
        		continue;
        	}
            if(mark[value] == 1) {
            	return false;
            }
            mark[value] = 1;
        }
        return true;
    }
    
    //��GIVEN: A board and a coordinate
    // WHERE: the board contains char '1' to '9' and '.'
    // RETURNS: the value of the grid board[i][j] iff the grid contains
    // '1' to '9', else return -1
    public int gridValue(char[][] board, int i, int j) {
    	if(board[i][j] == '.') {
    		return -1;
    	}
    	else {
    		return board[i][j] - '0';
    	}
    }
}

class SolutionExamples {
	Solution s = new Solution();
	char[][] board1 = this.stringArrayToCharArray(new String[] {
			"53..7....",
			"6..195...",
			".98....6.",
			"8...6...3",
			"4..8.3..1",
			"7...2...6",
			".6....28.",
			"...419..5",
			"....8..79"
	});
	
	// GIVEN: an array of Strings
	// RETURNS: an corresponding array of char array
	public char[][] stringArrayToCharArray(String[] ss) {
		char[][] board = new char[ss.length][];
		for(int i = 0; i < ss.length; i++) {
			board[i] = ss[i].toCharArray();
		}
		return board;
	}
	
	// tests for method isValidRow()
	boolean testIsValidRow(Tester t) {
		return
		t.checkExpect(s.isValidRow(board1, 0)) &&
		t.checkExpect(s.isValidRow(board1, 1));
	}
	
	// tests for method isValidCol()
	boolean testIsValidCol(Tester t) {
		return
		t.checkExpect(s.isValidCol(board1, 0)) &&
		t.checkExpect(s.isValidCol(board1, 0));
	}
	
	// tests for method isValidSudoku()
	boolean testIsValidSudoku(Tester t) {

		return
		t.checkExpect(s.isValidSudoku(board1), true);
	}
}
