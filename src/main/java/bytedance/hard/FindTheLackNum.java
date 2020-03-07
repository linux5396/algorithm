package bytedance.hard;

import com.linxu.algorithm.CostTime;

/**
 * @author linxu
 * @date 2020/3/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 比如3、4、1、-1 则第一个缺失的数据为2
 * 如
 */
public class FindTheLackNum {
    @CostTime("N")
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (inRange(len, nums[i]) && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
                //避免丢失
                i--;
            }
        }
        int i = 0;
        for (; i < len; i++) {
            if (nums[i] != i + 1) {
                break;
            }
        }
        return i + 1;
    }

    private boolean inRange(int length, int i) {
        return i > 0 && i <= length;
    }

    private void swap(int[] numbers, int i, int j) {
        numbers[i] ^= numbers[j];
        numbers[j] ^= numbers[i];
        numbers[i] ^= numbers[j];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, -1, 1};
        System.err.println(new FindTheLackNum().firstMissingPositive(arr));
    }
}
