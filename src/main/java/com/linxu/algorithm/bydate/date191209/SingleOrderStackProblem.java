package com.linxu.algorithm.bydate.date191209;

import com.linxu.algorithm.CostTime;
import com.linxu.algorithm.utils.GenerationUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author linxu
 * @date 2019/12/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 单调栈问题：
 * 1、用单调栈解决问题：给定一个不重复的数组，查找下标为i的数组的左边、右边最接近且数值比arr[i]小的元素，并且返回；
 * 2、用单调栈解决问题：...
 */
public class SingleOrderStackProblem {
    /**
     * 解决思路:
     * 1、没有重复元素的前提下，若idx元素比peek元素小，则出栈Peek,peek{nextPeek Or -1,idx};
     * 2、否则入栈即可
     * 3、最后对栈中元素进行一次性清理，按照相同的规则。
     */
    @CostTime("O(2*N)")
    public static int[][] searchInSingleOrderStack(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("null!");
        }
        Stack<Integer> stack = new Stack<>();
        //do normal
        int[][] result = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIdx = stack.pop();
                int leftIdx = stack.isEmpty() ? -1 : stack.peek();
                result[popIdx][0] = leftIdx;
                result[popIdx][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int pop = stack.pop();
            result[pop][1] = -1;
            result[pop][0] = stack.isEmpty() ? -1 : stack.peek();
        }
        return result;
    }

    public static int[][] searchInSingleOrderStackHasRepeatedEle(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("null!");
        }
        Stack<ArrayList<Integer>> stack = new Stack<>();
        //do normal
        int[][] result = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                ArrayList<Integer> popIdxs = stack.pop();
                //下一个下标集合的最后一个
                int leftIdx = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popIdx : popIdxs
                        ) {
                    result[popIdx][0] = leftIdx;
                    result[popIdx][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()){
            List<Integer> popIdxs = stack.pop();
            //下一个下标集合的最后一个
            int leftIdx = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popIdx : popIdxs
                    ) {
                result[popIdx][0] = leftIdx;
                result[popIdx][1] = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {3,4,1,5,6,2,7};
        GenerationUtil.print(searchInSingleOrderStackHasRepeatedEle(arr));
    }
}
