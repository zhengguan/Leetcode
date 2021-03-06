// Leetcode 201	
// Bitwise AND of Numbers Range
// https://leetcode.com/problems/bitwise-and-of-numbers-range/

// Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND 
// of all numbers in this range, inclusive.
// 
// For example, given the range [5, 7], you should return 4.

import tester.Tester;

public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
//    	return rangeBitwiseAndSimpleVersion(m, n);
    	return rangeBitwiseAndOptimizedVersion(m, n);
    }
    
    private int rangeBitwiseAndSimpleVersion(int m, int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			if (!existNthBitZero(m, n, i)) {
				result |= (1 << i);
			}
		}
		return result;
    }
    
    private int rangeBitwiseAndOptimizedVersion(int m, int n) {
        int result = 0;
    	int tmp = n;
        int i = 0;
        int mask = (~ (1 << 31));
        while(tmp != 0) {
        	if(!existNthBitZero(m, n, i)) {
        		result |= (1 << i);
        	}
        	i++;
        	tmp = (tmp >> 1) & mask;
        }
        return result;
    }
    
    // RETURNS: true iff there exists a number x in [start, end], 
    // such that the nth bit of x is 0
    private boolean existNthBitZero(int start, int end, int n) {
        int mask1 = 1 << n;
        int mask2 = ((1 << 31) >> (31  - n)); // 1111...000 (with n 0s)
        if ((start & mask1) == 0) {
            return true;
        }
        else {
        	// minimum number that is greater or equal to start and with 
        	// nth bit 0
/*        	Note: this is wrong because ((start & mask2) + mask1) might overflow;
        	int min = ((start & mask2) + mask1);
        	return min <= end;
        	
*/        	
            return (end - mask1) >= ((start & mask2));
        }
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	// tests for method rangeBitwiseAnd
	boolean testRangeBitwiseAnd(Tester t) {
		System.out.println(~(1 << 31));
		return
		t.checkExpect(s.rangeBitwiseAnd(5, 7), 4) &&
		t.checkExpect(s.rangeBitwiseAnd(1, 3), 0) &&
		t.checkExpect(s.rangeBitwiseAnd(2147483647, 2147483647), 2147483647);
		
	}
}
