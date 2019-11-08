package com.linxu.algorithm.bydate.date191108;

import com.linxu.algorithm.sort.Sorts;
import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2019/11/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：输入一个数组，查找其中最小的K个数字
 */
public class FindTheLeastK {
    /**
     * 特点：速度快，但是修改原本数组的数据位置，同时需要将数组完全加载入内存
     * @param arr     arr
     * @param k       kth
     * @param useSort 是否使用排序进行
     * @return ks
     */
    public static int[] findK(int[] arr, int k, boolean useSort) {
        //solve special case
        if (arr == null || arr.length == 0 || k > arr.length) {
            return null;
        }
        //normal
        if (useSort) {
            //this is O(NlogN)利用全局有序的快排
            return findBySort(arr, k);
        } else {
            //this is O(N) 属于局部无序的快排算法
            int[] ks = new int[k];
            int start = 0;
            int end = arr.length - 1;

            int idx = GenerationUtil.partitionWith2Sentinels(arr, start, end);
            while (idx != k - 1) {
                if (idx > k - 1) {
                    end = idx - 1;
                    idx = GenerationUtil.partitionWith2Sentinels(arr, start, end);
                } else {
                    start = idx + 1;
                    idx = GenerationUtil.partitionWith2Sentinels(arr, start, end);
                }
            }
            System.arraycopy(arr, 0, ks, 0, k);
            return ks;
        }
    }

    /**
     * by sort O(NlogN)
     *
     * @param arr arr
     * @param k   k
     * @return ks
     */
    private static int[] findBySort(int[] arr, int k) {
        Sorts.quickSort(arr);
        int[] ks = new int[k];
        System.arraycopy(arr, 0, ks, 0, k);
        return ks;
    }

    /**
     * 使用“容器”技术来处理，可以在不修改原本数组的基础上
     * 效率为O(NlogK)
     * 特点：不需要修改原本数值；第二个特点，可以创建较小的堆内存，然后每次从磁盘读取一个数值，适合处理大数据
     *
     * @param arr arr
     * @param k   kth
     * @return ks
     */
    public static int[] findKInContainer(int[] arr, int k) {
        //solve special case
        if (arr == null || arr.length == 0 || k > arr.length) {
            return null;
        }
        int[] container = new int[k];
        //first row add without check full status.
        for (int i = 0; i < k; i++) {
            System.arraycopy(arr, 0, container, 0, k);
        }
        //next do
        for (int i = k; i < arr.length; i++) {
            //每次都找出容器中的最大数值的IDX与数值
            int[] maxAndIdx = GenerationUtil.findTheMaxAndIdx(container);
            if (arr[i] < maxAndIdx[0]) {
                container[maxAndIdx[1]] = arr[i];
            }
        }
        return container;
    }


    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 67, 8, 1, 3, 1};
        GenerationUtil.print(findKInContainer(arr, 3), false);
    }
}
