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
    public int findTheFirstLack(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            if (inRange(numbers[i], length) && !(numbers[numbers[i] - 1] == numbers[i])) {
                swap(numbers, numbers[i] - 1, i);
            }
        }
        for (int i = 0; i < length; i++) {
            if (numbers[i] != i + 1) {
                return i + 1;
            }
        }
        return 0;
    }

    private boolean inRange(int number, int length) {
        return number > 0 && number < length;
    }

    private void swap(int[] numbers, int i, int j) {
        numbers[i] ^= numbers[j];
        numbers[j] ^= numbers[i];
        numbers[i] ^= numbers[j];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, -1};
        System.out.println(new FindTheLackNum().findTheFirstLack(arr));
    }
}
