package com.linxu.algorithm.hot100.backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author linxu
 * @date 2020/2/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,2]
 * 输出:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 */
public class SubCollectionII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        //排序
        Arrays.sort(nums);
        hasSub(new LinkedList<>(), list, nums, 0);
        return list;
    }

    private void hasSub(List<Integer> curSet, List<List<Integer>> list, int[] numbers, int i) {
        list.add(new LinkedList<>(curSet));
        for (int j = i; j < numbers.length; j++) {
            //重复元素只选择领头的
            if (j > i && numbers[j - 1] == numbers[j]) {
                continue;
            }
            curSet.add(numbers[j]);
            hasSub(curSet, list, numbers, j + 1);
            curSet.remove(curSet.size() - 1);
        }
    }
}
