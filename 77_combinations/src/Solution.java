// Leetcode 77
// Combinations
// https://leetcode.com/problems/combinations/

// Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
// 
// For example,
// If n = 4 and k = 2, a solution is:
// 
// [
//   [2,4],
//   [3,4],
//   [2,3],
//   [1,2],
//   [1,3],
//   [1,4],
// ]

import java.util.*;

public class Solution {
	
    public List<List<Integer>> combine(int n, int k) {
        if (n < k || k <= 0) {
            return new ArrayList<List<Integer>>();
        }
        else {
        	return combineHelper(n, k);
        }
    }
    
    // WHERE: 1 <= k && k <= n
    // RETURNS: all combinations of k numbers out of 1...n 
	// TERMINATION: the value of k becomes smaller
    private List<List<Integer>> combineHelper(int n, int k) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (k == 1) {
        	for (Integer i = 1; i <= n; i++) {
        		ArrayList<Integer> l = new ArrayList<Integer>();
        		l.add(i);
        		result.add(l);
        	}
        	return result;
        }
        else if (n == k) {
            ArrayList<Integer> l = new ArrayList<Integer>();
            for(int i = 1; i <= k; i++) {
                l.add(i);
            }
            result.add(l);
            return result;
        }
        else {
            //@assert 1 < k && k < n;
            result = combineHelper(n - 1, k - 1); // combinations that contain n
            for(List<Integer> l : result) {
                l.add(n);
            }
            result.addAll(combineHelper(n - 1, k));  // add combinations do not contain n
            return result;
        }
    }
    
    public static void main(String[] args) {
    	Solution s = new Solution();
    	System.out.println(s.combine(4, 2));
    }
}