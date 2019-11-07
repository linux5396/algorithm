package com.linxu.algorithm.bydate.date191107;

/**
 * @author linxu
 * @date 2019/11/7
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：采用一维数组优化八皇后问题
 */
public class SaveSpaceEightEmpress {
    /**
     * 一维数组,第一个皇后摆在array[0]列，第二个摆在array[1]列
     */
    private final int[] array;
    /**
     * N皇后
     */
    private final int N;
    /**
     * 解法总数
     */
    private int ways = 0;

    public SaveSpaceEightEmpress(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("N can not less than zero");
        }
        this.array = new int[n];
        N = n;
    }

    public void search(int n) {
        if (n == N) {
            ways++;
            return;
        }
        for (int i = 0; i < N; i++) {
            array[n] = i;
            if (check(n)) {
                search(n + 1);
            }
        }
    }

    private boolean check(int n) {
        for (int i = 0; i < n; i++) {
            //case 1同列
            //case 2是对角线判断的一个优化，通过画图，我们可以知道，主对角线\上行列只差是相等的。
            //副对角线/上的行列之和是相等的。
            /*    if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i]))*/
            if (array[i] == array[n] || (n - array[n]) == (i - array[i]) || (n + array[n]) == (i + array[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SaveSpaceEightEmpress inst = new SaveSpaceEightEmpress(4);
        inst.search(0);
        System.out.println("ways:" + inst.ways);

    }


}
