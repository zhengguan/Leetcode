// Leetcode 219	
// Contains Duplicate II
// https://leetcode.com/problems/contains-duplicate-ii/

// Given an array of integers and an integer k, find out whether there are two 
// distinct indices i and j in the array such that nums[i] = nums[j] and the 
// difference between i and j is at most k

import java.util.*;

public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; i++) 
        //@loop_invariant map contains all values of nums[0, i) and their 
        // biggest index in nums
        {
        	if(map.containsKey(nums[i])) {
        		int index = map.get(nums[i]);
        		if(i - index <= k) {
        			return true;
        		}
        		map.put(nums[i], i);
        	}
        	map.put(nums[i], i);
        }
        return false;
    }
}
