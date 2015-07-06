// Leetcode 137
// Single Number
// https://leetcode.com/problems/single-number-ii/

//Given an array of integers, every element appears three times except for one. Find that single one.
//
//Note:
//Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

import tester.Tester; 

import java.util.*;

import com.sun.org.apache.bcel.internal.generic.SWAP;


//public 
class Solution {
    public int singleNumberBruteForce(int[] nums) {
        int end = nums.length;
        for(int i = 0; i < end - 1; i++) {
            int j;
            for(j = i+1; j < end; j++) {
                if(nums[i] == nums[j]) {
                    break;
                }
            }
            if(j == end) {
                return nums[i];
            }
        }
        // This sentence will never be executed
        return 0;
    }
}

class SolutionExamples {
    Solution s = new Solution();
    
    boolean testSingleNumber(Tester t) {
        return
        t.checkExpect(s.singleNumberBruteForce(new int[]{1, 1, 2, 1}), 2) &&
        t.checkExpect(s.singleNumberBruteForce(new int[]{1, 1, 3, 2, 1, 3, 3}), 2);
    }
}

