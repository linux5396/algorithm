package bytedance.arrnsort;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linxu
 * @date 2020/3/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * <p>
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * <p>
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class MostLengthLSeq {
    public int longestConsecutive(int[] nums) {
        int max = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i:nums){
            if(map.getOrDefault(i,0)==0){
                int left = map.getOrDefault(i-1,0);
                int right = map.getOrDefault(i+1,0);
                map.put(i,left+right+1);
                //用于更新影响范围内的连续大小。
                if(left!=0){
                    map.put(i-left,left+right+1);
                }
                //用于更新影响范围内的连续大小。
                if(right!=0){
                    map.put(i+right,right+left+1);
                }
                max = max>(left+right+1)?max:(left+right+1);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr={100,5,4, 200, 1, 3, 2};
        System.out.println(new MostLengthLSeq().longestConsecutive(arr));
    }
}
