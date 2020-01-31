package com.linxu.algorithm.bydate.date200131;

import java.util.Arrays;

/**
 * @author linxu
 * @date 2020/1/31
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class CoinSolution {
    /**
     * 找零的动态规划解法
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    if (dp[i - coins[j]] != Integer.MAX_VALUE)
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1};
        System.out.println(new CoinSolution().coinChange(coins, 1));
    }
}
