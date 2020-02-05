package com.linxu.algorithm.hot100.doublepointer;

/**
 * @author linxu
 * @date 2020/2/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 */
public class DeleteTheTailK {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //n保证有效，只需要验证head
        if (head == null) {
            return null;
        }
        if (head.next == null && n == 1) {
            return null;
        }
        //放置virtualNode是为了解决特殊长度链表的问题
        ListNode virtualNode = new ListNode(0);
        virtualNode.next = head;
        ListNode slow =virtualNode, fast = virtualNode;
        ListNode preSlow =virtualNode;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            preSlow = slow;
            slow = slow.next;
            fast = fast.next;
        }
        //wait to delete is slow.next
        ListNode pre = preSlow;
        pre.next = preSlow.next.next;
        return virtualNode.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
