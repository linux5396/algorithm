package com.linxu.algorithm.bydate.date200130;


/**
 * @author linxu
 * @date 2020/1/30
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：股票的最大价值。
 * 例如一只股票的价值曲线为 9,11,8,5,7,12,16,14
 * 如果能在5的时候买入，16时候卖出，则价值最大。
 */
public class MostValInSharePrice {
    /**
     * 一开始想过使用单调栈解决，但是想了想，单调栈的场景是最近，而这个是最大差值，并不适用
     *
     * @param cases
     * @return
     */
    public int maxVal(int[] cases) {
        if (cases == null || cases.length < 2) {
            return 0;
        }
        int maxDiffPrice = cases[1] - cases[0];
        int min = cases[0];
        for (int i = 2; i < cases.length; i++) {
            if (cases[i - 1] < min) {
                min = cases[i - 1];
            }
            int curDiffPrice = cases[i] - min;
            maxDiffPrice = curDiffPrice > maxDiffPrice ? curDiffPrice : maxDiffPrice;
        }
        return maxDiffPrice;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 11, 8, 5, 7, 12, 16, 14};
        System.out.println(new MostValInSharePrice().maxVal(arr));
    }
}
