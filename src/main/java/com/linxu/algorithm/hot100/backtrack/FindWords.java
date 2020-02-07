package com.linxu.algorithm.hot100.backtrack;

import java.util.Arrays;

/**
 * @author linxu
 * @date 2020/2/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindWords {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        //init
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (goThrough(board, visited, 0, wordArray, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean goThrough(char[][] board, boolean[][] visited, int k, char[] cur, int i, int j) {
        //end calling
        if (i < 0 || j >= board[0].length || i >= board.length || j < 0) {
            return false;
        }
        //enough
        try {
            if (!visited[i][j] && board[i][j] == cur[cur.length - 1] && k == cur.length - 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(i+"...."+j);
        }
        //hasPath
        boolean hasPath = false;
        if (!visited[i][j] && board[i][j] == cur[k]) {
            visited[i][j] = true;
            hasPath = goThrough(board, visited, k + 1, cur, i + 1, j) ||
                    goThrough(board, visited, k + 1, cur, i, j + 1) ||
                    goThrough(board, visited, k + 1, cur, i - 1, j) ||
                    goThrough(board, visited, k + 1, cur, i, j - 1);
            if (!hasPath) {
                visited[i][j] = false;
            }
        }
        return hasPath;
    }

    public static void main(String[] args) {
        char[][] board =
                {
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}};
        System.out.println(new FindWords().exist(board, "ABCCED"));
    }
}
