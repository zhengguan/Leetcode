// Leetcode 60	
// Permutation Sequence
// https://leetcode.com/problems/permutation-sequence/

// The set [1,2,3,��,n] contains a total of n! unique permutations.
// 
// By listing and labeling all of the permutations in order,
// We get the following sequence (ie, for n = 3):
// 
// "123"
// "132"
// "213"
// "231"
// "312"
// "321"
// Given n and k, return the kth permutation sequence.
// 
// Note: Given n will be between 1 and 9 inclusive.
import tester.Tester;

import java.util.*;

public class Solution {

	// RETURNS: the kth permutation of number 1 to n
    public String getPermutation(int n, int k) {
        char[] cs = getArray(n);
        return getPermutationHelper(cs, 0, k - 1);
    }
    

    public String getPermutationHelper(char[] cs, int start, int k) {
    	if(k == 0) {
    		return new String(cs);
    	}
    	else {
    		Arrays.sort(cs, start, cs.length);
    		int tmp = this.factorial(cs.length - 1 - start);
    		swap(cs, start, start + k / tmp);
    		return this.getPermutationHelper(cs, start + 1, k % tmp);    		
    	}
    }
    
    public int factorial(int n) {
    	if(n <= 0) {
    		System.out.println("Error:factorial(" + n + ")");
    		System.exit(1);
    		return -1;
    	}
    	else {
    		int fact = 1;
    		for(int i = 0; i < n; i++) {
    			fact = fact * i;
    		}
    		return fact;
    	}
    }
    
    // RETURNS: an array of length n, with value from 1 to n
    public char[] getArray(int n) {
    	char[] cs = new char[n];
    	for(int i = 0; i < n; i++) {
    		cs[i] = (char)('0' + (i + 1));
    	}
    	return cs;
    }
    
    // EFFECT: swap the value of nums[i] and nums[j]
    public void swap(char[] nums, int i, int j) {
    	char tmp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = tmp;
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	boolean testGetPermutaion(Tester t) {
		return
		t.checkExpect(s.getPermutation(1, 1), new String("1")) &&
		t.checkExpect(s.getPermutation(2, 1), "12") &&
		t.checkExpect(s.getPermutation(3, 3), "213");
	}
}