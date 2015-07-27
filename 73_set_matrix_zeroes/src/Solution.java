// Leetcode 73
// Set Matrix Zeroes
// https://leetcode.com/problems/set-matrix-zeroes/

// Given a m x n matrix, if an element is 0, set its entire row and column to 0. 
// Do it in place.

public class Solution {
    public void setZeroes(int[][] matrix) {
        this.setZeroesBruteForce(matrix);
        
    }
    
    public void setZeroesBruteForce(int[][] matrix) {
    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    		return;
    	}
    	// whether row[i] should be all 0s
    	boolean[] row = new boolean[matrix.length];  
    	// whether col[i] should be all 0s
    	boolean[] col = new boolean[matrix[0].length];
    	for(int i = 0; i < matrix.length; i++) {
    		for(int j = 0; j < matrix.length; j++) {
    			if(matrix[i][j] == 0) {
    				row[i] = true;
    				col[j] = true;
    			}
    		}
    	}
    	for(int i = 0; i < matrix.length; i++) {
    		if(row[i] == true) {
    			this.setRowZero(matrix, i);
    		}
    	}
    	for(int j = 0; j < matrix[0].length; j++) {
    		if(col[j] == true) {
    			this.setColZero(matrix, j);
    		}
    	}
    	return;
    }
    
    // EFFECT: set all elements in matrix[row][] to 0
    public void setRowZero(int[][] matrix, int row) {
    	for(int j = 0; j < matrix[row].length; j++) {
    		matrix[row][j] = 0;
    	}
    	return;
    }
    
    // EFFECT: set all elements in matrix[][col] to 0
    public void setColZero(int[][] matrix, int col) {
    	for(int i = 0; i < matrix.length; i++) {
    		matrix[i][col] = 0;
    	}
    	return;
    }
}

class SolutionExamples {
	Solution s = new Solution();
	

}