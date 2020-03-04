package bytedance.chars;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linxu
 * @date 2020/3/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 */
public class PermutationContain {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() > s2.length()) {
            return false;
        }
        //return permutation(s1.toCharArray(), 0, s1.length() - 1, s2);
        Map<Character, Integer> needsWindows = new HashMap<>();
        Map<Character, Integer> currentWindows = new HashMap<>();
        //build needs
        for (int i = 0; i < s1.length(); i++) {
            needsWindows.put(s1.charAt(i), needsWindows.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int matchCount = 0;
        char[] ch2 = s2.toCharArray();
        int left, rifht;
        left = rifht = 0;
        while (rifht < ch2.length) {
            char rightChar = ch2[rifht];
            currentWindows.put(rightChar, currentWindows.getOrDefault(rightChar, 0) + 1);
            int needRightNums = needsWindows.getOrDefault(rightChar, 0);
            if (needRightNums > 0 && needRightNums >= currentWindows.get(rightChar)) {
                matchCount++;
            }
            //满足条件
            while (matchCount == s1.length()) {
                if ((rifht - left+1) == s1.length()) {
                    return true;
                }
                char leftChar = s2.charAt(left);
                int needLeftNums = needsWindows.getOrDefault(leftChar, 0);
                int curLeftNums = currentWindows.getOrDefault(leftChar, 0);
                if (needLeftNums > 0 && needLeftNums >= curLeftNums) {
                    matchCount--;
                }
                currentWindows.put(leftChar, curLeftNums == 0 ? 0 : curLeftNums - 1);
                left++;
            }
            rifht++;
        }
        return false;
    }

    /**
     * //TODO 这种做法会超时
     *
     * @param cur
     * @param start
     * @param end
     * @param s
     * @return
     */
    @Deprecated
    public boolean permutation(final char[] cur, int start, int end, final String s) {
        if (start == end) {
            //GenerationUtil.print(cur, false);
            return s.contains(new String(cur));
        }
        boolean can = false;
        for (int i = start; i <= end; i++) {
            swap(cur, start, i);
            can = permutation(cur, start + 1, end, s);
            if (can) {
                return true;
            }
            swap(cur, start, i);
        }
        return false;
    }

    public static void swap(char[] array, int idx1, int idx2) throws ArrayIndexOutOfBoundsException {
        if (array != null) {
            char temp = array[idx1];
            array[idx1] = array[idx2];
            array[idx2] = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println(new PermutationContain().checkInclusion("ab", "eidbaoo"));
    }
}
