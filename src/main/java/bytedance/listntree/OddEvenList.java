package bytedance.listntree;

import static java.lang.System.out;
/**
 * @author linxu
 * @date 2020/3/15
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 奇偶链表
 */
public class OddEvenList {
    public  ListNode oddEvenList(ListNode head) {
        out.print("");
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
