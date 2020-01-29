package com.linxu.algorithm.bydate.date200129;


import com.linxu.algorithm.utils.GenerationUtil;

import java.util.*;

/**
 * @author linxu
 * @date 2020/1/29
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：队列的最大值问题
 */
public class TheMostValInQueue {
    /**
     * 滑动窗口的最大值问题其实就是队列的最大数问题
     *
     * @param numbers
     * @param sizeOfWindows
     * @return
     */
    public ArrayList<Integer> maxValsInSlideWindow(int[] numbers, int sizeOfWindows) {
        ArrayList<Integer> pack = new ArrayList<>();
        //special case
        if (numbers == null || numbers.length == 0 || sizeOfWindows < 1) {
            return pack;
        }
        //normal do
        //queue store numbers index .
        Deque<Integer> helpQueue = new ArrayDeque<>();
        //队列的最大数问题，满足队列从头到尾的局部整体大小情况是：大->小
        for (int i = 0; i < numbers.length; i++) {
            while (!helpQueue.isEmpty() && numbers[helpQueue.peekLast()] <= numbers[i]) {
                helpQueue.pollLast();
            }
            helpQueue.addLast(i);
            //队列首位下标如果等于当前下标-窗口大小，则过期
            if (helpQueue.peekFirst() == i - sizeOfWindows) {
                helpQueue.pollFirst();
            }
            //前size个只有一个，之后每滑动一次则有一个元素
            if (i >= sizeOfWindows - 1) {
                pack.add(numbers[helpQueue.peekFirst()]);
            }
        }
        return pack;
    }

    public static void main(String[] args) {

    }
}
