package com.linxu.algorithm.bydate.date200109;

import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2020/1/9
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：拓展，查找数据流中的第一个只出现一次的字符
 */
public class FirstOnlyOneInStream {
    public static char find(char[] stream) {
        if (stream == null || stream.length == 0) {
            throw new IllegalArgumentException("stream is null");
        }
        if (stream.length == 1) {
            return stream[0];
        }
        //base array hash
        int[] hashMap = new int[256];
        //全部初始化为-1
        GenerationUtil.memset(hashMap, -1);
        for (int i = 0; i < stream.length; i++) {
            //超过1次则是-1；而不采用次数++，减少处理机
            //如果是-1，则存放下标;如果不是-1，则放入-2表示出现多次；
            hashMap[(int) stream[i]] = hashMap[(int) stream[i]] == -1 ? i : -2;
        }
        int minIdx = stream.length - 1;
        for (int i = 0; i < stream.length; i++) {
            if (hashMap[(int) stream[i]] >= 0 && minIdx >= hashMap[(int) stream[i]]) {
                minIdx = hashMap[(int) stream[i]];
            }
        }
        return stream[minIdx];
    }

    public static void main(String[] args) {
        System.out.println(find("googte".toCharArray()));
    }
}
