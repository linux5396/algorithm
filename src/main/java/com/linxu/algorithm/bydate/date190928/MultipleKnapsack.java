package com.linxu.algorithm.bydate.date190928;

import com.linxu.algorithm.bydate.date190921.Knapsack;
import com.linxu.algorithm.bydate.date190927.FullKnapsack;
import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2019/9/29
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 多重背包问题：
 * 1、除了cost[] value[] 还有对应的物品数量N[]
 * 2、直接转化0-1背包
 * 3、按幂转化为0-1背包与完全背包
 */
public class MultipleKnapsack {
    /**
     * 直接0-1转化，从某种意义上来讲，挺暴力的。
     * 但是最容易理解。
     *
     * @param N        包含空占位的数量数组
     * @param capacity 容量
     * @param value    包含空占位的价值数组
     * @param cost     包含空占位的花费数组
     */
    public static void directMaxValueInMultipleKnapsack(int[] N, int capacity, int[] value, int[] cost) {
        //todo verify the input parameters.
        int maxCount = 0;
        //算出转化为0-1之后的所有物件的数量
        for (int i = 0; i < N.length; i++) {
            maxCount += N[i];
        }
        int[] newCost = new int[maxCount + 1];
        int[] newValue = new int[maxCount + 1];
        //执行0-1转化；cur为当前在新数组中的位移；i为老数组中的位置；j为计数器，用于控制将批量同一物品转为多个物品的次数。
        for (int i = 1, cur = 1; i < N.length; i++) {
            //拆分单个元素进行插入
            for (int j = 0; j < N[i]; j++) {
                newCost[cur] = cost[i];
                newValue[cur] = value[i];
                cur++;
            }
        }
        Knapsack.maxValue(newCost, capacity, newCost, newValue);
    }

    /**
     * 多重背包的快速优化
     * ：利用了幂次求解思想以及结合0-1、完全背包。
     *
     * @param N
     * @param capacity
     * @param value
     * @param cost
     */
    public static void fastMaxValueInMultipleKnapsack(int[] N, int capacity, int[] value, int[] cost) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < N.length; i++) {
            //该物品可以是完全背包
            if (cost[i] * N[i] >= capacity) {
                completeKnapsack(cost[i], value[i], dp, capacity);
            } else {
                for (int k = 1; k <= N[i]; k <<= 1) {
                    zeroKnapsack(k * cost[i], k * value[i], dp, capacity);
                    N[i] -= k;
                }
                //剩余1位
                if (N[i] > 0) {
                    zeroKnapsack(N[i] * cost[i], N[i] * value[i], dp, capacity);
                }
            }
        }
        GenerationUtil.print(dp, false);

    }

    private static void completeKnapsack(int cost, int value, int[] dp, int capacity) {
        for (int j = cost; j <= capacity; j++) {
            dp[j] = Math.max(dp[j], dp[j - cost] + value);
        }
    }

    private static void zeroKnapsack(int cost, int value, int[] dp, int capacity) {
        for (int j = capacity; j >= cost; j--) {
            //要么不放要么放
            dp[j] = Math.max(dp[j], dp[j - cost] + value);
        }
    }

    public static void main(String[] args) {
        int[] N = {0, 4, 3, 2, 1};
        int[] cost = {0, 2, 4, 1, 6};
        int[] value = {0, 12, 13, 7, 48};
        int capacity = 9;
        directMaxValueInMultipleKnapsack(N, capacity, value, cost);
        System.out.println("***************************");
        fastMaxValueInMultipleKnapsack(N, capacity, value, cost);
    }
}
