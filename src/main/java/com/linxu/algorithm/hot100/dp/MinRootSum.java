package com.linxu.algorithm.hot100.dp;


import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2020/3/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class MinRootSum {
    public int minPathSum(int[][] grid) {
        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[j] = dp[j] + grid[i][j];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
        }
        //复用原本的二维数组，但是会改变原有数据
       /* for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) continue;
                else if (i == 0) grid[i][j] = grid[i][j - 1] + grid[i][j];
                else if (j == 0) grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }*/
        GenerationUtil.print(dp, false);
        // return grid[grid.length - 1][grid[0].length - 1];
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        new MinRootSum().minPathSum(arr);
    }
}
