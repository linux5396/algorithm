package com.linxu.algorithm.bydate.date190920;

/**
 * @author linxu
 * @date 2019/9/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 芬诺伊曼邻居问题：
 * 从一个1*1的方格开始，每次都在上次图形的周围再加上一圈，
 * 在第N次的时候要生成多少个方格（元胞自动机理论）
 * N从0开始，0次的时候也就是只有一个方格
 */
public class FenNoyiMan {
    public static int cells(int n) {
        //solve special case.
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return 2 * n * (n + 1) + 1;
    }

    public static void main(String[] args) {
        System.out.println(cells(4));
    }
}
