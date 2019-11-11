package com.linxu.algorithm.bydate.date191111;

import com.linxu.algorithm.CostTime;

/**
 * @author linxu
 * @date 2019/11/11
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：输入一个连续的整型数组，求其中的连续子数组的和的最大值。
 */
public class MaxSumInSubArray {
    /**
     * 算法核心：
     * <p>
     * 设置一个变量用于存储到达过的最大值。
     * 设置一个变量用于计算数组的元素之和。
     * 如果和<=0，刷新sum，从下一个元素开始
     * 如果和>0,则继续相加。
     * 每一轮计算都对比一下最大值是否更大，是则刷新。
     * </p>
     * <dynamic>
     *     动态规划思想构造出如下的递归公式：
     *     f(i)表示当前的和.
     *   f(i)=
     *          array[i]; i=0 or f(i-1)<=0
     *          f(i-1)+array[i]; i!=0&&f(i-1)>0
     *          //返回整个过程中最大的和即可。
     *     return max(f(i));
     *
     * </dynamic>
     *
     * @param array
     * @return
     */
    @CostTime("O(N)")
    public static int compute(int[] array) {
        //solve special case.
        if (array == null || array.length == 0) {
            return 0;
        }
        int sum = 0;
        int maxSumEver = 0;
        for (int i = 0; i < array.length; i++) {
            if (sum <= 0) {
                sum = array[i];
            } else {
                sum += array[i];
            }
            if (sum > maxSumEver) {
                maxSumEver = sum;
            }
        }
        return maxSumEver;
    }

    public static void main(String[] args) {
        int[] array = {1, -2, 3, 10, -4, 7, 2, -5};
        System.err.println(compute(array));
    }

}
