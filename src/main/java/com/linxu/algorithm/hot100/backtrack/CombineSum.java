package com.linxu.algorithm.hot100.backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author linxu
 * @date 2020/2/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class CombineSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new LinkedList<>();
        int curSum = 0;
        //为了剪枝
        Arrays.sort(candidates);
        combine(new LinkedList<>(), list, 0, target, 0, candidates);
        return list;
    }

    /**
     * 解法思想：决策树+剪枝+回溯
     *
     * @param curSet
     * @param list
     * @param curSum
     * @param target
     * @param index
     * @param numbers
     */
    private void combine(List<Integer> curSet, List<List<Integer>> list, int curSum, int target, int index, int[] numbers) {
        if (curSum == target) {
            list.add(new LinkedList<>(curSet));
            return;
        }
        //当i=0;则没有去重；
        for (int i = /*0*/index; i < numbers.length; i++) {
            //剪枝
            if (numbers[i] + curSum > target)
                break;
            curSet.add(numbers[i]);
            //由于每个数可以选多次，因此，index arg仍从i开始
            combine(curSet, list, curSum + numbers[i], target, i, numbers);
            curSet.remove(curSet.size() - 1);
        }
    }

    /**
     * 更加符合决策树的计算过程
     *
     * @param curSet
     * @param list
     * @param index
     * @param number
     * @param target
     */
    public void track(List<Integer> curSet, List<List<Integer>> list, int index, int[] number, int target) {
        if (target == 0) {
            list.add(new LinkedList<>(curSet));
            return;
        }
        for (int i = index; i < number.length; i++) {
            //剪枝
            if (target - number[i] < 0)
                break;
            curSet.add(number[i]);
            track(curSet, list, i, number, target - number[i]);
            curSet.remove(curSet.size() - 1);
        }
    }
}
