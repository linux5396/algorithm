package com.linxu.algorithm.bydate.date190924;

import java.util.Arrays;

/**
 * @author linxu
 * @date 2019/9/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 实现一个整数与整数相加的加法器
 */
public class Add {
    private static final char NAGATIVE = '-';

    public static String add(int p1, int p2) {
        String s1 = String.valueOf(p1);
        String s2 = String.valueOf(p2);
        if (p1 >= 0 && p2 >= 0) {
            return addPositiveNumber(s1, s2);
        } else {
            return addNegativeNumber(s1, s2);
        }
    }

    /**
     * 负数加法的实现：模拟计算机的二进制加法
     *
     * @param s1
     * @param s2
     * @return
     */
    private static String addNegativeNumber(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        return "";
    }


    /**
     * 获取补码
     *
     * @param nagative
     * @return
     */
    private static char[] getComplement(char[] nagative) {
        //2为 [符号][0] +....具体数值
        for (int i = 2; i < nagative.length; i++) {
            //除了符号位逐位取反
            nagative[i] = (char) ('9' - nagative[i] + 1);
        }
        return nagative;
    }

    /**
     * 两个正数相加
     *
     * @param p1
     * @param p2
     * @return
     */
    private static String addPositiveNumber(String p1, String p2) {
        boolean isP1Bigger = p1.length() > p2.length();
        int maxLength = p1.length();
        if (!isP1Bigger) {
            maxLength = p2.length();
        }
        //比长度更大的加数要多一个位
        char[] result = new char[maxLength + 1];
        memset(result, '0');
        char[] c1 = p1.toCharArray();
        char[] c2 = p2.toCharArray();
        //实现交换
        if (!isP1Bigger) {
            char[] temp = c1;
            c1 = c2;
            c2 = temp;
        }
        for (int i = c1.length - 1, j = c2.length - 1; i >= 0; i--) {
            //j>0
            if (j >= 0) {
                int sum = (c1[i] + c2[j] - 2 * '0');
                if (sum >= 10) {
                    sum -= 10;
                    result[i + 1] += sum;
                    //进位
                    result[i] += 1;
                } else {
                    //没有进位
                    result[i + 1] += sum;
                }
                //节省减法操作
                j--;
            } else {
                int sum = result[i + 1] - '0' + (c1[i] - '0');
                if (sum >= 10) {
                    sum -= 10;
                    result[i + 1] += sum;
                    result[i] += 1;
                } else {
                    result[i + 1] += sum;
                }
            }
        }
        //如果首位为，则向前移一位
        if (result[0] == '0') {
            System.arraycopy(result, 1, result, 0, result.length - 1);
        }
        return Arrays.toString(result);
    }

    private static void memset(char[] chars, char initElem) {
        //single init
        if (chars != null) {
            for (int i = 0; i < chars.length; i++) {
                chars[i] = initElem;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(add(2147483647, 2147483647));
    }
}
