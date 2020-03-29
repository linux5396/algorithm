package com.linxu.algorithm.hot100.dp;

import com.linxu.algorithm.Recommend;

/**
 * @author linxu
 * @date 2020/3/29
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
 * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TarSumWay {
    public static int findTargetSumWaysLow(int[] nums, int S) {
        int[] ways = {0};
        find(nums, 0, 0, S, ways);
        return ways[0];
    }

    public int findTargetSumWays(int[] nums, int S) {
        long packageSum = S;
        for (int i = 0; i < nums.length; i++) {
            packageSum += nums[i];
        }
        //for 0-1
        packageSum /= 2;
        int[] dp = new int[(int) packageSum + 1];
        dp[0] = 1;
        //把val和cost都当作number[i]
        //0-1:dp[j] = max(dp[j], dp[j - cost[i]] + value[i]);
        for (int i = 0; i < nums.length; i++) {
            for (int j = (int) packageSum; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[(int) packageSum];
    }
    @Recommend
    public static int findTargetSumWaysTrue(int[] nums, int s) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // 绝对值范围超过了sum的绝对值范围则无法得到
        if (Math.abs(s) > Math.abs(sum)) return 0;

        int len = nums.length;
        // 为何是sum*2+1 因为：- 0 +，整个数组分为正负两边
        int t = sum * 2 + 1;
        int[][] dp = new int[len][t];
        // 初始化
        if (nums[0] == 0) {
            dp[0][sum] = 2;
        } else {
            dp[0][sum + nums[0]] = 1;
            dp[0][sum - nums[0]] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < t; j++) {
                // 边界
                int l = (j - nums[i]) >= 0 ? j - nums[i] : 0;
                int r = (j + nums[i]) < t ? j + nums[i] : 0;
                //dp[i][j]，[i][j]的值由
                // FIXME dp[ i ][ j ] = dp[ i - 1 ][ j - nums[ i ] ] + dp[ i - 1 ][ j + nums[ i ] ]
                //由加和减得到
                dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
            }
        }
        //FIXME 这里的sum+S，其实sum只是走出左边的，因为我们数组的定义是sum*2+1;
        //实际是要求S
        return dp[len - 1][sum + s];
    }

    /**
     * O(2^N)，每一个都有可能正或者负，进行组合
     */
    private static void find(int[] numbers, int i, int sum, int S, int[] count) {
        if (i == numbers.length) {
            if (sum == S) {
                count[0]++;
            }
        } else {
            find(numbers, i + 1, sum + numbers[i], S, count);
            find(numbers, i + 1, sum - numbers[i], S, count);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1};
        System.out.println(findTargetSumWaysLow(arr, 3));
        System.out.println(new TarSumWay().findTargetSumWays(arr, 3));
    }
}
