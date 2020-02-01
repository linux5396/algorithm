package com.linxu.algorithm.hot100.gupiao;

/**
 * @author linxu
 * @date 2020/1/31
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class BestTimeToBuyAndSellWithCooldown {
    /**
     * 最简单的股票问题：
     * 只能购买一次，即K=1
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][2];
        //spent O(N*2) mem.
        for (int i = 0; i < n; i++) {
            //base case.
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * 最简单的股票问题的空间优化：
     * 只能购买一次，即K=1
     */
    public static int maxProfitOptimize(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int dp_0 = 0, dp_1 = -prices[0];
        //spent O(1) mem.
        for (int i = 0; i < n; i++) {
            //base case.
            if (i - 1 == -1) {
                continue;
            }
            dp_0 = Math.max(dp_0, dp_1 + prices[i]);
            dp_1 = Math.max(dp_1, -prices[i]);
        }
        return dp_0;
    }

    /**
     * 在购买次数无限的情况下，即K=INF
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
     * 如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的。可以这样改写框架：
     * 我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     *
     * @param prices
     * @return
     */
    public static int maxProfitInINFk(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int dp_0, dp_1;
        dp_0 = 0;
        dp_1 = -prices[0];
        for (int i = 0; i < prices.length; i++) {
            int temp = dp_0;
            dp_0 = Math.max(dp_0, dp_1 + prices[i]);
            dp_1 = Math.max(dp_1, temp - prices[i]);
        }
        return dp_0;
    }

    /**
     * 第二天是无法交易的，即具有冷冻期，次数仍然为无限
     * <p>
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
     * 解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
     *
     * @param prices
     * @return
     */
    public static int maxProfitWithCooldown(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int dp_0, dp_1, preDp = 0;
        dp_0 = 0;
        dp_1 = -prices[0];
        for (int i = 0; i < prices.length; i++) {
            int temp = dp_0;
            dp_0 = Math.max(dp_0, dp_1 + prices[i]);
            dp_1 = Math.max(dp_1, preDp - prices[i]);
            preDp = temp;
        }
        return dp_0;
    }

    /**
     * 无限次购买，但是每次都需要支付手续费
     *
     * @param prices
     * @return
     */
    public static int maxProfitWithFee(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int dp_0 = 0, dp_1;
        dp_1 = -prices[0];
        for (int i = 0; i < prices.length; i++) {
            int temp = dp_0;
            dp_0 = Math.max(dp_0, dp_1 + prices[i]);
            dp_1 = Math.max(dp_1, temp - prices[i] - fee);
        }
        return dp_0;
    }

    /**
     * 有限次购买
     *
     * @param prices
     * @return
     */
    public static int maxProfitInK(int[] prices, int k) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        //对于腾讯等公司来说，喜欢给出很大的K，导致内存爆炸，我们实际分析，
        //K的极值是多少，只能是n/2
        if (k > (prices.length >> 1)) {
            //相当于K=INF
            int dp_0, dp_1;
            dp_0 = 0;
            dp_1 = -prices[0];
            for (int i = 0; i < prices.length; i++) {
                int temp = dp_0;
                dp_0 = Math.max(dp_0, dp_1 + prices[i]);
                dp_1 = Math.max(dp_1, temp - prices[i]);
            }
            return dp_0;
        }
        //处理K<n/2的情况
        int[][][] dp = new int[prices.length][k + 1][2];
        for (int i = 0; i < prices.length; i++) {
            for (int j = k; j > 0; j--) {
                if (i - 1 == -1) {
                    //需要处理base情况，base情况由k控制
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][k][0];
    }


    public static void main(String[] args) {
        int[] arr = new int[]{9, 11, 8, 5, 7, 12, 16, 14};
        System.out.println(maxProfitInK(arr, 10));
    }
}
