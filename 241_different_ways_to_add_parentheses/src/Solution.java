// Leetcode 241	
// Different Ways to Add Parentheses
// https://leetcode.com/problems/different-ways-to-add-parentheses/

// Given a string of numbers and operators, return all possible results from 
// computing all the different possible ways to group numbers and operators. 
// The valid operators are +, - and *.
// 
// 
// Example 1
// Input: "2-1-1".
// 
// ((2-1)-1) = 0
// (2-(1-1)) = 2
// Output: [0, 2]
// 
// 
// Example 2
// Input: "2*3-4*5"
// 
// (2*(3-(4*5))) = -34
// ((2*3)-(4*5)) = -14
// ((2*(3-4))*5) = -10
// (2*((3-4)*5)) = -10
// (((2*3)-4)*5) = 10
// Output: [-34, -14, -10, -10, 10]

import java.util.*;


public class Solution {
	private List<Integer> result = new ArrayList<Integer>();
	
	/**
	 * 
	 * @param input 
	 * @return
	 */	
    public List<Integer> diffWaysToCompute(String input) {
        return diffValueInRange(input, 0, input.length());
    }
    
    /**
     * WHERE: 0 <= start && start < end && end < input.length();
     * @param input a String of an expression
     * @param start the start index of the sub-expression to be processed (inclusive)
     * @param end the end index of the sub-expression to be processed (exclusive)
     * @return a list of possible values of the sub-expression input[start, end)
     */
    private List<Integer> diffValueInRange(String input, int start, int end) {
    	if(containOperator(input, start, end)) {
    		List<Integer> result = new ArrayList<Integer>();
    		for(int i = start; i < end; i++) {
    			if(isOperator(input.charAt(i))) {
    				List<Integer> lvalues = diffValueInRange(input, start, i);
    				List<Integer> rvalues = diffValueInRange(input, i + 1, end);
    				result.addAll(valueOfAllCombination(input.charAt(i), lvalues, rvalues));
    			}
    		}
    		return result;
    	}
    	else {
    		return new ArrayList<Integer>(
    				Arrays.asList(
    						Integer.valueOf(
    								input.substring(start, end))));
    	}
    }
    
    /**
     * WHERE: 0 <= start && start < end && end < input.length(); 
     * @param input
     * @param start
     * @param end
     * @return true iff sub-string[start, end) contains operator
     */
    private boolean containOperator(String input, int start, int end) {
    	for(int i = start; i < end; i++) {
    		if(isOperator(input.charAt(i))) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * 
     * @param c
     * @return true iff c is one of '+', '-', '*'
     */
    private boolean isOperator(char c) {
    	return (c == '+')
    			|| (c == '-')
    			|| (c == '*');
    }
    
    /**
     * 
     * @param op is an operator('+', '-', '*')
     * @param lvalues a non-empty list of values 
     * @param rvalues a non-empty list of values
     * @return all possible result of apply op to one of lvalues and one of rvalues 
     */
    private List<Integer> valueOfAllCombination(char op, List<Integer> lvalues, 
    		List<Integer> rvalues) {
    	List<Integer> result = new ArrayList<Integer>();
    	for(Integer l: lvalues) {
    		for(Integer r: rvalues) {
    			result.add(apply(op, l, r));
    		}
    	}
    	return result;
    }
    
    /**
     * 
     * @param op is an operator('+', '-', '*')
     * @param l
     * @param r
     * @return the result of apply op to l and r
     */
    private Integer apply(char op, Integer l, Integer r) {
    	switch(op){
    		case '+': return l + r;
    		case '-': return l - r;
    		case '*': return l * r;
    	}    	
    	throw new RuntimeException("Illegal operator in apply()");
    }
}