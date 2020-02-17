package com.linxu.algorithm.hot100.binarysearch;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.Arrays;

/**
 * @author linxu
 * @date 2020/2/17
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxLengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return lengthOfLISInBinaryAndTail(nums);
    }

    /**
     * N^2 dp[i] 表示以I元素为结尾的最长升序子串。
     *
     * @param nums
     * @return
     */
    private int lengthOfLISInDp(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        GenerationUtil.print(dp, false);
        return max;
    }

    /**
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
     * 使用NLOGN解决。
     * tais数组是一个严格的递增序列，用于二分查找；
     * tais数组的元素表示以i为结尾的最长子串。
     * 理解：tails计算到最后，是一个全局的LIS。
     * 说明：我们再看一下数组 tail[i] 的定义：长度为 i + 1 的所有最长上升子序列的结尾的最小值。
     * 因此，在遍历的过程中，我们试图让一个大的值变小是合理的。
     *
     * @param nums
     * @return
     */
    private int lengthOfLISInBinaryAndTail(int[] nums) {
        //special
        if (nums.length <= 1) {
            return nums.length;
        }
        //define
        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        //tail p
        int tailEndIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            //append tail
            //promise the aesc order
            if (tails[tailEndIdx] < nums[i]) {
                tails[++tailEndIdx] = nums[i];
            } else {
                //use binary search find the location that can insert
                int left = 0;
                int right = tailEndIdx;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (tails[mid] < nums[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                tails[left] = nums[i];
            }
        }
        GenerationUtil.print(tails, false);
        return tailEndIdx + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        new MaxLengthOfLIS().lengthOfLIS(arr);
    }
}
