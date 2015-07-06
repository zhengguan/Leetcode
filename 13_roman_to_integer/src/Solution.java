import tester.Tester;

// Leetcode 13
// Roman to Integer
// Given a roman numeral, convert it to an integer.
//
// Input is guaranteed to be within the range from 1 to 3999.

// https://leetcode.com/problems/roman-to-integer/

public class Solution {
    public int romanToInt(String s) {
        int pre = 10000; // max value
        int sum = 0;
        int curr = 0;
        for(int i = 0; i < s.length(); i++) {
            curr = this.romanCharToInt(s.charAt(i));
            if(curr > pre) {
                sum = sum - 2 * pre + curr;
            }
            else {
                sum = sum + curr;
            }
            pre = curr;
        }
        return sum;
    }
    
    String romanChars = new String("IVXLCDM");
    int[] romanInts = new int[]{1, 5, 10, 50, 100, 500, 1000};
    
    public int romanCharToInt(char c) {
        for(int i = 0; i < romanChars.length(); i++) {
            if(romanChars.charAt(i) == c) {
                return romanInts[i];
            }
        }
        return -1;
    }
    
    
}

class SolutionExamples{
    Solution s = new Solution();

    // tests for method romanCharToInt()
    boolean testRomanCharToInt(Tester t) {
        return 
        t.checkExpect(s.romanCharToInt('I'), 1) &&
        t.checkExpect(s.romanCharToInt('M'), 1000);
    }
    
    boolean testRomanToInt(Tester t) {
        return
        t.checkExpect(s.romanToInt("I"), 1) &&
        t.checkExpect(s.romanToInt("IX"), 9) &&
        t.checkExpect(s.romanToInt("DM"), 500);
    }
}

