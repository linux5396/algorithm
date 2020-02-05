package com.linxu.algorithm.hot100.doublepointer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author linxu
 * @date 2020/2/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode low = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (low == fast) {
                return true;
            } else {
                low = low.next;
                fast = fast.next.next;
            }
        }
        return false;
    }

    /**
     * 查找入环的第一个入口
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode low = head;
        ListNode fast = head.next;
        int sizeOfCycle = 0;
        while (fast != null && fast.next != null) {
            //low and fast in loop
            if (low == fast) {
                //开始计数
                break;
            } else {
                low = low.next;
                fast = fast.next.next;
            }
        }
        //no cycle
        if (fast == null) {
            return null;
        }
        sizeOfCycle = countSizeOfCycle(low, fast);
        low = fast = head;
        //fast go
        for (int i = 0; i < sizeOfCycle; i++) {
            fast = fast.next;
        }
        while (low != fast) {
            low = low.next;
            fast = fast.next;
        }
        return low;
    }

    private int countSizeOfCycle(ListNode p1, ListNode p2) {
        int size = 0;
        do {
            p2 = p2.next;
            size++;
        } while (p1 != p2);
        return size;
    }
    /*
     * using hash set.
     */
    public ListNode detectCycleInHash(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return null;
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
