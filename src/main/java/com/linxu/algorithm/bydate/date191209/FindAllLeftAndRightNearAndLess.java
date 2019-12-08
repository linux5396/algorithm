package com.linxu.algorithm.bydate.date191209;

import com.linxu.algorithm.CostTime;
import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2019/12/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 给定一个不重复的数组，查找下标为i的数组的左边、右边最接近且数值比arr[i]小的元素，并且返回；
 * (满足为最接近，其次只要比它小即可)
 * 如：{3,4,1} 则返回
 * {
 * {-1,2},
 * {0,2},
 * {-1,-1}
 * }
 */
public class FindAllLeftAndRightNearAndLess {
    @CostTime("O(N^2)")
    public static int[][] search(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("null!");
        }
        //do normal
        int[][] result = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            result[i][0] = findTheMostNearAndLessInLeft(i, 0, arr, arr[i]);
            result[i][1] = findTheMostNearAndLessInRight(i+1, arr.length - 1, arr, arr[i]);
        }
        return result;
    }

    private static int findTheMostNearAndLessInRight(int start, int end, int[] arr, int target) {
        int ret = -1;
        if (start==arr.length){
            return ret;
        }
        for (int i = start; i <= end; i++) {
            if (arr[i] < target) {
                ret = i;
                break;
            }
        }
        return ret;
    }
    private static int findTheMostNearAndLessInLeft(int start, int end, int[] arr, int target) {
        int ret = -1;
        if (start==arr.length){
            return ret;
        }
        for (int i = start; i >= end; i--) {
            if (arr[i] < target) {
                ret = i;
                break;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[]arr={3,4,1,5,6,2,7};
        GenerationUtil.print(search(arr));
    }
}
