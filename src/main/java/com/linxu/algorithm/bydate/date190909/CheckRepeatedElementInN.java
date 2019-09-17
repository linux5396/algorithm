package com.linxu.algorithm.bydate.date190909;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author linxu
 * @date 2019/9/9
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 与查重不同，该算法是在特定的情况下进行查重：
 * 1、数字为0~N-1
 * 2、数组长度为N
 * <p>
 * 从整体上来看，是采用数组坐标与元素对应的算法。
 */
public class CheckRepeatedElementInN {
    public static int check(int[] array) throws NullPointerException, IllegalArgumentException {
        //第一步，检查
        if (array == null || array.length == 0) {
            throw new NullPointerException("数组为空");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0 || array[i] > array.length - 1) {
                throw new IllegalArgumentException("非法数组内容");
            }
        }
        for (int i = 0; i < array.length; i++) {
            while (array[i] != i) {
                if (array[i] == array[array[i]]) {
                    return array[i];
                }
                //交换
                int temp = array[i];
                array[i] = array[array[i]];
                array[array[i]] = temp;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int a[] ={3,1,2,0,2,5,3};
        System.err.println(check(a));
    }
}
