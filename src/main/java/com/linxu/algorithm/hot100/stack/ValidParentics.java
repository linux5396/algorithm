package com.linxu.algorithm.hot100.stack;

import java.util.Stack;

/**
 * @author linxu
 * @date 2020/2/23
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidParentics {
    public boolean isValid(String s) {
        char[] parentics = s.toCharArray();
        if ((parentics.length & 1) == 1) {
            return false;
        }
        //help stack
        Stack<Character> stack = new Stack<>();
        for (char c : parentics) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                char ch = stack.peek();
                if (match(ch, c)) {
                    stack.pop();
                }else {
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean match(char c1, char c2) {
        return c1 == '(' && c2 == ')' || c1 == '[' && c2 == ']' || c1 == '{' && c2 == '}';
    }
}
