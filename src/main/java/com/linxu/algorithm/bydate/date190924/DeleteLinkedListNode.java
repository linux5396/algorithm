package com.linxu.algorithm.bydate.date190924;

import com.linxu.algorithm.data_struct.SingleList;

/**
 * @author linxu
 * @date 2019/9/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 给定一个单向链表的头指针和一个节点的指针，定义一个函数在O(1)时间内删除该节点
 */
public class DeleteLinkedListNode {
    public static void delete(SingleList.Node node, SingleList list) {
        if (list != null && node != null) {
            //只有一个节点
            if (node == list.getHead() && node.getNext() == null) {
                node = null;
                return;
            }
            //尾节点,必须遍历之后才能够释放。
            //TODO
            if (node.getNext() == null) {
                SingleList.Node cur = list.getHead();
                while (cur.getNext() != node) {
                    cur = cur.getNext();
                }
                cur.setNext(null);
            } else {
                //直接把NODE替换成它的下一个的内容即可。
                node.setValue(node.getNext().getValue());
                node.setNext(node.getNext().getNext());
            }
        }
    }

    public static void main(String[] args) {

    }
}
