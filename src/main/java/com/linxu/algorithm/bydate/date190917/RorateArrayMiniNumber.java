package com.linxu.algorithm.bydate.date190917;

import com.linxu.algorithm.Recommend;

/**
 * @author linxu
 * @date 2019/9/17
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 查找旋转数组的最小数字。
 * 输入一个递增排序的一个数组的一个旋转，输出数组中的最小元素
 * 例如输入，{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，输出1.
 */
public class RorateArrayMiniNumber {
    /**
     * 求出数组中的最小元素，复杂度为O(N)
     *
     * @param arr array
     * @return min
     */
    public static int minNumberInOrder(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int min = arr[0];
        for (int a : arr) {
            if (min > a) {
                min = a;
            }
        }
        return min;
    }

    /**
     * 利用旋转的特性，从而快速查找，降低时间复杂度。
     * 使用二分查找
     *
     * @param arr array
     * @return min
     */
    public static int fastMinNumber(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int start = 0;
        int end = arr.length - 1;
        //初始化
        int middle = 0;
        //最终第一个指针指向前面递增数组的最后一个元素
        //第二个指针指向后面递增数组的第一个元素
        //这个时候第二个指针就是最小的。
        while (arr[start] >= arr[end]) {
            //如果只有两个
            if (end - start == 1) {
                middle = end;
                break;
            }
            //正常情况下有arr[start]>=arr[end]
            middle = ((end + start)>>1);
            //Special CASE:出现三个重复的，如{1,0,1,1,1},{1,1,1,0,1}会判断不出
            //因此需要调用完全遍历
            if (arr[start] == arr[end] && arr[start] == arr[middle]) {
                return minNumberInOrder(arr);
            }
            //前子数组
            if (arr[middle] >= arr[start]) {
                start = middle;
                //后子数组
                //if arr[middle] < arr[end] 则证明[middle]<[start]
            } else if (arr[middle] < arr[end]) {
                end = middle;
            }
        }

        return arr[middle];
    }

    public static void main(String[] args) {
        int[] test = {3,4,5,1,2};
        System.out.println(fastMinNumber(test));
    }
}
