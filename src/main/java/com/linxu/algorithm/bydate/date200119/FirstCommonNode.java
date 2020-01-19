package com.linxu.algorithm.bydate.date200119;


import com.linxu.algorithm.CostSpace;
import com.linxu.algorithm.CostTime;

import java.util.List;

/**
 * @author linxu
 * @date 2020/1/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：求两条单链表的第一个公共节点
 */
public class FirstCommonNode {
    @CostTime("O(N+M+max(N,M))")
    @CostSpace("O(1)")
    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        //ex
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        //normal
        int lengthOfList1 = count(pHead1);
        int lengthOfList2 = count(pHead2);
        if (lengthOfList1 > lengthOfList2) {
            while (lengthOfList1 > lengthOfList2) {
                pHead1 = pHead1.next;
                lengthOfList1--;
            }
        } else {
            while (lengthOfList2 > lengthOfList1) {
                pHead2 = pHead2.next;
                lengthOfList2--;
            }
        }
        //common through
        while (pHead1 != pHead2) {
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return pHead1;
    }

    /**
     * count length Of list.
     */
    private static int count(ListNode pHead) {
        int length = 0;
        ListNode p = pHead;
        while (p != null) {
            length++;
            p = p.next;
        }
        return length;
    }

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
