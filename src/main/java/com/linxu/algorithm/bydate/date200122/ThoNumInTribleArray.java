package com.linxu.algorithm.bydate.date200122;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linxu
 * @date 2020/1/22
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：一个数字中一个数字出现了1次，其余数字出现了三次，找出这个数字。
 */
public class ThoNumInTribleArray {
    public static int findTheOneInTribleArray(int[] array, boolean useHash) {
        if (array == null || array.length == 0) {
            return -1;
        }
        //normal space O(N)+ time O(N)
        if (useHash) {
            Map<Integer, Integer> map = new HashMap<>(array.length / 3 + 1);
            for (int i = 0; i < array.length; i++) {
                if (map.containsKey(array[i])) {
                    map.put(array[i], map.get(array[i]) + 1);
                } else {
                    map.put(array[i], 1);
                }
            }
            for (Map.Entry<Integer, Integer> e : map.entrySet()
                    ) {
                if (e.getValue() == 1) {
                    return e.getKey();
                }
            }
        }
        //not use hash
        return findInBitOps(array);

    }

    private static int findInBitOps(int[] array) {
        int[] bitSum = new int[32];
        for (int i = 0; i < array.length; i++) {
            int bitMask = 1;
            //31位是低位
            for (int j = 31; j >= 0; j--) {
                int bit = array[i] & bitMask;
                if (bit != 0) {
                    bitSum[j] += 1;
                }
                bitMask <<= 1;
            }
        }
        int result = 0;
        // 0位则是高位；每算一次之后都要向左移一位。
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result += bitSum[i] % 3;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr={2,2,2,3,3,3,4,5,5,5};
        System.out.println(findTheOneInTribleArray(arr,false));
    }
}
