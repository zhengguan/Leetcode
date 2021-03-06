// Leetcode 74
// Search a 2D Matrix
// https://leetcode.com/problems/search-a-2d-matrix/

// Write an efficient algorithm that searches for a value in an m x n matrix. This 
// matrix has the following properties:
// 
// Integers in each row are sorted from left to right.
// The first integer of each row is greater than the last integer of the previous 
// row.
// For example,
// 
// Consider the following matrix:
// 
// [
//   [1,   3,  5,  7],
//   [10, 11, 16, 20],
//   [23, 30, 34, 50]
// ]
// Given target = 3, return true.

import tester.Tester;

import java.util.Arrays;

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        for(int i = 0; i < row; i++) {
        	int targetIndex = this.binarySearch(matrix[i], target);
        	if(targetIndex != -1) {
        		return true;
        	}
        }
        return false;
    }
    
    // RETURNS: the index of target in array if exists, otherwise return -1
    int binarySearch(int[] array, int target) {
    	int start = 0;
    	int end = array.length;
    	while(start < end) 
    	//@loop_invariant 0 <= start && start <= end && end <= array.length;
    	/*@loop_invariant array[0, start) < target && 
    					  target < array[end, array.length); @*/ 
    	{
    		if(end - start == 1) {
    			return (array[start] == target) ? start : -1;
    		}
    		//@assert end - start >= 2;
    		int mid = start + (end - start) / 2;
    		//@assert start < mid && mid < end;
    		if(array[mid] == target) {
    			return mid;
    		}
    		else if(array[mid] > target) {
    			end = mid;
    		}
    		else {
    			start = mid + 1;
    		} 				
    	}
    	return -1;
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	// tests for searchMatrix()
	boolean testSearchMatrix(Tester t) {
		return
		t.checkExpect(s.searchMatrix(new int[][] {{1, 3, 5, 7},
												  {10, 11, 16, 20},
												  {23, 30, 34, 50}}, 
									 3), 
					  true);
	}
}