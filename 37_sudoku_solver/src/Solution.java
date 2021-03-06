// Leetcode 37	
// Sudoku Solver
// https://leetcode.com/problems/sudoku-solver/

/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.

53..7....
6..195...
.98....6.
8...6...3
4..8.3..1
7..2....6
.6....28.
...419..5
....8..79

A sudoku puzzle...




...and its solution numbers marked in red.
*/

public class Solution {
    Board board;
    
 // GIVEN: the state of a sudoku board
    // EFFECT: fill in the empty cells
    public void solveSudoku(char[][] board) {
    	solveSudokuRecursive(board);
    }
    
    // GIVEN: the state of a sudoku board
    // EFFECT: fill in the empty cells
    public void solveSudokuRecursive(char[][] board) {
    	this.board = new Board(board);
    	helper(0, 0);  	
    }
    
    // GIVEN: the state of a sudoku board and the coordinates of a start 
    // position
    // WHERE: ((row * 9) + col) represents the number of cells that have been 
    // filled
    // EFFECT: fill in empty cells start at the given one
    // RETURNS: true iff filled all empty cells successfully
    // EXAMPLE: helper(board, 1, 1) fills in cells (row, col) that 
    // ((row == 1) && (col >= 1)) || (row > 1)
    public boolean helper(int row, int col) {
    	if(row * 9 + col >= 81) {
    		//@assert all cells have been filled
    		return true;
    	}
    	else {
    		if(!board.isEmptyCell(row, col)) {
    			return helper(row + (col + 1) / 9, (col + 1) % 9);
    		}
    		for(int i = 1; i <= 9; i++) {
    			if(board.isLegal(i, row, col)) {
    				board.setBoardAndMark(i, row, col);    				
    				if(helper(row + (col + 1) / 9, (col + 1) % 9)) {
    					return true;
    				} else {
    					board.unsetBoardAndMark(i, row, col);
    				}
    			}
    		}
    		return false;
    	}
    }
}

class Board {
	// markRow[i][j] represents whether j + 1 has appeared in the ith row
    boolean[][] markRow;
	// markCol[i][j] represents whether j + 1 has appeared in the ith col        
    boolean[][] markCol;
	// markCol[i][j] represents whether j + 1 has appeared in the ith square        
    boolean[][] markSquare;
    char[][] board;    
    
    Board(char[][] board) {
    	this.board = board;
        this.markRow = new boolean[9][9];        
        this.markCol = new boolean[9][9];      
        this.markSquare = new boolean[9][9];
        
        for(int i = 0; i < 9; i++) {
        	for(int j = 0; j < 9; j++) {
        		if(board[i][j] != '.') {
        			setMark(board[i][j] - '0', i, j, true);
        		}
        	}
        }
    }    
    
    // RETURNS: true the cell at the given position is empty
    public boolean isEmptyCell(int row, int col) {
    	return board[row][col] == '.';
    }
    
    // GIVEN: a value and a position of a cell on a sudoku board
    /* WHERE: 1 <= value && value <= 9 
       && 0 <= row && row < 9 && 0 <= col && col < 9;
     */
    // RETURNS: true iff it is legal to set the cell to the given value
    public boolean isLegal(int value, int row, int col) {
    	return !(markRow[row][value - 1] 
    		    || markCol[col][value - 1]
    		    || markSquare[squareNumber(row, col)][value - 1]);    			
    }
    
    // GIVEN: the position of a cell in the board
    // RETURNS: the number of the square that contains that cell
    // DETAILS: squares are 3*3 cell groups, counted top down and left right,
    // started with 0
    static int squareNumber(int row, int col) {
    	return row / 3 * 3 + col / 3;
    }
    
    // GIVEN: a value and a position of the sudoku board
    /* WHERE: 1 <= value && value <= 9 
       && 0 <= row && row < 9 && 0 <= col && col < 9;
    */
    // EFFECT: set the cell at the given position the given value and     
    // set corresponding mark 
    public void setBoardAndMark(int value, int row, int col) {
    	board[row][col] = (char) (value + '0');
    	setMark(value, row, col, true);
    	return;
    }
    
    // GIVEN: a value and a position of the sudoku board
    /* WHERE: 1 <= value && value <= 9 
       && 0 <= row && row < 9 && 0 <= col && col < 9;
     */
    // EFFECT: unset the cell at the given position the given value and     
    // set corresponding mark 
    public void unsetBoardAndMark(int value, int row, int col) {
    	board[row][col] = '.';
    	setMark(value, row, col, false);
    	return;
    }
    
    // GIVEN: a value of a cell, the position of the cell, a mark value
    // WHERE: 1 <= value && value <= 9;
    // EFFECT: set the corresponding mark of the cell
    private void setMark(int value, int row, int col, boolean b) {
    	markRow[row][value - 1] = b;
    	markCol[col][value - 1] = b;
    	markSquare[squareNumber(row, col)][value - 1] = b;
    	return;
    }	
}