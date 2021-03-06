import java.util.Arrays;

// Leetcode 31
// Next Permutation
// https://leetcode.com/problems/next-permutation/

/*

Implement next permutation, which rearranges numbers into the lexicographically 
next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest 
possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its 
corresponding outputs are in the right-hand column.
1,2,3 �� 1,3,2
3,2,1 �� 1,2,3
1,1,5 �� 1,5,1

*/

import java.util.*;

public class Solution {
	public void nextPermutation(int[] nums) {
		if(nums.length < 2) {
			return;
		}
		else {
			// a map of (value, index)
			TreeMap<Integer, Integer> map  = new TreeMap<Integer, Integer>();
			
			for(int i = nums.length - 1; i >= 0; i--) {
				Map.Entry<Integer, Integer> entry = map.ceilingEntry(nums[i] + 1);
				if(entry != null) {
					int index = entry.getValue();
					swap(nums, i, index);
					Arrays.sort(nums, i + 1, nums.length);
					return;
				}
				else {
					map.put(nums[i], i);
				}
			}	
			reverse(nums, 0, nums.length);
			return;
		}		
	}
	
	public void nextPermutation2(int[] nums) {
		int s = -1, e = -1;
		for(int i = 0; i < nums.length - 1; i++) {
			int index = indexOfMinGreaterThan(nums, i + 1, nums.length, nums[i]);
			//@assert i + 1 <= index && index < nums.length;
			if(index != -1) {
				s = i;
				e = index;
			}
		}
		
		if(s == -1) {
			reverse(nums, 0, nums.length);
			return;
		} else {
			swap(nums, s, e);
			Arrays.sort(nums, s + 1, nums.length);
			return;
		}
	}
	
	// RETURNS: the index of the minimum elements in nums[start,end) that is 
	// greater than val, if there is no such element return -1;
	private int indexOfMinGreaterThan(int[] nums, int start, int end, int val) {
		int result = -1;
		for(int i = start; i < end; i++) {
			if(nums[i] > val) {
				if(result == -1) {
					result = i;
				}
				else {
					if(nums[i] < nums[result]) {
						result = i;
					}
				}
			}
		}
		return result;
	}

    
    // EFFECT: swap the value of nums[i] and nums[j]
    private void swap(int[] nums, int i, int j) {
    	int tmp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = tmp;
    }
    
    // EFFECT: reverse sub-array nums[start, end)
    private void reverse(int[] nums, int start, int end) {
    	while(start < end) {
    		swap(nums, start, end - 1);
    		start++;
    		end--;
    	}
    }
}