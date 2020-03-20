package bytedance.arrnsort.topk;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.*;

/**
 * @author linxu
 * @date 2020/3/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 * <div>  给定两个有序数组arr1和arr2，再给定一个整数k，返回来自arr1和arr2的两个数相加和最大的前k个，两个数必须分别来自两个数组 </div>
 * 按照降序输出 时间要求为klogk
 */
public class TopKInTwoSortArr {
    public int[] getTopKSum(int[] arr1, int[] arr2, int k) {
        if (arr1 == null || arr2 == null || k < 1) {
            return null;
        }
        int[] topk = new int[k];
        int curIdx = 0;
        Set<String> filter = new HashSet<>();
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o2.val - o1.val);
        heap.offer(new Node(arr1.length - 1, arr2.length - 1, arr1[arr1.length - 1] + arr2[arr2.length - 1]));
        filter.add((arr1.length - 1) + "_" + (arr2.length - 1));
        //get k
        while (k > curIdx && !heap.isEmpty()) {
            Node topNode = heap.poll();
            assert topNode != null;
            topk[curIdx++] = topNode.val;
            int i1 = topNode.arr1Index;
            int i2 = topNode.arr2Index;
            if (!filter.contains(String.valueOf(i1 + "_" + (i2 - 1)))) {
                filter.add(String.valueOf(i1 + "_" + (i2 - 1)));
                heap.offer(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
            }
            if (!filter.contains(String.valueOf((i1 - 1) + "_" + (i2)))) {
                filter.add(String.valueOf((i1 - 1) + "_" + (i2)));
                heap.offer(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
            }
        }
        return topk;
    }

    class Node {
        int arr1Index;
        int arr2Index;
        int val;

        public Node(int arr1Index, int arr2Index, int val) {
            this.arr1Index = arr1Index;
            this.arr2Index = arr2Index;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 4, 5, 7};
        int[] arr2 = {2, 3, 4, 7, 9, 11};
        GenerationUtil.print(new TopKInTwoSortArr().getTopKSum(arr1, arr2, 4), false);
    }
}
