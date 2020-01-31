package com.linxu.algorithm.bytype.stack_queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author linxu
 * @date 2020/1/31
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 最大值减去最小值等于num的子数组的数量,子数组可以是单个元素;
 * 解法：利用两个双向队列，一个大队、一个小队，满足可以用O（1）取出当前子数组中的最大、最小值；
 * 同时，观察规律，加入一个子数组i...j之间满足最大值-最小值<=num，那么以i为起点，且终点小于等于j的任意子数组都满足该条件。
 */
public class NumbersOfSubArrays {
    public static int getNumbersOfSubArrays(int[] rootArray, int num) {
        if (rootArray == null || rootArray.length == 0) {
            return 0;
        }
        int count = 0;
        Deque<Integer> maxQueue = new ArrayDeque<>();
        Deque<Integer> minQueue = new ArrayDeque<>();
        int i, j;
        i = 0;
        j = 0;
        while (i < rootArray.length) {
            while (j < rootArray.length) {
                if (minQueue.isEmpty() || minQueue.peekLast() != j) {
                    while (!maxQueue.isEmpty() && rootArray[maxQueue.peekLast()] <= rootArray[j]) {
                        maxQueue.pollLast();
                    }
                    maxQueue.addLast(j);
                    while (!minQueue.isEmpty() && rootArray[minQueue.peekLast()] >= rootArray[j]) {
                        minQueue.pollLast();
                    }
                    minQueue.addLast(j);
                }

                if (rootArray[maxQueue.peekFirst()] - rootArray[minQueue.peekFirst()] > num) {
                    break;
                }
                //在i不变的情况下，j++表示子数组多一
                j++;
            }
            count += (j - i);
            if (maxQueue.peekFirst() == i) {
                maxQueue.pollFirst();
            }
            if (minQueue.peekFirst() == i) {
                minQueue.pollFirst();
            }
            i++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 4, 3};
        System.out.println(getNumbersOfSubArrays(array, 1));
    }
}
