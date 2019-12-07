package com.linxu.algorithm.bydate.date191208;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author linxu
 * @date 2019/12/7
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 把数组排成最小的数字：如，3,32,321；则排成 321323
 */
public class SortTheArrayToTheMinNum {

    private static Comparator<String> comparator = (o1, o2) -> (o1 + o2).compareTo(o2 + o1);

    public static String minAppend(int[] array) {
        String min="";
        if (array == null) {
            throw new IllegalArgumentException("can not be null");
        }
        String[] numStrs = new String[array.length];
        //全部转化为字符串，避免大数问题
        for (int i = 0; i < array.length; i++) {
            numStrs[i] = array[i] + "";
        }
        Arrays.sort(numStrs, comparator);
        for (String s : numStrs) {
            min+=s;
        }
        return min;
    }


    public static void main(String[] args) {
        int[] a = {3,32,321};
        System.out.println( minAppend(a));
    }
}
