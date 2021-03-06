// Leetcode 122
// Best Time to Buy and Sell Stock II
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

// Say you have an array for which the ith element is the price of a given stock 
// on day i.
// 
// Design an algorithm to find the maximum profit. You may complete as many 
// transactions as you like (ie, buy one and sell one share of the stock multiple
// times). However, you may not engage in multiple transactions at the same time 
// (ie, you must sell the stock before you buy again).

import tester.Tester;

public class Solution {
    public int maxProfit(int[] prices) {
    	if(prices.length == 0) {
    		return 0;
    	}
//        return this.maxProfitFrom(prices, 0);
    	return this.maxProfitIter(prices);
    }
    
    // GIVEN: an array whose ith element is the price of a given stock on day 
    // i and an int represents the start day
    // WHERE: 0 <= start && start <= prices.length; 
    // RETURNS: the maximum profit can get if start doing transaction from day
    // start
    public int maxProfitFrom(int[] prices, int start) {
    	if(prices.length - start < 2) {
    		return 0;
    	}
    	else {
    		int profit = 0;
    		int minPrice = prices[start];
    		for(int i = start + 1; i < prices.length; i++) 
    		//@loop_invariant minPrice equal to the minimum elements in prices[start, i)
    		/*@loop_invariant profit equal to the maximum profit can get if the
    			first sale has to be finished before the ith day @*/
    		//@loop_invariant 0 <= start && start < i;
    		{
    			profit = 
    					Math.max(profit, 
    							prices[i] - minPrice 
    							+ this.maxProfitFrom(prices, i + 1));
    			minPrice = Math.min(minPrice, prices[i]);
    		}
    		return profit;
    	}
    }
    
    // GIVEN: an array whose ith element is the price of a given stock on day 
    // i and an int represents the start day
    // WHERE: 0 <= start && start <= prices.length; 
    // RETURNS: the maximum profit can get if start doing transaction from day
    // start
    public int maxProfitIter(int[] prices) {
    	if(prices.length < 2) {
    		return 0;
    	}
    	else {
    		int[] profits = new int[prices.length];
    		int maxSumProfitSalePrice = 0;
    		for(int i = prices.length; i > 0; i--)
    		/*@loop_invariant 0 <= i;
    		/*@loop_invariant for k in [i, prices.length) profits[k] represents
    		    the maximum profit can get if start doing transaction in day k
    		  @*/
    		/*@loop_invariant maxSumProfitSalePrice is equal to the maximum 
    		   value of profits[k] + prices[k] with k in [i, prices.length)
    		 @*/
    		{
    			if(prices.length - (i - 1) < 2) {
    				profits[i - 1] = 0;
    			}
    			else {
    				//@assert 0 < i && i <= prices.length - 1;
    				profits[i - 1] = Math.max( profits[i],
    						Math.max(0, maxSumProfitSalePrice - prices[i - 1]));    				
    			}
    			maxSumProfitSalePrice = 
    					Math.max(maxSumProfitSalePrice, 
    							profits[i - 1] + prices[i - 1]);
    		} 		
    		return profits[0];
    	}
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

