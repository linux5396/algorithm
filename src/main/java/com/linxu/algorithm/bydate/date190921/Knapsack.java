package com.linxu.algorithm.bydate.date190921;

import com.linxu.algorithm.CostSpace;
import com.linxu.algorithm.CostTime;
import com.linxu.algorithm.Recommend;

/**
 * @author linxu
 * @date 2019/9/21
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 0-1背包问题算法，动态规划
 * 有N件物品和一个容量为V的背包。第i件物品的费用是c[i]，价值是w[i]。求解将哪些物品装入背包可使价值总和最大。
 * <p>
 * //背包应用：选择作答题目、装物件、尽可能公平地划分集合等
 * //把背包问题应用到现实中，就可以实现各种最大的获利估计
 */
public class Knapsack {
    /**
     * 0-1背包问题
     * <p>
     * 二维数组的核心：f[i][j] = max(f[i - 1][j], f[i - 1][j - cost[i]] + value[i])
     *
     * @param N     N个物件
     * @param V     背包容量
     * @param cost  每个物件对应的花费
     * @param value 每个物件对应的价值
     * @return 能够获取的最大价值
     */
    @CostSpace("O(N*V)")
    @CostTime("O(N*V)")
    //TODO 0-1背包中的特殊例子，就是value与cost对等的情况下，还存在偶数优化情况：采用排序加取对
    //TODO 二维是否能够采用一维表示，也存在优化情况（从空间花费从大到小来构成for循环的话，就可以直接用一维数组来保存物品数-1 的值。）
    public static int[] maxValue(int[] N, int V, int[] cost, int[] value) {
        //TODO  solve special cases.
        int countOfThings = N.length;
        int[] knapsack = new int[countOfThings];
        //构造二维数组
        int[][] f = new int[countOfThings + 1][V + 1];
        for (int j = 0; j < V + 1; j++) {
            //初始化第一行，理解为不放东西，不论容量有多大，都为0
            f[0][j] = 0;
        }
        for (int i = 0; i < countOfThings + 1; i++) {
            //初始化第一列，可以理解成0容量，那么value肯定都为0
            f[i][0] = 0;
        }
        for (int i = 1; i < countOfThings + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                //如果能够放进背包
                if (cost[i] <= j) {
                    f[i][j] = max(f[i - 1][j], f[i - 1][j - cost[i]] + value[i]);
                } else {
                    f[i][j] = f[i - 1][j];
                }
            }
        }
        for (int i = 0; i < countOfThings + 1; i++) {
            for (int j = 0; j < V + 1; j++) {
                System.err.printf("%-5d", f[i][j]);
            }
            System.err.println();
        }
        return knapsack;
    }

    /**
     * 逆向背包，节省空间
     *
     * @param N     N个
     * @param V     容量
     * @param cost  花费
     * @param value 价值
     */
    @CostSpace("O(V)")
    @CostTime("O(N*V)")
    @Recommend
    public static void maxValueInLessSpace(int[] N, int V, int[] cost, int[] value) {
        int countOfThings = N.length;
        int[] dp = new int[V + 1];
        //初始化
        for (int i = 0; i < V + 1; i++) {
            dp[i] = 0;
        }
        for (int i = 0; i < countOfThings + 1; i++) {
            for (int j = V; j > 0; j--) {
                dp[j] = cost[i] <= j ? max(dp[j], dp[j - cost[i]] + value[i]) : dp[j];
            }
        }
        for (int i = 0; i < V + 1; i++) {
            System.out.println(dp[i]);
        }
    }

    private static int max(int first, int second) {
        return first > second ? first : second;
    }

    public static void main(String[] args) {
        int[] value = {0, 4, 5, 5, 100, 100};
        int[] N = {1, 2, 3, 4, 5};
        int[] cost = {0, 4, 5, 5, 100, 100};
        int V = 107;
        maxValueInLessSpace(N, V, cost, value);
    }
}
