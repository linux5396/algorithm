package bytedance.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linxu
 * @date 2020/3/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 字节二面题目
 * 给定m个不重复的字符 [a, b, c, d]，以及一个长度为n的字符串tbcacbdata，
 * 问能否在这个字符串中找到一个长度为m的连续子串，使得这个子串刚好由上面m个字符组成，
 * 顺序无所谓，返回任意满足条件的一个子串的起始位置，未找到返回-1。比如上面这个例子，acbd，3
 */
public class ByteDanceSecInterview {
    public static int solve(char[] m, char[] n) {
        //special
        if (m == null || n == null || m.length > n.length) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < m.length; i++) {
            map.put(m[i], -1);
        }
        int left = -1;
        int cnt = 0;
        for (int i = 0; i < n.length; i++) {
            char right = n[i];
            int preIndex = map.getOrDefault(right, Integer.MIN_VALUE);
            //初始且需要
            if (preIndex == -1) {
                //计算为0，则从i开始
                if (cnt == 0) {
                    left = i;
                }
                map.put(right, i);
                cnt++;
            } else if (preIndex == Integer.MIN_VALUE) {
                //如果是不需要的，则请空计数
                cnt = 0;
                left = i + 1;
            } else {
                //如果出现重复；判断一些影响范围
                int diff = i - preIndex;
                //窗口以内，则跳跃掉重复计数
                if (diff < m.length) {
                    left += diff;
                    map.put(right, i);
                    //跳跃
                    cnt = cnt - diff + 1;
                } else {
                    //窗口以外，那么不影响，正常更新
                    map.put(right, i);
                    cnt++;
                }
            }
            //匹配成功，返回最左指针
            if (cnt == m.length) {
                return left;
            }
        }
        return -1;
    }
}
