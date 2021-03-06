package com.linxu.algorithm.hot100.backtrack;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author linxu
 * @date 2020/2/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class Parenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> list = new LinkedList<>();
        track(list, "", 0, 0, n);
        return list;
    }

    private void track(List<String> list, String cur, int open, int close, int max) {
        if (cur.length() == max << 1) {
            list.add(cur);
            return;
        }
        if (open < max) {
            track(list, cur + "(", open + 1, close, max);
        }
        if (close < open) {
            track(list, cur + ")", open, close + 1, max);
        }
    }

    /**
     * open and close
     *
     * @param list
     * @param cur
     * @param open  init val is n
     * @param close init val is n
     * @param max   init val is n
     */
    private void trackReverse(List<String> list, String cur, int open, int close, int max) {
        if (cur.length() == max << 1) {
            list.add(cur);
            return;
        }
        //剪枝左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节
        if (open > close)
            return;
        if (open > 0) {
            track(list, cur + "(", open - 1, close, max);
        }
        if (close > 0) {
            track(list, cur + ")", open, close - 1, max);
        }
    }

    /**
     * review
     * @param n
     * @param close
     * @param open
     * @param cur
     * @param list
     */
    private void track(int n, int close, int open, String cur, List<String> list) {
        if (cur.length() == n << 1) {
            list.add(cur);
            return;
        }
        //闭小于开，则剪枝
        if (close < open) {
            return;
        }
        if (open > 0) {
            track(n, close, open - 1, cur + "(", list);
        }
        if (close > 0) {
            track(n, close - 1, open, cur + ")", list);
        }
    }

}
