package com.linxu.algorithm.bydate.date190909;

import com.linxu.algorithm.Recommend;
import com.linxu.algorithm.SaveSpace;
import com.linxu.algorithm.SaveTime;

/**
 * @author linxu
 * @date 2019/9/9
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 在不修改原本的数组的情况下查找重复的元素
 * 条件：
 * 1、一共为N+1个
 * 2、数字范围为1-N
 * 3、输出任意一个重复的元素
 */
public class CheckRepeatedEleNotChange {
    /**
     * 使用N+1个空间的辅助数组
     * 把原始数据逐个复制到辅助数组，如元素为3，则复制到下标为3的位置上。
     * 进行遍历，如果复制的时候发现已经存在下标，那么即是重复数据
     * 时间复杂度为N，空间复杂度为N
     *
     * @param array
     * @return
     */
    @SaveTime// N
    public static int checkUsingN(int[] array) {
        //第一步，检查
        if (array == null || array.length == 0) {
            throw new NullPointerException("数组为空");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0 || array[i] > array.length - 1) {
                throw new IllegalArgumentException("非法数组内容");
            }
        }
        //创建空间为N+1的辅助数组
        //方便计算
        int[] help = new int[array.length + 1];
        for (int i = 1; i < array.length; i++) {
            //如果该对应下标已经存在数据，则是重复出现的数据
            if (help[array[i]] != 0) {
                return help[array[i]];
            }
            help[array[i]] = array[i];
        }
        return -1;
    }

    /**
     * 采用二分查找的思想
     *
     * @param array
     * @return
     */
    @SaveSpace//空间O(1)
    public static int check(int[] array) {
        //第一步，检查
        if (array == null || array.length == 0) {
            throw new NullPointerException("数组为空");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0 || array[i] > array.length - 1) {
                throw new IllegalArgumentException("非法数组内容");
            }
        }
        //这一个空间可以省略，只是为了方便表示
        int length = array.length;
        int start = 1;
        int end = length - 1;
        while (start <= end) {//最后出现start=end
            //计算中位线
            int middle = ((end - start) >> 1) + start;
            int count = countRange(array, length, start, middle);
            if (end == start) {//start=end=3
                if (count > 1) {//count=2
                    return start;
                } else {
                    break;
                }
            }

            if (count > (middle - start + 1)) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    private static int countRange(int[] numbers, int length, int start, int end) {
        if (numbers == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (numbers[i] >= start && numbers[i] <= end) {
                ++count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 3, 4, 5};
       // System.err.println(checkUsingN(a));
        System.out.println(check(a));
    }
}
