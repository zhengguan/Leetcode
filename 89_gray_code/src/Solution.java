// Leetcode 89
// Gray Code
// https://leetcode.com/problems/gray-code/

// The gray code is a binary numeral system where two successive values differ 
// in only one bit.
// 
// Given a non-negative integer n representing the total number of bits in the 
// code, print the sequence of gray code. A gray code sequence must begin with 0.
// 
// For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
// 
// 00 - 0
// 01 - 1
// 11 - 3
// 10 - 2
// Note:
// For a given n, a gray code sequence is not uniquely defined.
// 
// For example, [0,2,3,1] is also a valid gray code sequence according to the 
// above definition.
// 
// For now, the judge is able to judge based on one instance of gray code 
// sequence. Sorry about that.

import java.util.*;

import tester.Tester;;

public class Solution {
    public List<Integer> grayCode(int n) {
        int count = (int)Math.pow(2, n);
        ArrayList<Integer> result = new ArrayList<Integer>();
        int[] ops = new int[32]; // represents operations on every bit of n
        this.initializeOperations(ops);
        int code = 0;
        for(int i = 0; i < count; i++) {
        	result.add(code);
        	code = this.nextValue(code, ops);
        }
        return result;
    }
    
    // WHERE: ops.length == 32;
    // EFFECT: initialize operations on every bit to +1(represents add 1)
    void initializeOperations(int[] ops) {
    	for(int i = 0; i < ops.length; i++) {
    		ops[i] = 1;
    	}
    	return;
    }
    
    // GIVEN: an int code represents the current value of gray code, an array 
    // of ints ops represents the operations can perform on every bit of gray 
    // code
    // WHERE: ops.length == 32;
    // EFFECT: set ops to next operations pattern 
    // RETURNS: the value next gray code
    public int nextValue(int code, int ops[]) {
    	for(int i = 31; i>= 0; i--) {
    		if(this.isLegalOperation(code, ops, i)) {
    			return this.performOperation(code, ops, i);
    		}
    		else {
    			ops[i] = -ops[i];  // change operation
    		}
    	}    	
    	return 0; // this line should never be executed
    }
    
    // GIVEN: a gray code, an operation ops[i] on the (31 - i)th bit
    // RETURNS: true iff this operation is legal
    boolean isLegalOperation(int code, int[] ops, int i) {
    	return !(ops[i] == 1 && ((code >> (31 - i)) & 1) == 1) &&
    			!(ops[i] == -1 && ((code >> (31 - i)) & 1) == 0);
    }
    
    // RETURNS: the result of perform ops[i] on the ith bit of code
    int performOperation(int code, int[] ops, int i) {
    	return code ^ (1 << (31 - i));
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	
	// RETURSN: a corresponding list of the given array
	List<Integer> arrayToList(int[] array) { 
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list;
	}
	
	// tests for method grayCode()
	boolean testGrayCode(Tester t) {
		return
		t.checkExpect(s.grayCode(1), this.arrayToList(new int[] {0, 1})) &&
		t.checkExpect(s.grayCode(2), this.arrayToList(new int[] {0, 1, 3, 2})) &&
		t.checkExpect(s.grayCode(3), this.arrayToList(new int[] {0, 1, 3, 2, 6, 7, 5, 4}));
		
	}
}