package com.linxu.algorithm.bydate.date191210;

/**
 * @author Lx
 * 问题：把数字翻译成字符串，0-a、1-b、2-c...25-z；
 * 如123 -> {
 * 1 2 3，
 * 12，3
 * 1，23
 * }.ways=3种
 */
public class TranslatorCounter {
    public static int countTranslationWays(int num, boolean useDp) {
        //solve special
        if (num < 0) {
            return 0;
        }
        //fast get
        if (num < 10) {
            return 1;
        }
        //normal case.
        //make nums to chars
        char[] nums = String.valueOf(num).toCharArray();
        if (useDp) {
            return dp(nums);
        }
        return f(nums, 0);
    }

    /**
     * 递归求解：会存在一些问题的重复求解，效率不高。
     */
    private static int f(char[] arr, int i) {
        if (i == arr.length - 1 || i == arr.length) {
            return 1;
        }
        return f(arr, i + 1) + inRange(arr, i, i + 1) * f(arr, i + 2);
    }

    /**
     * f(n)=0,f(n-1)=1,求f(0)
     * <p>
     * <p>12258
     * f(4) = 1
     * f(3) = f(4)+0 = 1
     * f(2) = f(3)+f(4) = 2
     * f(1) = f(2)+f(3) = 3
     * f(0) = f(1)+f(2) = 5
     *
     * @param arr
     * @return
     */
    private static int dp(char[] arr) {
        int[] dp = new int[arr.length];
        int count = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i < arr.length - 1) {
                //继承上一轮的结果
                count = dp[i + 1];
            } else {
                //first round
                count = 1;
            }
            //normal compute
            if (i < arr.length - 1) {
                //在范围内
                if (inRange(arr, i, i + 1) == 1) {
                    if (i < arr.length - 2) {
                        //有两步可以走，加两步
                        count += dp[i + 2];
                    } else {
                        //没有两步可以走，只能加当前的一位
                        count += 1;
                    }
                }
            }
            dp[i] = count;
        }
        return count;
    }

    /**
     * 判断是否在正常范围中。
     */
    private static int inRange(char[] arr, int firstIdx, int secIdx) {
        if (arr[firstIdx] == '2') {
            return (Integer.valueOf(arr[secIdx] + "") < 6) ? 1 : 0;
        }
        if (arr[firstIdx] == '0') {
            return 0;
        } else return arr[firstIdx] == '1' ? 1 : 0;
    }

    public static void main(String[] args) {
        System.out.println(countTranslationWays(12258, true));
    }


}
