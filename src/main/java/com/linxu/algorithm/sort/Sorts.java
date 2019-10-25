package com.linxu.algorithm.sort;

import com.linxu.algorithm.CostSpace;
import com.linxu.algorithm.CostTime;
import com.linxu.algorithm.Recommend;
import com.linxu.algorithm.utils.GenerationUtil;

import java.util.Stack;

/**
 * @author linxu
 * @date 2019/9/16
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class Sorts {

    public static void quickSort(int[] elems) {
        //solve special case.
        if (elems == null || elems.length == 0) {
            return;
        }
        //normal
        quickSort(elems, 0, elems.length - 1);
    }

    private static void quickSort(int[] elems, int start, int end) {
        if (start == end) {
            return;
        }
        int index = partitionWith2Sentinels(elems, start, end);
        //递归排序左半数组
        if (index > start) {
            quickSort(elems, start, index - 1);
        }
        //递归排序右半数组
        if (index < end) {
            quickSort(elems, index + 1, end);
        }
    }

    /**
     * 使用双哨兵模式
     *
     * @param arr  数组
     * @param low  低位哨兵
     * @param high 高位哨兵
     * @return 校准后的中间位置，用于实现二分
     */
    @Recommend
    //在亿级上，算法不稳定
    @CostTime("O(avg NlogN   worst N^2)")
    private static int partitionWith2Sentinels(int[] arr, int low, int high) {
        int i, j, base;
        i = low;
        j = high;
        //base就是基准位
        base = arr[low];
        while (i < j) {
            //先看右边，依次往左递减
            while (base <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (base >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换;
            //如果i>j,则证明原本顺序无需更换
            if (i < j) {
                //交换位置
                int t;
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        //here i=j; 使用meet指针使得代码具有可读性
        int meet = i = j;
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[meet];
        arr[meet] = base;
        return meet;
    }

    /**
     * @param elems
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] elems, int start, int end) {
        int small = start - 1;
        for (int index = start; index < end; index++) {
            //end as sentinel
            //把end作为base
            //这里是交换small和index的位置
            if (elems[index] < elems[end]) {
                small++;
                if (small != index) {
                    int temp = elems[index];
                    elems[index] = elems[small];
                    elems[small] = temp;
                }
            }
        }

        ++small;
        int temp = elems[small];
        elems[small] = elems[end];
        elems[end] = temp;
        return small;
    }


    /***********************栈快排*****************************/
    public static void quickSortInStack(int[] elems) {
        //solve special case.
        if (elems == null) {
            return;
        }
        //init a empty stack.
        Stack<Integer> stack = new Stack<>();
        int low = 0;
        int high = elems.length - 1;
        if (low < high)
        //stack is not empty.
        {
            stack.add(low);
            stack.add(high);
            while (!stack.empty()) {
                int hi = stack.pop();
                int lo = stack.pop();
                int middle = partitionWith2Sentinels(elems, lo, hi);
                if (lo < middle) {
                    stack.push(lo);
                    stack.push(middle - 1);
                }
                if (hi > middle) {
                    stack.push(middle + 1);
                    stack.push(hi);
                }
            }
        }
    }

    /***********************冒泡排序*************************/
    public static void bubbleSort(int[] array) {
        //solve special case.
        if (array == null) {
            return;
        }
        boolean flag;
        for (int i = 0; i < array.length; i++) {
            //优化标志位
            flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    //存在交换，需要排序，无法优化
                    flag = false;
                }
            }
            //没发生交换，跳出
            if (flag) {
                break;
            }
        }
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    /***********************选择排序**************************/
    public static void chosenSort(int[] array) {
        //solve special case.
        if (array == null) {
            return;
        }
        //normal case.
        //core idea:
        //define a index var.And find the min number idx per travel in range(i,arr.length).
        //核心思想就是每次都找到最X的数放到数组的起始段。
        for (int i = 0; i < array.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            swap(array, i, minIdx);
        }
    }

    /**********************递归归并排序************************/
    @CostTime("O(avg NlogN   worst NlogN)")
    @CostSpace("递归：O(N + LogN(递归调用栈所消耗的空间))")
    //是稳定的算法，不会在大数下出现较大浮动
    public static void mergeSort(int[] array) {
        //solve special case.
        if (array == null) {
            return;
        }
        mergeSortImpl(array, 0, array.length - 1);
    }

    private static void mergeSortImpl(int[] nums, int left, int right) {
        if (left < right) {
            //递归切割成分片
            int middle = (left + right) >> 1;
            mergeSortImpl(nums, left, middle);
            mergeSortImpl(nums, middle + 1, right);
            //分治合并
            merge(nums, left, middle, right);
        }
    }

    private static void merge(int[] nums, int left, int middle, int right) {
        int[] tempArray = new int[right - left + 1];
        //左从左开始
        int leftIdx = left;
        //右从中间位置的下一位开始
        int rightIdx = middle + 1;
        //临时索引
        int tempIdx = 0;
        //把较小的数移到新数组中
        while (leftIdx <= middle && rightIdx <= right) {
            if (nums[leftIdx] <= nums[rightIdx]) {
                tempArray[tempIdx++] = nums[leftIdx++];
            } else {
                tempArray[tempIdx++] = nums[rightIdx++];
            }
        }
        //把左边剩下的移动到数组中
        while (leftIdx <= middle) {
            tempArray[tempIdx++] = nums[leftIdx++];
        }
        //把右边剩下的移动到数组中
        while (rightIdx <= right) {
            tempArray[tempIdx++] = nums[rightIdx++];
        }
        //把合并后的数组覆盖到原本数组的对应位置上
        System.arraycopy(tempArray, 0, nums, left, tempArray.length);
    }

    /**
     * @param array
     */
    @Recommend
    @CostTime("O(avg NlogN   worst NlogN)")
    @CostSpace("非递归：O（N）")
    //归并非递归排序
    public static void mergeSortNotRecursive(int[] array) {
        //solve special case.
        if (array == null) {
            return;
        }
        //定义步长
        int stepLength = 1;
        while (stepLength < array.length) {
            mergePass(array, stepLength);
            stepLength <<= 1;
        }
    }

    private static void mergePass(int[] array, int stepLength) {
        int start = 0;
        while (start + (stepLength << 1) - 1 < array.length) {
            int middle = start + stepLength - 1;
            int end = start + (stepLength << 1) - 1;
            merge(array, start, middle, end);
            start += (stepLength << 1);
        }
        //剩下的无法进行分组，也要进行处理
        if (start + stepLength - 1 < array.length) {
            merge(array, start, start + stepLength - 1, array.length - 1);
        }
    }

    /****************************插入排序***************************/
    public static void insertSort(int[] nums) {
        if (nums != null && nums.length != 0) {
            sortByMove(nums);
        }
    }

    private static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                //通过多次的交换来插入待排元素
                if (array[j] < array[j - 1]) {
                    int t = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = t;
                }
            }
        }
    }

    private static void sortByMove(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j;
            for (j = i; j > 0 && array[j - 1] > temp; j--) {
                //通过向后移动元素来插入待排元素
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
    }




    //TEST
    public static void main(String[] args) {
        int[] arr = {7, 9, 2, 4, 1, 54};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        GenerationUtil.pause();
        System.out.println();
        int[] arr1 = {7, 9, 2, 4, 1, 54};
        quickSortInStack(arr1);
        for (int i = 0; i < arr1.length; i++) {
            System.err.print(arr1[i] + " ");
        }
        GenerationUtil.pause();
        int[] arr2 = {9, 7, 2, 4, 1, 54};
       // mergeSortNotRecursive(arr2);
        insertSort(arr2);
        GenerationUtil.print(arr2, false);
    }
}
