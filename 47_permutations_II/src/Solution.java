// Leetcode 47	
// Permutations II
// https://leetcode.com/problems/permutations-ii/

// Given a collection of numbers that might contain duplicates, return all 
// possible unique permutations.
// 
// For example,
// [1,1,2] have the following unique permutations:
// [1,1,2], [1,2,1], and [2,1,1].

import java.util.*;

public class Solution {
	private List<List<Integer>> result;
	
	/**
	 * 
	 * @return all unique permutations of ints in the given array
	 */
    public List<List<Integer>> permuteUnique(int[] nums) {
    	result = new ArrayList<List<Integer>>();
        List<Integer> l = arrayToList(nums);          
        permuteUniqueHelper(l, 0);
        return result;
    }
    
    /**
     * 
     * @return a list representation of nums
     */
    static public List<Integer> arrayToList(int[] nums) {
    	List<Integer> l = new ArrayList<Integer>();
    	for(int i = 0; i < nums.length; i++) {
    		l.add(nums[i]);
    	}
    	return l;
    }
    
    /**
     * 
     * EFFECT: permute nums[start,nums.length), for each permutation add a 
     * list corresponding to the state of nums to this.result. 
     */
    private void permuteUniqueHelper(List<Integer> l, int start) {
    	int len = l.size();
    	if(start == len) {
    		List<Integer> tmp = new ArrayList<Integer>(l);
    		result.add(tmp);
    		return;
    	}
    	else {
    		Set<Integer> records = new TreeSet<Integer>();
    		for(int i = start; i < len; i++) {
    			if(!records.contains(l.get(i))) {
					swap(l, i, start);
					permuteUniqueHelper(l, start + 1);
					swap(l, i, start);
					records.add(l.get(i));
    			}
    		}
			return;
    	}
    }
    
    
    /**
     * WHERE: 0 <= i && i < l.size() && 0 <= j && j < l.size();
     * EFFECT: swap the ith element and the jth element of l
     */
    static <T> void swap(List<T> l, int i, int j) {
    	T t = l.get(i);
    	l.set(i, l.get(j));
    	l.set(j, t);
    	return;
    }
}