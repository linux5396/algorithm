package com.linxu.algorithm.bydate.date191108;

import com.linxu.algorithm.CostSpace;
import com.linxu.algorithm.CostTime;
import com.linxu.algorithm.Recommend;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linxu
 * @date 2019/11/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：输入一个数字数组，找出其中出现次数超过一半的数字，并返回输出。
 */
public class FindTheNumberOverHalf {
    /**
     * 使用hashMap的方式来求解
     *
     * @param numbers 数组
     */
    @CostTime("O（3N）")
    @CostSpace("O(N)")
    public static int findTheOverHalfNumber(int[] numbers) {
        //solve the special case.
        if (numbers == null || numbers.length == 0) {
            return -1;
        }
        //init a map.
        Map<Integer, Integer> map = new HashMap<>(numbers.length);
        //use hash map to count the number.
        //TOTAL O(2N)
        for (int i = 0; i < numbers.length; i++) {
            //O(1)
            if (map.containsKey(numbers[i])) {
                //O(1)+O(1)
                map.put(numbers[i], map.get(numbers[i]) + 1);
            } else {
                map.put(numbers[i], 1);
            }
        }
        return findImpl(numbers.length >> 1, map);
    }

    /**
     * @param half half of the numbers.length
     * @param map  counter map
     * @return the number
     */
    @CostTime("O(N)")
    private static int findImpl(int half, Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()
                ) {
            if (entry.getValue() > half) {
                return entry.getKey();
            }
        }
        return -1;
    }

    @CostTime("O(2N)")
    @CostSpace("O(1)")
    @Recommend
    public static int findTheOverHalfNumberFast(int[] numbers) {
        int key = numbers[0];
        int value = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (key == numbers[i]) {
                value++;
            } else if (value == 1) {
                try {
                    key = numbers[i];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return key;
                }
            } else {
                value--;
            }
        }
        if (value > 0 && check(key, numbers)) {
            return key;
        }
        return 0;
    }

    /**
     * check is over half or not.
     *
     * @param key     the key
     * @param numbers array
     * @return t or f
     */
    @CostTime("O(N)")
    private static boolean check(int key, int[] numbers) {
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (key == numbers[i]) {
                count++;
            }
        }
        return count > numbers.length >> 1;
    }

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        //System.out.println(findTheOverHalfNumber(num));
        System.out.println(findTheOverHalfNumberFast(num));
    }
}
