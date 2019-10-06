package com.linxu.algorithm.bydate.date191006;

import com.linxu.algorithm.data_struct.SingleList;
import com.linxu.algorithm.data_struct.SingleList.*;

/**
 * @author linxu
 * @date 2019/10/6
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 合并两个有序的单链表,使得合并后还是递增顺序
 */
public class MergeSortedList {
    public static Node merge(Node headInListOne, Node headInListTwo) {
        //solve special case.
        if (headInListOne == null) {
            return headInListTwo;
        }
        if (headInListTwo == null) {
            return headInListOne;
        }
        //solve normal case.
        Node retHead;
        //define the travel p
        Node cur1 = headInListOne;
        Node cur2 = headInListTwo;
        //交换作用
        Node temp = null;
        //set the ret head.
        retHead = cur1.getValue() < cur2.getValue() ? cur1 : cur2;
        //do merge.
        while (cur2 != null) {
            if (cur1.getValue() <= cur2.getValue() && cur1.getNext() != null && cur2.getValue() <= cur1.getNext().getValue()) {
              //接轨
                temp = cur1.getNext();
                cur1.setNext(cur2);
                //保存cur2的next
                Node temp1=cur2.getNext();
                cur1.getNext().setNext(temp);
                //移动
                cur2 = temp1;
                cur1 = cur1.getNext();
            } else if (cur1.getValue() <= cur2.getValue() && cur1.getNext() == null) {
                cur1.setNext(cur2);
                cur2 = cur2.getNext();
            } else if (cur2.getValue() > cur1.getNext().getValue()) {
               //移动cur1
                cur1=cur1.getNext();
            }
        }
        return retHead;
    }

    public static void main(String[] args) {
        Node node1 = new Node();
        node1.setValue(1);
        Node node2 = new Node();
        node2.setValue(3);
        Node node3 = new Node();
        node3.setValue(5);
        Node node4 = new Node();
        node4.setValue(7);
        Node node5 = new Node();
        node5.setValue(11);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        //list 2
        Node node11 = new Node();
        node11.setValue(2);
        Node node12 = new Node();
        node12.setValue(3);
        Node node13 = new Node();
        node13.setValue(6);
        Node node14 = new Node();
        node14.setValue(8);
        Node node15 = new Node();
        node15.setValue(10);
        node11.setNext(node12);
        node12.setNext(node13);
        node13.setNext(node14);
        node14.setNext(node15);

        //System.out.println(merge(node1,node11).getValue());

        Node head=merge(node1,node11);
        while (head.getNext() != null) {
            System.err.printf("%-5d", head.getValue());
            head = head.getNext();
        }
        System.err.printf("%-5d",head.getValue());

    }
}
