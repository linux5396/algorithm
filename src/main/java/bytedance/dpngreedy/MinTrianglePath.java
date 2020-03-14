package bytedance.dpngreedy;

import java.util.List;

/**
 * @author linxu
 * @date 2020/3/14
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 最小三角形路径
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 */
public class MinTrianglePath {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int maxSize = 0;
        //找到最大长度
        for (int i = 0; i < triangle.size(); i++) {
            maxSize = Math.max(maxSize, triangle.get(i).size());
        }
        int[] dp = new int[maxSize];
        for (int j = triangle.size() - 1; j >= 0; j--) {
            dp[j] = triangle.get(triangle.size() - 1).get(j);
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    public static int minimumTotalNotBest(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int[][] dp = new int[triangle.size()][triangle.size()];
        for (int j = triangle.size() - 1; j >= 0; j--) {
            dp[triangle.size() - 1][j] = triangle.get(triangle.size() - 1).get(j);
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
