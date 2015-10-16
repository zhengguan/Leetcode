// Leetcode 17	
// Letter Combinations of a Phone Number
// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
/*
Given a digit string, return all possible letter combinations that the number 
could represent.

A mapping of digit to letters (just like on the telephone buttons) is given 
below.


Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */

import java.util.*;

public class Solution {
    String[] digitToLetters = new String[]{" ", "", "abc", "def", "ghi", "jkl", 
    		"mno", "pqrs", "tuv", "wxyz"};
    List<String> result;
    public List<String> letterCombinations(String digits) {
    	result = new ArrayList<String>();
    	char[] buf = new char[digits.length()];
    	letterCombinationsHelper(digits, buf, 0);
    	return result;
    }
    
    // WHERE: (digits.length() == buf.length);
    // EFFECT: calculate all possible letter combinations that the numbers in 
    // digits[start, digits.length()) represents, for each combinations comb 
    // add the concatenation of digits[0, start) and comb to this.result 
    private void letterCombinationsHelper(String digits, char[] buf, int start) {
    	if (start == digits.length()) {
    		result.add(new String(buf));
    		return;
    	} else {
    		char digit = digits.charAt(start);
    		String letters = digitToLetters[(int) digit - '0'];
    		for (int i = 0; i < letters.length(); i++) {
    			buf[start] = letters.charAt(i);
    			letterCombinationsHelper(digits, buf, start + 1);
    		}
    	}
    }
}