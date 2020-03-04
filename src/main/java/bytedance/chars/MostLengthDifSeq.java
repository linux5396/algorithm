package bytedance.chars;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linxu
 * @date 2020/3/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class MostLengthDifSeq {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLength = 0;
        int curLength = 0;
        Map<Character, Integer> lastIndexs = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            lastIndexs.put(chars[i], -1);
        }
        for (int i = 0; i < chars.length; i++) {
            int preIndex=lastIndexs.get(chars[i]);
            if (preIndex<0||i-preIndex>curLength) {
                curLength++;
            } else {
                if (maxLength<curLength){
                    maxLength=curLength;
                }
                curLength=i-preIndex;
            }
            lastIndexs.put(chars[i], i);
        }
        maxLength=Math.max(curLength,maxLength);
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new MostLengthDifSeq().lengthOfLongestSubstring("pwwkew"));
        ;
    }
}
