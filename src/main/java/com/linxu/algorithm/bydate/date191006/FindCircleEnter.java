package com.linxu.algorithm.bydate.date191006;

import com.linxu.algorithm.data_struct.SingleList;
import com.linxu.algorithm.data_struct.SingleList.*;
import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2019/10/6
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 不只是要找到环，还要找到环的入口
 */
public class FindCircleEnter {
    public static Node findTheEnterNode(Node head) {
        //solve special case.
        if (head == null) {
            return null;
        }
        Node meet = findMeetingNode(head);
        if (meet == null) {
            //不存在环
            return null;
        }
        //至少一个
        int nodesInLoop = 1;
        Node travelNode = meet;
        //count the loop size
        while (travelNode.getNext() != meet) {
            travelNode = travelNode.getNext();
            nodesInLoop++;
        }
        //solve normal case.
        Node p1 = head;
        Node p2 = head;
        //go fast
        for (int i = 0; i < nodesInLoop; i++) {
            p2 = p2.getNext();
        }
        //until meet again.
        while (p1 != p2) {
            p1 = p1.getNext();
            p2 = p2.getNext();
        }
        return p1;
    }

    //找到相遇的结点
    private static Node findMeetingNode(Node head) {
        Node slow = head.getNext();
        //只有一个结点
        if (slow == null) {
            return null;
        }
        //define
        Node fast = slow.getNext();
        while (slow != null && fast != null) {
            if (slow == fast) {
                return slow;
            }
            slow = slow.getNext();
            fast = fast.getNext();
            //impl faster than it.
            if (fast.getNext() != null) {
                fast = fast.getNext();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SingleList list = new SingleList();
        Node node = new Node();
        node.setValue(1);
        Node node1 = new Node();
        node1.setValue(2);
        Node node2 = new Node();
        node2.setValue(3);
        Node node3 = new Node();
        node3.setValue(4);
        Node node4 = new Node();
        node4.setValue(5);
        Node node5 = new Node();
        node5.setValue(6);
        node.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node2);
        list.setHead(node);
        //find
        System.out.println(findTheEnterNode(list.getHead()).getValue());
    }
}
