// Leetcode 115	
// Distinct Subsequences
// https://leetcode.com/problems/distinct-subsequences/

/*
Given a string S and a string T, count the number of distinct subsequences of T
in S.

A subsequence of a string is a new string which is formed from the original 
string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
 */

public class Solution {
    public int numDistinct(String s, String t) {
//        return helperRecur(s, 0, t, 0);
    	return helperIterOptimized(s, t);
    }
    
    // GIVEN: two Strings s, t, two ints s1, t1
    // WHERE: 0 <= s1 && s1 <= s.length() && 0 <= t1 && t1 <= t.length();
    // RETURNS: the number of distinct subsequence of t[t1, t.length())
    // in s[s1, s.length())
    // TERMINATION: the value of t1 becomes smaller
    private int helperRecur(String s, int s1, String t, int t1) {
    	if(t1 == t.length()) {
    		return 1;
    	}    	
    	else if (s1 == s.length()) {
    		return 0;
    	}
    	else {
    		int count = 0;    		
    		if(s.charAt(s1) == t.charAt(t1)) {
    			count += helperRecur(s, s1 + 1, t, t1 + 1);
    		}
    		count += helperRecur(s, s1 + 1, t, t1);
    		return count;
    	}
    }
    
    // GIVEN: two Strings s, t, two ints s1, t1
    // RETURNS: the number of distinct subsequence of t in s
    private int helperIter(String s, String t) {
    	int[][] counts = new int[s.length() + 1][t.length() + 1];
    	for(int i = 0; i < s.length() + 1; i++) {
    		counts[i][t.length()] = 1;
    	}
    	
    	for(int i = s.length() - 1; i >= 0; i--) 
    	//@loop_invariant -1 <= i && i <= s.length() - 1;
    	/*@loop_invariant (counts[k][l] == helperRecur(s, k, t, l))
    		k in [i + 1, s.length()), l in [0, t.length())
    	  @*/
    	{
    		for(int j = t.length() - 1; j >= 0; j--) 
    		//@loop_invariant -1 <= i && i <= t.length() - 1;
    		/*@loop_invariant (counts[i][l] = helperRecur(s, i, t, l)),
    		   l in [j + 1, t.length())
    		  @*/
    		{
    			if(s.charAt(i) == t.charAt(j)) {
    				counts[i][j] += counts[i + 1][j + 1];
    			}
    			counts[i][j] += counts[i + 1][j];
    		}
    	}
    	return counts[0][0];
    }
    
    // GIVEN: two Strings s, t, two ints s1, t1
    // RETURNS: the number of distinct subsequence of t in s
    private int helperIterOptimized(String s, String t) {
    	int[][] counts = new int[2][t.length() + 1];
    	for(int i = 0; i < 2; i++) {
    		counts[i][t.length()] = 1;
    	}
    	
    	for(int i = s.length() - 1; i >= 0; i--) 
    	//@loop_invariant -1 <= i && i <= s.length() - 1;
    	/*@loop_invariant (counts[(i+1) % 2][k] == helperRecur(s, i + 1, t, k))
    	   for k in [0, t.length)
    	 */
    	{
    		for(int j = t.length() - 1; j >= 0; j--) 
    		//@loop_invariant -1 <= i && i <= t.length() - 1;
    		/*@loop_invariant (counts[i % 2][l] == helperRecur(s, i, t, l)),
    		   l in [j + 1, t.length())
    		  @*/
    		{
    			counts[i % 2][j] = 0;
    			if(s.charAt(i) == t.charAt(j)) {
    				counts[i % 2][j] += counts[(i + 1) % 2][j + 1];
    			}
    			counts[i % 2][j] += counts[(i + 1) % 2][j];
    		}
    	}
    	return counts[0][0];    	
    }
}