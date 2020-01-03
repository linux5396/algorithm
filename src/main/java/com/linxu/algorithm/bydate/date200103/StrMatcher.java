package com.linxu.algorithm.bydate.date200103;

/**
 * @author Lx
 * @date 12月
 * @package com.linxu.algorithm.bydate.date191217
 * 蛮力法解 字符串匹配
 */
public class StrMatcher {
    public static boolean isMatches(String text, String pattern) {
        if (text == null && pattern == null) {
            return true;
        }
        if (text == null) {
            return false;
        }
        if (pattern == null) {
            return false;
        }
        char[] textArr = text.toCharArray();
        char[] patternArr = text.toCharArray();
        for (int i = 0; i < text.length() - pattern.length(); i++) {
            int idx = 0;
            while (idx<patternArr.length-1&&patternArr[idx]==textArr[i+idx]){
                idx++;
                System.err.println(idx);
                if (idx==patternArr.length-1){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isMatches("oktestmyuloveghhhs","myulove"));
    }
}
