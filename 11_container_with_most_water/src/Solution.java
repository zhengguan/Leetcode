// Leetcode 11	
// Container With Most Water
// https://leetcode.com/problems/container-with-most-water/

/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at 
coordinate (i, ai). n vertical lines are drawn such that the two endpoints of 
line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis 
forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/

public class Solution {
    public int maxArea(int[] height) {
//        return maxAreaHelper(height, height.length);
    	return maxAreaBruteForce(height);
    }
    
    private int maxAreaBruteForce(int[] height) {
    	int max = 0;
    	for (int i = 0; i < height.length - 1; i++) {
    		for (int j = i + 1; j < height.length; j++) {
    			max = Math.max(max, (j-i)*Math.min(height[i], height[j]));
    		}
    	}
    	return max;
    }
    
    // RETURNS: the maximum area of the container formed by lines of (i, height[i]),
    // where i in [0, count)
    private int maxAreaHelper(int[] height, int count) {
        if (count < 2) return 0;
        
        int max = 0;
        for (int i = 0; i < count - 1; i++) {
            max = Math.max((count-1-i) * Math.min(height[count-1], height[i]),
                               max);
        }
        return Math.max(max, maxAreaHelper(height, count-1));
    }
}