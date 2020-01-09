package com.linxu.algorithm.bydate.date200109;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linxu
 * @date 2020/1/9
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：第一个只出现一次的字符。
 */
public class FirstOnlyOneChar {
    /**
     * 存在优化的点，就是直接用数组，可以省去一个hashMap。
     */
    public static char findTheFirstOnlyOne(char[] chars) {
        if (chars == null || chars.length == 0) {
            throw new IllegalArgumentException("chars be null.");
        }
        if (chars.length == 1) {
            return chars[0];
        }
        //normal do
        Map<Character, Integer> hashMap = new HashMap<>();
        Map<Character, Integer> idxMap = new HashMap<>();
        //idx map
        for (int i = 0; i < chars.length; i++) {
            idxMap.put(chars[i], i);
        }
        //O(N)
        for (char c : chars) {
            if (hashMap.containsKey(c)) {
                hashMap.put(c, (hashMap.get(c) + 1));
            } else {
                hashMap.put(c, 1);
            }
        }
        int minIdx = chars.length - 1;
        //search
        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            int idx = idxMap.get(entry.getKey());
            if (entry.getValue() == 1 && minIdx >= idx) {
                minIdx = idx;
            }
        }
        return chars[minIdx];
    }

    public static void main(String[] args) {
        System.out.println(findTheFirstOnlyOne("abcdfgeacbh".toCharArray()));
    }
}
