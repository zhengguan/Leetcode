// Leetcode 217
// Contains Duplicate
// https://leetcode.com/problems/contains-duplicate/


// Given an array of integers, find if the array contains any duplicates. Your 
// function should return true if any value appears at least twice in the array,
// and it should return false if every element is distinct.

import tester.Tester;

public class Solution {
    public boolean containsDuplicate(int[] nums) {
        this.sort(nums, 0, nums.length);
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i+1]) {
                return true;
            }
        }
        return false;
    }
    
    // GIVEN: a array of ints and two indexes
    // WHERE: 0 <= start <= end <= nums.length
    // EFFECT: array nums[start, end) is sorted
    // TERMINATION: the value of end - start becomes smaller
    void sort(int[] nums, int start, int end) {
        if(end - start == 0) {
            return;
        }
        // start == end
        
        int tmp = partition(nums, start, end);
        //@assert start <= tmp < end;
        sort(nums, start, tmp);
        sort(nums, tmp + 1, end);        
    }
 
    // GIVEN: a array of ints and two indexes
    // WHERE: 0 <= start < end <= nums.length
    // EFFECT: do partition on elements [start, end), get [start, pos), [pos, end)
    // such that nums[start, pos) <= nums[pos] < nums[pos, end)
    // RETURNS: pos 
    // ENSURES: pos belongs to [start, end), nums[start, pos) <= nums[pos], 
    // nums[pos, end) > nums[pos];
    int partition(int[] nums, int start, int end) {
        int mid = start + (end - start) / 2;
        swap(nums, start, mid);
        int i = start + 1;
        int j = end;
        while(i < j)
        //@loop_invariant start < i <= j <=end;
        //@loop_invariant nums[j:end) > nums[start];
        //@loop_invariant nums[start:i) <= nums[start];
        {
            if(nums[i] > nums[start]) {
                swap(nums, i, j - 1);
                j--;
            }
            else {
                i++;
            }
        }
        //@assert (j == i) && (nums[start:j) <= nums[j:end));
        swap(nums, start, j - 1);
        return j - 1;
    }
        
    
    // EFFECT: swap the elements at index i and j
    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

class SolutionExamples{
    Solution s = new Solution();
    
    // tests for method partition
    boolean testPartition(Tester t) {
        return
        t.checkExpect(s.partition(new int[]{1, 3, 2}, 0, 3), 2) &&
        t.checkExpect(s.partition(new int[]{1, 3}, 0, 2), 1) &&
        t.checkExpect(s.partition(new int[]{1}, 0, 1), 0) &&
        t.checkExpect(s.partition(new int[]{1, 3, 2, 4}, 0, 4), 1);
    }

    // RETURNS: true iff nums is sorted by non-decreasing order
    boolean isSorted(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] > nums[i+1]) {
                return false;
            }                
        }
        return true;
    }    
    
    // tests for method sort
    boolean testSort(Tester t) {
        int[] nums1 = new int[] {1};
        int[] nums2 = new int[] {2, 1};
        int[] nums3 = new int[] {2, 1, 3};
        int[] nums4 = new int[] {2, 1, 2, 3};
        s.sort(nums1, 0, 1);
        s.sort(nums2, 0, 2);
        s.sort(nums3, 0, 3);
        s.sort(nums4, 0, 4);
        return
        t.checkExpect(isSorted(nums1)) &&
        t.checkExpect(isSorted(nums2)) &&
        t.checkExpect(isSorted(nums3)) &&
        t.checkExpect(isSorted(nums4));
    }
    
    
    boolean testContainsDuplicate(Tester t) {
        return
        t.checkExpect(s.containsDuplicate(new int[]{1}), false) &&
        t.checkExpect(s.containsDuplicate(new int[]{1, 2}), false) &&
        t.checkExpect(s.containsDuplicate(new int[]{1, 2, 4, 2}), true);
    }
}