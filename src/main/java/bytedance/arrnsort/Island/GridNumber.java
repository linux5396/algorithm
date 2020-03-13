package bytedance.arrnsort.Island;

/**
 * @author linxu
 * @date 2020/3/13
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 只有那些被0包围的1才算是岛屿，求这些岛屿的数量
 */
public class GridNumber {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    island(grid, i, j);
                }
            }
        }
        return count;
    }

    /**
     * 利用DFS，把连通的1全部变成0，统计有多少个0即可
     * @param grid
     * @param row
     * @param col
     */
    private void island(char[][] grid, int row, int col) {
        //可以走到这个位置
        if (row < 0 || col == grid[0].length || col < 0 || row == grid.length) {
            return;
        }
        if (grid[row][col] == '1') {
            //record
            grid[row][col] = '0';
            island(grid, row - 1, col);
            island(grid, row + 1, col);
            island(grid, row, col - 1);
            island(grid, row, col + 1);
        }
    }
}
