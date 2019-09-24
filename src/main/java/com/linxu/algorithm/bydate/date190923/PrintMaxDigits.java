package com.linxu.algorithm.bydate.date190923;

/**
 * @author linxu
 * @date 2019/9/23
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 打印最大的N位数字，即输入3，则打印1、2、3、...、999
 */
public class PrintMaxDigits {
    private final static char ZERO = '0';

    /**
     * 通过数组模拟大数情况
     *
     * @param n
     * @throws IllegalArgumentException
     */
    public static void printDigitByBigChars(int n) throws IllegalArgumentException {
        //solve special case
        if (n <= 0) {
            throw new IllegalArgumentException("number of digit is null.");
        }
        char[] digits = new char[n];
        memset(digits, ZERO, true);
        while (!increment(digits)) {
            print(digits);
        }
    }

    /**
     * 基于字符数组实现递增器
     *
     * @param number
     * @return
     */
    private static boolean increment(char[] number) {
        boolean isOverFlow = false;
        int takeOver = 0;
        int length = number.length;
        for (int i = length - 1; i >= 0; i--) {
            int sum = number[i] - ZERO + takeOver;
            //都是在个位执行加1操作
            if (i == length - 1) {
                sum++;
            }
            if (sum >= 10) {
                if (i == 0) {
                    isOverFlow = true;
                } else {
                    sum -= 10;
                    takeOver = 1;
                    number[i] = (char) (ZERO + sum);
                }
            } else {
                number[i] = (char) (ZERO + sum);
                //各位相加不满足进位则跳出方法
                break;
            }
        }
        return isOverFlow;
    }

    /**
     * 通过递归实现数的组合
     *
     * @param n
     * @throws IllegalArgumentException
     */
    public static void printDigit(int n) throws IllegalArgumentException {
        //solve special case
        if (n <= 0) {
            throw new IllegalArgumentException("number of digit is null.");
        }
        char[] digits = new char[n];
        memset(digits, ZERO, true);
        for (int i = 0; i < 10; i++) {
            digits[0] = (char) (i + ZERO);
            printRecursively(digits, 0);
        }
    }

    private static void printRecursively(char[] digits, int index) {
        if (index == digits.length - 1) {
            print(digits);
            //必须返回
            return;
        }
        for (int i = 0; i < 10; i++) {
            digits[index + 1] = (char) (i + ZERO);
            printRecursively(digits, index + 1);
        }
    }

    /**
     * 模拟C++中的内存初始化函数
     *
     * @param chars    数组
     * @param initElem 初始化元素
     */
    private static void memset(char[] chars, char initElem, boolean multiThread) {
        //single init
        if (chars != null && !multiThread) {
            for (int i = 0; i < chars.length; i++) {
                chars[i] = initElem;
            }
        }
        //multi threads init.
        if (chars != null && multiThread) {
            Runnable r1 = () -> {
                for (int i = 0; i <= (chars.length >> 1); i++) {
                    chars[i] = initElem;
                }
            };
            Runnable r2 = () -> {
                for (int i = chars.length - 1; i > (chars.length >> 1); i--) {
                    chars[i] = initElem;
                }
            };
            //TODO use thread pool
            //这里会存在线程同步问题，因此，需要使用线程辅助工具，两个线程都完成了才返回。
            new Thread(r1).start();
            new Thread(r2).start();
        }
    }

    private static void print(char[] digits) {
        if (digits != null && digits.length != 0) {
            int begin = 0;
            //找到第一个非0数的标志
            boolean flag = false;
            for (int i = 0; i < digits.length; i++) {
                if (digits[i] != ZERO && !flag) {
                    flag = true;
                    begin = i;
                }
            }
            //print
            for (int i = begin; i < digits.length && flag; i++) {
                System.out.print(digits[i]);
            }
            //enter
            System.out.print(",");
        }
    }

    public static void main(String[] args) {
        /*char[] chars = {'1', '1', '0', '3'};
        print(chars);
        */
        // printDigit(2);
        printDigitByBigChars(2);
    }
}
