package com.linxu.algorithm.bydate.date190915;

/**
 * @author linxu
 * @date 2019/7/31
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 1、you can climb one or two stair per stage.
 * 2、how many ways to climb to the top  could you find ?
 */
public class LouTi {
    public static int climbStairs(int n) {
        return climb_Stairs(0, n);
    }

    public int dynamicClimbStairs(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    public int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int slow = 2;
        int fast = 1;
        int third = 0;
        for (int i = 2; i < n; i++) {
            third = slow + fast;
            fast = slow;
            slow = third;
        }
        return third;
    }

    /**
     * Recursive Tree algorithm
     * time: O(2^n) due to depth of the tree is N(max).
     *
     * @param i current index
     * @param n target
     * @return is Ok or Not
     */
    private static int climb_Stairs(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
    }


    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        System.out.println(new LouTi().climbStairs(3));
        System.out.println("time:" + (System.currentTimeMillis() - start));
        // System.err.println(new LouTi().dynamicClimbStairs(5));
        //  System.out.println(new LouTi().fibonacci(5));
    }
}
