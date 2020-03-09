package com.linxu.algorithm.hot100.dp;

import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2020/3/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 打家劫舍问题
 */
public class HouseRobber {
    /**
     * [1,2,3,1]
     * 4
     *
     * @return
     */
    public int rob(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int[] dp = new int[numbers.length + 1];
        dp[0] = 0;
        dp[1] = numbers[0];
        for (int i = 2; i <= numbers.length; i++) {
            dp[i] += Math.max(dp[i - 2] + numbers[i - 1], dp[i - 1]);
        }
        return dp[numbers.length];
    }

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        int[] inp = {1,2,3,1};
        System.out.println(new HouseRobber().rob(inp));
        // System.out.println("Hello World!");
    }
}
