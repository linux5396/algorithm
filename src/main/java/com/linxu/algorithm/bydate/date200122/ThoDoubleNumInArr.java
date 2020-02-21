package com.linxu.algorithm.bydate.date200122;

import com.linxu.algorithm.CostSpace;
import com.linxu.algorithm.CostTime;

/**
 * @author linxu
 * @date 2020/1/21
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：在数组中有两个数字出现了1次，其余都出现了2次，请在
 * O(N)的时间复杂度与O(1)的空间复杂度内找出这两个数字
 */
public class ThoDoubleNumInArr {
    @CostSpace("1")
    @CostTime("N")
    public static int[] find(int[] array) {
        //special case solve.
        if (array == null || (array.length & 1) == 1) {
            return new int[0];
        }
        int[] retVals = new int[2];
        int xorSum = 0;
        for (int i = 0; i < array.length; i++) {
            xorSum ^= array[i];
        }
        int boundIdx = findTheBoundBitIdx(xorSum);
        for (int i = 0; i < array.length; i++) {
            if (isBound(array[i], boundIdx)) {
                retVals[0] ^= array[i];
            } else {
                retVals[1] ^= array[i];
            }
        }
        return retVals;
    }

    /**
     * 查找异或后结果的第一个1，用来分组
     */
    private static int findTheBoundBitIdx(int num) {
        int indexOfBit = 0;
        while ((num & 1) == 0 && indexOfBit < 32) {
            num >>= 1;
            indexOfBit++;
        }
        return indexOfBit;
    }

    /**
     * 判断是在哪一组
     *
     * @param num
     * @param boundIdx
     * @return
     */
    private static boolean isBound(int num, int boundIdx) {
        num >>= boundIdx;
        return (num & 1) == 1;
    }
    public int[] singleNumberOK(int[] nums) {
        int xor = 0;
        for (int i : nums)// 一样的抵消,不一样的两个数字异或运算结果必定有一位是1
            xor ^= i;

        int mask = xor & (-xor);

        int[] ans = new int[2];
        for (int i : nums) {
            if ((i & mask) == 0)//== 0、 == mask 两种结果
                ans[0] ^= i;
            else
                ans[1] ^= i;
        }

        return ans;

    }

}
