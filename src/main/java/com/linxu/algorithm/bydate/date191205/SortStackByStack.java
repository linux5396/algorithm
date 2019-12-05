package com.linxu.algorithm.bydate.date191205;

import java.util.Stack;

/**
 * @author linxu
 * @date 2019/12/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 算法：使用一个辅助栈对一个数据栈进行排序。
 */
public class SortStackByStack {
    private static Stack<Integer> helpStack = new Stack<>();
    private static Stack<Integer> dataStack = new Stack<>();

    public static void sort() {
        while (!dataStack.isEmpty()) {
            int cur = dataStack.pop();
            while (!helpStack.isEmpty() && helpStack.peek() < cur) {
                dataStack.push(helpStack.pop());
            }
            helpStack.push(cur);
        }
            while (!helpStack.isEmpty()) {
                dataStack.push(helpStack.pop());
            }

    }

    public static void main(String[] args) {
        dataStack.push(4);
        dataStack.push(1);
        dataStack.push(3);
        dataStack.push(2);
        dataStack.push(7);

        sort();

        while (!dataStack.isEmpty()){
            System.out.println(dataStack.pop());
        }
    }
}
