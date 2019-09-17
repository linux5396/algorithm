package com.linxu.algorithm.bydate.date190909;

import java.util.Arrays;

/**
 * @author linxu
 * @date 2019/9/10
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 二分查找的简单实现
 */
public class Test {
    /**
     * @param array 有顺序的数组
     * @return 下标
     * @throws NullPointerException 异常
     */
    public static int find(int[] array, int number) throws NullPointerException {
        if (array == null || array.length == 0) {
            throw new NullPointerException("空数组");
        }
        //不在该范围内
        if (number > array[array.length - 1] || number < array[0]) {
            return -1;
        }
        int start = 0;
        int end = array.length - 1;
        //空间复杂度为1 时间复杂度为N/2 比N快一倍
        while (start <= end) {
            int middle = (((end + start) >> 1));
            if (array[middle] == number) {
                return middle;
            }
            if (number > array[middle]) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 5, 6, 7, 8};
        System.out.println(find(array, 8));
    }
}
