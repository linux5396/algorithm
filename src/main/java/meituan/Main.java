package meituan;


import java.util.Arrays;
import java.util.Scanner;

/**
 * @author linxu
 * @date 2020/3/12
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 有一个2*n的网格，有一个人位于(1,1)的位置，即左上角，他希望从左上角走到右下角，
 * 即(2,n)的位置。在每一次，他可以进行三种操作中的一种：
 * <p>
 * 1． 向右走一格，即从(x,y)到(x,y+1);
 * <p>
 * 2． 向上右方走一格，即，如果他在(2,y)的位置可以走到(1,y+1);
 * <p>
 * 3． 向下右方走一格，即，如果他在(1,y)的位置可以走到(2,y+1);
 * <p>
 * <p>
 * <p>
 * 问题当然不会这么简单，在这2*n的格子中，有一部分格子上有障碍物，他不能停在障碍物上，
 * 当然也不能走出网格，请问他有多少种不同的路线可以到达(2,n)。
 */
public class Main {
    public int canMoveWays(char[][] matrix) {
        int[] ways = {-1};
        if (matrix == null || matrix.length < 2 || matrix[0].length == 0) {
            return ways[0];
        }
        //剪枝
        if (matrix[1][matrix[0].length - 1] == 'X' || matrix[0][0] == 'X') {
            return -1;
        }
        //提判
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 'X' && matrix[1][i] == 'X') {
                return ways[0];
            }
        }
        canMove(matrix, 0, 0, ways);
        return ways[0] == -1 ? -1 : ways[0] + 1;
    }

    private void canMove(char[][] matrix, int row, int col, int[] ways) {
        if (row < 0 || col >= matrix[0].length || row > 1) {
            return;
        }
        if (hasBlocked(matrix, row, col)) {
            return;
        }
        if (row == 1 && col == matrix[0].length - 1) {
            ways[0]++;
        }
        if (row == 0) {
            //剪枝
            if (!hasBlocked(matrix, row + 1, col + 1)) {
                canMove(matrix, row + 1, col + 1, ways);
            }
            if (!hasBlocked(matrix, row, col + 1)) {
                canMove(matrix, row, col + 1, ways);
            }
        } else {
            if (!hasBlocked(matrix, row - 1, col + 1)) {
                canMove(matrix, row - 1, col + 1, ways);
            }
            if (!hasBlocked(matrix, row, col + 1)) {
                canMove(matrix, row, col + 1, ways);
            }
        }
    }

    private boolean hasBlocked(char[][] matrix, int row, int col) {
        return matrix[row][col] == 'X';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cols = scanner.nextLine();
        String input = null;
        char[][] matrix = new char[2][Integer.valueOf(cols)];
        int times = 0;
        while (!(input = scanner.nextLine()).equals("")) {
            matrix[times++] = input.toCharArray();
            if (times == 2) {
                System.out.println(new Main().canMoveWays(matrix));
                break;
            }
        }
    }
}
