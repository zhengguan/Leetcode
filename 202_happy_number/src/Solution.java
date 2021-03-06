// Leetcode 202
// Happy Number
// https://leetcode.com/problems/happy-number/

// Write an algorithm to determine if a number is "happy".
// 
// A happy number is a number defined by the following process: Starting with 
// any positive integer, replace the number by the sum of the squares of its 
// digits, and repeat the process until the number equals 1 (where it will stay), 
// or it loops endlessly in a cycle which does not include 1. Those numbers for 
// which this process ends in 1 are happy numbers.
// 
// Example: 19 is a happy number
// 
// 12 + 92 = 82
// 82 + 22 = 68
// 62 + 82 = 100
// 12 + 02 + 02 = 1
// Credits:

import tester.Tester;

import java.util.*;

public class Solution {
	// WHERE: n > 0;
	// RETURNS: true iff n is a happy number
    public boolean isHappy(int n) {
        Set<Integer> records = new HashSet<Integer>();
        while(!records.contains(n)) {
        	if (n == 1) {
        		return true;
        	}
        	records.add(n);
        	n = sumOfSquareOfDigits(n);
        }
        return false;
    }
     
    // RETURNS: the sum of squares of the given number's digits(in decimal)
    private int sumOfSquareOfDigits(int n) {
    	int sum = 0;
    	while (n != 0) {
    		sum += (int) Math.pow(n % 10, 2);
    		n = n / 10;    		
    	}
    	return sum;
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	// test for method isHappy
	boolean testIsHappe(Tester t) {
		return
		t.checkExpect(s.isHappy(19), true) &&
		t.checkExpect(s.isHappy(25), false);
				
	}
}