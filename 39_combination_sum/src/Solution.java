// Leetcode 39	
// Combination Sum
// Given a set of candidate numbers (C) and a target number (T), find all 
// unique combinations in C where the candidate numbers sums to T.
// https://leetcode.com/problems/combination-sum/

//The same repeated number may be chosen from C unlimited number of times.
//
//Note:
//All numbers (including target) will be positive integers.
//Elements in a combination (a1, a2, �� , ak) must be in non-descending order. 
// (ie, a1 �� a2 �� �� �� ak).
// The solution set must not contain duplicate combinations.
// For example, given candidate set 2,3,6,7 and target 7, 
// A solution set is: 
// [7] 
// [2, 2, 3] 
import tester.Tester;

import java.awt.print.Printable;
import java.lang.reflect.Array;
import java.util.*;


public class Solution {
	ArrayList<List<Integer>> combinations;
	
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	Arrays.sort(candidates);
        ArrayList<Integer> prev = new ArrayList<Integer>();
        combinations = new ArrayList<List<Integer>>();
        combinationSumWithPrev(prev, candidates, 0, target);
        return combinations;
    }
    
    // GIVEN: a list of previously chosen numbers, an array of candidates,
    // an integer specify the available candidates starting position,
    // the target value after subtracting the previously chosen number
    // EFFECT: add each combinations to ArrayList combinations
    // TERMINATION: the value of candidates.length - start becomes smaller;
    public void combinationSumWithPrev(ArrayList<Integer> prev, int[] candidates, 
    		int start, int target) {
    	if(target == 0) {
    		ArrayList<Integer> combination = new ArrayList<Integer>();
    		combination.addAll(prev);
    		combinations.add(combination);
    	}
    	else if(start == candidates.length || target < candidates[start]) {
    		return;
    	}
    	else { 
    		//solutions without current candidate 
    		combinationSumWithPrev(prev, candidates, start + 1, target);
    		int currCandidate  =candidates[start];
    		int n = target / currCandidate;
    		for(int i = 0; i < n; i++)
    		//@loop_invariant sum of prev and target is unchanged;
    		{
    			prev.add(currCandidate);
    			target = target - currCandidate;
    			combinationSumWithPrev(prev, candidates, start + 1, target);
    		}
    		for(int i = 0; i < n; i++) {
    			prev.remove(prev.size()-1);
    		}
    	}
    }  
}

class SolutionExamples {
	Solution s = new Solution();
	
	boolean testCombinationSum(Tester t) {
		int[] candidates = new int[]{5, 2, 3, 6, 7};
//		List<Integer> combinations = 
//				new ArrayList<ArrayList<Integer>>()
//					.add(Arrays.asList(new Integer[]{1,1,1,1,5}));
		System.out.println(s.combinationSum(candidates, 7));
		System.out.println(s.combinationSum(candidates, 2));
		System.out.println(s.combinationSum(candidates, 0));
		return
		true;
	}
}