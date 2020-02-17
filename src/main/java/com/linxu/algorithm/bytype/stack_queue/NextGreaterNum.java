package com.linxu.algorithm.bytype.stack_queue;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author linxu
 * @date 2020/2/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NextGreaterNum {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int[] res = new int[nums.length];
        Stack<Integer> singleStack = new Stack<>();
        for (int i = (nums.length << 1) - 1; i >= 0; i--) {
            while (!singleStack.isEmpty() && nums[singleStack.peek()] <= nums[i % nums.length]) {
                int popIdx = singleStack.pop();
                int leftIdxActNextGreaterIdx = singleStack.isEmpty() ? -1 : singleStack.peek();
                res[popIdx] = leftIdxActNextGreaterIdx == -1 ? -1 : nums[leftIdxActNextGreaterIdx];
            }
            singleStack.push(i % nums.length);
        }
        return res;
    }
    //这里的single stack只存在空的情况；有些情况要分stack是否是空处理；
    //找一边的话，single stack会是空的；
    //找两边的话，single stack存在非空的情况；

    public static void main(String[] args) {
        int[] a = {1, 2, 1};
        GenerationUtil.print(new NextGreaterNum().nextGreaterElements(a), false);
    }
}
