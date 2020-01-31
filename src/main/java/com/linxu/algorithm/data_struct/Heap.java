package com.linxu.algorithm.data_struct;

import java.util.Comparator;

/**
 * @author linxu
 * @date 2019/11/11
 * <tip>take care of yourself.everything is no in vain.</tip>
 * <p>
 * 底层存储：数组
 * </p>
 * <p>
 * 方便拓展：使用比较器
 * </p>
 * <p>
 * 核心要求：坐标从1开始，减少每次的+-操作，节省CPU片段，坐标>>1即得父节点坐标
 * </p>
 * <code>
 *     核心插入实现算法：
 *     //获取下一个空位
 *     int hole = ++currentSize;
 *         //0坐标保存插入元素
 *         for (array[0] = x; comparator.compare(x, array[hole >> 1]) < 0 && hole > 0; hole >>= 1) {
 *             //父节点持续下沉
 *             array[hole] = array[hole >> 1];
 *         }
 *         //合适的插入位置
 *         array[hole] = x;
 * </code>
 * <code>
 *     //调整算法
 *      private void shiftDown(int hole) {
 *         int child;
 *         T tmp = array[hole];
 *         // hole = child只有在出现一次交换之后才会执行，否则是直接break
 *         //因为交换一次，导致后面的也可能是局部失序，因为，hole=child,是因为上一次交换就是hole与child
 *         //所以要从child开始
 *         for (; hole << 1 <= currentSize; hole = child) {
 *             child = hole << 1;
 *             //case1 保证child+1不会越界
 *             //case2 如果child与child+1哪个更小
 *             if (child != currentSize &&
 *                     comparator.compare(array[child + 1], array[child]) < 0) {
 *                 child++;
 *             }
 *             //更小的child如果比tmp小，则与tmp交换
 *             if (comparator.compare(array[child], tmp) < 0) {
 *                 array[hole] = array[child];
 *             } else {
 *                 break;
 *             }
 *         }
 *         array[hole] = tmp;
 *     }
 * </code>
 */
public class Heap<T extends Comparable<? super T>> {

    private static final int DEFAULT_CAPACITY = 10;
    private final Comparator<? super T> comparator;
    /**
     * Number of elements in heap
     */
    private int currentSize;
    /**
     * The heap array
     */
    private T[] array;

    /**
     * Construct the binary heap.
     */
    public Heap() {
        this(DEFAULT_CAPACITY, null);
    }

    /**
     * Construct the binary heap.
     *
     * @param capacity the capacity of the binary heap.
     */
    @SuppressWarnings("unchecked")
    public Heap(int capacity, Comparator<? super T> comparator) {
        currentSize = 0;
        array = (T[]) new Comparable[capacity + 1];
        this.comparator = comparator;
    }

    /**
     * Construct the binary heap given an array of items.
     */
    @SuppressWarnings("unchecked")
    public Heap(T[] items, Comparator<? super T> comparator) {
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];
        this.comparator = comparator;
        int i = 1;
        for (T item : items) {
            array[i++] = item;
        }
        buildHeap();
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     *
     * @param x the item to insert.
     */
    public void insert(T x) {
        if (currentSize == array.length - 1) {
            enlargeArray(array.length << 1 + 1);
        }
        /*
         * hole/2是当前空穴的父节点的数组下标，如果x比父节点的元素小，则父节点元素下沉，空穴上冒
         * */
        int hole = ++currentSize;
        //0坐标保存插入元素
        for (array[0] = x; comparator.compare(x, array[hole >> 1]) < 0 && hole > 0; hole >>= 1) {
            //父节点持续下沉
            array[hole] = array[hole >> 1];
        }
        //合适的插入位置
        array[hole] = x;
    }

    /**
     * enlarge扩容方法
     *
     * @param newSize 新的容器大小
     */
    @SuppressWarnings("unchecked")
    private void enlargeArray(int newSize) {
        T[] old = array;
        array = (T[]) new Comparable[newSize];
        System.arraycopy(old, 0, array, 0, old.length);
    }

    /**
     * Find the smallest item in the priority queue.
     *
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public T findMin() {
        if (isEmpty()) {
            throw new UnsupportedOperationException();
        }
        return array[1];
    }

    /**
     * Remove the smallest item from the priority queue.
     *
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public T poll() {
        if (isEmpty()) {
            throw new UnsupportedOperationException();
        }

        T minItem = findMin();
        array[1] = array[currentSize--];
        shiftDown(1);

        return minItem;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     * 用数组实现堆时，元素的下标是从1开始，不是从0开始。
     * 原因是因为当插入的元素是比堆顶还小的元素时，我们不需要对堆做任何操作即可把堆冒出。
     * <p>
     * 同时，从下标1开始，有2>>1=1;3>>1=1;不需要每次都执行一次加法操作，利用一个空间位置，节省每次
     * 添加的一个加法运算时间。
     * </p>
     */
    private void buildHeap() {
        for (int i = currentSize >> 1; i > 0; i--) {
            //2、1
            shiftDown(i);
        }
    }

    /**
     * 内部方法在堆中向下渗透。
     *
     * @param hole 空洞。
     */
    private void shiftDown(int hole) {
        int child;
        T tmp = array[hole];
        // hole = child只有在出现一次交换之后才会执行，否则是直接break
        //因为交换一次，导致后面的也可能是局部失序，因为，hole=child,是因为上一次交换就是hole与child
        //所以要从child开始
        for (; hole << 1 <= currentSize; hole = child) {
            child = hole << 1;
            //case1 保证child+1不会越界
            //case2 如果child与child+1哪个更小
            if (child != currentSize &&
                    comparator.compare(array[child + 1], array[child]) < 0) {
                child++;
            }
            //更小的child如果比tmp小，则与tmp交换
            if (comparator.compare(array[child], tmp) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = tmp;
    }

    /**
     * BinarySearch if the priority queue is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        currentSize = 0;
        array = (T[]) new Comparable[DEFAULT_CAPACITY];
    }


    // BinarySearch program
    public static void main(String[] args) {
        Integer[] integers = {19, 13, 16, 14, 18};
        Heap<Integer> heap = new Heap<Integer>(integers, (o1, o2) -> (o2 - o1));
       /* heap.insert(19);
        heap.insert(13);
        heap.insert(16);
        heap.insert(14);
        heap.insert(18);*/
        for (int i = 0; i < 5; i++) {
            System.err.println(heap.poll());
        }
        System.out.println(heap.currentSize);


    }
}
