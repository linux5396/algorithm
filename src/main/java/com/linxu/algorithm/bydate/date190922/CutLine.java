package com.linxu.algorithm.bydate.date190922;

import com.linxu.algorithm.CostSpace;
import com.linxu.algorithm.CostTime;
import com.linxu.algorithm.Recommend;
import com.linxu.algorithm.SaveSpace;

/**
 * @author linxu
 * @date 2019/9/22
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题:
 * 一根长度为N的绳子，把绳子剪成M段，（M、N都是整数）
 * m>1&n>1；每个绳子长度记为k[0]...k[m]
 * 问k[0]*k[1]*...*k[m]的乘积最大为多少
 * 例子：N=8，M=3，则为2、3、3 =18
 */
public class CutLine {

    /**
     * 题目分析：
     * 先举几个例子，可以看出规律来。
     * 4 ： 2*2
     * 5 ： 2*3
     * 6 ： 3*3
     * 7 ： 2*2*3 或者4*3
     * 8 ： 2*3*3
     * 9 ： 3*3*3
     * 10：2*2*3*3 或者4*3*3
     * 11：2*3*3*3
     * 12：3*3*3*3
     * 13：2*2*3*3*3 或者4*3*3*3
     * <p>
     * 下面是分析：
     * 首先判断k[0]到k[m]可能有哪些数字，实际上只可能是2或者3。
     * 当然也可能有4，但是4=2*2，我们就简单些不考虑了。
     * 5<2*3,6<3*3,比6更大的数字我们就更不用考虑了，肯定要继续分。
     * 其次看2和3的数量，2的数量肯定小于3个，为什么呢？因为2*2*2<3*3，那么题目就简单了。
     * 直接用n除以3，根据得到的余数判断是一个2还是两个2还是没有2就行了。
     * 由于题目规定m>1，所以2只能是1*1，3只能是2*1，这两个特殊情况直接返回就行了。
     * <p>
     * 乘方运算的复杂度为：O(log n)，用动态规划来做会耗时比较多。
     */
    @CostTime("logN")
    @CostSpace("1")
    public static int maxProduct(int N) {
        //solve special case
        if (N < 2) {
            return 0;
        }
        if (N == 2) {
            return 1;
        }
        if (N == 3) {
            return 2;
        }
        int x = N % 3;
        int y = N / 3;
        if (x == 0) {
            //6 ->3*3=3^2
            return (int) Math.pow(3, y);
        } else if (x == 1) {
            //7 ->2*2*3
            return 2 * 2 * (int) Math.pow(3, y - 1);
        } else {
            //5 ->2*3
            return 2 * (int) Math.pow(3, y);
        }
    }

    /**
     * @param N
     * @return
     */
    public static int dpMaxProduct(int N) {
        //solve special case
        if (N < 2) {
            return 0;
        }
        if (N == 2) {
            return 1;
        }
        if (N == 3) {
            return 2;
        }
        int[] dp = new int[N + 1];
//好多人定义product数组的时候，解释product[0]- producdt[3]中存储的是各段乘机的最大值，(⊙o⊙)…我感觉不是吧！
//这道题目的临界点其实是4，也就是说4以下的其实不论怎么分，成绩都不可能比自身的数大！那么此时存储的其实是最大的值
// 不一定是乘法最大值
        dp[0] = 0;
        dp[1] = 1;//设置为1，避免相乘为0，长度为1的时候实际上乘积为0；
        dp[2] = 2;//dp[2]表示长度为2的子绳子，product[3]同理
        dp[3] = 3;//从dp[4]开始，product[i]的含义成了长度为i的绳子的最优解
        int max;
        //i为绳子的长度，确保每个i都是最优的情况下，知道i=N,也会是最优
        for (int i = 4; i < N + 1; i++) {
            max = 0;
            for (int j = 1; j <= i >> 1; j++) {
                int product = dp[j] * dp[i - j];
                if (max < product) {
                    max = product;
                }
                dp[i] = max;
            }
        }
        max = dp[N];
        for (int a : dp) {
            System.err.print(a + "    ");
        }
        return max;
    }

    /**
     * 添加贪婪法解决
     * @param N
     * @return
     */
    public static int greedMaxProduct(int N) {
        //solve special case
        if (N < 2) {
            return 0;
        }
        if (N == 2) {
            return 1;
        }
        if (N == 3) {
            return 2;
        }
        int timesOf3 = N / 3;
        int timesOf2 = 0;
        if (N - timesOf3 * 3 == 1) {
            timesOf3--;
        }
        timesOf2 = (N - timesOf3 * 3) >> 1;
        return (int) Math.pow(3, timesOf3) * (int) Math.pow(2, timesOf2);
    }

    public static void main(String[] args) {
        System.out.println(dpMaxProduct(5));
        System.out.println(greedMaxProduct(8));
    }
}
