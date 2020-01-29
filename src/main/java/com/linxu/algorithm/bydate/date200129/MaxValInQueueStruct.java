package com.linxu.algorithm.bydate.date200129;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author linxu
 * @date 2020/1/29
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：实现一个O（1）复杂度的队列结构；
 * 实现思路：
 * 用一个数据队列；一个最大队列的辅助队列；
 * 满足辅助队列的大小是整体的 大到小，从而能够实现O（1）的max方法。
 * 由于需要pop,因此，对元素进行封装，具有坐标和真实数据，如果pop的时候坐标等于data的坐标，则需要pop辅助队列；
 * 如果pop坐标不等，则只需要pop数据队列
 */

public class MaxValInQueueStruct {

    public static void main(String[] args) {
        MaxQueue<Integer> maxQueue = new MaxQueue<>();
        maxQueue.pushBack(2);
        maxQueue.pushBack(4);
        maxQueue.pushBack(6);
        System.out.println(maxQueue.maxEle());
        System.out.println(maxQueue.popFront());
    }

    private static class MaxQueue<T extends Comparable> {
        Deque<InnerData> dataDeque = new ArrayDeque<>();
        Deque<InnerData> maxElesDeque = new ArrayDeque<>();
        int curIdx = 0;

        /**
         * O(1)
         *
         * @param ele
         */
        @SuppressWarnings("unchecked")
        public void pushBack(T ele) {
            while (!maxElesDeque.isEmpty() && ele.compareTo(maxElesDeque.peekLast().num) >= 0) {
                maxElesDeque.pollLast();
            }
            InnerData node = buildNode(ele, curIdx++);
            maxElesDeque.addLast(node);
            dataDeque.addLast(node);
        }

        private InnerData buildNode(T num, int index) {
            return new InnerData(num, index);
        }

        /**
         * O(1)
         */
        public T popFront() {
            if (dataDeque.isEmpty() || maxElesDeque.isEmpty()) {
                throw new NullPointerException();
            }
            if (maxElesDeque.peekFirst().index == dataDeque.peekFirst().index) {
                maxElesDeque.pollFirst();
            }
            return dataDeque.pollFirst().num;
        }

        /**
         * O(1)
         *
         * @return
         */
        public T maxEle() {
            return maxElesDeque.peekFirst().num;
        }

        private class InnerData {
            T num;
            int index;

            public InnerData(T num, int index) {
                this.num = num;
                this.index = index;
            }
        }

    }
}
