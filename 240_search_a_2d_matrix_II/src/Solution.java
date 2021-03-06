// Leetcode 240
// Search a 2D Matrix II
// https://leetcode.com/problems/search-a-2d-matrix-ii/

// Write an efficient algorithm that searches for a value in an m x n 
// matrix. This matrix has the following properties:
// 
// Integers in each row are sorted in ascending from left to right.
// Integers in each column are sorted in ascending from top to bottom.
// For example,
// 
// Consider the following matrix:
// 
// [
//   [1,   4,  7, 11, 15],
//   [2,   5,  8, 12, 19],
//   [3,   6,  9, 16, 22],
//   [10, 13, 14, 17, 24],
//   [18, 21, 23, 26, 30]
// ]
// Given target = 5, return true.
// 
// Given target = 20, return false.

import tester.Tester;

import java.util.*;

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    		return false;
    	}
    	int row = matrix.length;
    	int col = matrix[0].length;
    	return this.searchMatrixHelper(matrix, 0, col, 0, row, target);
    }
    
    // GIVEN: a matrix, left, right, up, down border of a region of the matrix
    /* WRERE: 0 <= left && left < right && right <= matrix[0].length &&
      		  0 <= up && up < down && down <= matrix.length;
	*/ 
    // RETURNS: true iff exist matrix[i][j] == target, i in [up, down), 
    // j in [left, right)
    // TERMINATION: the value of (right - left) * (down - right) becomes smaller
    public boolean searchMatrixHelper(int[][] matrix, int left, int right, 
    		int up, int down, int target) {
    	if(down - up == 1) {
    		return this.searchInRow(matrix, up, target, left, right);
    	}
    	if(right - left == 1) {
    		return this.searchInColumn(matrix, left, target, up, down);
    	}
    	//@assert down - up >= 2 && right - left >= 2;
    	int midRow = up + (down - up) / 2;
    	//@assert up < midRow && midRow < down;
    	int midCol = left + (right - left) / 2;
    	//@assert left < midCol && midCol < right;
    	if(matrix[midRow][midCol] == target) {
    		return true;
    	}
    	else if(matrix[midRow][midCol] < target){
    		return
    		this.searchMatrixHelper(matrix, midCol, right, midRow, down, target) ||
    		this.searchMatrixHelper(matrix, left, midCol, midRow, down, target) ||   		
    		this.searchMatrixHelper(matrix, midCol, right, up, midRow, target);
    	}
    	else {
    		//@assert matrix[midRow][midCol] > target;
    		return
    		this.searchMatrixHelper(matrix, left, midCol, up, midRow, target) ||
    		this.searchMatrixHelper(matrix, left, midCol, midRow, down, target) ||    		
    		this.searchMatrixHelper(matrix, midCol, right, up, midRow, target);
    	}
    }
    
    // RETURNS: true iff matrix[row][j] == target, j in [left, right)
    public boolean searchInRow(int[][] matrix, int row, int target, 
    		int left, int right) {
    	return Arrays.binarySearch(matrix[row], left, right, target) >= 0;
    }
    
    /* WHERE: matrix!= null && matrix[0] != null && col < matrix[0].length 
       			&& up < down; */
    // RETURNS: true iff exists matrix[i][col] == target, i in [up, down)
    public boolean searchInColumn(int[][] matrix, int col, int target, 
    		int up, int down) {
    	int start = up;
    	int end = down;
    	while(start < end) 
    	{
    		if(end - start == 1) {
    			return matrix[start][col] == target;
    		}
    		//@assert end - start >= 2;    		
    		int mid = start + (end - start) / 2;
    		//@assert start < mid && mid < start;
    		if(matrix[mid][col] == target) {
    			return true;
    		}
    		else if(matrix[mid][col] < target) {
    			start = mid + 1;
    		}
    		else {
    			end = mid; 
    		}
    	}
    	return false;
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	// tests for method searchInColumn()
	boolean testSearchInColumn(Tester t) {
		return
		t.checkExpect(s.searchInColumn(new int[][] {{1}}, 
						0, 1, 0, 1),
				 	  true) &&
		t.checkExpect(s.searchInColumn(new int[][] {{1}}, 
						0, 2, 0, 1),
				 	  false) &&
		t.checkExpect(s.searchInColumn(new int[][] {{1, 2},
				  									{3, 4},},
						0, 2, 0, 2),
		   	 	      false) &&
		t.checkExpect(s.searchInColumn(new int[][] {{1, 2},
													{3, 4},},
						0, 3, 0, 2),
					  true);		 
	}
	
	// tests for method searchMatrixHelper()
	boolean testSearchMatrixHelper(Tester t) {
		return
		t.checkExpect(s.searchMatrixHelper(new int[][] {{1, 2},
				  									{3, 4},},
						0, 1, 1, 2, 2),
		   	 	      false) &&
		t.checkExpect(s.searchMatrixHelper(new int[][] {{1, 2},
													    {3, 4},},
						0, 1, 1, 2, 3),
					  true);		 
	}
	
	// tests for method searchMatrix()
	boolean testSearchMatrix(Tester t) {
		return
		t.checkExpect(s.searchMatrix(new int[][] {{1}}, 
									 1),
					  true) &&
        t.checkExpect(s.searchMatrix(new int[][] {{1}}, 
   								     3),
				      false) &&					
		t.checkExpect(s.searchMatrix(new int[][] {{1, 2},
												  {3, 4},}, 
									 3),
					  true) &&
		t.checkExpect(s.searchMatrix(new int[][] {{1, 4,  7, 11, 15},
							  					  {2, 5,  8, 12, 19},
							  					  {3, 6,  9, 16, 22},
							  					  {10,13, 14, 17, 24},
							  					  {18,21, 23, 26, 30}}, 
						  		     5),
					  true);
	}
}