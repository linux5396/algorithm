package com.linxu.algorithm.bydate.date191018;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author linxu
 * @date 2019/10/17
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 二叉树的层次遍历
 */
public class LevelTraverse {
    /**
     * 常量换行符
     */
    private static final String ENTER = "\n";

    /**
     * 不分行的层次遍历；
     *
     * @param root
     * @return
     */
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

    /**
     * 使用两个变量控制换行；一个为当前行还没打印的节点数；一个为下一行的节点数。
     *
     * @param root
     */
    public void printWithEnter(Node root) {
        if (root != null) {
            Queue<Node> queue = new ArrayDeque<>();
            ((ArrayDeque<Node>) queue).addFirst(root);
            int rareNode = 1;
            int nextLevelNodes = 0;
            while (!queue.isEmpty()) {
                Node headNode = ((ArrayDeque<Node>) queue).pollFirst();
                rareNode--;
                System.out.print(headNode.val + ",");
                if (headNode.left != null) {
                    ((ArrayDeque<Node>) queue).addLast(headNode.left);
                    nextLevelNodes++;
                }
                if (headNode.right != null) {
                    ((ArrayDeque<Node>) queue).addLast(headNode.right);
                    nextLevelNodes++;
                }
                if (rareNode == 0) {
                    System.out.print(ENTER);
                    rareNode = nextLevelNodes;
                    nextLevelNodes = 0;
                }
            }
        }
    }

    /**
     * 使用两个变量控制换行；一个为当前行还没打印的节点数；一个为下一行的节点数。
     * “之”型
     *
     * @param root
     */
    public void printInZ(Node root) {
        if (root != null) {
            Stack<Node>[] stacks = new Stack[2];
            int current = 0;
            int next = 1;
            stacks[0] = new Stack<>();
            stacks[1] = new Stack<>();
            stacks[current].push(root);
            while (!stacks[0].empty() || !stacks[1].empty()) {
                Node head =  stacks[current].pop();
                System.out.print(head.val + ",");
                if (current == 0) {
                    if (head.left != null) {
                        stacks[next].push(head.left);
                    }
                    if (head.right != null) {
                        stacks[next].push(head.right);
                    }
                } else {
                    if (head.right != null) {
                        stacks[next].push(head.right);
                    }
                    if (head.left != null) {
                        stacks[next].push(head.left);
                    }
                }
                if (stacks[current].empty()) {
                    System.out.print(ENTER);
                    current = 1 - current;
                    next = 1 - next;
                }
            }
        }
    }

    static class Node {
        int val;
        Node left;
        Node right;
    }

    public static void main(String[] args) {
        Node root = new Node();
        root.val = 8;
        Node n1 = new Node();
        n1.val = 6;
        Node n2 = new Node();
        n2.val = 10;
        Node n3 = new Node();
        n3.val = 5;
        Node n4 = new Node();
        n4.val = 7;
        Node n5 = new Node();
        n5.val = 9;
        Node n6 = new Node();
        n6.val = 11;

        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;

        new LevelTraverse().printInZ(root);
    }
}
