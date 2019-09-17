package com.linxu.algorithm.bydate.date190915;

import com.linxu.algorithm.Recommend;

/**
 * @author linxu
 * @date 2019/9/15
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 斐波那契数列的第N项
 */
public class Fibonacci {
    /**
     * 采用递归求解斐波那契数列的第N项
     * 会存在很多重复计算的问题，效率低下
     *
     * @param no
     * @return
     */

    public static int getTheN(int no) {
        if (no <= 0) {
            return 0;
        }
        if (no == 1) {
            return 1;
        }
        return getTheN(no - 1) + getTheN(no - 2);
    }

    /**
     * 采用动态规划计算
     * @param no
     * @return
     */
    @Recommend
    public static int getFastN(int no) {
        //solve special case
        if (no <= 0) {
            return 0;
        }
        if (no == 1) {
            return 1;
        }
        
        //dynamic compute
        int low = 0;
        int fast = 1;
        int n = 0;
        for (int i = 1; i < no; i++) {
            n = low + fast;
            low = fast;
            fast = n;
        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(getTheN(5));
        System.err.println(getFastN(2));
    }
}
