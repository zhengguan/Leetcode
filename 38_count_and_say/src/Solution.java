// Leetcode 38
// Count and Say
// https://leetcode.com/problems/count-and-say/

//The count-and-say sequence is the sequence of integers beginning as follows:
//1, 11, 21, 1211, 111221, ...
//
//1 is read off as "one 1" or 11.
//11 is read off as "two 1s" or 21.
//21 is read off as "one 2, then one 1" or 1211.
//Given an integer n, generate the nth sequence.
//
//Note: The sequence of integers will be represented as a string.

import tester.Tester;

import java.util.*;

public class Solution {
    public String countAndSay(int n) {
        ArrayList<Character> sequence = new ArrayList<Character>();
        sequence.add('1');
        for(int i = 1; i < n; i++) 
        //@loop_invariant sequence represents the ith count and say
        {
        	sequence = nextCountAndSay(sequence);
        }
        return this.listToString(sequence);
    }
    
    // GIVEN: an ArrayList sequence represents a sequence of count and say
    // WHERE: sequence is non-empty
    // RETURNS: the next sequence
    ArrayList<Character> nextCountAndSay(ArrayList<Character> sequence) {
    	ArrayList<Character> next = new ArrayList<Character>();
    	int count = 0;
    	Character c = sequence.get(0);
    	for(int i = 0; i < sequence.size(); i++) 
    	//@loop_invariant count represents the length of the longest 
    	// consecutive sequence s in sequence[0, i), all Characters in s
    	// equal to c and s contains the last element of sequence[0, i) if 
    	// exists
    	{
    		if(count == 0) {
    			count = 1;
    			c = sequence.get(i);
    		}
    		else {
    			if(c == sequence.get(i)) {
    				count++;
    			}
    			else {
    				this.addCount(next, count);
    				this.addSay(next, c);
    				count = 1;
    				c = sequence.get(i);
    			}
    		}    		
    	}
    	this.addCount(next, count);
    	this.addSay(next, c);
    	return next;
    }
    
    // EFFECT: add all Characters of n to the given sequence
    public void addCount(ArrayList<Character> sequence, int n) {
    	String s = Integer.toString(n);
    	for(int i = 0; i < s.length(); i++) {
    		sequence.add(s.charAt(i));
    	}
    	return;
    }
    
    // EFFECT: add the given digit c into sequence
    public void addSay(ArrayList<Character> sequence, Character c) {
    	sequence.add(c);
    	return;
    }
    
    // RETURNS: a String representation of the given sequence 
    public String listToString(ArrayList<Character> sequence) {
    	char[] cs = new char[sequence.size()];
    	for(int i = 0; i < cs.length; i++) {
    		cs[i] = sequence.get(i);
    	}
    	return new String(cs);
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	// tests for method countAndSay()
	boolean testCountAndSay(Tester t) {
		return
		t.checkExpect(s.countAndSay(1), "1") &&
		t.checkExpect(s.countAndSay(3), "21") &&
		t.checkExpect(s.countAndSay(4), "1211");
	}
}