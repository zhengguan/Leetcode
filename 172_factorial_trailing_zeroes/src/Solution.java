// Leetcode 172	
// Factorial Trailing Zeroes
// https://leetcode.com/problems/factorial-trailing-zeroes/

/*
Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/

public class Solution {
    public int trailingZeroes(int n) {
        String s = Integer.toString(n, 5);
        int sum = 0;
        int count = 0;
        for(int i = 0; i < s.length() - 1; i++)
        //@loop_invariant count represents the amount of numbers 
        {
//        	String sub = s.substring(0, s.length() - 1 - i);
//        	sum += Integer.parseInt(sub, 5);
        	count = count * 5 + (s.charAt(i) - '0');
        	sum += count;
        }
        return sum;
    }
}