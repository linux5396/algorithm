package com.linxu.algorithm.bydate.date200108;

import java.util.Arrays;

/**
 * @author linxu
 * @date 2020/1/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 丑数：只包含因子2、3、5的数称为丑数。
 */
public class UglyNumber {
    /**
     * 求指定INDEX的丑数，默认1为第一个丑数。
     */
    public static int uglyNumber(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("index less than 1");
        }
        if (index == 1) {
            return 1;
        }
        //申请一块index个int的内存
        int[] numbers = new int[index];
        //init
        numbers[0] = 1;
        int nextIdx = 1;
        //m2 m3 m5为丑数范围内的对应因子最大下标，满足numbers[m_]*_<=最大丑数
        int m2 = 0;
        int m3 = 0;
        int m5 = 0;
        while (nextIdx < index) {
            //下一个丑数一定是他们中M2*2 M3*3 M5*5的最小的那一个
            //1 2 3 4 5 6 8 9 10 12
            numbers[nextIdx] = min(numbers[m2] << 1, numbers[m3] * 3, numbers[m5] * 5);
            while (numbers[m2] << 1 <= numbers[nextIdx]) {
                m2++;
            }
            while (numbers[m3] * 3 <= numbers[nextIdx]) {
                m3++;
            }
            while (numbers[m5] * 5 <= numbers[nextIdx]) {
                m5++;
            }
            ++nextIdx;
        }
        System.out.println("m2 "+m2 +" m3 "+m3+" m5 "+m5);
        //normal do.
        return numbers[index - 1];
    }

    private static int min(int... args) {
        Arrays.sort(args);
        return args[0];
    }

    /**
     * 是否是丑数
     *
     * @param number 判断是否是丑数
     * @return T OT F
     */
    private static boolean isUglyNumber(int number) {
        while (number % 2 == 0) {
            number >>= 1;
        }
        while (number % 3 == 0) {
            number /= 3;
        }
        while (number % 5 == 0) {
            number /= 5;
        }
        return number == 1;
    }

    public static void main(String[] args) {
        //1 2 3 4 5 6 8 9 10 12
        //System.out.println(isUglyNumber(12));
        System.out.println(uglyNumber(10));
    }
}
