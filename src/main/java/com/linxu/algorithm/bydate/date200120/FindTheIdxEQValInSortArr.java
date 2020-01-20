package com.linxu.algorithm.bydate.date200120;

/**
 * @author linxu
 * @date 2020/1/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：在一个单调递增的数组中，查找坐标与值相等的数字并且返回
 */
public class FindTheIdxEQValInSortArr {
    public static int findIdxEQVal(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int start = 0;
        int end = array.length - 1;
        int mid;
        while (start <= end) {
            mid = (start + end) >> 1;
            if (array[mid] == mid) {
                return mid;
            } else if (array[mid] > mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {-5, -1, 0, 2, 4, 7, 8};
        System.out.println(findIdxEQVal(arr));
    }
}
