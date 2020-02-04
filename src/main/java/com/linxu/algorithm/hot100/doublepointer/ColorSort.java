package com.linxu.algorithm.hot100.doublepointer;

/**
 * @author linxu
 * @date 2020/2/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class ColorSort {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int left, mid, right;
        left = mid = 0;
        right = nums.length - 1;
        while (mid <= right) {
            if (nums[mid] == 0) {
                swap(nums, left, mid);
                left++;
                //从低位换到中位的如果是0或者是1；或者从一种情况讲，由于首先会把0换给低位，所以后面从低位换上来的肯定是0，直接移动mid
                if (nums[mid] == 0 || nums[mid] == 1)
                    mid++;
            } else if (nums[mid] == 2) {
                //从高位换回来的不知道是多少，因此不动mid指针
                swap(nums, right, mid);
                right--;
            } else {
                mid++;
            }
        }
    }

    private void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
}
