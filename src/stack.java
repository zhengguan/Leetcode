import java.util.*;


class MinStack {
    int MAX = 1000;
    int[] arr = new int[MAX];
    int index=0; // index of next empty position
    int minIndex = 0;
    
    TreeMap<int, int> map = new TreeMap();
//    TreeSet treeSet = new TreeSet();
    
    public void push(int x) {
//        if(index >= this.arr.length) {
//            this.reallocArr();
//        }
//        this.arr[index++] = x;
    }
    
    void reallocArr() {
        int[] newArr = new int[this.arr.length + this.MAX];
        System.arraycopy(this.arr, 0, newArr, 0, this.arr.length);
        this.arr = newArr;
        return;
    }

    // DETAILS: this stack must be non-empty
    public void pop() {
        this.index--;
    }

    // DETAILS: this stack must be non-empty
    public int top() {
        return this.arr[index-1];
    }

    // DETAILS: this stack must be non-empty
    public int getMin() {
        int min = 0;
        for(int i = 0; i < index; i++) {
            if(arr[i] < arr[min]) {
                min = i;
            }
        }
        return arr[min];
    }
}


//public class Solution {
//    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        int len = m + n; //un-merged elements 
//        int i = m ; // un-merged elements in nums1[]
//        int j = n ; // un-merged elements in nums2[]
//        while(i >= 1 && j >= 1) 
//            //@loop_invariant 0 <= i && i <= m;
//            //@loop_invariant 0 <= j && j <= n;
//            //@loop_invariant 0 <= len && len <= m + n ; 
//            //@loop_invariant i + j == len;
//            /*@loop_invariant geSegs(nums1,len, m + n, nums1, 0, i)
//                              && geSegs(nums1, len, m + n, nums2, 0, j)
//                              && isSorted(nums1, len, m + n);
//            @*/
//        {
//            if(nums1[i-1] > nums2[j-1]) {
//                nums1[len-1] = nums1[i-1];
//                i--;
//            }
//            else {
//                nums1[len-1] = nums1[j-1];
//                j--;
//            }
//            len--;
//        }
//        //@assert i == 0 || j == 0;
//        while(j >= 1)
//        //@loop_invariant i + j == len;
//        //@loop_invariant 0 <= j && j <= n;
//        //@loop_invariant 0 <= len && len <= m + n ; 
//        //@loop_invariant i + j == len;
//        /*@loop_invariant geSegs(nums1,len, m + n, nums1, 0, i)
//                          && geSegs(nums1, len, m + n, nums2, 0, j)
//                          && isSorted(nums1, len, m + n);
//        @*/
//        {
//            nums1[len-1] = nums2[j-1];
//            j--;
//            len--;
//        }
//        //@assert j == 0 && len == i;
//        return;        
//    }
//}