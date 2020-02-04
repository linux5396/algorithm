package com.linxu.algorithm.hot100.dp;

import java.util.Stack;

/**
 * @author linxu
 * @date 2020/2/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 */
public class MaxSubRectangle {
    /**
     * 采用单调栈结构解决最大矩形面积问题
     */
    public int maximalRectangleInSingleStack(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        if (matrix.length == 1 && matrix[0].length == 1) {
            return matrix[0][0] == '0' ? 0 : 1;
        }
        int[] heights = new int[matrix[0].length];
        int maxSize = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }
            maxSize = Math.max(maxSize, maxRectangle(heights));
        }
        return maxSize;
    }

    private int maxRectangle(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int maxSize = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int popIdx = stack.pop();
                int leftIdx = stack.isEmpty() ? -1 : stack.peek();
                maxSize = Math.max(maxSize, heights[popIdx] * (i - leftIdx - 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIdx = stack.pop();
            int leftIdx = stack.isEmpty() ? -1 : stack.peek();
            maxSize = Math.max(maxSize, heights[popIdx] * (heights.length - 1 - leftIdx));
        }
        return maxSize;
    }

    /**
     * 使用动态规划解决最大子矩阵大小问题
     * @param matrix
     * @return
     */
    public int maximalRectangleInDp(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        if (matrix.length == 1 && matrix[0].length == 1) {
            return matrix[0][0] == '0' ? 0 : 1;
        }
        int maxSize = 0;
        return maxSize;
    }
}
