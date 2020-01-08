package com.linxu.algorithm.bydate.date200108;

import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author Lx
 * @date 1月
 * @package com.linxu.algorithm.bydate.date200108
 * 查找最长不包含重复字符的子字符串
 */
public class MostLengthSingleCharStr {
    /**
     * 字符中只有 a~z
     * 暴力解   O(N^3)
     */
    public static String findCrazily(char[] str) {
        if (str == null) {
            return null;
        }
        int maxLength = 0;
        String maxStr = str[0] + "";
        if (str.length == 1) {
            return maxStr;
        }
        for (int i = 0, j = 1; i < str.length && j < str.length; ) {
            if (findRepeate(str, i, j)) {
                i++;
                j = i + 1;
            } else {
                if (maxLength <= j - i + 1) {
                    maxLength = j - i + 1;
                    maxStr = construct(str, i, j);
                }
                j++;
            }
        }
        return maxStr;
    }

    private static boolean findRepeate(char[] str, int start, int end) {
        for (int i = start; i < end; i++) {
            for (int j = i + 1; j <= end; j++) {
                if (str[i] == str[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String construct(char[] str, int from, int to) {
        return String.valueOf(str, from, to - from + 1);
    }

    /**
     * 动态规划O(N)
     * 如果不是字母，那么用哈希表即可。
     */
    public static int findDp(char[] str) {
        if (str == null) {
            return 0;
        }
        if (str.length == 1) {
            return 1;
        }
        //表示f(i-1)
        int curLength = 0;
        int maxLength = 0;
        int[] position = new int[26];
        GenerationUtil.memset(position, -1);
        for (int i = 0; i < str.length; i++) {
            int preIdx = position[str[i] - 'a'];
            if (preIdx < 0 || i - preIdx > curLength) {
                //f(i)=f(i-1)+1;
                ++curLength;
            } else {
                if (curLength > maxLength) {
                    maxLength = curLength;
                }
                //f(i)=d;
                curLength = i - preIdx;
            }
            position[str[i]-'a']=i;
        }
        if (curLength>maxLength){
            maxLength=curLength;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(findCrazily("arabcacfr".toCharArray()));
        System.out.println(findDp("arabcacfr".toCharArray()));
    }

}
