package bytedance.listntree;

/**
 * @author linxu
 * @date 2020/3/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = null;
        ListNode node = null;
        while (head != null) {
            //旧链表做头拆
            node = head;
            head = head.next;
            //老链表做头删
            node.next = newHead;
            newHead = node;
        }
        return newHead;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
