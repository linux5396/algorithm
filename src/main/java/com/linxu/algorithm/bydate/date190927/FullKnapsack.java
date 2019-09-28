package com.linxu.algorithm.bydate.date190927;

/**
 * @author linxu
 * @date 2019/9/28
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 完全背包问题。
 */
public class FullKnapsack {
    public static int maxValue(int[] N, int[] cost, int[] value, int V) {
        //special case
        if (N == null || cost == null || value == null || V <= 0) {
            return 0;
        }
        //TODO 优化
        //1、可以算出每种物品的价值/COST比例，如果COST相同，价值比例小的先行剔除。O(N*N)
        //2、清除COST单价比容量V还大的物品，复杂度O(N)

        //构造二维数组
        int[][] dp = new int[N.length + 1][V + 1];
        memset(dp, 0);
        for (int i = 1; i <= N.length; i++) {
            for (int j = 1; j <= V; j++) {
                if (cost[i] <= j) {
                    //是否再放一件I和一件都不放
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - cost[i]] + value[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        //print
        for (int i = 0; i < N.length + 1; i++) {
            for (int j = 0; j < V + 1; j++) {
                System.err.printf("%-5d", dp[i][j]);
            }
            System.err.println();
        }
        return 0;
    }

    public static int maxValueInSingleArray(int[] N, int[] cost, int[] value, int V) {
        //special case
        if (N == null || cost == null || value == null || V <= 0) {
            return 0;
        }
        //背包
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N.length; i++) {
            for (int j = cost[i]; j <= V; j++) {
                dp[j] = max(dp[j], dp[j - cost[i]] + value[i]);
            }
        }
        for (int i = 0; i < V+1; i++) {
            System.out.printf("%-5d",dp[i]);
        }
        return 0;
    }

    private static void memset(int[][] arr, int initElem) {
        //single init
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    arr[i][j] = initElem;
                }
            }
        }
    }

    private static int max(int first, int second) {
        return first > second ? first : second;
    }

    public static void main(String[] args) {
        int[] N = {1, 2, 3, 4, 5};
        int[] cost = {0, 6, 1, 5, 2, 3};
        int[] value = {0, 48, 7, 40, 12, 8};
        int V = 9;
        maxValue(N, cost, value, V);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("******************************");
        maxValueInSingleArray(N,cost,value,V);
    }
}
