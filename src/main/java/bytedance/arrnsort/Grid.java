package bytedance.arrnsort;

import java.util.Arrays;

/**
 * @author linxu
 * @date 2020/3/13
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class Grid {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int cur = canMove(grid, i, j);
                maxArea = Math.max(cur, maxArea);
            }
        }
        return maxArea;
    }

    private int canMove(int[][] grid, int row, int col) {
        if (row < 0 || col == grid[0].length || col < 0 || row == grid.length) {
            return 0;
        }
        if (grid[row][col] == 1) {
            grid[row][col] = 0;
            return 1 + canMove(grid, row + 1, col) +
                    canMove(grid, row, col + 1) +
                    canMove(grid, row, col - 1) +
                    canMove(grid, row - 1, col);
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] grid = {
                /*      {1, 1, 0, 0, 0},
                      {1, 1, 0, 0, 0},
                      {0, 0, 0, 1, 1},
                      {0, 0, 0, 1, 1}*/
                {0, 1, 0},
                {1, 1, 1}
        };
        System.out.println(new Grid().maxAreaOfIsland(grid));
    }
}
