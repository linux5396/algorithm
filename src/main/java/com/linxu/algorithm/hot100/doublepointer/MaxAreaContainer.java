package com.linxu.algorithm.hot100.doublepointer;

/**
 * @author linxu
 * @date 2020/3/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class MaxAreaContainer {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left, right;
        left = 0;
        right = height.length - 1;
        int maxCapacity = 0;
        //大的不需要移动
        //小的可以满足，那么大的肯定也可以；所以只移动小的
        while (left < right) {
            maxCapacity = height[left] < height[right] ? Math.max(maxCapacity, (right - left) * height[left++]) : Math.max(maxCapacity, (right - left) * height[right--]);
        }
        return maxCapacity;
    }
}
