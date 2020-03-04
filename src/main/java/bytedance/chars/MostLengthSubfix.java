package bytedance.chars;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linxu
 * @date 2020/3/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：最长公共前缀
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 */
public class MostLengthSubfix {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder res = new StringBuilder();
        if (strs == null || strs.length == 0) {
            return res.toString();
        }
        List<String> list = new ArrayList<>();
        int mostLessLength = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
            list.add(strs[i]);
            mostLessLength = mostLessLength > strs[i].length() ? strs[i].length() : mostLessLength;
        }
        //search
        for (int i = 0; i < mostLessLength; i++) {
            int rowCount = 0;
            char col = strs[0].charAt(i);
            for (String s : list) {
                if (col == s.charAt(i)) {
                    rowCount++;
                }
            }
            if (rowCount == list.size()) {
                res.append(col);
            } else {
                break;
            }
        }
        return res.toString();
    }
}
