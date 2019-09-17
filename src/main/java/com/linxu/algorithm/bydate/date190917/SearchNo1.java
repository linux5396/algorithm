package com.linxu.algorithm.bydate.date190917;

import java.text.NumberFormat;

/**
 * @author linxu
 * @date 2019/9/17
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class SearchNo1 {
    public static int counter = 0;

    public static int numberOf1(int n) {
        if (n < 0) {
            String sequence = Integer.toBinaryString(n);
            char[] chars = sequence.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '1') {
                    counter++;
                }
            }
        }
        if (n == 0) {
            return 0;
        }
        int rare;
        rare = n % 2;
        if (rare == 1) {
            counter++;
        }
        numberOf1(n / 2);
        return counter;
    }

    public static int bitCompute(int n) {
        int count = 0;
        //java中负数会自动转为补码
        while (n != 0) {
            //每次判断最低位是否为1
            count += (n & 1);
            n >>>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
       // System.out.println(bitCompute(-1));
        int test=-1;
        System.out.println(Integer.toBinaryString(test&0XFFFFFFFF));
    }
}
