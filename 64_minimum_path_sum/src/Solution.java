import tester.Tester;

// Leetcode 64	
// Minimum Path Sum
// https://leetcode.com/problems/minimum-path-sum/

// Given a m x n grid filled with non-negative numbers, find a path from top 
// left to bottom right which minimizes the sum of all numbers along its path.
// 
// Note: You can only move either down or right at any point in time.

public class Solution {
    public int minPathSum(int[][] grid) {
//        return this.minPathSumRecur(grid);
        return this.minPathSumIter(grid);
    }
    
    // RETURNS: find the path from top-left of grid to bottom-right of grid 
    // such that the sum of elements on the path have the minimum value
    public int minPathSumRecur(int[][] grid) {
    	return this.minPathSumRecurHelper(grid, 0, 0);
    }
    
    // GIVNE: a array contains only non-negative numbers and coordinates of a
    // position in grid
    // RETURNS: find the path from the given position to bottom-right of grid 
    // which minimizes the sum of all numbers along the path
    // TERMINATION: the value of (grid.length - x) + (grid[0].length - y) 
    // becomes smaller    
    public int minPathSumRecurHelper(int[][] grid, int x, int y) {
    	int row = grid.length; // row number
    	int col = grid[0].length; // col number
    	if((x == row - 1) && (y == col - 1)) {
    		return grid[x][y];
    	}
    	else if(x == row - 1) {
    		return grid[x][y] + this.minPathSumRecurHelper(grid, x, y + 1);
    	}
    	else if(y == col - 1) {
    		return grid[x][y] + this.minPathSumRecurHelper(grid, x + 1, y);
    	}
    	else {
    		return grid[x][y] + 
    				Math.min(this.minPathSumRecurHelper(grid, x + 1, y), 
    						this.minPathSumRecurHelper(grid, x, y + 1));
    	}
    }
    
    // GIVEN: a non-empty array    
    // RETURNS: find the path from top-left of grid to bottom-right of grid 
    // such that the sum of elements on the path have the minimum value
    public int minPathSumIter(int[][] grid) {
    	int row = grid.length;
    	int col = grid[0].length;
    	//@assert row >= 1 && col >= 1;
    	int[][] minPathSums = new int[row][col];
    	minPathSums[row - 1][col - 1] = grid[row - 1][col - 1];
    	for(int i = row - 2; i >= 0; i--) 
    	/*@loop_invariant 
    		minPathSums[i + 1][col - 1] == 
    		this.minPathSumRecurHelper(grid,i + 1, col - 1); @*/
    	{
    		minPathSums[i][col - 1] = minPathSums[i + 1][col - 1] + grid[i][col - 1];
    	}
    	for(int j = col - 2; j >= 0; j--) 
    	/*@loop_invariant
    	  	minPathSums[row - 1][j + 1] ==
    	  	this.minPathSumRecurHelper(grid, row - 1, j + 1); @*/
    	{
    		minPathSums[row - 1][j] = minPathSums[row - 1][j + 1] + grid[row - 1][j];    		
    	}
    	
    	for(int i = row - 2; i >= 0; i--) {
    		for(int j = col - 2; j >= 0; j--) 
    		/*@loop_invariant 
    		  	minPathSums[i][j + 1] ==
    		  	this.minPathSumRecurHelper(grid, i, j + 1); 
    		 */
    		{
    			minPathSums[i][j] = grid[i][j] +
    					Math.min(minPathSums[i][j + 1], minPathSums[i + 1][j]);
    		}
    	}
    	return minPathSums[0][0];
    }    
}

class SolutionExamples {
	Solution s = new Solution();
	
	// tests for method minPathSum()
	boolean testMinPathSum(Tester t) {
		return
		t.checkExpect(s.minPathSum(new int[][]{{1}}), 1) &&
		t.checkExpect(s.minPathSum(new int[][]{{1, 2}, {3, 4}}), 7) &&
		t.checkExpect(s.minPathSum(new int[][]{{1, 2, 8}, 
											   {3, 4, 9},
											   {0, 3, 10}}), 
								   17);
	}
}