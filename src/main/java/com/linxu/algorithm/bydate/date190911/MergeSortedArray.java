package com.linxu.algorithm.bydate.date190911;

import java.util.Arrays;

/**
 * @author linxu
 * @date 2019/9/11
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 1、数组A是有序的
 * 2、数组B也是有序的
 * 3、数组A有足够包含数组A（含有元素）、B的内存大小
 * 4、实现将AB合并，同时是排序的
 */
public class MergeSortedArray {
    /**
     * 从后往前判断，并将A中元素逐个后移
     * 当两者都比较大时，需要的时间效率接近于N^2
     *
     * @param a
     * @param b
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public static int[] merge(int[] a, int[] b) throws NullPointerException, IllegalArgumentException {
        if (a == null || b == null) {
            throw new NullPointerException("A、B存在空数组");
        }
        int i = 0;
        //遍历N次，在实际应用中是必要的，避免做无用功
        while (a[i] != 0) {
            i++;
        }
        //此时的i就是A数组中的实际元素末尾
        if ((a.length - (i + 1) < b.length)) {
            throw new IllegalArgumentException("数组A的长度不够");
        }
        for (int j = b.length - 1; j >= 0; ) {
            //每一次添加一个到A，记得i++
            if (j < b.length - 1) {
                i++;
            }
            for (int k = i - 1; k >= 0; k--) {
                if (b[j] >= a[k]) {
                    //执行后移
                   /* for (int t = i; t > k; t--) {
                        a[t] = a[t - 1];
                    }*/
                    System.arraycopy(a, k, a, k + 1, i - k);
                    //执行插入
                    a[k + 1] = b[j];
                    j--;
                    //插入成功，执行B数组的下一个迁移
                    break;
                }
            }
        }
        return a;
    }

    /**
     * 利用排序算法进行合并 时间效率为NlogN
     *
     * @param a
     * @param b
     * @return
     */
    public static int[] mergeBySorted(int[] a, int[] b) {
        if (a == null || b == null) {
            throw new NullPointerException("A、B存在空数组");
        }
        int i = 0;
        //遍历N次，在实际应用中是必要的，避免做无用功
        while (a[i] != 0) {
            i++;
        }
        //此时的i就是A数组中的实际元素末尾
        if ((a.length - (i + 1) < b.length)) {
            throw new IllegalArgumentException("数组A的长度不够");
        }
        //将B的内容迁移到A
        for (int t = 0; t < b.length; t++) {
            a[i + t] = b[t];
        }
        Arrays.sort(a);
        return a;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 6, 8, 210, 222, 223, 224, 256, 276, 298, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] b = {1, 3, 7, 8, 9, 11, 12, 13, 14, 15, 16, 16, 18, 34, 56, 100, 267, 300, 301};
        int[] cloneA = a.clone();
        //削弱机器影响
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long ok = System.currentTimeMillis();
        mergeBySorted(cloneA, b);
        System.out.println(System.currentTimeMillis() - ok);
        long start = System.currentTimeMillis();
        merge(a, b);
        System.out.println(System.currentTimeMillis() - start);
        /*for (int s : c
                ) {
            if (s != 0) {
                System.out.println(s);
            }

        }*/
    }
}
