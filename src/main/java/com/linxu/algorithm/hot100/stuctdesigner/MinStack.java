package com.linxu.algorithm.hot100.stuctdesigner;

import java.util.Stack;

/**
 * @author linxu
 * @date 2020/2/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class MinStack {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> helpStack = new Stack<>();

    public MinStack() {

    }

    public void push(int x) {
        //put data
        dataStack.push(x);
        //put help Min
        if (helpStack.isEmpty()) {
            helpStack.push(x);
        } else {
            int min = helpStack.peek();
            if (min >= x) {
                helpStack.push(x);
            } else {
                helpStack.push(min);
            }
        }
    }

    public void pop() {
        if (!dataStack.isEmpty()) {
            dataStack.pop();
            helpStack.pop();
        }
    }

    public int top() {
        return dataStack.isEmpty() ? -1 : dataStack.peek();
    }

    public int getMin() {
        return helpStack.isEmpty() ? -1 : helpStack.peek();
    }
}
