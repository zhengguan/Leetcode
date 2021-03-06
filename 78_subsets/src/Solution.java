// Leetcode 78
// Subsets
// https://leetcode.com/problems/subsets/

// Given a set of distinct integers, nums, return all possible subsets.
// 
// Note:
// Elements in a subset must be in non-descending order.
// The solution set must not contain duplicate subsets.
// For example,
// If nums = [1,2,3], a solution is:
// 
// [
//   [3],
//   [1],
//   [2],
//   [1,2,3],
//   [1,3],
//   [2,3],
//   [1,2],
//   []
// ]
import tester.Tester;

import java.util.*;

//public class Solution {
//	List<List<Integer>> allSubSets;
//	
//    public List<List<Integer>> subsets(int[] nums) {
//    	Arrays.sort(nums, 0, nums.length);
//    	allSubSets = new ArrayList<List<Integer>>();
//    	ArrayList<Integer> subsetPrevPart = new ArrayList<Integer>();
//        subsetsHelper(subsetPrevPart, nums, 0);
//        return allSubSets;
//    }
//    
//    // EFFECT: sort nums[start:end) in place
//    public void sort(int[] nums, int start, int end) {
//    	if(start >= end) {
//    		return;
//    	}
//    	else {
//    		int pos = partition(nums, start, end);
//    		sort(nums, start, pos);
//    		sort(nums, pos + 1, end);
//    	}
//    }
//    
//    // GIVEN: a sub-array of numbers nums, nums[start,end)
//    // WHERE: start < end
//    // EFFECT: seperate nums[start,end) in place and put nums[start] in its 
//    // final place, such that elements on the left of nums[start] not greater 
//    // than it, elements on the right of nums[start] greater than it.
//    // RETURNS: the position of nums[start] 
//    public int partition(int[] nums, int start, int end) {
//    	int mid = start + (end - start) / 2;
//    	swap(nums, start, mid);
//    	int i = start + 1, j = end;
//    	while(i < j) 
//    	//@loop_invariant start < i <= j <= end;
//    	//@loop_invariant nums[start, i) <= nums[start];
//    	//@loop_invariant nums[j,end) > nums[start];
//    	{
//    		if(nums[i] <= nums[start]) {
//    			i++;
//    		}
//    		else {
//    			swap(nums, i, j - 1);
//    			j--;
//    		}
//    	}
//    	//@assert i == j;
//    	swap(nums, start, i - 1);
//    	return i - 1;
//    }
//    
//    // EFFECT: swap elements nums[i] with nums[j]
//    public void swap(int[] nums, int i, int j) {
//    	int tmp = nums[i];
//    	nums[i] = nums[j];
//    	nums[j] = tmp;
//    }
//    
//    // GIVEN: a list of numbers subsetPrevPart represents a subset of 
//    // nums[0:start) which means the numbers in nums with indexs belong 
//    // to [0: start)
//    // EFFECT: add all subsets of nums that contain subsetPrevPart to allSubSets
//    // TERMINATION: the value of nums.length - start becomes smaller
//    public void subsetsHelper(ArrayList<Integer> subsetPrevPart, int[] nums, int start) {
//    	if(start == nums.length) {
//    		ArrayList<Integer> subset = new ArrayList<Integer>();
//    		subset.addAll(subsetPrevPart);
//    		allSubSets.add(subset);
//    	}
//    	else {
//    		subsetsHelper(subsetPrevPart, nums, start + 1);
//    		subsetPrevPart.add(nums[start]);
//    		subsetsHelper(subsetPrevPart, nums, start + 1);
//    		subsetPrevPart.remove(subsetPrevPart.size() - 1);
//    	}
//    }   
//}



//public class Solution{
//	public List<List<Integer>> subsets(int[] nums) {
//		ArrayList<ArrayList<Integer>> result = 
//				new ArrayList<ArrayList<Integer>>();
//		result.add(new ArrayList<Integer>());
//		Arrays.sort(nums);
//		for(int i = 0; i < nums.length; i++) {
//			ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
//			tmp.addAll(result);
//			for(ArrayList<Integer> loi:tmp) {
//				loi.add(nums[i]);
//			}
//			result.addAll(tmp);
//		}		
//		return result;
//	}
//}

public class Solution{
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = 
				new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());
		Arrays.sort(nums);
		for(int i = 0; i < nums.length; i++) {
			List<List<Integer>> tmp = copyListOfList(result);			
			addToEachListOfList(tmp, nums[i]);
			result.addAll(tmp);
		}		
		return result;
	}
	
	// RETURNS: a deep copy of the loloi
	public List<List<Integer>> copyListOfList(List<List<Integer>> loloi) {
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		for(List<Integer> loi: loloi) {
			List<Integer> tmp = new ArrayList<Integer>();
			tmp.addAll(loi);
			result.add(tmp);
		}
		return result;
	}

	// EFFECT: add n to each list of loloi
	public void addToEachListOfList(List<List<Integer>> loloi, int n) {
		for(List<Integer> loi:loloi) {
			loi.add(n);
		}
		return;
	}
}

class SolutionExamples {
	Solution s = new Solution();
	// tests for method subsets()
	boolean testSubsets(Tester t) {
		System.out.println(s.subsets(new int[]{4, 1, 0}));
		return
		true;
	}
}