package com.linxu.algorithm.bydate.date191211;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Lx
 * @date 12月
 * @package com.linxu.algorithm.bydate.date191211
 * 问题：最大子矩阵：
 * 矩阵中有0  1
 * 例如 1 1 1 0 的最大子矩阵大小为3
 * <p>
 * //思想：矩阵切割与单调栈的应用。
 */
public class MaxSubMatrix {
    public static int maxArea(int[][] matrix) {
        try {
            if (matrix == null || matrix[0] == null) {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
        int maxArea = 0;
        //定义高度数组
        int[] heights = new int[matrix[0].length];
        //scan by rows.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                heights[j] = matrix[i][j] == 0 ? 0 : ++heights[j];
            }
            maxArea = Math.max(maxRectrangleArea(heights), maxArea);
        }
        return maxArea;
    }

    private static int maxRectrangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            //这里处理重复元素的方式与单调栈不同：
            //1、通过画图，可以清楚地看到，如果出现元素重复，那么最后那个重复的元素计算的最大面积可以代表其他重复元素的最大面积。
            //因此，无需使用列表来维护重复元素，从而提高效率。
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int popIdx = stack.pop();
                int leftIdx = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - leftIdx - 1) * heights[popIdx];
                maxArea = Math.max(curArea, maxArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIdx = stack.pop();
            int leftIdx = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (heights.length - leftIdx - 1) * heights[popIdx];
            maxArea = Math.max(curArea, maxArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String iniCommand = scanner.nextLine();
        int rows = Integer.parseInt(iniCommand.split(" ")[0]);
        int cols = Integer.parseInt(iniCommand.split(" ")[1]);
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        System.out.println(maxArea(matrix));
    }
}
