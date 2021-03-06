// Leetcode 33
// Search in Rotated Sorted Array
// https://leetcode.com/problemset/algorithms/

// Suppose a sorted array is rotated at some pivot unknown to you beforehand.
// 
// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
// 
// You are given a target value to search. If found in the array return its index,
// otherwise return -1.
// 
// You may assume no duplicate exists in the array.


import tester.Tester;

import java.util.*;

public class Solution {
    public int search(int[] nums, int target) {
    	return this.searchHelperIter(nums, 0, nums.length, target);
    }
    
    // GIVEN: nums is a rotated sorted array with no repeated elements, 
    // [start, end) represents a range, target represents the value to be 
    // searched in nums[start, end)
    // WHERE: 0 <= start && start <= end && end <= nums.length;
    // RETURNS: the index of the element in nums[start, end) that is equal 
    // to target if exists, otherwise -1 
    // TERMINATION: the value of (end - start) becomes smaller
    public int searchHelper(int[] nums, int start, int end, int target) {
    	if(end - start == 0) {
    		return -1;
    	}
    	else {
    		int mid = start + (end - start) / 2;
    		//@assert start <= mid && mid < end;
    		if(nums[mid] == target) {
    			return mid;
    		}
    		else {
    			if(!this.notContains(nums, start, mid, target)) {
    				return this.searchHelper(nums, start, mid, target);
    			}
    			else {
    				return this.searchHelper(nums, mid + 1, end, target);
    			}
    		}
    	}
    }
    
    // GIVEN: a rotated sorted sub-array nums[start, end), a target value
    // WHERE: 0 <= start && start <= end && end <= nums.length;
    // RETURNS: true iff it is impossible that nums[start, end) contains an 
    // element that is equal to target
    public boolean notContains(int[] nums, int start, int end, int target) {
    	if(end - start == 0) {
    		return false;
    	}
    	else {
    		//@assert start < end;
    		return !
    		(((nums[start] <= nums[end - 1])
    		 &&(nums[start] <= target && target <= nums[end - 1])) ||
    		 ((nums[start] > nums[end - 1]) 
    		 && (nums[start] <= target || target <= nums[end - 1]))); 
    	}
    }
    
    
    // GIVEN: nums is a rotated sorted array with no repeated elements, 
    // [start, end) represents a range, target represents the value to be 
    // searched in nums[start, end)
    // WHERE: 0 <= start && start <= end && end <= nums.length;
    // RETURNS: the index of the element in nums[start, end) that is equal 
    // to target if exists, otherwise -1 
    public int searchHelperIter(int[] nums, int start, int end, int target) {  	
    	while(start < end) 
    	//@loop_invariant 0 <= start && start <= end && end <= nums.length;
    	/*@loop_invariant (this.notContains(nums, 0, start, target)) &&
    	  				  (this.notContains(nums, end, nums.length, target));
    	 @*/
    	{
    		int mid = start + (end - start) / 2;
    		//@assert start <= mid && mid < end;
    		if(nums[mid] == target) {
    			return mid;
    		}
    		else {
    			if(!this.notContains(nums, start, mid, target)) {
    				end = mid;
    			}
    			else {
    				start = mid + 1;
    			}
    		}
    	}
    	return -1;
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	// tests for method search()
	boolean testSearch(Tester t) {
		return
		t.checkExpect(s.search(new int[] {1, 3}, 3), 1) &&
		t.checkExpect(s.search(new int[] {1, 0}, 1), 0) &&
		t.checkExpect(s.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3), -1) &&
		t.checkExpect(s.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 5), 1) &&
		t.checkExpect(s.search(new int[] {0, 1, 2, 4, 5, 6, 7, }, 0), 0) &&
		t.checkExpect(s.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 2), 6);
	}
}

/*
CONCLUSION: the nature of binary search is to eliminate half(or part) elements from 
the search area, it is also possible to eliminate half elements from a rotated
sorted array, so it is possible to do binary search in rotated sorted array
*/
