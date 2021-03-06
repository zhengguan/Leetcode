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
    	return this.maxProfitIter(2, prices);
    }
    
    public int maxProfitRecur(int k, int[] prices) {
    	return this.maxProfitFrom(0, k, prices);    	
    }
    
    // WHERE: 0 <= start
    // RETURNS: the maximum profit can get from day start to the last day with
    // at most k transaction
    // TERMINATION: the value of k and (prices.length - start) become smaller
    public int maxProfitFrom(int start, int k, int[] prices) {
    	if(k <= 0) {
    		return 0;
    	}
    	if(prices.length - start < 2) {
    		return 0;
    	}
    	else {
    		//@assert 0 <= start && start <= prices.length - 2;
    		int profit = 0;
    		int minPrice = prices[start];
    		for(int i = start + 1; i < prices.length; i++) 
    		//@loop_invariant start < i && i <= prices.length;
    		/*@loop_invariant minPrices equal to the minimum element in 
    			prices[start, i) @*/
    		/*@loop_invariant profit represents the maximum profit can get 
    		   if the first transaction is finished before the ith day
    		 @*/
    		{
    			profit = Math.max(profit, 
    					prices[i] - minPrice 
    					+ this.maxProfitFrom(i + 1, k - 1, prices));
    			minPrice = Math.min(minPrice, prices[i]);
    		}
    		return profit;
    	}
    }
    
    // RETURNS: the maximum profit can get if can perform at most k 
    // transactions 
    public int maxProfitIter(int k, int[] prices) {
    	if(k <= 0 || prices.length < 2) {
    		return 0;
    	}
    	else {
    		int[][] profits = new int[k + 1][prices.length];
    		for(int i = 1; i < k + 1; i++)
    		//@loop_invariant 1 <= i && i <= k + 1;
    		/*@loop_invariant profits[t][d] represents the max Profit can get
    		   from day d in t transactions	
    		   (t in [0, i), d in [0, prices.length)) @*/
    		{
    			int maxSumProfitSale = 0;
    			for(int j = prices.length; j > 0; j--)
    			//@loop_invariant 0 <= j && j <= prices.length;
    			/*@loop_invariant profits[i][d] represents the max Profit can
    			   get from day d in i transactions (for d in [j, prices.length))
    			 @*/
    			/*@loop_invariant maxSumProfitSale represents the maximum value
    			   of (profits[i - 1][d] + prices[d]) for d in [j, prices.length)
    			 @*/ 
    			{
    				if(prices.length - (j - 1) < 2) {
    					// one day buy, one day sale
    					profits[i][j -1] = 0;
    				}
    				else {
    					//@assert 0 < j && j < prices.length - 1;
    					profits[i][j - 1] = 
    							Math.max(
    									Math.max(profits[i - 1][j - 1], 
    											maxSumProfitSale - prices[j - 1]),
    									profits[i][j]);
    				}
					maxSumProfitSale = Math.max(maxSumProfitSale,
							profits[i - 1][j - 1] + prices[j - 1]);
    			}
    		}
    		return profits[k][0];
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

