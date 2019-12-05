package com.linxu.algorithm.bydate.date191205;

import java.util.Stack;

/**
 * @author linxu
 * @date 2019/12/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 利用递归翻转一个栈
 */
public class ReverseStackInRecursion {

    public static void reverseStack(Stack<Integer> stack) {
        if (stack == null) {
            throw new IllegalArgumentException("栈不允许为空.");
        }
        reverse(stack);
    }

    /**
     * 获取栈的底元素并且移除
     */
    private static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    private static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        reverseStack(stack);
        //4 3 2 1
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
