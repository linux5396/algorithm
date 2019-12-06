package com.linxu.algorithm.bydate.date191207;

import com.linxu.algorithm.CostTime;
import com.linxu.algorithm.utils.GenerationUtil;

import java.util.Deque;
import java.util.LinkedList;


/**
 * @author linxu
 * @date 2019/12/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 生成窗口的最大值数组：
 * 输入一个ARRAY，以及窗口的大小，窗口每次滑动一格，输出每次窗口内的最大值。
 */
public class WindowMaxValues {
    /**
     * 遍历一次，每个元素最多入队一次 出队一次  接近于2N。
     *
     */
    @CostTime("O(N)")
    public static int[] getMaxWindowValues(int[] arr, int window) {
        //solve special case.
        if (arr == null || window < 1 || arr.length < window) {
            return null;
        }
        //do normal solution.
        //try locate
        int[] result = new int[arr.length - window + 1];
        int idx = 0;
        //make a  dequeue
        Deque<Integer> deque = new LinkedList<>();
        //do compute
        for (int i = 0; i < arr.length; i++) {
            //满足局部都是大->小的排列
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                deque.pollLast();
            }
            //把元素下标放入尾部
            deque.offerLast(i);
            //如果刚好超过窗口大小，则队列头剔除无效下标
            if (inStepBound(deque.peekFirst(), i - window)) {
                deque.pollFirst();
            }
            //达到起步
            if (i >= window - 1) {
                result[idx++] = arr[deque.peekFirst()];
            }
        }
        return result;
    }

    private static boolean inStepBound(int index, int step) {
        return index == step;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        GenerationUtil.print(getMaxWindowValues(arr, 2), false);
    }
}
