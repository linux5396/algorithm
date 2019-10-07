package com.linxu.algorithm.sort;

import java.util.*;

/**
 * @author linxu
 * @date 2019/10/7
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class BucketSort {
    public static void bucketSort(int[] array, int bucketSize) {
        if (array == null || bucketSize < 1) {
            return;
        }
        //construct some bucket.
        LinkedList[] buckets = new LinkedList[bucketSize];
        //初始化桶
        for (int i = 0; i < bucketSize; i++) {
            buckets[i] = new LinkedList();
        }
        for (int i = 0; i < array.length; i++) {
            buckets[getBucketIndex(array[i])].add(array[i]);
        }
        for (int i = 0; i < bucketSize; i++) {
            Collections.sort(buckets[i]);
            printList(buckets[i],i);
            System.out.println();
        }

    }

    private static void printList(List<Integer> list,int idx) {
        // TODO Auto-generated method stub
        System.out.println("bucket"+idx);
        while (list.size() > 0) {
            System.out.print(list.remove(0) + "\t");
        }
    }

    //假设为6个桶
    private static int getBucketIndex(int number) {
        if (number > 0 && number < 100) {
            return 0;
        }
        if (number > 100 && number < 200) {
            return 1;
        }
        if (number > 200 && number < 300) {
            return 2;
        }
        if (number > 300 && number < 400) {
            return 3;
        }
        if (number > 400 && number < 500) {
            return 4;
        }
        if (number > 500 && number < 600) {
            return 5;
        } else {
            return 6;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            array[i] = random.nextInt(1000);
        }
        bucketSort(array,7);
    }
}
