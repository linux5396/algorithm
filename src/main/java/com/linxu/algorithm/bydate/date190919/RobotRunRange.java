package com.linxu.algorithm.bydate.date190919;

import com.linxu.algorithm.Recommend;

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
    @Recommend
    public static int canMoving(int[][] matrix, int k) {
        //solve special case
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        //base count
        //所有结点都是在该节点的基础上自增
        int count = 0;
        //construct a boolean array to record the visit.
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        count = canMove(matrix, visited, k, 0, 0, count);
        return count;
    }

    private static int canMove(int[][] matrix, boolean[][] visited, int k, int row, int col, int count) {
        //solve special case
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        if (check(matrix, visited, k, row, col)) {
            visited[row][col] = true;
            count = 1 + canMove(matrix, visited, k, row + 1, col, count)
                    + canMove(matrix, visited, k, row - 1, col, count)
                    + canMove(matrix, visited, k, row, col + 1, count)
                    + canMove(matrix, visited, k, row, col - 1, count);
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

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 4},
                {2, 3, 78, 79},
                {4, 80, 6, 7}
        };
        System.out.println(canMoving(matrix, 3));
    }
}
