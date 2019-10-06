package com.linxu.algorithm.bydate.date191006;

import com.linxu.algorithm.data_struct.SingleList;
import com.linxu.algorithm.data_struct.SingleList.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author linxu
 * @date 2019/10/6
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class FindKthToTail {
    public static Node find(Node head, int k) {
        //solve special case.
        if (head == null || k <= 0) {
            return null;
        }
        //solve normal case. using twin pointer way.
        Node slow = head;
        Node fast = slow;
        for (int i = 1; i < k; i++) {
            if (fast.getNext() != null) {
                fast = fast.getNext();
                //第K个结点刚好是最后一个，即倒数第K个是第一个
            } else if (i == (k - 1)) {
                return head;
            } else {
                //即长度达不到K.
                throw new IllegalArgumentException("长度不足K");
            }
        }
        //travel
        while (fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext();
        }
        return slow;
    }

    public static void main(String[] args) {
        SingleList list = new SingleList();
        List<Node> nodes = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            Node n = new Node();
            n.setValue(i + 2);
            nodes.add(n);
        }
        Node head = new Node();
        head.setValue(1);
        list.init(nodes, head);

        System.out.println(find(head, 3).getValue());

        //print
        while (head.getNext() != null) {
            System.err.printf("%-5d", head.getValue());
            head = head.getNext();
        }
        System.err.printf("%-5d",head.getValue());
    }
}
