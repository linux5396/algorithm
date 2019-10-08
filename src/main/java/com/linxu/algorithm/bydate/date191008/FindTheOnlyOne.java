package com.linxu.algorithm.bydate.date191008;

/**
 * @author linxu
 * @date 2019/10/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 61校招笔试题目：一个数组中有成对的整数，存在一个唯一的，找出
 */
public class FindTheOnlyOne {
    /**
     * 使用异或连续计算
     * @param numbers
     * @return
     */
    public static int findTheOnlyOne(int[] numbers) {
        int onlyOne = numbers[0];
        for (int i = 1; i < numbers.length ; i++) {
            onlyOne^=numbers[i];
        }
        return onlyOne;
    }

    public static void main(String[] args) {
        int[] arr={3,4,3,6,6};
        System.out.println(findTheOnlyOne(arr));
    }
}
