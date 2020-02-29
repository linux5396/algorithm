package com.linxu.algorithm.sort;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.Random;

/**
 * @author linxu
 * @date 2020/2/29
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class HeapSort {
    static  int times=0;
    public static void sort(int[] array) {
        //non verify
        for (int i = array.length; i > 1; i--) {
            shift(array, i);
            swap(array, 0, i - 1);
        }
        System.out.println("h t"+times);
    }

    private static void shift(int[] array, int length) {
        int i;
        for (i = (length >> 1) - 1; i >= 0; i--) {
            //判断左子树
            if ((i << 1) + 1 < length && array[i] < array[(i << 1) + 1]) {
                swap(array, i, ((i << 1) + 1));
            }
            //判断右子树
            if ((i << 1) + 2 < length && array[i] < array[(i << 1) + 2]) {
                swap(array, i, ((i << 1) + 2));
            }
        }
    }
    /**
     * by xor
     */
    private static void swap(int[] array, int top, int tail) {
        if (tail < 0) {
            return;
        }
        times++;
        array[top] ^= array[tail];
        array[tail] ^= array[top];
        array[top] ^= array[tail];
    }

    public static void main(String[] args) {
        //交换之后检查被shiftDown的节点是否满足与下边节点的关系;这一步完全不需要
               /* if ((((((i<< 1) + 1) << 1) + 1) < length && array[(i << 1) + 1] < array[(((i << 1) + 1) << 1) + 1])
                        ||
                        ((((i<< 1) + 1)<< 1) + 2 < length && array[(i << 1) + 1] < array[(((i<< 1) + 1)<< 1) + 2])
                        ) {
                    //re check
                    shift(array, length);
                }*/
        //交换之后检查被shiftDown的节点是否满足与下边节点的关系
               /* if (((((i << 1) + 2) << 1) + 1 < length && array[(i << 1) + 2] < array[(((i << 1) + 2) << 1) + 1])
                        ||
                        ((((i << 1) + 2) << 1) + 2 < length && array[(i << 1) + 2] < array[(((i << 1) + 2) << 1) + 2])
                        ) {
                    //re check
                    shift(array, length);
                }*/
        int[] a = new int[10000*3];
        int[] quick = new int[10000*3];
        Random random = new Random();
        for (int i = 0; i < 10000*3; i++) {
            a[i] = random.nextInt(50000);
            quick[i] = a[i];
        }
        System.err.println("start...");
        long start = System.currentTimeMillis();
        Sorts.quickSort(quick);
        long end = System.currentTimeMillis();
        System.out.println("quick:" + (end - start) + "");
       // GenerationUtil.print(quick, false);
        start = System.currentTimeMillis();
        sort(a);
        System.out.println("heap:" + (System.currentTimeMillis() - start) + "");
       // GenerationUtil.print(a, false);
    }
}
