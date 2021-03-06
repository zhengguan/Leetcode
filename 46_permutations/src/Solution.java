// Leetcode 46
// Permutations
// https://leetcode.com/problems/permutations/

// Given a collection of numbers, return all possible permutations.
// 
// For example,
// [1,2,3] have the following permutations:
// [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].

import tester.Tester;
import java.util.*;

public class Solution {
	List<List<Integer>> result = new ArrayList<List<Integer>>();
	
	// EFFECT: add all permutations of nums to result
	// RETURNS: result
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
        	return result;
        }
        else {
	    	permuteHelper(nums, 0);
	        return result;
        }
    }
    
    // WHERE: 0 <= start && start <= nums.length;
    // EFFECT: add all permutations of nums[start,) to result, for each
    // permutation add nums[0, start) to the beginning.
    public void permuteHelper(int[] nums, int start) {
    	if (start == nums.length) {    		
    		result.add(arrayToArrayList(nums));
    		return;
    	}
    	else {
    		for (int i = start; i < nums.length; i++) {
    			swap(nums, start, i);
    			permuteHelper(nums, start + 1);
    			swap(nums, start, i);
    		}
    	}
    }
    
    // EFFECT: swap the value of nums[i] and nums[j]
    void swap(int[] nums, int i, int j) {
    	int tmp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = tmp;
    	return;
    }
    
    // RETURNS: a list representation of the given array
    private ArrayList<Integer> arrayToArrayList(int[] ts) {
    	ArrayList<Integer> lot = new ArrayList<Integer>();
    	for(int i = 0; i < ts.length; i++) {
    		lot.add(ts[i]);
    	}
    	return lot;
    }
}


class SolutionExamples {
	Solution s = new Solution();
	int[] nums1 = new int[] {};
	int[] nums2 = new int[] {1, 2, 3};
	
	boolean testPermute(Tester t) {
		System.out.println(s.permute(nums2));
		return 
		t.checkExpect(s.permute(nums1), new ArrayList<List<Integer>>());
	}
}