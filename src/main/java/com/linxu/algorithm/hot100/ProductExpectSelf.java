package com.linxu.algorithm.hot100;

/**
 * @author linxu
 * @date 2020/3/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 除自身以外的数组乘积
 */
public class ProductExpectSelf {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length];
        //record left
        int[] leftMultiply = new int[nums.length];
        //record right
        int[] rightMultiply = new int[nums.length];
        leftMultiply[0] = 1;
        for (int i = 1; i < leftMultiply.length; i++) {
            leftMultiply[i] = leftMultiply[i - 1] * nums[i - 1];
        }
        rightMultiply[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            rightMultiply[i] = rightMultiply[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < nums.length; i++) {
            res[i] = leftMultiply[i] * rightMultiply[i];
        }
        return res;
    }

    public int[] productExceptSelfInFast(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length];
        //record left
        res[0] = 1;
        //基于答案数组先存储左乘积
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        //在计算右乘积的时候顺带存储
        int R = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            res[i] = res[i + 1] * R;
            R *= nums[i];
        }
        return res;
    }
}
