package com.linxu.algorithm.hot100.stack;

import java.util.Stack;

/**
 * @author linxu
 * @date 2020/2/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定有效字符串 "abc"。
 * <p>
 * 对于任何有效的字符串 V，我们可以将 V 分成两个部分 X 和 Y，使得 X + Y（X 与 Y 连接）等于 V。
 * （X 或 Y 可以为空。）那么，X + "abc" + Y 也同样是有效的。
 * <p>
 * 例如，如果 S = "abc"，则有效字符串的示例是："abc"，"aabcbc"，"abcabc"，"abcabcababcc"。
 * 无效字符串的示例是："abccba"，"ab"，"cababc"，"bac"。
 * <p>
 * 如果给定字符串 S 有效，则返回 true；否则，返回 false。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-word-is-valid-after-substitutions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidWord {
    /**
     * 每次遇到C，就弹出栈，看看是否是AB，不是就返回false.
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        char[] parentics = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : parentics
                ) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (c == 'c') {
                    char pop1 = stack.pop();
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        char pop2 = stack.pop();
                        if (!(pop1 == 'b' && pop2 == 'a')) {
                            return false;
                        }
                    }
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }
}
