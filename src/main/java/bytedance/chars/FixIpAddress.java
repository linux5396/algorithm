package bytedance.chars;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linxu
 * @date 2020/3/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class FixIpAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        //special
        if (s == null || s.length() == 0) {
            return res;
        }
        track(s,"",0,0,res);
        return res;
    }

    /**
     * 2 -3
     */
    private void track(String source, String currentStr, Integer currentLevel, Integer offset, List<String> res) {
        //满足
        if (currentLevel == 3) {
            if (inRange(source.substring(offset))) {
                res.add(currentStr + "."+source.substring(offset));
            }
            return;
        }
        //
        for (int i = 1; i <= 3; i++) {
            if (offset > source.length() || offset + i >= source.length()) {
                return;
            }
            String oldStr = currentStr;
            String tryEnlarge = source.substring(offset, offset + i);
            if (currentStr.length() == 0) {
                currentStr = tryEnlarge;
            } else {
                currentStr = currentStr + "." + tryEnlarge;
            }
            if (inRange(tryEnlarge)) {
                track(source, currentStr, currentLevel + 1, offset + i, res);
                //回溯删除
                currentStr = oldStr;
            }
        }

    }

    private boolean inRange(String str) {
        return str.length() != 0 && str.length() <= 3 && Integer.parseInt(str) >= 0 && Integer.parseInt(str) <= 255
                && (!str.startsWith("0") || str.length() <= 1);
    }
}
