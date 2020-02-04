package com.linxu.algorithm.hot100.dp;

/**
 * @author linxu
 * @date 2020/2/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class SharePriceSimple {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], - prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}
