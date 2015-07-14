// Leetcode 59	
// Spiral Matrix II
// https://leetcode.com/problems/spiral-matrix-ii/

// Given an integer n, generate a square matrix filled with elements from 1 to 
// n2 in spiral order.
// 
// For example,
// Given n = 3,
// 
// You should return the following matrix:
// [
//  [ 1, 2, 3 ],
//  [ 8, 9, 4 ],
//  [ 7, 6, 5 ]
// ]

import tester.Tester;

import java.util.*;

public class Solution {
    public int[][] generateMatrix1(int n) {
    	n = Math.abs(n);
        int[][] matrix = new int[n][n];
        int number = 1;
        int start = 0; //(start, start) is the start position
        int end = n - 1;   // 
        while(number <= n * n) {
            number = this.generateBorder(matrix, start, end, number);
            start++;
            end--;
        }
        return matrix;
    }
    
    // GIVEN: a square matrix, two ints represents two positions (start, start) 
    // and (end, end) marks a square, a number n
    // WHERE: 0 <= start && start <= end && end < matrix.length;
    // EFFECT: fill in the all elements on the border of the rectangle with 
    // value from n to n+1, n+2..., start from (start, start) in clockwise
    // direction
    // RETURNS: the number followed the last number fill in the matrix 
    public int generateBorder(int[][] matrix, int start, int end, int n) {
    	if(end == start) {
    		matrix[start][start] = n++;
    		return n;
    	}
    	else {
    		//@assert start < end;
    		for(int j = start; j < end; j++) {
    			matrix[start][j] = n++;
    		}
    		for(int i = start; i < end; i++) {
    			matrix[i][end] = n++;
    		}
    		for(int j = end; j > start; j--) {
    			matrix[end][j] = n++;
    		}
    		for(int i = end; i > start; i--) {
    			matrix[i][start] = n++;
    		}
    		return n;
    	}
    }
    
    public int[][] generateMatrix(int n) {
    	n = Math.abs(n);
    	int[][] matrix = new int[n][n];
    	int direction = 0, x = 0, y = 0;
    	int maxLength = n - 1;
    	int count = 0;
    	int value = 1;
    	while(value <= n * n) {
    		matrix[x][y] = value++;
    		if(direction == 0) {
    			y++;
    		}
    		else if(direction == 1) {
    			x++;
    		}
    		else if(direction == 2) {
    			y--;
    		}
    		else if(direction == 3) {
    			x--;
    		}
    		count++;
    		if(count == maxLength) {
    			count = 0;
    			if(direction == 3) {
    				direction = 0;
    				maxLength -= 2;
    				x++;
    				y++;
    			}
    			else {
    				direction++;
    			}
    		}
    	}
    	return matrix;
	}
}

class SolutionExamples {
	Solution s = new Solution();
	
	// WHERE: the matrix is non-empty
	// EFFECT: print all values of the given matrix
	public void printMatrix(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
		return;
	}
	
	// tests for method generateMatrix()
	boolean testGenerateMatrix(Tester t) {
//		System.out.println(s.generateMatrix(2)[1][1]);
		this.printMatrix(s.generateMatrix(4));
		return
		t.checkExpect(s.generateMatrix(1), new int[][]{{1}}) &&
		t.checkExpect(s.generateMatrix(-1), new int[][]{{1}}) &&
		t.checkExpect(s.generateMatrix(2), 
				new int[][]{{1, 2},
							{4, 3}}) &&
		t.checkExpect(s.generateMatrix(3),
				new int[][]{{1, 2, 3},
							{8, 9, 4},
							{7, 6, 5}});
	}
}