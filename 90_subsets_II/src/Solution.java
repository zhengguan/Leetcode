// Leetcode 90
// Subsets II
// https://leetcode.com/problems/subsets-ii/

/*
Given a collection of integers that might contain duplicates, nums, 
return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/
import java.util.*;

public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        else {
        	int[] copy = new int[nums.length];
        	System.arraycopy(nums, 0, copy, 0, nums.length);
        	Arrays.sort(copy);
        	reverseArray(copy);
        	return helper(copy, 0);
        }
    }
    
    // EFFECT: reverse nums
    private void reverseArray(int[] nums) {    	
    	for(int i = 0, j = nums.length - 1; i < j; i++, j--) 
    	//@loop_invariant nums[i, j] still need to be reversed
    	{
    		int tmp = nums[i];
    		nums[i] = nums[j];
    		nums[j] = tmp;
    	}
    	return;
    }
    
    // WHERE: nums might contain duplicates
    // RETURNS: all subsets of elements in nums[start, nums.length)
    private List<List<Integer>> helper(int[] nums, int start) {
    	if(start == nums.length) {
    		List<List<Integer>> result = new ArrayList<List<Integer>>();
    		result.add(new ArrayList<Integer>());
    		return result;
    	}
    	else {
    		int i;
    		for(i = start + 1; i < nums.length && nums[i] == nums[start]; i++);
    		
    		List<List<Integer>> result = new ArrayList<List<Integer>>();
    		List<List<Integer>> rest = helper(nums, i);
    		result.addAll(rest);
    		for(int j = 1; j <= i - start; j++) {
    			List<List<Integer>> tmp = addEach(rest, nums[start], j);
    			result.addAll(tmp);
    		}
    		return result;
    	}
    }
    
    // RETURNS: add n values to each list of lol and return the new formed 
    // list of lists
    private List<List<Integer>> addEach(List<List<Integer>> lol, int value, int n) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	for(List<Integer> l : lol) {
    		List<Integer> tmp = new ArrayList<Integer>(l);
    		addElements(tmp, value, n);
    		result.add(tmp);
    	}
    	return result;
    }
    
    // EFFECT: add n value to l
    private void addElements(List<Integer> l, int value, int n) {
    	for(int i = 0; i < n; i++) {
    		l.add(value);
    	}
    	return;
    }
}