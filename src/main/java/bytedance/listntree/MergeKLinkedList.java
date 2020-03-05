package bytedance.listntree;

import com.linxu.algorithm.CostTime;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author linxu
 * @date 2020/3/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class MergeKLinkedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode[] listNodes = new ListNode[]{l1, l2};
        return mergeKLists(listNodes);
    }

    @CostTime("NLogN")
    public ListNode mergeKLists(ListNode[] lists) {
        //special solve
        if (lists == null || lists.length == 0) {
            return null;
        }
        //使用小顶堆。
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (int i = 0; i < lists.length; i++) {
            //必须过滤
            if (lists[i] != null) {
                queue.add(lists[i]);
            }
        }
        if (queue.isEmpty()) {
            return null;
        }
        ListNode temp = new ListNode(queue.peek().val);
        ListNode head = temp;
        while (!queue.isEmpty()) {
            ListNode top = queue.poll();
            temp.next = top;
            temp = temp.next;
            if (top.next != null) {
                queue.offer(top.next);
            }
        }
        return head.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
