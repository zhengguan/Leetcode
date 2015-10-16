// Leetcode 42	
// Trapping Rain Water
// https://leetcode.com/problems/trapping-rain-water/

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


 */

public class Solution {
    public int trap(int[] height) {
        int[] map = mapAfterRaining(height);
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            sum += map[i] - height[i];
        }
        return sum;
    }
    
    // GIVEN: an array representing an elevation map
    // RETURNS: the map after raining
    private int[] mapAfterRaining(int[] height) {
        if (height.length < 3) {
            return height;
        } else {
            //@assert height.lenght >= 2;
            int[] map = new int[height.length];
            int prePeakIndex = 0;
            // whether there is ascending in height[prePeakIndex, i]
            boolean ascending = false; 
            // whether there is descending in height[prePeakIndex, i]	            
            boolean descending = false;
            for (int i = 0; i < height.length; i++) {
            	if (i > 0 && height[i-1] < height[i]) {
            		if (!ascending) {
            			ascending = true;
            		}
            	}            	
            	if (i > 0 && (i == height.length - 1 || height[i-1] > height[i])) {
            		if (!descending) {
            			descending = true;
            			prePeakIndex = i-1;
            		} else if (ascending) {
            			fill(height, prePeakIndex, map, prePeakIndex, i - prePeakIndex, 
            					Math.min(height[prePeakIndex], height[i-1]));
            			prePeakIndex = i;
            			ascending = false;
            		}
            	}            	
            }
            
            for (int i = 0; i < map.length; i++) {
            	System.out.print(map[i] + "  ");
            }
            System.out.println("");
            return map;
        }
    }
    
    
    // EFFECT: copy maximum value of src[srcStart, srcStart+size) and min to 
    // dst[dstStart, dstStart+size) 
    private void fill(int[] src, int srcStart, int[] dst, int dstStart, int size, int min) {
    	System.out.println("Entering fill()");
        for (int i = 0; i < size; i++) {
            dst[dstStart+i] = Math.max(src[srcStart+i], min);
        }        
        return;
    }
}