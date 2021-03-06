// Leetcode
// 1	Two Sum
// https:// leetcode.com/problems/two-sum/
// 	
// 
// Given an array of integers, find two numbers such that they add up to a 
// specific target number.
// 
// The function twoSum should return indices of the two numbers such that they 
// add up to the target, where index1 must be less than index2. Please note 
// that your returned answers (both index1 and index2) are not zero-based.
// 
// You may assume that each input would have exactly one solution.
// 
// Input: numbers={2, 7, 11, 15}, target=9
// Output: index1=1, index2=2
import tester.Tester;

import java.util.*;

public class Solution {
	// GIVEN: an array nums and a value target
	// RETURNS: an array ot two indexes of nums \result, such that 
	// (\result[0] < \result[1]) 
	// && (nums[\result[0]] + nums[\result[1]] == target)
    public int[] twoSum(int[] nums, int target) {
        int[] numsCopy = new int[nums.length];
        System.arraycopy(nums, 0, numsCopy, 0, nums.length);
        Arrays.sort(numsCopy);
        int start = 0;
        int end = numsCopy.length - 1;
        while(start <= end) 
        /*@loop_invariant 
           canSumTo(nums, 0, start, nums, end, nums.length, target) == false;
         @*/
        {
        	if(numsCopy[start] + numsCopy[end] == target) {
        		break;
        	}
        	else if (numsCopy[start] + numsCopy[end] < target) {
        		start++;
        	}
        	else {
        		end--;
        	}
        }
        if(start > end) {
        	System.out.println("WRONG INPUT ARRAY");
        	System.exit(1);
        }
        return indexOfTwoInt(nums, numsCopy[start], numsCopy[end]);
    }
    
    // GIVEN: an array nums and two ints x, y
    // WHERE: nums contains elements nums[i] == x, nums[j] == y
    // RETURNS: i and j in an array, sort by their value
    public int[] indexOfTwoInt(int[] nums, int x, int y) {
    	int i;
    	int[] indexes = new int[2];
    	for(i = 0; i < nums.length; i++) {
    		if(nums[i] == x || nums[i] == y) {
    			indexes[0] = i;
    			break;
    		}
    	}
    	for(; i < nums.length; i++) {
    		if(nums[i] == x + y - nums[indexes[0]]) {
    			indexes[1] = i;
    			break;
    		}
    	}
    	indexes[0]++;
    	indexes[1]++;
    	return indexes;
    		
    }
    
    // GIVEN: two sub arrays nums1[s1,e1), nums2[s2,e2) and a value target
    // RETURNS: true iff exist i1 in [s1, e1), i2 in [s2, e2), such that
    // nums1[i1] + nums2[i2] == target
    boolean canSumTo(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2, int target) {
    	for(int i = s1; i < e1; i++) {
    		for(int j = s2; j < e2; j++) {
    			if(nums1[i] + nums2[j] == target) {
    				return true;
    			}
    		}
    	}
    	return false;
    } 
}

class SolutionExamples{
	Solution s = new Solution();
	
	// RETURNS: true iff twoSum() returns the indexes of two elements in nums,
	// such the sum of the two elements equal to target
	boolean twoSumChecker(int[] nums, int target) {
		int[] indexes =  s.twoSum(nums, target);
		System.out.println(Integer.toString(indexes[0]) + " "+ indexes[1]);
		return 
		indexes.length == 2 &&
		indexes[0] < indexes[1] &&
		nums[indexes[0] - 1] + nums[indexes[1] - 1] == target;
	}
	
	// tests for method twoSum()
	boolean testTwoSum(Tester t) {
		return
		t.checkExpect(twoSumChecker(new int[]{1, 2}, 3)) &&
		t.checkExpect(twoSumChecker(new int[]{1, 3, 2, 6}, 5)) &&
		t.checkExpect(twoSumChecker(new int[]{1, 3, 2, 6}, 4)) &&
		t.checkExpect(twoSumChecker(new int[]{1, 2, 6, 10, 3}, 13)) &&
		t.checkExpect(twoSumChecker(new int[]{2, 1, 6, 3, 8}, 8));
	}
}