package com.linxu.algorithm.bydate.date190918;

/**
 * @author linxu
 * @date 2019/9/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 1、使用回溯法，在一个矩阵中查找是否存在一条路径，并且该路径上的点不能够重叠
 */
public class FindRoute {
    /**
     * 步行计数器
     */
    private static class Foot {
        int val;

        void setVal(int val) {
            this.val = val;
        }
    }

    public static boolean hasPath(char[][] matrix, String specifyPath) {
        //判断是否为空，行的长度是否为0，列的长度是否为0
        if (matrix == null || matrix.length == 0 || matrix[matrix.length - 1].length == 0) {
            return false;
        }
        Foot foot = new Foot();
        foot.setVal(0);
        //路径记录，判断是否走过
        //TODO 这里可以修改为一维数组，即将二维转化为一维
        boolean[][] pathRecord = new boolean[matrix.length][matrix[matrix.length - 1].length];
        int rows = matrix.length;
        int cols = matrix[matrix.length - 1].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPath(matrix, pathRecord, i, j, specifyPath.toCharArray(), foot)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasPath(char[][] matrix, boolean[][] records, int row, int col, char[] route, Foot curIdx) {
        //越界路径不通
        if (row < 0 || col > matrix[matrix.length - 1].length || col < 0 || row > (matrix.length - 1)) {
            return false;
        }
        if (route.length == curIdx.val) {
            //到达终点
            return true;
        }
        boolean hasPath =false;
        if (matrix[row][col] == route[curIdx.val] && !records[row][col]) {
            curIdx.setVal(curIdx.val + 1);
            records[row][col] = true;
            hasPath = hasPath(matrix, records, row, col - 1, route, curIdx)
                    || hasPath(matrix, records, row, col + 1, route, curIdx)
                    || hasPath(matrix, records, row - 1, col, route, curIdx)
                    || hasPath(matrix, records, row + 1, col, route, curIdx);
            if (!hasPath) {
                curIdx.setVal(curIdx.val - 1);
                records[row][col] = false;
            }
        }
        return hasPath;
    }


    public static void main(String[] args) {
        char[][] matrix = {
                {'a', 'b', 't', 'g'},
                {'c', 'f', 'c', 'f'},
                {'b', 'f', 'c', 'e'}
        };
        System.out.println(hasPath(matrix,"bfce"));
    }
}
