// Leetcode	87
// Scramble String
// https://leetcode.com/problems/scramble-string/
/*
Given a string s1, we may represent it as a binary tree by partitioning it 
to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a 
scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it 
produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled 
string of s1.
 */

import java.util.*;

public class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        } else if (s1 == null || s2 == null) {
            return false;
        } else if (s1.length() != s2.length()) {
            return false;
        } else {
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < s1.length(); i++) {
                sum1 += (int) s1.charAt(i);
                sum2 += (int) s2.charAt(i);
            }            
            return (sum1 == sum2) && isScrambleHelper2(s1, 0, s1.length(), s2, 0, s2.length());
        }
    }
    
    // GIVEN: two substring s1[start1, end1) and s2[start2, end2)
    /* WHERE: (end1 - start1 == end2 - start2) 
        && 0 <= start1 && start1 <= end1 && end1 <= s1.length
        && 0 <= start2 && start2 <= end2 && end2 <= s2.length
    */
    // RETURNS: true iff s1[start1,end1) is a scrambled string of s2[start2, end2)
    private boolean isScrambleHelper(String s1, int start1, int end1, String s2, int start2, int end2) {
        if (end1 - start1 == 0) {
            return true;
        } else if (end1 - start1 == 1) {
            return s1.charAt(start1) == s2.charAt(start2);
        } else {
            int len = end1 - start1;
            Set<Character> frontOfS1 = new HashSet<Character>();
            frontOfS1.add(s1.charAt(start1));
            Set<Character> frontOfS2 = new HashSet<Character>();
            frontOfS2.add(s2.charAt(start2));
            Set<Character> endOfS2 = new HashSet<Character>();
            endOfS2.add(s2.charAt(end2 - 1));
            for (int i = 1; i < len; i++) {
                if ((//frontOfS1.containsAll(frontOfS2) && frontOfS1.size() == frontOfS2.size()
                     frontOfS1.equals(frontOfS2)
                     && isScrambleHelper(s1, start1, start1 + i, s2, start2, start2 + i)
                     && isScrambleHelper(s1, start1 + i, end1, s2, start2 + i, end2))
                    || (//frontOfS1.containsAll(endOfS2) && frontOfS1.size() == endOfS2.size()
                        frontOfS1.equals(endOfS2)
                        && isScrambleHelper(s1, start1, start1 + i, s2, end2 - i, end2)
                        && isScrambleHelper(s1, start1 + i, end1, s2, start2, end2 - i)))
                    {
                        return true;
                    }
                frontOfS1.add(s1.charAt(start1 + i));
                frontOfS2.add(s2.charAt(start2 + i));
                endOfS2.add(s2.charAt(end2 - 1 - i));
            }
            return false;
        }
    }
    
    // GIVEN: two substring s1[start1, end1) and s2[start2, end2)
    /* WHERE: (end1 - start1 == end2 - start2) 
        && 0 <= start1 && start1 <= end1 && end1 <= s1.length
        && 0 <= start2 && start2 <= end2 && end2 <= s2.length
    */
    // RETURNS: true iff s1[start1,end1) is a scrambled string of s2[start2, end2)
    private boolean isScrambleHelper2(String s1, int start1, int end1, String s2, int start2, int end2) {
        if (end1 - start1 == 0) {
            return true;
        } else if (end1 - start1 == 1) {
            return s1.charAt(start1) == s2.charAt(start2);
        } else {
            int len = end1 - start1;
            int frontOfS1 = 0;
            frontOfS1 += (int) s1.charAt(start1);
            int frontOfS2 = 0;
            frontOfS2 += (int) s2.charAt(start2);
            int endOfS2 = 0;
            endOfS2 += (int) s2.charAt(end2 - 1);
            for (int i = 1; i < len; i++) {
                if ((//frontOfS1.containsAll(frontOfS2) && frontOfS1.size() == frontOfS2.size()
                     frontOfS1 == frontOfS2
                     && isScrambleHelper(s1, start1, start1 + i, s2, start2, start2 + i)
                     && isScrambleHelper(s1, start1 + i, end1, s2, start2 + i, end2))
                    || (//frontOfS1.containsAll(endOfS2) && frontOfS1.size() == endOfS2.size()
                        frontOfS1 == endOfS2
                        && isScrambleHelper(s1, start1, start1 + i, s2, end2 - i, end2)
                        && isScrambleHelper(s1, start1 + i, end1, s2, start2, end2 - i)))
                    {
                        return true;
                    }
                frontOfS1 += (int) s1.charAt(start1 + i);
                frontOfS2 += (int) s2.charAt(start2 + i);
                endOfS2 += (int) s2.charAt(end2 - 1 - i);
            }
            return false;
        }
    }    
}