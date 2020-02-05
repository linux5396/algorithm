package com.linxu.algorithm.hot100.doublepointer;

/**
 * @author linxu
 * @date 2020/2/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class GetRainWater {
    /**
     * 暴力解，查找每个格子左边最大柱子高度和右边最大柱子高度；
     * O（N^2）
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int capacity = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int leftMaxHeight = 0;
            int rightMaxHeight = 0;
            for (int j = 0; j <= i; j++) {
                leftMaxHeight = Math.max(leftMaxHeight, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                rightMaxHeight = Math.max(rightMaxHeight, height[j]);
            }
            capacity += Math.min(leftMaxHeight, rightMaxHeight) - height[i];
        }
        return capacity;
    }

    /**
     * 备忘录解法
     */
    public int trapMemorandum(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int capacity = 0;
        //init
        int[] leftCache = new int[height.length];
        leftCache[0] = height[0];
        int[] rightCache = new int[height.length];
        rightCache[height.length - 1] = height[height.length - 1];
        for (int i = 1; i < height.length; i++) {
            leftCache[i] = Math.max(height[i], height[i - 1]);
        }
        for (int i = leftCache.length - 1; i >= 0; i--) {
            rightCache[i] = Math.max(height[i], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            capacity += Math.min(leftCache[i], rightCache[i]) - height[i];
        }
        return capacity;
    }

    /**
     * 双指针优化法：在备忘录法的基础上进行分析
     * 如果能够在计算备忘录的过程同时进行判断，就能够省下不必要的空间。
     *
     */
    public int trapInDoubleP(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int capacity = 0;
        int maxLeft = height[0], maxRight = height[height.length - 1];
        int left = 0, right = height.length - 1;
        while (left <= right) {
            maxLeft = Math.max(maxLeft, height[left]);
            maxRight = Math.max(maxRight, height[right]);
            if (maxLeft < maxRight) {
                capacity += maxLeft - height[left];
                left++;
            } else {
                capacity += maxRight - height[right];
                right--;
            }
        }
        return capacity;
    }
}
