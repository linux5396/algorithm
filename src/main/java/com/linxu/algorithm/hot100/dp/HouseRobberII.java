package com.linxu.algorithm.hot100.dp;

import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2020/3/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 打家劫舍进阶，第一个房子和最后一个房子是紧挨着的。
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
       /* int[] first = new int[nums.length - 1];
        System.arraycopy(nums, 0, first, 0, nums.length - 1);
        int[] after = new int[nums.length - 1];
        System.arraycopy(nums, 1, after, 0, nums.length - 1);*/
        // return Math.max(robImpl(first), robImpl(after));
        return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length - 1));
    }

    private int robImpl(int[] numbers) {
        int[] dp = new int[numbers.length + 1];
        dp[0] = 0;
        dp[1] = numbers[0];
        for (int i = 2; i <= numbers.length; i++) {
            dp[i] += Math.max(dp[i - 2] + numbers[i - 1], dp[i - 1]);
        }
        //GenerationUtil.print(dp, false);
        return dp[dp.length - 1];
    }

    private int rob(int[] numbers, int start, int length) {
        int[] dp = new int[length + 1 ];
        dp[0] = 0;
        dp[1] = numbers[start];
        for (int i = 2; i <= length; i++) {
            //记得+start
            dp[i] += Math.max(dp[i - 2] + numbers[i + start - 1], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        int[] inp = {2,3,2};
        System.out.println(new HouseRobberII().rob(inp));
        // System.out.println("Hello World!");
    }
}
