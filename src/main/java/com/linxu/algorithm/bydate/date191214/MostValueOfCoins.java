package com.linxu.algorithm.bydate.date191214;

/**
 * @author Lx
 * @date 12月
 * @package com.linxu.algorithm.bydate.date191214
 * 动态规划之  币值最大化问题：给定N个硬币，排成一排，如果选择互不相邻的硬币，使得总额最大化。
 */
public class MostValueOfCoins {
    /**
     * 动态规划
     *
     * @param coins
     * @return
     */
    public static int mvc(int[] coins) {
        if (coins == null) {
            return 0;
        }
        if (coins.length == 1) {
            return coins[0];
        }
        int[] nCoins = new int[coins.length + 1];
        System.arraycopy(coins, 0, nCoins, 1, coins.length);
        //0 - n-1
        int[] dp = new int[coins.length + 1];
        dp[0] = 0;
        dp[1] = nCoins[1];
        for (int i = 2; i < nCoins.length; i++) {
            dp[i] = Math.max((nCoins[i] + dp[i - 2]), dp[i - 1]);
        }
        return dp[coins.length];
    }

    public static int mvcInrecursive(int[] coins) {
        if (coins == null) {
            return 0;
        }
        if (coins.length == 1) {
            return coins[0];
        }
        int max ;
        max = mvcInrecursive(coins, coins.length - 1);
        return max;
    }

    private static int mvcInrecursive(int[] coins, int i) {
        if (i < 0) {
            System.err.println("123");
        }
        if (i == 0) {
            return coins[0];
        }
        if (i == 1) {
            return coins[1];
        }
        return Math.max(mvcInrecursive(coins, i - 1), mvcInrecursive(coins, i - 2) + coins[i]);
    }

    public static void main(String[] args) {
        int[] coins = {
                1, 2, 2, 4, 1
        };
        System.out.println(mvcInrecursive(coins));
    }
}
