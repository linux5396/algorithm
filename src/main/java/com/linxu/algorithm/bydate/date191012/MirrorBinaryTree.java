package com.linxu.algorithm.bydate.date191012;

import java.util.Stack;

/**
 * @author linxu
 * @date 2019/10/12
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 镜像二叉树，比如树为
 * 8
 * / \
 * 4   7
 * / \   / \
 * 1   2  3  5
 * <p>
 * 转化之后的形状为
 * 8
 * / \
 * 7   4
 * / \   / \
 * 5   3  2  1
 */
public class MirrorBinaryTree {
    /**
     * 递归解法
     *
     * @param root
     */
    public static void mirrorTransfer(Node root) {
        if (root != null) {
            Node tempNode = root.left;
            root.left = root.right;
            root.right = tempNode;
            if (root.right != null) {
                mirrorTransfer(root.right);
            }
            if (root.left != null) {
                mirrorTransfer(root.left);
            }
        }
    }

    /**
     * 用循环实现
     *
     * @param root
     */
    public static void mirrorTransferInLoop(Node root) {
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.empty()) {
                root=stack.pop();
                swap(root);
                if (root.left != null) {
                    stack.push(root.left);
                }
                if (root.right != null) {
                    stack.push(root.right);
                }
            }
        }
    }

    private static void swap(Node node) {
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    private static class Node {
        int val;
        Node left;
        Node right;
    }

}
