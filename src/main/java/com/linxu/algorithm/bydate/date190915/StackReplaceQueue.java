package com.linxu.algorithm.bydate.date190915;

import java.util.Stack;

/**
 * @author linxu
 * @date 2019/9/15
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 使用两个栈实现队列的appendTail和删除头部节点的功能
 */
public class StackReplaceQueue {
    //把元素添加进第一个栈，再出到第二个栈，就完成了一个队列的功能。
    private Stack<Integer> firstStack = new Stack<>();
    private Stack<Integer> nextStack = new Stack<>();

    public void appendTail(int elem) {
        firstStack.push(elem);
    }

    public int deleteHead() {
        if (nextStack.empty()) {
            while (firstStack.size() > 0) {
                int t = firstStack.pop();
                nextStack.push(t);
            }
        }
        if (nextStack.size() == 0) {
            throw new NullPointerException("空队列");
        }
        int head = nextStack.pop();
        return head;
    }

    public static void main(String[] args) {
        StackReplaceQueue inst = new StackReplaceQueue();
        for (int i = 1; i < 6; i++) {
            inst.appendTail(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(inst.deleteHead());
        }
    }
}
