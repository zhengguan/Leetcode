// Leetcode 134

// Gas Station
// https://leetcode.com/problems/gas-station/
import tester.Tester;

//public 
class Solution {
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int len = gas.length;
        if(len == 1) {
            return gas[0] >= cost[0]?0:-1;
        }
        int[] left = new int[len];
        for(int i = 0; i < len; i++) {
            // if left[i] >= 0, then it is possible to arrive station 
            // (i + 1) % len from  station i
            left[i] = gas[i] -cost[i];
        }
        
        int totalLeft = 0;
        int start = 0, i;
        for(i = 0; i < len; i++) 
        /*@loop_invariant isCompleteCircuit(gas, cost, j) == false, 
                          for 0 <= j < i; @*/
        /*@loop_invariant isCompleteRange(gas, cost, start, i % len) == true;
        /*@loop_invariant totalLeft is the amount of gas left from station start
           to station i(and hasn't been refueled yet)
         */
        {
            totalLeft = totalLeft + left[i];
            if(totalLeft < 0) {
                totalLeft = 0;
                start = i + 1;
            }
        }
        if(start == len) {
            return -1;
        }
        else {
            for(i = 0; i <= start; i++) 
            //@loop_invariant isCompleteRange(gas, cost, start, i)
            {
                totalLeft = totalLeft + left[i];
                if(totalLeft < 0) {
                    return -1;
                }
            }
            return start;
        }
    }
    

    // GIVEN: a rotated sorted array
    // RETURNS: the minimum element of the array
    public int findMin(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        
        return binFindMin(nums, 0, nums.length);
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
    
    
    boolean isCompleteCircuit(int[] gas, int[] cost, int station) {
        int totalLeft = 0; // gas left
        int len = gas.length;
        for(int i = 0; i < len; i++) {
            totalLeft = totalLeft + gas[(station + i) % len] 
                    - cost[(station + i) % len];
            if(totalLeft < 0) {
                return false;
            }
        }
        return true;
    }
    
    // GIVEN: the information gas station and cost of a circuit route,
    // two stations start, end where 0 <= start, end < gas.length
    // RETURNS: true iff it is possible to arrive station end from station 
    // start 
    boolean isCompleteRange(int[] gas, int[] cost, int start, int end) {
       int len = gas.length;
       int gasLeft = 0;
       for(int i = start; i < end; i = (i + 1) % len) 
       //@loop_invariant it is possible to arrive station i from station start
       {
           gasLeft = gasLeft + gas[i] - cost[i];
           if(gasLeft < 0) {
               return false;
           }
       }
       return true;       
    }
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] left = new int[gas.length];
        for(int i = 0; i < gas.length; i++) {
            left[i] = gas[i] - cost[i];
        }
        
        int sum = 0;
        int start = 0;
        for(int i = 0; i < left.length; i++) {
            sum = sum + left[i];
            if(sum < 0) {
                start = (i + 1) % left.length;                
            }            
        }
        if(sum < 0) {
            return -1;
        }
        else {
            return start;
        }
    }
    
    public int canCompleteCircuitBruteForce(int[] gas, int[] cost) {
        if(gas.length == 1) {
            return 0;
        }
        
        int[] left = new int[gas.length];
        for(int i = 0; i < gas.length; i++) {
            left[i] = gas[i] - cost[i]; 
        }
        
        for(int i = 0; i < gas.length; i++) {
            int leftNow = 0;
            int k;
            for(k = 0; k < left.length; k++) 
            //@loop_invariant it is possible to reach station (i+k)%left.length from i;
            {
               leftNow = leftNow + left[(i+k)%left.length];
               if(leftNow < 0) {
                   break;
               }
            }
            
            if(k == left.length) {
                return i;
            }
        }
        return -1;
    }
}

class SolutionExamples {

    static void test(int[] gas, int[] cost) {
        Solution s = new Solution();
        System.out.println(s.canCompleteCircuit(gas, cost));
    }
//    public static void main(String[] args) {
//        int[] gas = {1, 2, 3};
//        int[] cost = {1, 2, 4};
//
//          int[] gas = {1};
//          int[] cost = {2};
//
//        int[] gas = new int[] {1, 2};
//        int[] cost = {1, 2};
//        
//        test(gas, cost);
//    }
    
    Solution s = new Solution();
    
    boolean testCanCompleteCircuits(Tester t) {
        return
        t.checkExpect(s.canCompleteCircuit(new int[] {1, 2}, new int[] {1, 2}),
                      0) &&
        t.checkExpect(
                s.canCompleteCircuit(new int[] {1, 2, 3}, new int[] {1, 2, 4}),
                -1) &&
        t.checkExpect(
                s.canCompleteCircuit(new int[] {2, 4, 1}, new int[] {3, 2, 2}),
                1);
    }
    
    boolean testCanCompleteCircuitBruteForce(Tester t) {
        return
        t.checkExpect(s.canCompleteCircuitBruteForce(new int[] {1}, new int[] {2}),
                      0) &&
        t.checkExpect(s.canCompleteCircuitBruteForce(new int[] {1,2}, new int[] {2,3}),
                      -1) &&
        t.checkExpect(s.canCompleteCircuitBruteForce(new int[] {1, 2}, new int[] {1, 2}),
                      0) &&
        t.checkExpect(
                s.canCompleteCircuitBruteForce(new int[] {1, 2, 3}, new int[] {1, 2, 4}),
                -1) &&
        t.checkExpect(
                s.canCompleteCircuitBruteForce(new int[] {2, 4, 1}, new int[] {3, 2, 2}),
                1);
    }
    
    boolean testCanCompleteCircuit2(Tester t) {
        return
        t.checkExpect(s.canCompleteCircuit2(new int[] {1}, new int[] {5}),
                      -1) &&
        t.checkExpect(s.canCompleteCircuit2(new int[] {1,2}, new int[] {1,3}),
                        -1) &&
        t.checkExpect(s.canCompleteCircuit2(new int[] {1, 2}, new int[] {1, 2}),
                      0) &&
        t.checkExpect(s.canCompleteCircuit2(new int[] {1, 5}, new int[] {2, 4}),
                      1) &&                      
        t.checkExpect(
                s.canCompleteCircuit2(new int[] {1, 2, 5}, new int[] {2, 3, 1}),
                2) &&                      
        t.checkExpect(
                s.canCompleteCircuit2(new int[] {1, 3, 5}, new int[] {6, 2, 4}),
                -1) &&
        t.checkExpect(
                s.canCompleteCircuit2(new int[] {2, 4, 1}, new int[] {3, 2, 2}),
                 1);
    }
    
    
}