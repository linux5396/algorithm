package bytedance.arrnsort.Island;

/**
 * @author linxu
 * @date 2020/3/13
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 求岛的周长
 */
public class GridLine {
    public int islandPerimeter(int[][] grid) {
        int sum = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    int lines = 4;
                    //判断这个岛旁边连接了多少个岛，默认1个各自有4的周长，相连就减
                    if(i > 0 && grid[i - 1][j] == 1) lines--;
                    if(i < grid.length - 1 && grid[i + 1][j] == 1) lines--;
                    if(j > 0 && grid[i][j - 1] == 1) lines--;
                    if(j < grid[0].length - 1 && grid[i][j + 1] == 1) lines--;
                    sum += lines;
                }
            }
        }
        return sum;
    }
}
