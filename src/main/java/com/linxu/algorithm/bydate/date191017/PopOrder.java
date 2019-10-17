package com.linxu.algorithm.bydate.date191017;

import java.util.Stack;

/**
 * @author linxu
 * @date 2019/10/17
 * <tip>take care of yourself.everything is no in vain.</tip>
 * **************************************************************************
 * 问题：                                                                   *
 * 输入两个整数序列，第一个序列表示栈的压入顺序，                             *
 * 请判断第二个序列是否可能为该栈的弹出顺序。                                 *
 * 假设压入栈的所有数字均不相等。                                            *
 * 例如序列1,2,3,4,5是某栈的压入顺序，
 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class PopOrder {

    public boolean isPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null || pushA.length == 0 || popA.length == 0) {
            return false;
        }
        //创建辅助栈
        Stack<Integer> helpStack = new Stack<>();
        //构造三个指针
        int popPosition = 0;
        int pushPosition = 0;
        final int endPosition = popA.length - 1;
        //step1
        while (pushPosition <= endPosition) {
            if (popA[popPosition] != pushA[pushPosition]) {
                helpStack.push(pushA[pushPosition++]);
            } else {
                helpStack.push(pushA[pushPosition]);
                pushPosition++;
                break;
            }
        }

        //step2
        while (!helpStack.empty() && popPosition <= endPosition) {
            int top = helpStack.peek();
            if (top == popA[popPosition]) {
                helpStack.pop();
                popPosition++;
                //还有元素没有压入，继续压入配对
            } else if (pushPosition <= endPosition) {
                //再次执行step1
                while (pushPosition <= endPosition) {
                    if (popA[popPosition] != pushA[pushPosition]) {
                        helpStack.push(pushA[pushPosition++]);
                    } else {
                        helpStack.push(pushA[pushPosition++]);
                        break;
                    }
                }
            } else {
                //在压入到底的情况下仍然不匹配
                return false;
            }
        }
        return helpStack.empty();
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {4, 3, 5, 1, 2};
        System.out.println(new PopOrder().isPopOrder(a, b));
    }


}
