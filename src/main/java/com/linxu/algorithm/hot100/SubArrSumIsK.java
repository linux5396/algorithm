package com.linxu.algorithm.hot100;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linxu
 * @date 2020/3/7
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class SubArrSumIsK {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count = sum;
        //如果累计总和，在索引 i 和 j 处相差 k，即 sum[i] - sum[j] = k;
        // sum[i]−sum[j]=k，则位于索引 i 和 j 之间的元素之和是 k。
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            //类似备忘录；即key=X,表示value之前有多少个子数组和为K
            //sum-k就是判断之前有没有这个子数组，有多少个
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 2, 1};
        System.err.println(new SubArrSumIsK().subarraySum(arr, 3));
    }
}
