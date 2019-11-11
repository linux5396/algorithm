package com.linxu.algorithm.bydate.date191110;

import com.linxu.algorithm.data_struct.Heap;
import com.linxu.algorithm.utils.GenerationUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author linxu
 * @date 2019/11/10
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：读取数据流的中位数。
 */
public class SearchMedianInStream {
    //o2-o1使得默认小顶成为大顶堆
    private static final PriorityQueue<Integer> MAXHEAP = new PriorityQueue<>((o1, o2) -> (o2 - o1));
    private static final PriorityQueue<Integer> MINHEAP = new PriorityQueue<>();
    /**
     * 初始化大小顶堆
     */
    private static final Heap<Integer> MAX_HEAP = new Heap<>(10, (o1, o2) -> (o2 - o1));
    private static final Heap<Integer> MIN_HEAP = new Heap<>(10, (o1, o2) -> (o1 - o2));

    private static int count = 0;

    public static int searchMedian(FileInputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String numbersWithSplit = reader.readLine();
        String[] pureNumbers = numbersWithSplit.split(",");
        Integer[] numbers = format(pureNumbers);
        if (numbers != null) {
            for (Integer number : numbers) {
                //insert(number,MINHEAP,MAXHEAP);
                insert(number, MIN_HEAP, MAX_HEAP);
            }
        }

        return GenerationUtil.isEven(count) ? (MAX_HEAP.poll() + MIN_HEAP.poll()) >> 1 : MIN_HEAP.poll();
    }

    /**
     * 利用自己实现的大小顶堆
     *
     * @param num num
     * @param minHeap min
     * @param maxHeap max
     */
    private static void insert(int num, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        //1(min) 3(max)
        if (GenerationUtil.isEven(count)) {
            maxHeap.offer(num);
            //may throws a nullptr
            int temp = maxHeap.poll();
            //实质插入到min
            minHeap.offer(temp);
        } else {
            minHeap.offer(num);
            //may throws a nullptr
            int temp = minHeap.poll();
            //实质插入到max
            maxHeap.offer(temp);
        }
        count++;
    }

    private static void insert(int num, Heap<Integer> minHeap, Heap<Integer> maxHeap) {
        //1(min) 3(max)
        if (GenerationUtil.isEven(count)) {
            maxHeap.insert(num);
            int temp = maxHeap.poll();
            //实质插入到min
            minHeap.insert(temp);
        } else {
            minHeap.insert(num);
            int temp = minHeap.poll();
            //实质插入到max
            maxHeap.insert(temp);
        }
        count++;
    }

    /**
     * 格式化
     *
     * @param args some numbers
     * @return integers
     */
    private static Integer[] format(String... args) {
        if (args != null && args.length != 0) {
            Integer[] integers = new Integer[args.length];
            for (int i = 0; i < args.length; i++) {
                try {
                    integers[i] = Integer.valueOf(args[i]);
                } catch (NumberFormatException e) {
                    break;
                }
            }
            return integers;
        }
        return null;
    }


    public static void main(String[] args) {
        try {
            System.out.println(searchMedian(new FileInputStream("dt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
