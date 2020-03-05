package bytedance.arrnsort;

import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2020/3/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 */
public class KthMaxEle {
    public int findKthLargest(int[] nums, int k) {
        //non verify
        int length = nums.length;
        for (int i = length; i > 1; i--) {
            shift(nums, i);
            swap(nums, 0, i - 1);
        }
        return k<=length?nums[k-1]:-1;
    }

    private void shift(int[] arr, int length) {
        for (int i = (length >> 1); i >= 0; i--) {
            //比较左
            int leftIdx = (i << 1) + 1;
            int rightIdx = leftIdx + 1;
            if (leftIdx < length && arr[i] > arr[leftIdx]) {
                swap(arr, leftIdx, i);
            }
            if (rightIdx < length && arr[i] > arr[rightIdx]) {
                swap(arr, rightIdx, i);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 6, 4};
        System.out.println(new KthMaxEle().findKthLargest(arr, 2));
        GenerationUtil.print(arr, false);
    }
}
