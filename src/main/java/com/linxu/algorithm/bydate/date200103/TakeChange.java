package com.linxu.algorithm.bydate.date200103;

import java.util.Arrays;

/**
 * @author Lx
 * @date 12月
 * @package com.linxu.algorithm.bydate.date191215
 * 找零问题：
 * 1、贪心找零
 * 2、动态规划找0
 */
public class TakeChange {
    /**
     * 假设货币面额有1,2,5,10,20,50,100，每种数量都无限多，现在给出金额n(1<=n<=100000)，求出最少的货币数量。
     * 这种题可以用贪心来做，因为前n-1项的和都不超过第n项的值，所以符合贪心的规则。
     * 贪心：每次都拿最大的，直到不能拿  就拿第二大的。
     * 前提：因为前n-1项的和都不超过第n项的值。否则会出现当前选择会影响后面的选择的问题，不适合用贪心。
     */
    public static int greedy(int[] coins, int target) {
        //lack illegal adjust
        int nums = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            //if target < coins[i]  nums+=0; target=target
            nums += target / coins[i];
            target %= coins[i];
        }
        return nums;
    }

    /**
     * 假设货币面额有1,2,4,5,10，每种数量都无限多，现在给出金额n(1<=n<=100000)，求出最少的货币数量。
     * 此时则不可以用贪心算法，而是应该用动态规划。因为此时前n-1项之和不再小于第n项的值，所以贪心不再成立。
     * 例如：8元如果用贪心查找，每次都选最大的，那我们找不到解，而实际上可以用两张4元就可以解决。
     */
    public static int dp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    //这个是达到A的关键，如果原本是INF，那么相加之后会是负数，则不能代入
                    if (dp[i - coins[j]] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 10};
        System.out.println(greedy(arr, 14));
    }
}
