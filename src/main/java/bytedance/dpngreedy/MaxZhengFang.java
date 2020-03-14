package bytedance.dpngreedy;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author linxu
 * @date 2020/3/14
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 最大正方形面积
 */
public class MaxZhengFang {
    /**
     * 单调栈法不能全部AC
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] height = new int[matrix[0].length];
        Arrays.fill(height, 0);
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < height.length; j++) {
                max = Math.max(max, matrix[i][j] == '1' ? 1 : 0);
                height[j] += matrix[i][j] == '1' ? 1 : 0;
            }
            max = Math.max(max, maxAreaSquare(height));
        }
        return max;
    }

    public int maxAreaSquare(int[] height) {
        //GenerationUtil.print(height,false);
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int top = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                maxArea = (i - left - 1) == height[top] ? Math.max(height[top] * height[top], maxArea) :
                        i - left - 1 > height[top] ? Math.max(maxArea, height[top] * height[top]) : Math.max((i - left - 1) * (i - left - 1), maxArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIdx = stack.pop();
            int leftIdx = stack.isEmpty() ? -1 : stack.peek();
            maxArea = height[popIdx] > popIdx - leftIdx - 1 ? Math.max((popIdx - leftIdx - 1) * (popIdx - leftIdx - 1), maxArea) :
                    Math.max(maxArea, height[popIdx] * height[popIdx]);
        }
        return maxArea;
    }

    public int maximalSquareDP(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int maxLine = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        //初始行
        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            maxLine = Math.max(maxLine, dp[i][0]);
        }
        //初始列
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            maxLine = Math.max(maxLine, dp[0][i]);
        }
        //DP统计
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                maxLine = Math.max(maxLine, dp[i][j]);
            }
        }
        return maxLine * maxLine;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                "01".toCharArray()
        };
        System.out.println(new MaxZhengFang().maximalSquareDP(matrix));
    }

}
