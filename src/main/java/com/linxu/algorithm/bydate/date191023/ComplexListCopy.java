package com.linxu.algorithm.bydate.date191023;

/**
 * @author linxu
 * @date 2019/10/23
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 复杂链表的复制算法
 * 链表有val、next、sibling(另外一个指针)三个属性
 */
public class ComplexListCopy {
    //O(N^2)第一遍复制；第二遍查找sibling步数，走同样的步数
    public ComplexListNode copy(ComplexListNode root) {
        if (root != null) {
            ComplexListNode newHead = new ComplexListNode();
            newHead.val = root.val;
            ComplexListNode backUpNode = newHead;
            ComplexListNode iterateNode = root.next;
            //next==null  -> ending
            //1、copy link with next pointer
            boolean linkRootFlag = false;
            while (iterateNode != null) {
                ComplexListNode createNode = new ComplexListNode();
                //first link node.
                if (!linkRootFlag) {
                    newHead.next = createNode;
                    linkRootFlag = true;
                }
                createNode.val = root.next.val;
                //回溯上一个节点
                backUpNode.next = createNode;
                backUpNode = createNode;
                //next loop
                iterateNode = iterateNode.next;
            }
            //2、copy the sibling p
            int counter;
            iterateNode = root;
            ComplexListNode newIterateNode = newHead;
            while (iterateNode != null) {
                if (iterateNode.sibling != null) {
                    counter = findSiblingNodeLocation(iterateNode.sibling, root);
                    if (counter == 0) {
                        newIterateNode.sibling = newHead;
                    } else {
                        newIterateNode.sibling = getSiblingNodeByIdx(newHead, counter);
                    }
                    iterateNode = iterateNode.next;
                    newIterateNode = newIterateNode.next;
                } else {
                    iterateNode = iterateNode.next;
                    newIterateNode = newIterateNode.next;
                }
            }
            return newHead;
        }
        return null;
    }

    //return steps
    private int findSiblingNodeLocation(ComplexListNode siblingNode, ComplexListNode root) {
        int steps = -1;
        ComplexListNode temp = root;
        //root return 0
        if (root == siblingNode) {
            steps++;
        }
        while (temp != siblingNode && temp != null) {
            steps++;
            temp = temp.next;
        }
        return steps;
    }

    private ComplexListNode getSiblingNodeByIdx(ComplexListNode root, int idx) {
        while (idx-- != 0) {
            root = root.next;
        }
        return root;
    }

    //O(N)
    public ComplexListNode fastCopy(ComplexListNode root) {
        if (root != null) {
            //复制出双倍的节点
            ComplexListNode rootNode = cloneNodes(root);
            //连接sibling
            linkSibling(rootNode);
            //disLink
            return disLink(rootNode);
        }
        return null;
    }
    //取奇数位并且进行连接
    private ComplexListNode disLink(ComplexListNode root){
        ComplexListNode cur=root;
        ComplexListNode newHead=null;
        ComplexListNode cloneNode=null;
        if (cur!=null){
            newHead=cloneNode=cur.next;
            cur.next=cloneNode.next;
            cur=cur.next;
        }
        while (cur!=null){
            cloneNode.next=cur.next;
            cloneNode=cloneNode.next;
            cur.next=cloneNode.next;
            cur=cur.next;
        }
        return newHead;
    }
    private ComplexListNode cloneNodes(ComplexListNode root) {
        ComplexListNode temp = root;
        while (temp != null) {
            ComplexListNode clone = new ComplexListNode();
            clone.val = temp.val;
            clone.next = temp.next;
            clone.sibling = null;

            temp.next = clone;
            temp = clone.next;
        }
        return root;
    }

    private void linkSibling(ComplexListNode root) {
        ComplexListNode node = root;
        while (node != null) {
            ComplexListNode clone = node.next;
            if (node.sibling != null) {
                clone.sibling = node.sibling.next;
            }
            node = clone.next;
        }
    }

    class ComplexListNode {
        int val;
        ComplexListNode next;
        ComplexListNode sibling;
    }
}
