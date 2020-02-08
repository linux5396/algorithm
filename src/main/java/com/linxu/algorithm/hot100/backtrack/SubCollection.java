package com.linxu.algorithm.hot100.backtrack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author linxu
 * @date 2020/2/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubCollection {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        hasSub(new LinkedList<>(), list, nums, 0);
        return list;
    }

    private void hasSub(List<Integer> curSet, List<List<Integer>> list, int[] numbers, int i) {
        list.add(new LinkedList<>(curSet));
        if (i == numbers.length) {
            return;
        }
        for (int j = i; j < numbers.length; j++) {
            curSet.add(numbers[j]);
            hasSub(curSet, list, numbers, j + 1);
            curSet.remove(curSet.size() - 1);
        }
    }

    /**
     * 二进制解
     *
     * @return
     */
    public List<List<Integer>> binaryBit(int[] numbers) {
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < (1 << numbers.length); i++) {
            List<Integer> sub = new LinkedList<>();
            for (int j = 0; j < numbers.length; j++) {
                if (((i >> j) & 1) == 1) {
                    sub.add(numbers[j]);
                }
            }
            list.add(sub);
        }
        return list;
    }

}
