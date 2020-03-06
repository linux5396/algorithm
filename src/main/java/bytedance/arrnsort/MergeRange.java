package bytedance.arrnsort;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author linxu
 * @date 2020/3/6
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class MergeRange {
    /**
     * 基于排序之后再合并区间
     *
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        });
        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        int i = 1;
        while (i < list.size()) {
            int start1 = list.get(i - 1)[0];
            int end1 = list.get(i - 1)[1];
            int start2 = list.get(i)[0];
            int end2 = list.get(i)[1];
            //inrange
            if (start2 >= start1 && start2 <= end1) {
                list.get(i - 1)[1] = Math.max(end1, end2);
                list.remove(i);
            } else {
                i++;
            }
        }
        //type transfer.
        int[][] holder = new int[0][0];
        return list.toArray(holder);
    }

    public static void main(String[] args) {

    }
}
