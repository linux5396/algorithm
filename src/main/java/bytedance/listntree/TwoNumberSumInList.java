package bytedance.listntree;

import com.linxu.algorithm.Recommend;

import java.util.List;

/**
 * @author linxu
 * @date 2020/3/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class TwoNumberSumInList {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val == 0 && l2.val == 0) {
            return new ListNode(0);
        }
        if (l1.val == 0) {
            return l2;
        }
        if (l2.val == 0) {
            return l1;
        }
        int number1 = 0;
        int number2 = 0;
        int i = 0;
        while (l1 != null) {
            number1 += Math.pow(10, i++) * l1.val;
            l1 = l1.next;
        }
        i = 0;
        while (l2 != null) {
            number2 += Math.pow(10, i++) * l2.val;
            l2 = l2.next;
        }
        //build
        int sum = number1 + number2;
        ListNode backUpNode = new ListNode(sum % 10);
        ListNode resultList = backUpNode;
        while (sum != 0) {
            int bit = sum % 10;
            backUpNode.next = new ListNode(bit);
            backUpNode = backUpNode.next;
            sum /= 10;
        }
        return resultList.next;
    }

    /**
     * 直接两数两加，同时保持进位
     *
     * @param l1
     * @param l2
     * @return
     */
    @Recommend
    public ListNode addTwoNumbersOK(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        //进位
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            //计算进位
            carry = sum / 10;
            //计算当前位
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        //最后的进位情况
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int sum = 708;
        ListNode node = new ListNode(8);
        ListNode result = node;
        while (sum != 0) {
            int bit = sum % 10;
            node.next = new ListNode(bit);
            node = node.next;
            sum /= 10;
        }
        while (result != null) {
            System.err.println(result.val);
            result = result.next;
        }
    }
}
