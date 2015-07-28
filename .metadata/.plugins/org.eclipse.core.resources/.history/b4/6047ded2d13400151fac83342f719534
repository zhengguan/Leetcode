// Leetcode 123
// Best Time to Buy and Sell Stock III
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

// Say you have an array for which the ith element is the price of a given 
// stock on day i.

// Design an algorithm to find the maximum profit. You may complete at most 
// two transactions.

import tester.Tester;

public class Solution {
    public int maxProfit(int[] prices) {
    	if(prices.length == 0) {
    		return 0;
    	}
        return this.maxProfitFrom(prices, 0);
    }
    

}


class SolutionExamples {
	Solution s = new Solution();
	
	// tests for method maxProfit()
	boolean testMaxProfit(Tester t) {
		return
		t.checkExpect(s.maxProfit(new int[]{1, 2}), 1) &&
		t.checkExpect(s.maxProfit(new int[]{2, 1}), 0) &&
		t.checkExpect(s.maxProfit(new int[]{2, 1, 3}), 2) &&
		t.checkExpect(s.maxProfit(new int[]{2, 1, 2, 4}), 3) &&
		t.checkExpect(s.maxProfit(new int[]{1, 2, 1, 2}), 2) &&
		t.checkExpect(s.maxProfit(new int[]{1, 2, 5, 4}), 4);
	}
}

