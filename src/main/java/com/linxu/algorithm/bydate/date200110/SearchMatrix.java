package com.linxu.algorithm.bydate.date200110;

/**
 * @author linxu
 * @date 2020/1/9
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 探索二维矩阵:
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 */
public class SearchMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        //EX
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        //快速判断
        if (target < matrix[0][0] || target > matrix[matrix.length - 1][matrix[0].length - 1]) {
            return false;
        }
        int rightTop;
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            rightTop = matrix[row][col];
            if (rightTop == target) {
                return true;
            } else {
                if (rightTop > target) {
                    col--;
                } else {
                    row++;
                }
            }
        }
        return false;
    }
}
