package com.linxu.algorithm.hot100.binarysearch;

/**
 * @author linxu
 * @date 2020/2/17
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchSpinSortArr {
    /**
     * 分段二分：
     * 1、首先找出最小点；
     * 2、根据最小点，判断范围进行二分。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int index = -1;
        int left = 0;
        int right = nums.length - 1;
        int mid = left;
        while (left < right) {
            if (right - left == 1) {
                //left最终是前数组的最后，right是后数组的最前
                mid = right;
                break;
            }
            mid = (right + left) >> 1;
            if (nums[left] <= nums[mid]) {
                left = mid;
            } else if (nums[right] >= nums[mid]) {
                right = mid;
            }
        }
        int less = mid;
        boolean isNoSpin = false;
        //没有旋转的特殊情况
        if (less!=0&&nums[less-1]<nums[less]){
            isNoSpin=true;
            return binarySearch(nums,0,nums.length-1,target);
        }
        int leftMAX = less == 0 ? 0 : less - 1;
        int rightMAX = nums.length - 1;
        //System.out.println(less);
        if (target <= nums[rightMAX]) {
            index = binarySearch(nums, less, rightMAX, target);
        } else {
            index = binarySearch(nums, 0, leftMAX, target);
        }
        return index;
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        //non verify
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        //not found.
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 3,5};
        System.out.println(new SearchSpinSortArr().search(a, 1));
    }
}
