package bytedance.arrnsort;

/**
 * @author linxu
 * @date 2020/3/13
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 最长连续递增序列
 */
public class MostLengthLUpSeq {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length <= 1)
            return nums.length;
        int ans = 1;
        int count = 1;
        for(int i=0;i<nums.length-1;i++) {
            if(nums[i+1] > nums[i]) {
                count++;
            } else {
                count = 1;
            }
            ans = count > ans ? count : ans;
        }
        return ans;
    }
}
