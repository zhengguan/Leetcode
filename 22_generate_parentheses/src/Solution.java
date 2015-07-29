// Leetcode 22
// Generate Parentheses
// https://leetcode.com/problems/generate-parentheses/

// Given n pairs of parentheses, write a function to generate all combinations
// of well-formed parentheses.
// 
// For example, given n = 3, a solution set is:
// 
// "((()))", "(()())", "(())()", "()(())", "()()()"
import tester.Tester;

import java.util.*;

public class Solution {
	List<String> parens;
    public List<String> generateParenthesis(int n) {
        this.parens = new ArrayList<String>();
        if(n <= 0) {
        	return parens;
        }
        else {
        	this.generateParenthesisHelper("", 0, 0, n);
        	return parens;
        }
    }
    
    // GIVEN: pre represents part of a well-formed n pairs of parentheses 
    // String, left and right represents the number of left and right 
    // parentheses in pre
    /* WHERE: 1 <= n && pre.length() <= 2 * n
       			&& 0 <= right && right <= left && left <= n;   
     */    
    // EFFECT: add all Strings of well-formed n pairs of parentheses that begin 
    // with pre to this.parens
    // TERMINATION: the value of (n * 2 - pre.length()) becomes smaller
    public void generateParenthesisHelper(String pre, int left, int right, int n) {
    	if(pre.length() == n * 2) {
    		this.parens.add(pre);
    	}
    	else {
    		if(left == right) {
    			this.generateParenthesisHelper(pre + "(", left + 1, right, n);
    		}
    		else{
    			//@assert left > right;
    			if(left == n) {
    				this.parens.add(pre + this.generateString(')', left - right));
    			}
    			else {
    				//@assert right < left && left < n;
    				this.generateParenthesisHelper(pre + "(", left + 1, right, n);
    				this.generateParenthesisHelper(pre + ")", left, right + 1, n);
    			}
    		}
    	}
    }
    
    // RETURN: a String with only contains n c
    public String generateString(char c, int n) {
    	char[] buf = new char[n];
    	Arrays.fill(buf, c);
    	return new String(buf);
    }
}

// REPRESENTS:
class ListRelatedMethods<X> {
	// RETURNS: true iff l1 contains all elements in l2
	boolean contains(List<X> l1, List<X> l2) {
		for(int i = 0; i < l2.size(); i++) {
			if(!l1.contains(l2.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	// RETURNS: true iff l1 and l2 contains the same elements
	boolean equals(List<X> l1, List<X> l2) {
		return this.contains(l1, l2) && this.contains(l2, l1);
	}
	
	// RETURSN: a corresponding list of the given array
	List<X> arrayToList(X[] array) { 
		ArrayList<X> list = new ArrayList<X>();
		for(int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list;
	}
}

class SolutionExamples {
	Solution s = new Solution();
	ListRelatedMethods<String> listMethods = new ListRelatedMethods<String>();
	
	// RETURNS: true iff the set of Strings in loParens and arroParens are 
	// equal
	boolean compareGeneratedParen(List<String> loParens, String[] arroParens) {
		return 
			this.listMethods.equals(loParens, 
					this.listMethods.arrayToList(arroParens));
	}
	
	// tests for method generateParenthesis()
	boolean testGenerateParenthesis(Tester t) {
		System.out.println(s.generateParenthesis(2));
		return
		t.checkExpect(this.compareGeneratedParen(s.generateParenthesis(0), 
				new String[0])) &&
		t.checkExpect(this.compareGeneratedParen(s.generateParenthesis(1), 
				new String[]{"()"})) &&
		t.checkExpect(this.compareGeneratedParen(s.generateParenthesis(2), 
				new String[]{"()()", "(())"})) &&
		t.checkExpect(this.compareGeneratedParen(s.generateParenthesis(3), 
				new String[]{"((()))", "(()())", "(())()", 
					"()(())", "()()()"}), true);
	}
}