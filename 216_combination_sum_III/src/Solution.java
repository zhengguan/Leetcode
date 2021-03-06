// Leetcode 216	
// Combination Sum III
// https://leetcode.com/problems/combination-sum-iii/

/*
Find all possible combinations of k numbers that add up to a number n, given 
that only numbers from 1 to 9 can be used and each combination should be a 
unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
 */
import java.util.*;

public class Solution {
	List<List<Integer>> result;
	
    public List<List<Integer>> combinationSum3(int k, int n) {
    	result = new ArrayList<List<Integer>>();
    	helper(k, n, new ArrayList<Integer>(), 1);
        return result;
    }
    
    /**
     * 
     * @param k the number of ints could choose
     * @param sum the expected sum of ints to be choose 
     * @param prev list of previously chosen ints
     * @param start the range of ints could choose [start, 9]
     * WHERE: 0 <= k && 0 <= sum && 1 <= start && start <= 9;
     * EFFECT: add all lists to result which begin with all elements in 
     * prev and contains another k ints in range [start, n] that sums to sum
     */
    private void helper(int k, int sum, List<Integer> prev, int start) {
    	if(k == 0) {
			if (sum == 0) {
				List<Integer> l = new ArrayList<Integer>();
				l.addAll(prev);
				result.add(l);
				return;
			}
    	}
    	else {
    		if(start <= sum && start <= 9) {
    			helper(k, sum, prev, start + 1);
    			prev.add(start);
    			helper(k - 1, sum - start, prev, start + 1);
    			prev.remove(prev.size() - 1);
    			return;
    		}
    	}
    } 
}