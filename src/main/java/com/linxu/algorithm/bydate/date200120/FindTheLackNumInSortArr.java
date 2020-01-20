package com.linxu.algorithm.bydate.date200120;

/**
 * @author linxu
 * @date 2020/1/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：一个数组长度为N-1
 * 里面所有数字都是唯一的，且范围在0~n-1之内，在范围0-N-1中只有一个数组不在该数组中，
 * 找出该数字
 */
public class FindTheLackNumInSortArr {
    /**
     * 二分查找的应用（如果数组有序，那么可以往二分查找去思考）
     * @param array
     * @return
     */
    public static int findLackNum(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        //binary search
        int start = 0;
        int end = array.length - 1;
        int mid;
        while (start <= end) {
            mid = (end + start) >> 1;
            if (array[mid] != mid) {
                if (mid == 0 || array[mid - 1] == mid - 1) {
                    return mid;
                }
                end = mid - 1;
            } else {
                start = mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr={0,1,2,3,4,5,6,8};
        System.out.println(findLackNum(arr));
    }
}
