package com.linxu.algorithm.hot100.greedy;

import java.util.*;

/**
 * @author linxu
 * @date 2020/2/3
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * <p>
 * 你需要计算完成所有任务所需要的最短时间。
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 * <p>
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 */
public class TaskDispatcher {
    /**
     * O(tasks.length)
     * 0(1) space.
     * 体现贪心的地方在于向每个bucket放入任务的时候都是尽可能找数量最大的
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || (tasks.length == 0) || tasks.length > 10000) {
            return 0;
        }
        if (n < 0 || n > 100 || n == 0) {
            return tasks.length;
        }
        Map<Character, Integer> map = new HashMap<>(26);
        int buckets = 0;
        //有多少个都是最大的，决定了最后一个桶的大小
        int mulMaxEqualsNumTask = 0;
        for (Character ch : tasks) {
            int count = map.getOrDefault(ch, 0);
            map.put(ch, ++count);
            //任务数最大的决定buckets
            buckets = Math.max(buckets, count);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == buckets)
                mulMaxEqualsNumTask++;
        }
        //每个桶的大小N+1，放入顺序总是放数据最多的。
        return Math.max((n + 1) * buckets + mulMaxEqualsNumTask, tasks.length);
    }

    public static void main(String[] args) {
        char[] tasks = "AAAB".toCharArray();
        System.out.println(new TaskDispatcher().leastInterval(tasks, 1));
    }
}
