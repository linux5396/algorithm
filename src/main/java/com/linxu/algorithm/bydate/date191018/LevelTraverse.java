package com.linxu.algorithm.bydate.date191018;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * @author linxu
 * @date 2019/10/17
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 二叉树的层次遍历
 */
public class LevelTraverse {
    public ArrayList<Integer> print(Node root) {
        if (root != null) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            Queue<Node> queue = new ArrayDeque<>();
            ((ArrayDeque<Node>) queue).addFirst(root);
            while (!queue.isEmpty()) {
                Node headNode = ((ArrayDeque<Node>) queue).pollFirst();
                arrayList.add(headNode.val);
                System.out.print(headNode.val);
                if (headNode.left != null) {
                    ((ArrayDeque<Node>) queue).addLast(headNode.left);
                }
                if (headNode.right != null) {
                    ((ArrayDeque<Node>) queue).addLast(headNode.right);
                }
            }
            return arrayList;
        }
        return new ArrayList<>();
    }

    static class Node {
        int val;
        Node left;
        Node right;
    }
}
