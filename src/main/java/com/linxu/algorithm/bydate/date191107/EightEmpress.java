package com.linxu.algorithm.bydate.date191107;

/**
 * @author linxu
 * @date 2019/11/7
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：八皇后问题
 * 在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 */
public class EightEmpress {
    /**
     * 棋盘
     */
    private static int[][] arry = new int[8][8];
    /**
     * 存储方案结果数量
     */
    private static int ways = 0;

    /**
     * 寻找皇后节点
     *
     * @param i
     */
    public static void findQueen(int i) {
        //八皇后的解
        if (i > 7) {
            ways++;
            //打印八皇后的解
            print();
            return;
        }
        //深度回溯,递归算法 每个皇后都尝试放置8列
        for (int m = 0; m < 8; m++) {
            //检查皇后摆放是否合适
            if (check(i, m)) {
                arry[i][m] = 1;
                findQueen(i + 1);
                //清零，以供回溯使用
                arry[i][m] = 0;
            }
        }
    }

    /**
     * @param k   第K只皇后（看作行坐标）
     * @param col 列坐标（从0-7）中的一位
     * @return 是否冲突
     */
    private static boolean check(int k, int col) {
        //检查列冲突
        for (int row = 0; row < 8; row++) {
            if (arry[row][col] == 1) {
                return false;
            }
        }
        //TODO 左右对角线的检查只需要向row--的方向检查即可
        //检查左对角线
        for (int i = k - 1, m = col - 1; i >= 0 && m >= 0; i--, m--) {
            if (arry[i][m] == 1) {
                return false;
            }
        }
        //检查右对角线
        for (int i = k - 1, m = col + 1; i >= 0 && m <= 7; i--, m++) {
            if (arry[i][m] == 1) {
                return false;
            }
        }
        //不存在冲突，可以放
        return true;
    }

    /**
     * print
     */
    private static void print() {
        System.out.print("方案" + ways + ":" + "\n");
        for (int i = 0; i < 8; i++) {
            for (int m = 0; m < 8; m++) {
                if (arry[i][m] == 1) {
                    System.out.print("o ");
                } else {
                    System.out.print("+ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("八皇后问题");
        findQueen(0);
        System.out.println("八皇后问题共有：" + ways + "种可能");
    }
}
