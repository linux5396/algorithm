package com.linxu.algorithm.hot100.doublepointer;

import java.util.Stack;

/**
 * @author linxu
 * @date 2020/2/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 */
public class HUIWENLinkedList {
    /**
     * 使用递归或者List存储  都将是N的空间，递归包含调用栈。
     * 要使得空间为1，就需要改变输入；翻转后半链表
     * <p>
     * 这种方式会改变输入，或者最后翻转回来，也会出现多线程问题；
     */
    public boolean isPalindrome(ListNode head) {
        // 边界条件：空链表或只有一个节点的链表
        if (head == null || head.next == null) {
            return true;
        }
        //dummynode的作用是：如果不加哑头结点，如果输入的链表只有两个节点，不可以，比如链表：1->2,将输出true,显然判断是错误的
        //因为当只有12的时候，low和fast在相同位置
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode slow = dummyNode;
        ListNode fast = dummyNode;

        // 慢指针一次走一步，快指针一次走两步，当快指针走到终点，慢指针刚好处于中点位置
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // fast指针置于下半段链表的起点
        fast = slow.next;
        // 断开前后两个链表
        slow.next = null;
        // slow指针置于前半段链表的起点
        slow = dummyNode.next;

        // 反转后半段链表
        ListNode pre = null; // 保存指针前一节点的信息，用于反转
        while (fast != null) {
            ListNode nextTemp = fast.next;
            fast.next = pre;
            pre = fast;
            fast = nextTemp;
        }
        // 前后半链表逐一比较，当链表长度为奇数时前半段链表长度比后半段多1，所以以后半段为准
        while (pre != null) {
            if (slow.val != pre.val) {
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }

    /**
     * 用栈解决，但是，性能可能没有双向链表好，双向链表可以用N/2来判断是否是回文。
     *
     * @param head
     * @return
     */
    public boolean isPalindromeInStack(ListNode head) {
        // 边界条件：空链表或只有一个节点的链表
        if (head == null || head.next == null) {
            return true;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        while (p != null) {
            stack.add(p);
            p = p.next;
        }
        p = head;
        while (!stack.isEmpty()) {
            if (stack.pop().val != p.val) {
                return false;
            }
            p = p.next;
        }
        return true;
    }

    /**
     * 递归解法，递归调用栈的深度也是N，其实和栈是一致的。
     *
     * @param head
     * @return
     */
    public boolean isPalindromeInRecursive(ListNode head) {
        // 边界条件：空链表或只有一个节点的链表
        if (head == null || head.next == null) {
            return true;
        }
        ListNode[] recursiveNode = {head};
        return isPalindromeInRecursive(head, recursiveNode);
    }

    private boolean isPalindromeInRecursive(ListNode cur, ListNode[] recurisiveNode) {
        if (cur != null) {
            if (!isPalindromeInRecursive(cur.next, recurisiveNode)) {
                return false;
            }
            if (cur.val != recurisiveNode[0].val) {
                return false;
            }
            recurisiveNode[0] = recurisiveNode[0].next;
        }
        return true;
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
