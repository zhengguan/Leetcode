import java.util.*;

import tester.Tester;

// Leetcode 81	
// Search in Rotated Sorted Array II
// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

// Follow up for "Search in Rotated Sorted Array":
// What if duplicates are allowed?
// 
// Would this affect the run-time complexity? How and why?
// 
// Write a function to determine if a given target is in the array.

public class Solution {
    public boolean search(int[] nums, int target) {
//        return this.searchHelper(nums, 0, nums.length, target);
    	return this.searchHelper(nums, 0, nums.length, target);
    }
    
    // WHERE: 0 <= start && start <= end && end <= nums.length;
    // RETURNS: true iff nums[start, end) contains element that is equal to 
    // target
    public boolean searchHelper(int[] nums, int start, int end, int target) {
    	if(start == end) {
    		return false;
    	}
    	else {
    		//@assert start < end;
    		int mid = start + (end - start) / 2;
    		if(nums[mid] == target) {
    			return true;
    		}
    		else if (nums[mid] < target) {
    			if (nums[mid] < nums[end - 1]) {
    				return 
    				searchHelper(nums, start, mid, target)
    				|| (Arrays.binarySearch(nums, mid + 1, end, target)) > -1 ;
    			}
    			else if (nums[mid] == nums[end - 1]) {
    				return 
    				searchHelper(nums, start, mid, target)
    				|| linearSearch(nums, mid + 1, end, target);
    			}
    			else {
    				//@assert nums[mid] > nums[end - 1];
    				return 
    				Arrays.binarySearch(nums, start, mid, target) > -1
    				|| searchHelper(nums, mid + 1, end, target);
    			}
    		}
    		else {
    			//@assert nums[mid] > target;
    			if (nums[start] < nums[mid]) {
    				return 
    				Arrays.binarySearch(nums, start, mid, target) > -1
    				|| searchHelper(nums, mid + 1, end, target);
    			}
    			else if (nums[start] == nums[mid]) {
    				return 
    				linearSearch(nums, start, mid, target)
    				|| searchHelper(nums, mid + 1, end, target);
    			}
    			else {
    				//@assert nums[start] > nums[mid];
    				return 
    				searchHelper(nums, start, mid, target) 
    				|| Arrays.binarySearch(nums, mid + 1, end, target) > -1;
    			}
    		}
    	}
    }
    
    // GIVEN: a sub-array nums[start, end) and a target value
    // WHERE: 0 <= start && start <= end && end <= nums.length;
    // RETURNS: true iff the given sub-array contains an element equal to 
    // target
    private boolean linearSearch(int[] nums, int start, int end, int target) {
    	for (int i = start; i < end; i++) {
    		if (nums[i] == target) {
    			return true;
    		}
    	}
    	return false;
    }
   
    

}

class SolutionExamples {
	Solution s = new Solution();
	
	// RETURNS: true iff nums contains element that is equal to target
	boolean searchBruteForce(int[] nums, int target) {
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == target) {
				return true;
			}
		}
		return false;
	}
	
	// RETURNS: true iff Solution.search() returns the correct result
	boolean checkSearch(int[] nums, int target) {
		return 
			this.s.search(nums, target) 
			== this.searchBruteForce(nums, target);
	}
	
	// tests for method search()
	boolean testSearch(Tester t) {
		return
		t.checkExpect(this.checkSearch(new int[] {1, 3, 1}, 3), true) &&
		t.checkExpect(this.checkSearch(new int[] {1, 3, 1}, 2), true) &&
		t.checkExpect(this.checkSearch(new int[] {1, 0}, 1), true) &&
		t.checkExpect(this.checkSearch(new int[] {4, 5, 6, 7, 0, 1, 2}, 3), true) &&
		t.checkExpect(this.checkSearch(new int[] {4, 5, 6, 7, 0, 1, 2}, 5), true) &&
		t.checkExpect(this.checkSearch(new int[] {0, 1, 2, 4, 5, 6, 7}, 0), true) &&
		t.checkExpect(this.checkSearch(new int[] {4, 5, 6, 7, 0, 1, 2}, 2), true) &&
		t.checkExpect(this.checkSearch(new int[]{1,3,1,1}, 3), true);
	}
}