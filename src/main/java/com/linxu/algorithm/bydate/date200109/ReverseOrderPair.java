package com.linxu.algorithm.bydate.date200109;

import com.linxu.algorithm.CostTime;
import com.linxu.algorithm.utils.GenerationUtil;

import java.util.*;

/**
 * @author linxu
 * @date 2020/1/9
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 查找一个数组中的逆序对：
 * {7,5,6,4}
 * 则逆序对为7，5；7，6；7，4；5，4；6，4；
 */
public class ReverseOrderPair {
    /**
     * 暴力解
     */
    @CostTime("O(N^2)")
    public static Vector<String> findReverseOrderPairs(int[] numbers) {
        Vector<String> vector = new Vector<>();
        //ex
        if (numbers == null || numbers.length == 0 || numbers.length == 1) {
            return vector;
        }
        //normal
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    vector.addElement(buildPair(numbers[i], numbers[j]));
                }
            }
        }
        return vector;
    }

    /**
     * 这里与剑指OFFER不符合，计算出来的是全局的逆序对；
     * 而题目要求的是按照数组先后顺序的逆序对。
     *
     * @param numbers
     * @return
     */
    @CostTime("O(NLogN)")
    public static int findReverseOrderPairsFast(int[] numbers) {
        //ex
        if (numbers == null || numbers.length == 0 || numbers.length == 1) {
            return 0;
        }
        //normal
        int[] copy = new int[numbers.length];
        System.arraycopy(numbers, 0, copy, 0, numbers.length);


        return recursiveMerge(numbers, copy, 0, numbers.length - 1);
    }

    private static int recursiveMerge(int[] numbers, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = numbers[start];
            return 0;
        }
        int mid = (end + start) >> 1;

        int leftCount = recursiveMerge(numbers, copy, start, mid);
        int rightCount = recursiveMerge(numbers, copy, mid + 1, end);

        int leftLastIdx = mid;
        int rightLastIdx = end;
        int idxCopy = end;
        int count = 0;
        while (leftLastIdx >= start && rightLastIdx >= mid + 1) {
            if (numbers[leftLastIdx] > numbers[rightLastIdx]) {
                copy[idxCopy--] = numbers[leftLastIdx--];
                count += rightLastIdx - mid;
            } else {
                copy[idxCopy--] = numbers[rightLastIdx--];
            }
        }

        for (; leftLastIdx >= start; leftLastIdx--) {
            copy[idxCopy--] = numbers[leftLastIdx];
        }
        for (; rightLastIdx >= mid + 1; rightLastIdx--) {
            copy[idxCopy--] = numbers[rightLastIdx];
        }
        return count + leftCount + rightCount;
    }



    private static String buildPair(int... args) {
        return args[0] + ":" + args[1];
    }

    public static void main(String[] args) {
        int[] number = {7, 5, 6, 4};
        for (String pair : findReverseOrderPairs(number)) {
            System.out.println(pair);
        }
        System.out.println(findReverseOrderPairsFast(number));

    }
}
