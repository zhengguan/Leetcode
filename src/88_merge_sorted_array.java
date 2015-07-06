// leetcode 88 merge sorted array
import java.util.*;

import com.sun.rowset.internal.Row;

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr1 = new int[4];
        int[] arr2 = new int[1];
        arr1[0] = 1;
        arr2[0] = 2;
        s.merge(arr1, 1, arr2, 1);
        System.out.println(arr1[0] + " " + arr1[1]);
    }
    
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n; //un-merged elements 
        int i = m ; // un-merged elements in nums1[]
        int j = n ; // un-merged elements in nums2[]
        while(i >= 1 && j >= 1) 
            //@loop_invariant 0 <= i && i <= m;
            //@loop_invariant 0 <= j && j <= n;
            //@loop_invariant 0 <= len && len <= m + n ; 
            //@loop_invariant i + j == len;
            /*@loop_invariant geSegs(nums1,len, m + n, nums1, 0, i)
                              && geSegs(nums1, len, m + n, nums2, 0, j)
                              && isSorted(nums1, len, m + n);
            @*/
        {
            if(nums1[i-1] > nums2[j-1]) {
                nums1[len-1] = nums1[i-1];
                i--;
            }
            else {
                nums1[len-1] = nums2[j-1];
                j--;
            }
            len--;
        }
        //@assert i == 0 || j == 0;
        while(j >= 1)
        //@loop_invariant i + j == len;
        //@loop_invariant 0 <= j && j <= n;
        //@loop_invariant 0 <= len && len <= m + n ; 
        //@loop_invariant i + j == len;
        /*@loop_invariant geSegs(nums1,len, m + n, nums1, 0, i)
                          && geSegs(nums1, len, m + n, nums2, 0, j)
                          && isSorted(nums1, len, m + n);
        @*/
        {
            nums1[len-1] = nums2[j-1];
            j--;
            len--;
        }
        //@assert j == 0 && len == i;
        return;        
    }
    
}


