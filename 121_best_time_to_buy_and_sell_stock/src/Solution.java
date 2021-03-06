// Leetcode 121
// Best Time to Buy and Sell Stock
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

// Say you have an array for which the ith element is the price of a given stock 
// on day i.
// 
// If you were only permitted to complete at most one transaction (ie, buy one and
// sell one share of the stock), design an algorithm to find the maximum profit.
import tester.Tester;

public class Solution {
    public int maxProfit(int[] prices) {
    	if(prices.length == 0) {
    		return 0;
    	}
    	//@assert 1 <= prices.length;
    	int minPrice = prices[0];
    	int maxProfit = 0;
    	for(int i = 1; i < prices.length; i++) 
    	/*@loop_invariant minPrices is equal to the minimum element of 
    	   prices[0, i) @*/
    	//@loop_invariant 1 <= i;
    	/*@loop_invariant maxProfit represents the maximum profit can get if 
    		sold before the ith day @*/
    	{
    		if(prices[i] < minPrice) {
    			minPrice = prices[i];
    		}
    		maxProfit = Math.max(maxProfit, prices[i] - minPrice);
    	}
    	return maxProfit;
    }
}

class SolutionExamples {
	Solution s = new Solution();
	
	// tests for method maxProfit()
	boolean testMaxProfit(Tester t) {
		return
		t.checkExpect(s.maxProfit(new int[] {}), 0) &&
		t.checkExpect(s.maxProfit(new int[] {1, 3, 5, 4}), 4) &&
		t.checkExpect(s.maxProfit(new int[] {5, 3, 1, 4}), 3);		
	}
}