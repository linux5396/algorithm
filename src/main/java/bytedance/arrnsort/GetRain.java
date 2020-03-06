package bytedance.arrnsort;

import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2020/3/6
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class GetRain {
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int rains = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int ISR = Math.min(leftMax[i], rightMax[i]);
            rains += ISR - height[i];
        }
        return rains;
    }

    public int trapInFast(int[] heights) {
        if (heights == null || heights.length < 3) {
            return 0;
        }
        int leftMax, rightMax;
        leftMax = heights[0];
        rightMax = heights[heights.length - 1];
        int left, right;
        left = 0;
        right = heights.length - 1;
        int rains = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, heights[left]);
            rightMax = Math.max(rightMax, heights[right]);
            if (leftMax < rightMax) {
                rains += leftMax - heights[left];
                left++;
            } else {
                rains += rightMax - heights[right];
                right--;
            }
        }
        return rains;
    }

    public static void main(String[] args) {
        int[] waters = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new GetRain().trap(waters));
    }
}
