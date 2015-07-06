// Leetcode 56
// Merge Intervals

// Given a collection of intervals, merge all overlapping intervals.

// For example,
// Given [1,3],[2,6],[8,10],[15,18],
// return [1,6],[8,10],[15,18].


/**
 * Definition for an interval.
 */
import tester.Tester;

import java.util.*;

class Interval {
    int start;
    int end;
    
    Interval() { start = 0; end = 0;}
    Interval(int s, int e) { start = s; end = e; }
}

 
public class Solution {
    // GIVEN: a list of intervals
    // RETURNS: a list of intervals after merging all overlap intervals in
    // the given list
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals.size() == 0) {
            return new ArrayList<Interval>();
        }
        List<Interval> intervalsCopy = new ArrayList<Interval>();
        intervalsCopy.addAll(intervals);         // Why need we sort the list??
        sortByStart(intervalsCopy);
        List<Interval> result = new ArrayList<Interval>();
        Interval curr = intervalsCopy.get(0);
        result.add(curr);
        for(int i = 1; i < intervalsCopy.size(); i++) {
            Interval tmp = intervalsCopy.get(i);
            if(curr.end >= tmp.start) {
                curr.end = Math.max(curr.end, tmp.end);
            }
            else {
                curr = tmp;
                result.add(curr);
            }
        }
        return result;
    }
    
    // GIVEN: a list of intervals
    // EFFECT: sort intervals in the list by their start
    void sortByStart(List<Interval> intervals) {
        Collections.sort(intervals,
            new Comparator<Interval>() {
                public int compare(Interval itrv1, Interval itrv2) {
                    return itrv1.start - itrv2.start;
                }
            });
    }
}

class SolutionExamples {
    Solution s = new Solution();
    
    boolean testMerge(Tester t) {
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(4, 6));
        intervals.add(new Interval(2, 4));
        List<Interval> result = new ArrayList<Interval>();
        result.add(new Interval(1, 6));
        return
        t.checkExpect(s.merge(intervals), result);
    }
}