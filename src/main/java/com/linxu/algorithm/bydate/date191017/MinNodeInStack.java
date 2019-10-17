package com.linxu.algorithm.bydate.date191017;

import java.util.Stack;

/**
 * @author linxu
 * @date 2019/10/17
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 定义栈的数据结构，同时实现能够得到最小元素的min函数，该方法的时间复杂度为O(1)
 */
public class MinNodeInStack {
    //数据栈
    Stack<Integer> dataStack = new Stack<>();
    //辅助栈
    Stack<Integer> helpStack = new Stack<>();

    public void push(int node) {
        if (helpStack.empty()) {
            helpStack.push(node);
            dataStack.push(node);
            return;
        }
        int min = helpStack.peek();
        if (node < min) {
            helpStack.push(node);
        } else {
            helpStack.push(min);
        }
        dataStack.push(node);
    }

    public int pop() {
        if (helpStack.empty() && dataStack.empty()) {
            return 0;
        }
        helpStack.pop();
        return dataStack.pop();
    }

    public int min() {
        if (helpStack.empty() && dataStack.empty()) {
            return 0;
        }
        return helpStack.peek();
    }
}
