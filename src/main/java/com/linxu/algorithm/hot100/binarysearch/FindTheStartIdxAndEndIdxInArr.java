package com.linxu.algorithm.hot100.binarysearch;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.Arrays;

/**
 * @author linxu
 * @date 2020/2/16
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTheStartIdxAndEndIdxInArr {
    /**
     * 一次二分，不能降低时间复杂度。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] idxs = new int[2];
        Arrays.fill(idxs, -1);
        if (nums == null || nums.length == 0 || target > nums[nums.length - 1] || target < nums[0]) {
            return idxs;
        }
        if (nums.length == 1 && nums[0] == target) {
            idxs[0] = 0;
            idxs[1] = 0;
            return idxs;
        }
        //normal
        int left, right;
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                idxs[0] = idxs[1] = mid;
                int mostLeft = mid, mostRight = mid;
                for (int i = mostLeft; i >= 0; i--) {
                    if (nums[i] == target) {
                        mostLeft--;
                    }
                }
                for (int i = mostRight; i < nums.length; i++) {
                    if (nums[i] == target) {
                        mostRight++;
                    }
                }
                idxs[0] = mostLeft + 1;
                idxs[1] = mostRight - 1;
                break;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        Arrays.sort(idxs);
        return idxs;
    }

    /**
     * 多次二分
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRangeInBinarySearch(int[] nums, int target) {
        int[] targetRange = new int[2];
        Arrays.fill(targetRange, -1);
        targetRange[1] = right_bound(nums, target);
        targetRange[0] = left_bound(nums, target);
        return targetRange;
    }

    int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意
        while (left < right) { // 注意
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        // target 比所有数都大
        if (left == nums.length) return -1;
        // 类似之前算法的处理方式
        return nums[left] == target ? left : -1;
    }

    int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        if (left == 0) return -1;
        return nums[left-1] == target ? (left-1) : -1;
    }


    public static void main(String[] args) {
        int[] a = {1, 3, 3, 5, 7};
        GenerationUtil.print(new FindTheStartIdxAndEndIdxInArr().searchRangeInBinarySearch(a, 2), false);
    }
}
