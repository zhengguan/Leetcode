// Leetcode 91 
// Decode Ways
// https:// leetcode.com/problems/decode-ways/

// A message containing letters from A-Z is being encoded to numbers using the following mapping:
// 
// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Given an encoded message containing digits, determine the total number of ways to decode it.
// 
// For example,
// Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
// 
// The number of ways decoding "12" is 2.

import tester.Tester;
import java.util.*;

public class Solution {
    public int numDecodings(String s) {
    	if(s.length() == 0) {
    		return 0;
    	}
//        return numDecodingsIter(s);
    	return numDecodingsRecur(s);
    }
    
    public int numDecodingsRecur(String s) {
    	return this.numDecodingsHelperRecur2(s.toCharArray(), 0, s.length());
    }
    
    // GIVEN: an sub-array of chars cs[start, end)
    // WHERE: 0 <= start <= end <= cs.length;
    // RETURNS: the number of ways to decode the message corresponding the sub-array
    public int numDecodingsHelperRecur(char[] cs, int start, int end) {
        if(start == end) {
            return 1;
        }
        if(cs[start] == '0') {
            return 0;
        }
        else if(cs[start] == '1') {
            if(start + 1 < end) {
                return  numDecodingsHelperRecur(cs, start + 1, end)
                        + numDecodingsHelperRecur(cs, start + 2, end);
            }
            return  numDecodingsHelperRecur(cs, start + 1, end);
        }
        else if (cs[start] == '2'){
            if(start + 1 < end && '0' <= cs[start + 1] && cs[start + 1] <= '6') {
                return  numDecodingsHelperRecur(cs, start + 1, end) 
                        + numDecodingsHelperRecur(cs, start + 2, end);
            }
            return  numDecodingsHelperRecur(cs, start + 1, end);
        }
        else {
        	//@assert '3' <= cs[start] && cs[start] <= '9'; 
        	return numDecodingsHelperRecur(cs, start + 1, end);
        }
    }
    
    // GIVEN: an sub-array of char cs[start, end)
    // WHERE: start < end;
    // RETURNS: the number of possible decodings
    public int numDecodingsHelperRecur2(char[] cs, int start, int end) {
    	int len = end - start;
    	if(len == 1) {
    		if(cs[start] == '0') {
    			return 0;
    		}
    		else {
    			return 1;
    		}
    	}
    	if(len == 2) {
    		int n = 0;
    		if(isValidOneDigit(cs, start)) {
    			n = n + numDecodingsHelperRecur2(cs, start + 1, end);
    		}
    		if(isValidTwoDigits(cs, start)) {
    			n = n + 1;
    		}
    		return 
    		(isValidOneDigit(cs, start) ?
    				numDecodingsHelperRecur2(cs, start + 1, end):0)
    		+ (isValidTwoDigits(cs, start) ? 1:0);
    	}
    	else {
    		//@assert len > 2;
    		return 
    		(isValidOneDigit(cs, start)? numDecodingsHelperRecur2(cs, start + 1, end):0)
			+ (isValidTwoDigits(cs, start)? numDecodingsHelperRecur2(cs, start + 2, end):0);
    	}
    }
    
    public int numDecodingsIter(String s) {
    	return this.numDecodingsHelperIter2(s.toCharArray(), 0, s.length());
    }
    
    public int numDecodingsHelperIter(char[] cs, int start, int end) {
    	// ith element means number of ways to decode cs[i,cs.length) 
    	int[] numOfDecodings = new int[cs.length + 2];
    	numOfDecodings[cs.length] = 1;
    	numOfDecodings[cs.length + 1]= 0;
    	for(int i = cs.length - 1; i >= 0; i--) 
       	// numOfDecodings[i + 1] equals to number of ways to decode cs[i + 1,cs.length)
    	{
    		if(isValidOneDigit(cs, i)) {
    			numOfDecodings[i] = numOfDecodings[i] + numOfDecodings[i + 1];
    		}
    		if(isValidTwoDigits(cs, i)) {
    			numOfDecodings[i] = numOfDecodings[i] + numOfDecodings[i + 2];
    		}
    	}
    	return numOfDecodings[0];
    }
    
    // WHERE: start < end;
    public int numDecodingsHelperIter2(char[] cs, int start, int end) {
    	int f1 = 1, f2 = 0, f3 = 0;
    	int i = cs.length - 1; 
    	while(i >= 0) 
    	//@loop_invariant f1 equals the number of decodings of cs[i + 1, cs.length);
       	//@loop_invariant f1 equals the number of decodings of cs[i + 2, cs.length);
       	//@loop_invariant f1 equals the number of decodings of cs[i + 3, cs.length);
    	{

    		f3 = f2;
    		f2 = f1;
    		f1 = 0;
    		if(isValidOneDigit(cs, i)) {
    			f1 = f1 + f2;  
    		}
    		if(isValidTwoDigits(cs, i)) {
    			f1 = f1 + f3;
    		}
    		i--;
    	}
    	return f1;
    }
    
    public boolean isValidOneDigit(char[] cs, int i) {
    	if(i < 0 || i >= cs.length) {
    		return false;
    	}
    	return '1' <= cs[i] && cs[i] <= '9';
    }
    
    public boolean isValidTwoDigits(char[] cs, int i) {
    	if(i < 0 || i + 1 >= cs.length) {
    		return false;
    	}
    	if(cs[i] == '1') {
    		return true;
    	}
    	else if(cs[i] == '2') {
    		return '0' <= cs[i + 1] && cs[i + 1] <= '6';
    	}
    	return false;
    }
}

class SolutionExamples {
	Solution s = new Solution();

	// tests for method numDecodingsRecur()
	boolean testNumDecondingsRecur(Tester t) {
		return
//		t.checkExpect(s.numDecodingsRecur(""), 1) &&
		t.checkExpect(s.numDecodingsRecur("0"), 0) &&
		t.checkExpect(s.numDecodingsRecur("1"), 1) &&
		t.checkExpect(s.numDecodingsRecur("3"), 1) &&
		t.checkExpect(s.numDecodingsRecur("32"), 1) &&
		t.checkExpect(s.numDecodingsRecur("13"), 2) &&
		t.checkExpect(s.numDecodingsRecur("121"), 3);
	}
	
	// tests for method numDecodingHelperIter()
	boolean testNumDecodingIter(Tester t) {
		return
		t.checkExpect(s.numDecodingsIter("12"),
				s.numDecodingsRecur("12")) &&
		t.checkExpect(s.numDecodingsIter("132"),
				s.numDecodingsRecur("132")) &&
		t.checkExpect(s.numDecodingsIter("119430569871364862"),
				s.numDecodingsRecur("119430569871364862"));
	}
}