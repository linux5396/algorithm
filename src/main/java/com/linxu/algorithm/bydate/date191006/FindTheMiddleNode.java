package com.linxu.algorithm.bydate.date191006;

import com.linxu.algorithm.data_struct.SingleList;
import com.linxu.algorithm.data_struct.SingleList.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author linxu
 * @date 2019/10/6
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 查找链表的中间结点
 */
public class FindTheMiddleNode {
    public static Node findMiddleNode(Node head) {
        //solve special case.
        if (head == null) {
            return null;
        }
        //normal case.
        Node oneStep = head;
        Node twoStep = head;
        //travel
        while (twoStep.getNext() != null) {
            if (twoStep.getNext().getNext() != null) {
                twoStep = twoStep.getNext().getNext();
                oneStep = oneStep.getNext();
            }else {
                break;
            }
        }
        return oneStep;
    }

    public static void main(String[] args) {
        SingleList list = new SingleList();
        List<Node> nodes = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            Node n = new Node();//1234567
            n.setValue(i + 2);
            nodes.add(n);
        }
        Node head = new Node();
        head.setValue(1);
        list.init(nodes, head);

        System.out.println(findMiddleNode(head).getValue());

        while (head.getNext() != null) {
            System.err.printf("%-5d", head.getValue());
            head = head.getNext();
        }
        System.err.printf("%-5d",head.getValue());
    }

}
