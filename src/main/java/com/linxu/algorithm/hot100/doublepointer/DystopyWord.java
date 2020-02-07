package com.linxu.algorithm.hot100.doublepointer;


import java.util.*;

/**
 * @author linxu
 * @date 2020/2/7
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * <p>
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * <p>
 * 说明：
 * <p>
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * <p>
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * 输出:
 * [0, 6]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 * <p>
 * 输入:
 * s: "abab" p: "ab"
 * <p>
 * 输出:
 * [0, 1, 2]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class DystopyWord {
    /**
     * 使用字符排列的解法，会超时。
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> idxs = new LinkedList<>();
        //special
        if (s == null || s.length() == 0) {
            return idxs;
        }
        //利用hash集合
        Set<String> wordSet = new HashSet<>();
        //构造全排列
        permutation(p.toCharArray(), 0, p.length() - 1, wordSet);
        int steps = p.length();
        for (int i = 0; i < s.length() - p.length(); i++) {
            if (wordSet.contains(s.substring(i, i + steps))) {
                idxs.add(i);
            }
        }
        return idxs;
    }

    private void permutation(char[] chars, int start, int end, Set<String> set) {
        if (start == end) {
            set.add(new String(chars));
        } else {
            for (int i = start; i <= end; i++) {
                swap(chars, i, start);
                permutation(chars, start + 1, end, set);
                swap(chars, start, i);
            }
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public List<Integer> findAnagramsInDoubleP(String s, String p) {
        List<Integer> idxs = new LinkedList<>();
        //special
        if (s == null || s.length() == 0 || p.length() > s.length()) {
            return idxs;
        }
        Map<Character, Integer> curWindows = new HashMap<>();
        Map<Character, Integer> needWindows = new HashMap<>();
        //init need
        for (int i = 0; i < p.length(); i++) {
            needWindows.put(p.charAt(i), needWindows.getOrDefault(p.charAt(i), 0) + 1);
        }
        int left, right;
        int enough = 0;
        left = right = 0;
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            curWindows.put(rightChar, curWindows.getOrDefault(rightChar, 0) + 1);
            //判断
            if (needWindows.getOrDefault(rightChar, 0) > 0 && needWindows.get(rightChar) >= curWindows.get(rightChar)) {
                enough++;
            }
            //满足条件
            while (enough == p.length()) {
                if (right - left + 1 == p.length()) {
                    idxs.add(left);
                }
                char leftChar = s.charAt(left);
                if (needWindows.getOrDefault(leftChar, 0) > 0 && needWindows.get(leftChar) >= curWindows.get(leftChar)) {
                    enough--;
                }
                curWindows.put(leftChar, curWindows.getOrDefault(leftChar, 0) == 0 ? 0 : curWindows.get(leftChar) - 1);
                left++;
            }
            right++;
        }
        return idxs;
    }

    public static void main(String[] args) {
        List<Integer> list = new DystopyWord().findAnagramsInDoubleP("abab", "ab");
        for (Integer u : list
                ) {
            System.out.println(u);
        }
    }
}
