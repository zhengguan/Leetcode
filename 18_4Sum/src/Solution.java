// Leetcode 18	
// 4Sum
// https://leetcode.com/problems/4sum/

/*
Given an array S of n integers, are there elements a, b, c, and d in S such 
that a + b + c + d = target? Find all unique quadruplets in the array which 
gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. 
(ie, a ¡Ü b ¡Ü c ¡Ü d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
 */
import java.util.*;

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
    	return fourSumRecur(nums, target);
    }
    
    List<List<Integer>> result;
    private List<List<Integer>> fourSumIter(int[] nums, int target) {
        Arrays.sort(nums);
    	result = new ArrayList<List<Integer>>();
    	fourSumIterHelper(nums, 0, new Integer[4], 0, target);
    	return result;
    }
    
    private void fourSumIterHelper(int[] nums, int start, Integer[] buf, 
    		int bufStart, int target) {
    	
    }
    
    public List<List<Integer>> fourSumRecur(int[] nums, int target) {    	
        Arrays.sort(nums);
        List<List<Integer>> result = fourSumHelper(nums, 0, 4, target);
        for (List<Integer> l : result) {
        	Collections.reverse(l);
        }
        return result;
    }
    
    // GIVEN: a sorted sub-array nums[start,nums.length), an integer n represents the number of
    // elements could be choose, an integer n represents the target sum value
    // RETURNS: all combination of n numbers in nums[start,nums.length) that sum
    // to target, keep elements in each combination in non-descending order
    private List<List<Integer>> fourSumHelper(int[] nums, int start, int n, int target) {
        if (target == 0 && n == 0) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            result.add(new ArrayList<Integer>());
            return result;
        } else if (n == 0 || start >= nums.length || target < nums[start] 
        		|| nums.length - start < n) {
            return new ArrayList<List<Integer>>();
        } else {
            List<List<Integer>> result = fourSumHelper(nums, start+1, n-1, target-nums[start]);
            for (List<Integer> l : result) {
                l.add(nums[start]);
            }
            result.addAll(fourSumHelper(nums, start+1, n, target));
            return result;
        }
    }
}