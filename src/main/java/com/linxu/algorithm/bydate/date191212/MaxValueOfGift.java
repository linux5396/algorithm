package com.linxu.algorithm.bydate.date191212;

/**
 * @author Lx
 * @date 12月
 * @package com.linxu.algorithm.bydate.date191212
 * 问题：礼物的最大价值
 * 在M*N的棋盘中，从左上角出发，每次向下或者向右移动一格，直到到达右下角，求拿到礼物的最大价值。
 */
public class MaxValueOfGift {

    public static int maxValue(int[][] gifts) {
        //solve special fast
        if (gifts == null || gifts[0] == null) {
            return 0;
        }
        return coreMaxValue(gifts, gifts.length - 1, gifts[0].length - 1);
    }

    /**
     * 动态规划用递归求解礼物最大值
     *
     * @param gifts
     * @param i
     * @param j
     * @return
     */
    private static int coreMaxValue(int[][] gifts, int i, int j) {
        if (i == 0) {
            return gifts[0][0];
        }
        if (j == 0) {
            return gifts[i][j] + coreMaxValue(gifts, i - 1, j);
        }
        //recur
        return Math.max(coreMaxValue(gifts, i - 1, j), coreMaxValue(gifts, i, j - 1)) + gifts[i][j];
    }

    public static int maxValueInDp(int[][] gifts) {
        //solve special fast
        if (gifts == null || gifts[0] == null) {
            return 0;
        }
        //locate dp
        int[][] dp = new int[gifts.length][gifts[0].length];
        int maxValue = 0;
        for (int i = 0; i < gifts.length; i++) {
            for (int j = 0; j < gifts[0].length; j++) {
                int left = 0;
                int up = 0;
                if (i > 0) {
                    up = dp[i - 1][j];
                }
                if (j > 0) {
                    left = dp[i][j - 1];
                }
                dp[i][j] = Math.max(left, up) + gifts[i][j];
            }
        }
        maxValue = dp[gifts.length - 1][gifts[0].length - 1];
        return maxValue;
    }

    public static void main(String[] args) {
        int[][] gifts = {
                {1, 10, 3, 8},
                {12, 2, 9, 6},
                {5, 7, 4, 11},
                {3, 7, 16, 5}
        };
        System.out.println(maxValueInDp(gifts));
    }
}
