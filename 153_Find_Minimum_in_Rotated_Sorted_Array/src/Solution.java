// Leetcode 153
// Find Minimum in Rotated Sorted Array

// Suppose a sorted array is rotated at some pivot unknown to you beforehand.
// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
// Find the minimum element.
// You may assume no duplicate exists in the array.
import tester.Tester;


public class Solution {
    // GIVEN: a rotated sorted array
    // RETURNS: the minimum element of the array
    public int findMin(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        
        return binFindMinIter(nums, 0, nums.length);
    }
    
    // GIVEN: a rotated sorted array, and a range[start, end)
    // WHERE: 0 <= start < end <= nums.length && nums.length > 1;
    // RETURNS: the minimum elements of the the array with index in the given 
    // range
    // TERMINATION: the value of end - start becomes smaller.
    int binFindMinIter(int[] nums, int start, int end) {        
        while(true) {
            if(end - start == 1) {
                return nums[start];
            }
            //@assert end - start >= 2;
            int mid = start + (end - start) / 2;
            //@assert start < mid && mid < end;
            if(nums[mid] < nums[mid-1]) {
                return nums[mid];
            }
            if(nums[mid] < nums[start]) {
                end = mid;
            }
            else {
                //@assert nums[mid] > nums[start];
                if(nums[mid] <= nums[end-1]) {
                    //@assert nums[start,mid) is sorted
                    return nums[start];
                }
                else {
                    //@assert nums[mid] > nums[end-1];
                    start = mid;
                }
            }
        }
    }

    // GIVEN: a rotated sorted array, and a range[start, end)
    // WHERE: 0 <= start < end <= nums.length && nums.length > 1;
    // RETURNS: the minimum elements of the the array with index in the given 
    // range
    // TERMINATION: the value of end - start becomes smaller.
    int binFindMin(int[] nums, int start, int end) {
        if(end - start == 1) {
            return nums[start];
        }
        //@assert end - start >= 2;
        int mid = start + (end - start) / 2;
        //@assert start < mid && mid < end;
        if(nums[mid] < nums[mid-1]) {
            return nums[mid];
        }
        if(nums[mid] < nums[start]) {
            return binFindMin(nums, start, mid);
        }
        else {
            //@assert nums[mid] > nums[start];
            if(nums[mid] <= nums[end-1]) {
                //@assert nums[start,mid) is sorted
                return nums[start];
            }
            else {
                //@assert nums[mid] > nums[end-1];
                return binFindMin(nums, mid, end);
            }
        }        
    }
    
    public int linearFindMin(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < nums[(i+nums.length-1) % nums.length]) {
                return nums[i];
            }
        }
        return nums[0];
    }
}

class SolutionExamples{
    Solution s = new Solution();
    
    // tests for method findMin()
    boolean testFindMin(Tester t) {
        int size = 100000000;
        int[] nums = new int[size];
        for(int i = 0; i < size; i++) {
            nums[i] = (i + size / 2) % size;
        }
        long startTime=System.currentTimeMillis(); 
        s.binFindMin(nums, 0, size);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        
        startTime=System.currentTimeMillis();
//        s.linearFindMin(nums);
        s.binFindMinIter(nums, 0, size);
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        
        return
        t.checkExpect(s.findMin(new int[]{1}), 1) &&
        t.checkExpect(s.findMin(new int[]{2,1}), 1) &&
        t.checkExpect(s.findMin(new int[]{3,1,2}), 1);
    }
}