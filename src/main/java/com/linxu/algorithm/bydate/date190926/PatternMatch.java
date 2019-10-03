package com.linxu.algorithm.bydate.date190926;

/**
 * @author linxu
 * @date 2019/9/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 正则表达式匹配问题，.可以代表任何一个字符，而*则表示它前面的字符可以出现任意次
 */
public class PatternMatch {
    private static final char POINT = '.';
    private static final char STAR = '*';

    public boolean matchStr(char[] str, int i, char[] pattern, int j) {
        // 边界
        if (i == str.length && j == pattern.length) {
            // 字符串和模式串都为空
            return true;
        } else if (j == pattern.length) {
            // 模式串为空
            return false;
        }
        boolean next = (j + 1 < pattern.length && pattern[j + 1] == STAR);
        // 模式串下一个字符是'*'
        if (next) {
            if (i < str.length && (pattern[j] == POINT || str[i] == pattern[j])) {
                // 要保证i<str.length，否则越界
                return matchStr(str, i, pattern, j + 2) || matchStr(str, i + 1, pattern, j);
            } else {
                return matchStr(str, i, pattern, j + 2);
            }
        } else {
            //普通的.匹配或者正常匹配处理
            if (i < str.length && (pattern[j] == POINT || str[i] == pattern[j])) {
                return matchStr(str, i + 1, pattern, j + 1);
            } else {
                return false;
            }
        }
    }

    public boolean match(char[] str, char[] pattern) {
        return matchStr(str, 0, pattern, 0);
    }
}
