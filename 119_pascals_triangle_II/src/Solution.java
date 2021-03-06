// Leetcode 119
// Pascal's Triangle II
// https://leetcode.com/problems/pascals-triangle-ii/

// Given an index k, return the kth row of the Pascal's triangle.
// 
// For example, given k = 3,
// Return [1,3,3,1].
// 
// Note:
// Could you optimize your algorithm to use only O(k) extra space?

import tester.Tester;

import java.util.*;

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        Integer[] row = new Integer[rowIndex + 1];
        for(int i = 0; i < rowIndex + 1; i++) {
        	row[i] = 0;
        }
        row[0] = 1;
        for(int i = 0; i < rowIndex; i++) 
        //@loop_invariant 0 <= i && i <= rowIndex;
        //@loop_invariant row[0, i + 1) contains the ith row of Pascal's triangle
        {
        	for(int k = i + 1; k > 0; k--) 
        	//@loop_invariant 1 <= k && k < rowIndex + 1;
        	{
        		row[k] += row[k-1];
        	}
        }
        return Arrays.asList(row);
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	// RETURSN: a corresponding list of the given array
	List<Integer> arrayToList(int[] array) { 
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list;
	}
	
	// tests for method getRow() 
	boolean testGetRow(Tester t) {
		System.out.println(s.getRow(0));
		System.out.println(s.getRow(1));
		System.out.println(s.getRow(2));
		System.out.println(s.getRow(3));
		return
		t.checkExpect(s.getRow(0), this.arrayToList(new int[] {1})) &&
		t.checkExpect(s.getRow(1), this.arrayToList(new int[] {1, 1})) &&
		t.checkExpect(s.getRow(2), this.arrayToList(new int[] {1, 2, 1})) &&
		t.checkExpect(s.getRow(3), this.arrayToList(new int[] {1, 3, 3, 1}));
	}
}