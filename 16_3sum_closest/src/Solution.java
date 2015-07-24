// Leetcode 16
// 3Sum Closest
// https://leetcode.com/problems/3sum-closest/

// Given an array S of n integers, find three integers in S such that the sum is
// closest to a given number, target. Return the sum of the three integers. You 
// may assume that each input would have exactly one solution.
// 
//     For example, given array S = {-1 2 1 -4}, and target = 1.
// 
//     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
import tester.Tester;

import java.util.*;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        return this.sumClosest(nums, 0, 
        		0, 3, target);
    }
    
    // GIVEN: a sub-array of ints nums[start,nums.length) represents the all
    // of the optional numbers, sumOfRest represents the sum of all numbers 
    // that has been chose, count represents the numbers that need to be chose
    // WHERE: (0 <= startRst) && (startRst <= rst.length) &&
    //		  (count >= nums.length - start)
    // EFFECT: choose count ints in nums[start, nums.length) such that the 
    // the sum of all of the chosen numbers is closest to target
    // RETURNS: choose count ints in nums[start, nums.length) such that the 
    // the sum of all of the chosen numbers is closest to target, return the 
    // sum of all chosen numbers
    // TERMINATION: the value of (nums.length - start) becomes smaller
    public int sumClosest(int[] nums, int start, 
    		int sumOfRest, int count, int target) {
    	if(count == 0) {
    		return sumOfRest;
    	}
    	else if(count == nums.length - start) {
    		return sumOfRest + this.sumOfSubArray(nums, start, nums.length);
    	}
    	else {
    		int sum1 = this.sumClosest(nums, start + 1, sumOfRest, count, target);
    		int sum2 = this.sumClosest(nums, start + 1, sumOfRest + nums[start], 
    					count - 1, target);
    		return this.closet(sum1, sum2, target);
    	}
    }
    
    // RETURNS: the closet one to target in n1 and n2
    public int closet(int n1, int n2, int target) {
    	if(Math.abs(n1 - target) < Math.abs(n2 - target)) {
			return n1;
		}
		else {
			return n2;
		}
    }
    
    // RETURNS: the sum of all numbers in the given array
    public int sumOfArray(int[] nums) {
    	return this.sumOfSubArray(nums, 0, nums.length);
    }
    
    // RETURNS: the sum of all numbers in nums[start, end)
    public int sumOfSubArray(int[] nums, int start, int end) {
    	int sum = 0;
    	for(int i = start; i < end; i++) {
    		sum += nums[i];
    	}
    	return sum;
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	// tests for method threeSumCloset() 
	boolean testThreeSumCloset(Tester t) {
		return
		t.checkExpect(s.threeSumClosest(new int[]{-1, 2, 1}, 0), 2) &&
		t.checkExpect(s.threeSumClosest(new int[]{-1, 2, 1, -4}, 0), -1) &&
		t.checkExpect(s.threeSumClosest(new int[]{-1, 0, 1, -4}, 0), 0);
	}
	
	// tests for method sumClosest()
	boolean testSumClosest(Tester t) {
		return
		t.checkExpect(s.sumClosest(new int[]{1, 2, 3}, 0, 0, 1, 3), 3) &&
		t.checkExpect(s.sumClosest(new int[]{1, 2, 5}, 0, 0, 1, 3), 2) &&
		t.checkExpect(s.sumClosest(new int[]{1, 2, 3}, 0, 0, 2, 3), 3) &&
		t.checkExpect(s.sumClosest(new int[]{-1, 2, 1, -4}, 0, 0, 3, 0), -1);
	}
}