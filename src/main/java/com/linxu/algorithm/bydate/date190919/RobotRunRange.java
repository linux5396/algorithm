package com.linxu.algorithm.bydate.date190919;

/**
 * @author linxu
 * @date 2019/9/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 地上有一个M行N列的方格，机器人从0，0开始，
 * 每次都可以向四个方向移动一个位置，但是不能进入行坐标和列坐标的数位之和大于K的格子
 * 请问机器人能到达多少个格子？
 * 数位之和（例如(35,37)数位之和为3+5+3+7=18）
 */
public class RobotRunRange {
    public static int canMoving(int[][] matrix, int k) {
        //solve special case
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        //normal case
        int count = 0;
        //construct a boolean array to record the visit.
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (check(matrix, visited, k, 0, 0)) {

        }
        return count;
    }

    private static int getNumber(int row, int col) {
        int sum = 0;
        while (row > 0) {
            sum += row % 10;
            row /= 10;
        }
        while (col > 0) {
            sum += col % 10;
            col /= 10;
        }
        return sum;
    }

    private static boolean check(int[][] matrix, boolean[][] visited, int k, int row, int col) {
        if (col < 0 || row < 0 || row > matrix.length - 1 || col > matrix[0].length - 1) {
            return false;
        }
        boolean canMove = false;
        if (!visited[row][col] && getNumber(row, col) <= k) {
            canMove = true;
        }
        return canMove;
    }
}
