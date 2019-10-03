package com.linxu.algorithm.bydate.date191003;

/**
 * @author linxu
 * @date 2019/10/3
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 实现一个函数用来判断一个字符串是否表示数组（包括整数和小数）
 * 例如：
 * 字符串“+100”、“5e2”、“-123”、“3.1415”、“-1E-16”表示数值
 * 而“12e”、“123a4”、“1.2.3”、“+-5”、“12e+5.4”都不是数值
 */
public class ExpressionNumber {
    public static boolean isNumber(String str) {
        //case 1
        if (str == null) {
            return false;
        }
        //case 2
        boolean hasNoSpecial = scanSpecialCharacter(str.toCharArray(), 0);
        if (!hasNoSpecial) {
            return false;
        }
        //normal solve
        return isNumeric(str.toCharArray());
    }

    //扫描是否存在特殊字符
    private static boolean scanSpecialCharacter(char[] c, int i) {
        if (i == c.length) {
            return true;
        }
        if (('0' <= c[i] && c[i] <= '9') || c[i] == '+' || c[i] == '-' || c[i] == 'E' || c[i] == 'e' || c[i] == '.') {
            return scanSpecialCharacter(c, i + 1);
        } else {
            return false;
        }
    }

    private static boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        int[] index = new int[1];
        index[0] = 0;
        // 用于记录当前字符位置
        // 先判断A
        boolean isNumeric;
        //用于记录是否满足条件
        isNumeric = isInteger(str, index);
        // 判断B
        if (index[0] < str.length && (str[index[0]] == '.')) {
            index[0]++;
            isNumeric = isUnsignedInteger(str, index) || isNumeric;
            // .B和A.和A.B形式均可以
        }
        // 判断e后面的A
        if (index[0] < str.length && (str[index[0]] == 'e' || str[index[0]] == 'E')) {
            index[0]++;
            isNumeric = isInteger(str, index) && isNumeric;
        }
        if (isNumeric && index[0] == str.length) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isInteger(char[] str, int[] index) { // 用int[]才能传值，int的话需要定义index为全局变量
        if (index[0] < str.length && (str[index[0]] == '+' || str[index[0]] == '-')) {
            index[0]++;
        }
        return isUnsignedInteger(str, index);
    }

    private static boolean isUnsignedInteger(char[] str, int[] index) {
        int start = index[0];
        while (index[0] < str.length && (str[index[0]] - '0' <= 9 && str[index[0]] - '0' >= 0)) {
            index[0]++;
        }
        if (index[0] > start) {
            return true;
        } else {
            return false;
        }
    }


}
