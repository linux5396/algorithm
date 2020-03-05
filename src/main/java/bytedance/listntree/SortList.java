package bytedance.listntree;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author linxu
 * @date 2020/3/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 排序链表
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        ListNode p = head;
        while (p != null) {
            minHeap.add(p);
            p = p.next;
        }
        int headSize = minHeap.size();
        ListNode sortHead = minHeap.poll();
        p = sortHead;
        while (!minHeap.isEmpty()) {
            p.next = minHeap.poll();
            p = p.next;
        }
        System.out.println("************");
        ListNode p1 = sortHead;
        //消除环
        for (int i = 1; i < headSize && p1 != null; i++) {
            p1 = p1.next;
        }
        if (p1 != null)
            p1.next = null;
        return sortHead;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        ListNode p = new SortList().sortList(head);
        while (p != null) {
            System.err.println(p.val);
            p = p.next;
        }
    }
}
