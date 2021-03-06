// Leetcode 67
// Add Binary
// https://leetcode.com/problems/add-binary/

// Given two binary strings, return their sum (also a binary string).
// 
// For example,
// a = "11"
// b = "1"
// Return "100".

import tester.Tester;

public class Solution {
	public String addBinary(String a, String b) {
//		return this.addBinary1(a, b);
//		return this.addBinary2(a, b);
		return this.addBinary3(a, b);
	}
	
	// GIVEN: two binary Strings
	// WHERE: the ints represented by the given Strings must be not greater 
	// than the maximum value of int
	// RETURNS: the binary String of their sum
    public String addBinary1(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a, 2) 
        		+ Integer.parseInt(b,2));
    }
    
	// GIVEN: two binary Strings
	// RETURNS: the binary String of their sum
    public String addBinary2(String a, String b) {
    	char[] sum = new char[Math.max(a.length(), b.length()) + 1];
    	int carry = 0;
    	for(int i = 0; i < sum.length; i++) {
    		int sumOfBit = this.getBit(a, i) + this.getBit(b, i) + carry;
    		sum[sum.length - 1 - i] = (char)('0' + sumOfBit % 2);
    		carry = sumOfBit / 2;
    	}
    	if(sum[0] == '0') {
    		return new String(sum, 1, sum.length - 1);
    	}
    	else {
    		return new String(sum);
    	}
    }
    
    // GIVEN: a String represents the binary of an integer and an int i 
    // represents index
    // RETURNS: the ith bit of the integer counted from left start from 0
    public int getBit(String s, int i) {
    	if(i >= s.length()) {
    		return 0;
    	}
    	else {
    		return s.charAt(s.length() - 1 - i) - '0';
    	}
    }
    
	// GIVEN: two binary Strings
	// WHERE: the ints represented by the given Strings must be not greater 
	// than the maximum value of int
	// RETURNS: the binary String of their sum
    public String addBinary3(String a, String b) {
    	return this.intToBinaryString(this.binaryStringToInt(a) 
    								+ this.binaryStringToInt(b));
    }
   
    // GIVEN: a binary Strings
    // WHERE: its corresponding value is not greater than the maximum value of
    // int
    // RETURNS: the corresponding int
    public int binaryStringToInt(String s) {
    	int value = 0;
    	for(int i = 0; i < s.length(); i++) 
    	//@loop_invariant value == Integer.parseInt(s.subString(0, i) ,2);
    	{
    		value = value * 2 + (s.charAt(i) - '0');
    	}
    	return value;
    }
    
    // RETURNS: the corresponding binary string of n
    public String intToBinaryString(int n) {
    	if(n == 0) {
    		return "0";
    	}
    	else {
    		int length = 32;
    		char[] cs = new char[length];
    		int i;
    		for(i = length; n != 0; i--) 
    		/*@loop_invariant cs[i, length) represent the lowest (length - i)
    		   bits of the original n; @*/    		
    		{
    			cs[i - 1] = (char)('0' + (n & 1));
    			n = n >>> 1;
    		}
    		return new String(cs, i, length - i);
    	}
    }    
}

class SolutionExamples {
	Solution s = new Solution();
	
	// tests for method binaryStringToInt()
	boolean testBinaryStringToInt(Tester t) {
		return
		t.checkExpect(s.binaryStringToInt("0"), 0) &&
		t.checkExpect(s.binaryStringToInt("1"), 1) &&
		t.checkExpect(s.binaryStringToInt("11"), 3);
	}
	
	// tests for method intToBinaryString()
	boolean testIntToBinaryString(Tester t) {
		return
		t.checkExpect(s.intToBinaryString(0), "0") &&
		t.checkExpect(s.intToBinaryString(1), "1") &&
		t.checkExpect(s.intToBinaryString(5), "101");		
	}
	
	// tests for method addBinary()
	boolean testAddBinary(Tester t) {
		return
		t.checkExpect(s.addBinary("0", "1"), "1") &&
		t.checkExpect(s.addBinary("1", "1"), "10") &&
		t.checkExpect(s.addBinary("11", "11"), "110");		
	}
}