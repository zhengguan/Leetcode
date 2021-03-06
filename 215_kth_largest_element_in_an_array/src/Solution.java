// Leetcode 215
// Kth Largest Element in an Array
// https://leetcode.com/problems/kth-largest-element-in-an-array/

/*

Find the kth largest element in an unsorted array. Note that it is the kth 
largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 �� k �� array's length.

*/

import java.util.*;

public class Solution {
    public int findKthLargest(int[] nums, int k) {
    	k--;
    	int start = 0, end = nums.length;
    	
    	while(start < end) 
    	//@loop_invariant 0 <= start && start <= k && k < end && end <= nums.length;
    	{
    		int mid = start + (end - start) / 2;
    		int index = partition(nums, start, mid, end); 
    		//@assert start <= index && index < end;
    		if(index == k) {
    			return nums[index];
    		}
    		else if (index > k) {
    			end = index;
    		}
    		else {
    			start = index + 1;
    		}
    	}
    	
    	// this line should never been executed
    	return 0;
    }
    
    /**
     * 
     * @param nums a array of ints
     * @param start represents the start index of the range(inclusive)
     * @param mid index of element that is used to do partition on array
     * @param end represents the end index of the range(exclusive)
     * @where 0 <= start && start < end;
     * @effect do partition on nums[start, end) by nums[mid]
     * @return \result, such that (start <= \result && \result < end), all 
     * elements in nums[start, \result) >= nums[\result], all elements in 
     * nums[\result + 1, end) < nums[\result]    
     */
    private int partition(int[] nums, int start, int mid, int end) {
    	int i = start, j = end;
    	swap(nums, i, mid);
    	i++;
    	
    	while(i < j) 
    	/*@loop_invariant 0 <= start && start < i 
    		&& i <= j && j <= end && end <= nums.length;
    	 @*/ 
    	/*@loop_invariant nums[start, i) >= nums[start]; @*/
    	/*@loop_invariant nums[j, end) < nums[start]; @*/
    	{
    		if(nums[i] >= nums[start]) {
    			i++;
    		}
    		else {
    			swap(nums, i, j - 1);
    			j--;
    		}
    	}
    	swap(nums, start, j - 1);
    	return j - 1;
    }
    
    private void swap(int[] nums, int i, int j) {
    	int tmp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = tmp;
    	return;
    }
}