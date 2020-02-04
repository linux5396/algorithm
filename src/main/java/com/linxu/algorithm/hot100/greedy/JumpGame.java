package com.linxu.algorithm.hot100.greedy;


import java.util.Arrays;

/**
 * @author linxu
 * @date 2020/2/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * ex:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class JumpGame {
    /**
     * 正向解法非贪婪：costSpaceO(N)  costTime O(N^2)
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        boolean[] steps = new boolean[nums.length];
        Arrays.fill(steps, false);
        steps[0] = nums[0] != 0;
        if (!steps[0]) {
            return nums.length == 1;
        }
        //normal
        for (int i = 0; i < nums.length; i++) {
            int rightIdx = nums[i] + i;
            //add !steps[j] condition to reduce loop
            for (int j = i; j <= rightIdx && j < nums.length && !steps[j]; j++) {
                if (steps[i]) {
                    steps[j] = true;
                }
            }
        }
        return steps[nums.length - 1];
    }

    /**
     * 贪心算法解法，O（N）
     */
    public boolean canJumpInGreedy(int[] nums) {
        int lastPos = nums.length - 1;
        //假设最后一个是可以到达的；往前推，如果最后能够到达0，那么则是可达的。
        for (int i = nums.length - 1; i >= 0; i--) {
            //贪心体现在lastPosition坐标，如果后面那些是可达的，那么，
            //我只需要保证当前位置i到lastPosition可达即可。
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 0, 4};
        System.out.println(new JumpGame().canJump(a));
    }
}
