package com.linxu.algorithm.bydate.date191112;

import com.linxu.algorithm.CostTime;

/**
 * @author linxu
 * @date 2019/11/12
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：输入一个整数N，求1~N中1出现的次数，如输入12，则包含1的有1、10、11、12，1一共出现了5次
 */
public class NumberOf1Between1AndN {
    @CostTime("O(N*K)")
    public static int countOne(int N) {
        if (N < 0) {
            N = -N;
        }
        int times = 0;
        //O(N)
        for (int i = 1; i <= N; i++) {
            times += count(i);
        }
        return times;
    }

    /**
     * 对每个数字进行每一位的判断。
     *
     * @param n
     * @return
     */
    @CostTime("O(K)")
    private static int count(int n) {
        int number = 0;
        while (n != 0) {
            if (n % 10 == 1) {
                number++;
            }
            n /= 10;
        }
        return number;
    }

    /**
     * fast
     */
    @CostTime("O(logN)")
    public static int fastCount(int n) {
        if (n <= 0) {
            return 0;
        }

        int count = 0;
        //i为1 10 100 1000
        for (int i = 1; i <= n; i *= 10) {
            //计算在第i位上总共有多少个1
            count = count + (n / (10 * i)) * i;
            //不足i的部分有可能存在1
            int mod = n % (10 * i);
            //如果超出2*i -1，则多出的1的个数是固定的
            if (mod > 2 * i - 1) {
                //个位则是1个  十位则是10个
                count += i;
            } else {
                //只有大于i的时候才能会存在1
                if (mod >= i) {
                    count += (mod - i) + 1;
                }
            }
            System.err.println(count);
        }
        return count;
    }

    public static void main(String[] args) {
        //System.out.println(countOne(123));
        System.out.println(fastCount(123));
    }
}
