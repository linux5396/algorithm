package com.linxu.algorithm.bydate.date200103;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.Arrays;

/**
 * @author Lx
 * @date 12月
 * @package com.linxu.algorithm.bydate.date191215
 * 动态规划之硬币收集问题：
 * 在N*M的矩阵中，放着一些硬币，每格的硬币最多放一个，机器人从当前位置，每次只能向右或向下
 * ，当遇到一个有硬币的单元格，就会进行收集，求能收集到的最大硬币数以及路径。
 * 1、先求最大硬币数
 * 2、求路径
 */
public class CoinsCollection {
    public static int maxCoinsNumber(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        //do normal
        int count = 0;
        //locate dp arr
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int[][] newMatrix = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[i + 1][j + 1] = matrix[i][j];
            }
        }
        //对硬币进行收集
        for (int i = 1; i < matrix.length + 1; i++) {
            for (int j = 1; j < matrix[0].length + 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + hasCoin(newMatrix, i, j);
            }
        }

        GenerationUtil.print(dp);
        count = dp[matrix.length][matrix[0].length];
        System.out.println("从左上方到右下方收集到最大硬币数的路径（PS：其中元素为-1 表示行走路径）：");
        //其实到最后，肯定都是从左上角到右下角，因此，从右下角开始，按照判断条件，依次走回左上角。
        dp[1][1] = 1;   //最左上方位置
        dp[matrix.length][matrix[0].length] = -1;     //最右下方位置
        int maxI = matrix.length;
        int maxJ = matrix[0].length;
        while (maxI >= 1 && maxJ >= 1) {
            //如果收集了J
            if (dp[maxI][maxJ - 1] >= dp[maxI - 1][maxJ]) {
                dp[maxI][maxJ - 1] = -1;
                maxJ = maxJ - 1;
            } else {
                dp[maxI - 1][maxJ] = -1;
                maxI = maxI - 1;
            }
        }

        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++)
                System.out.print(dp[i][j] + "\t");
            System.out.println();
        }
        return count;
    }

    /**
     * 这个其实与机器人查找礼物的最大价值，从起点开始是一个道理。因为当前点开始实际上就可以从起点走过来。
     *
     * @param matrix
     * @return
     */
    public static int maxCoinsNumberInLessSpace(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        //use this
        int[] dp = new int[matrix[0].length + 1];
        int[][] newMatrix = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[i + 1][j + 1] = matrix[i][j];
            }
        }
        //对硬币进行收集
        for (int i = 1; i < matrix.length + 1; i++) {
            for (int j = 1; j < matrix[0].length + 1; j++) {
                dp[j] = Math.max(dp[j], dp[j - 1]) + hasCoin(newMatrix, i, j);
            }
        }
        return dp[dp.length - 1];
    }


    private static int hasCoin(int[][] matrix, int i, int j) {
        return matrix[i][j];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {1, 1, 1, 0},
                {1, 0, 0, 1}
        };
        System.err.println(maxCoinsNumber(matrix));
    }

}
