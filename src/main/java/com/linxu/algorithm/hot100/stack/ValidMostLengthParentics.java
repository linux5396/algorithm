package com.linxu.algorithm.hot100.stack;

import java.util.Stack;

/**
 * @author linxu
 * @date 2020/2/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * <p>
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidMostLengthParentics {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            //左括号直接入栈
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                //否则弹出栈顶；
                stack.pop();
                //如果栈空，直接入栈
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    //否则计算坐标
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}
