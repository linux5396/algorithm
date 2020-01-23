package com.linxu.algorithm.bydate.date200123;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linxu
 * @date 2020/1/23
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：递增数字和问题
 */
public class SumIsSInArray {
    /**
     * 求在一个连续的递增序列中，求和为S的任意一对数字
     *
     * @param array
     * @param sum
     * @return
     */
    public static int[] findThePairSumIsS(int[] array, int sum) {
        if (array == null || array.length < 2) {
            return null;
        }
        if ((array[array.length - 1] + array[array.length - 2]) < sum) {
            return null;
        }
        int[] pair = new int[2];
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int pairSum = (array[start] + array[end]);
            if (pairSum == sum) {
                pair[0] = array[start];
                pair[1] = array[end];
                return pair;
            } else if (pairSum > sum) {
                end--;
            } else {
                start++;
            }
        }
        //not found
        return pair;
    }

    /**
     * target is sum,1~sum之间找出连续的序列和为sum的，序列中至少有数字两个
     *
     * @param sum
     * @return
     */
    public static List<List<Integer>> findThePairsSumIsS(int sum) {
        if (sum < 3) {
            return null;
        }
        List<List<Integer>> pairs = new ArrayList<>();
        int low = 1;
        int high = 2;
        int mid = (1 + sum) >> 1;
        int curSum = low + high;
        while (low <= mid) {
            if (curSum == sum) {
                pairs.add(buildSequence(low, high));
            }
            while (curSum > sum && low <= mid) {
                curSum -= low;
                low++;
                if (curSum == sum) {
                    pairs.add(buildSequence(low, high));
                }
            }
            high++;
            curSum += high;
        }
        return pairs;
    }

    private static List<Integer> buildSequence(int start, int end) {
        List<Integer> seq = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            seq.add(i);
        }
        return seq;
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 5, 6, 11, 14, 15};
        GenerationUtil.print(findThePairSumIsS(array, 16), true);

        for (List<Integer> seq : findThePairsSumIsS(15)
                ) {
            for (Integer in : seq
                    ) {
                System.out.print(in + ",");
            }
            System.out.println();
        }
    }
}
