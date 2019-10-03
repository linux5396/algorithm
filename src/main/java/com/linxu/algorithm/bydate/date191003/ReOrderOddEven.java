package com.linxu.algorithm.bydate.date191003;

import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2019/10/3
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 重新对数组进行排序，使得奇数在前，偶数在后。
 */
public class ReOrderOddEven {
    //采用策略模式进行拓展
    public static int[] reorder(int[] array,OderStrategy<Integer> strategy) {
        if (array == null) {
            return null;
        }
        int low = 0;
        int high = array.length - 1;
        while (low < high) {
            while (strategy.defaultStrategy(array[low])) {
                low++;
            }
            while (!strategy.defaultStrategy(array[high])) {
                high--;
            }
            if (low<=high) {
                int temp = array[low];
                array[low] = array[high];
                array[high] = temp;
            }
        }
        return array;
    }

    private static boolean isOdd(int number) {
        return (number & 1) == 1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        GenerationUtil.print(reorder(arr,new MyStrategy()), false);
    }
    static class MyStrategy extends OderStrategy<Integer>{
        @Override
        protected boolean defaultStrategy(Integer elem) {
            return super.defaultStrategy(elem);
        }
    }
}
