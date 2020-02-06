package com.linxu.algorithm.hot100.doublepointer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author linxu
 * @date 2020/2/6
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinCoverageSubStr {
    public String minWindow(String s, String t) {
        if (s == null || "".equals(s)) {
            return "";
        }
        Map<Character, Integer> curWindows = new HashMap<>();
        Map<Character, Integer> needWindows = new HashMap<>();
        //init needWindows
        for (int i = 0; i < t.length(); i++) {
            int count = needWindows.getOrDefault(t.charAt(i), 0);
            needWindows.put(t.charAt(i), count + 1);
        }
        int left, right;
        left = right = 0;
        //窗口中包含需要字符的长度
        int length = 0;
        int minLength = Integer.MAX_VALUE;
        String res = "";
        while (right < s.length()) {
            char ch = s.charAt(right);
            //把当前字符添加到当前窗口中
            curWindows.put(ch, curWindows.getOrDefault(ch, 0) == 0 ? 1 : curWindows.get(ch) + 1);
            //如果当前字符是需要的：
            if (needWindows.getOrDefault(ch, 0) > 0 && needWindows.getOrDefault(ch, 0) >=curWindows.getOrDefault(ch, 0)) {
                length++;
            }
            while (length == t.length()) {
                char leftChar = s.charAt(left);
                //尝试记录最小串
                if (right - left - 1 < minLength) {
                    minLength = right - left - 1;
                    res = s.substring(left, right + 1);
                }
                //如果左边位是需要的，那么包含长度减1
                if (needWindows.getOrDefault(leftChar, 0) > 0 && needWindows.getOrDefault(leftChar, 0) >= curWindows.getOrDefault(leftChar, 0)) {
                    length--;
                }
                int leftCharInCurWindowNumber = curWindows.getOrDefault(leftChar, 0);
                curWindows.put(leftChar, leftCharInCurWindowNumber == 0 ? 0 : --leftCharInCurWindowNumber);
                left++;
            }
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MinCoverageSubStr().minWindow("bba", "ab"));
    }
}
