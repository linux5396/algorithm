package com.linxu.algorithm.bydate.date190925;

import com.linxu.algorithm.data_struct.SingleList;
import com.linxu.algorithm.data_struct.SingleList.*;

/**
 * @author linxu
 * @date 2019/9/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 删除一个排序链表的重复节点
 */
public class DeleteReduplicateNode {
    public static SingleList delete(SingleList list) {
        if (list == null) {
            return null;
        }
        Node slow = list.getHead();
        //如果头节点为空，那就什么都不用算了，直接返回
        if (slow != null) {
            Node fast = slow.getNext();
            while (fast != null) {
                if (slow.getValue() == fast.getValue()) {
                    //直接接到下一位
                    slow.setNext(fast.getNext());
                    //释放重复节点
                    fast.setNext(null);
                    //fast先行
                    fast = slow.getNext();
                    while (fast != null && fast.getValue() == slow.getValue()) {
                        fast = fast.getNext();
                    }
                    slow.setNext(fast);
                } else {
                    //找不到的时候就是直接移动
                    slow = slow.getNext();
                    fast = fast.getNext();
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        SingleList list = new SingleList();
        Node node = new Node();
        node.setValue(1);
        Node node1 = new Node();
        node1.setValue(2);
        Node node2 = new Node();
        node2.setValue(2);
        Node node3 = new Node();
        node3.setValue(3);
        Node node4 = new Node();
        node4.setValue(3);
        list.setHead(node);
        list.getHead().setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        Node cur=list.getHead();
        while (cur!=null){
            System.out.println(cur.getValue());
            cur=cur.getNext();
        }
        delete(list);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cur=list.getHead();
        while (cur!=null){
            System.err.println(cur.getValue());
            cur=cur.getNext();
        }
    }
}
