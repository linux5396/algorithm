package com.linxu.algorithm.hot100.binarysearch;

import com.linxu.algorithm.CostTime;
import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2020/2/15
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMidInTwoArr {
    /**
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        //处理任何一个nums为空数组的情况
        if (m == 0) {
            if (n % 2 != 0)
                return 1.0 * nums2[n / 2];
            return (nums2[n / 2] + nums2[n / 2 - 1]) / 2.0;
        }
        if (n == 0) {
            if (m % 2 != 0)
                return 1.0 * nums1[m / 2];
            return (nums1[m / 2] + nums1[m / 2 - 1]) / 2.0;
        }
        //normal
        int total = m + n;
        //总数为奇数，找第 total / 2 + 1 个数
        if ((total & 1) == 1) {
            return findKth(nums1, nums2, 0, 0, total / 2 + 1);
        }
        //总数为偶数，找第 total / 2 个数和第total / 2 + 1个数的平均值
        return (findKth(nums1, nums2, 0, 0, total / 2) + findKth(nums1, nums2, 0, 0, total / 2 + 1)) * 0.5;
    }

    private double findKth(int[] numbers_1, int[] numbers_2, int index1, int index2, int k) {
        //如果1数组完了，剩下需要的K个只可能在2数组
        if (index1 >= numbers_1.length) {
            return numbers_2[index2 + k - 1];
        }
        //如果2数组完了，剩下需要的K个只可能在1数组
        if (index2 >= numbers_2.length) {
            return numbers_1[index1 + k - 1];
        }
        //如果只需要一个
        if (k == 1) {
            return Math.min(numbers_1[index1], numbers_2[index2]);
        }
        //如果都没用完，继续二分
        //numbers1的第k/2个
        int halfKofNumber1 = Integer.MAX_VALUE;
        //numbers2的第k/2个
        int halfKofNumber2 = Integer.MAX_VALUE;
        if (index1 + (k / 2) - 1 < numbers_1.length) {
            halfKofNumber1 = numbers_1[index1 + (k / 2) - 1];
        }
        if (index2 + (k / 2) - 1 < numbers_2.length) {
            halfKofNumber2 = numbers_2[index2 + (k / 2) - 1];
        }
        //执行二分判断
        if (halfKofNumber1 < halfKofNumber2) {
            //在1的后半段继续找剩下的0.5K
            return findKth(numbers_1, numbers_2, index1 + (k / 2), index2, k - (k / 2));
        } else {
            //在2的后半段找剩下的0.5K
            //注意 这里的k-(k/2)不能直接为k/2
            //比如K=5；k/2=2,剩下的就是5-2=3；
            return findKth(numbers_1, numbers_2, index1, index2 + (k / 2), k - (k / 2));
        }
    }


    /**
     * 使用合并法，需要N+M
     *
     * @param nums1
     * @param nums2
     * @return
     */
    @CostTime("O(N+M)")
    public int[] findMedianSortedArraysInMerge(int[] nums1, int[] nums2) {
        int[] tempMerge = new int[nums1.length + nums2.length];
        int leftIdx = 0;
        int rightIdx = 0;
        int curIdx = 0;
        while (leftIdx < nums1.length || rightIdx < nums2.length) {
            while (leftIdx < nums1.length && rightIdx < nums2.length) {
                tempMerge[curIdx++] = nums1[leftIdx] < nums2[leftIdx] ? nums1[leftIdx++] : nums2[rightIdx++];
            }
            while (leftIdx < nums1.length) {
                tempMerge[curIdx++] = nums1[leftIdx++];
            }
            while (rightIdx < nums2.length) {
                tempMerge[curIdx++] = nums2[rightIdx++];
            }
        }
        //find the mid.即可
        return tempMerge;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3};
        int[] arra = {2};
        System.out.println(new FindMidInTwoArr().findMedianSortedArrays(arr, arra));
    }
}
