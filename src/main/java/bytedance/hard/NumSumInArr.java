package bytedance.hard;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linxu
 * @date 2020/3/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class NumSumInArr {
    public static int[] findTwoNumberSumInSortArr(int[] arr, int target) {
        int[] indexs = new int[2];
        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            valueIndexMap.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            int diff = target - arr[i];
            if (valueIndexMap.containsKey(diff)) {
                indexs[0] = i;
                indexs[1] = valueIndexMap.get(diff);
                break;
            }
        }
        return indexs;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6, 8, 9, 11, 15, 28};
        GenerationUtil.print(findTwoNumberSumInSortArr(arr,29),false);
    }
}
