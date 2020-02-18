package com.linxu.algorithm.hot100.stack;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.Stack;

/**
 * @author linxu
 * @date 2020/2/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EveryDayTemperatures {
    public static int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        if (T == null || T.length == 0) {
            return res;
        }
        //
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int popIdx = stack.pop();
                res[popIdx] = i - popIdx;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a={73, 74, 75, 71, 69, 72, 76, 73};
        GenerationUtil.print(dailyTemperatures(a),false);
    }
}
