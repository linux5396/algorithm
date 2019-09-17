package com.linxu.algorithm.bydate.date190912;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;

/**
 * @author linxu
 * @date 2019/9/12
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 重建二叉树,根据给定两个的序列，该序列为前序和中序遍历的结果，重构二叉树
 */
public class RebuildBinaryTree {
    static class Tree {
        Node root;

        public void setRoot(Node root) {
            this.root = root;
        }

        void printFront(Node node) {
            if (node == null) {
                return;
            }
            System.out.print(node.value);
            printFront(node.left);
            printFront(node.right);
        }

        void printBetween(Node node) {
            if (node != null) {
                printBetween(node.left);
                System.out.print(node.value);
                printBetween(node.right);
            }
        }

        void printAfter(Node node) {
            if (node != null) {
                printAfter(node.left);
                printAfter(node.right);
                System.out.print(node.value);
            }
        }

        public Node build(int[] preOrderSequence, int[] inOrderSequence) {
            root = build(preOrderSequence, 0, preOrderSequence.length - 1, inOrderSequence, 0, inOrderSequence.length - 1);
            return root;
        }

        private Node build(int[] preSequence, int preStart, int preEnd, int[] inSequence, int inStart, int inEnd) {
            //创建根节点
            Node root = new Node(preSequence[preStart]);
            //中序序列的开端
            int rootIdx = inStart;
            //计算ROOT在中序序列中的位置
            //TODO 如果上层没有对序列进行验证，这里会存在数组越界、空指针等问题
            while (rootIdx <= inEnd && inSequence[rootIdx] != root.value) {
                rootIdx++;
            }
            //计算左子树的规模
            int leftLength = rootIdx - inStart;
            //计算在前序序列中的左子树的结束坐标=前序的起始坐标+左子树规模
            int leftPreEnd = preStart + leftLength;
            //如果左子树存在
            if (leftLength > 0) {
                root.left = build(preSequence, preStart + 1, leftPreEnd, inSequence, inStart, rootIdx - 1);
            }
            //如果右子树存在
            if (leftLength < preEnd - preStart) {
                root.right = build(preSequence, leftPreEnd + 1, preEnd, inSequence, rootIdx + 1, inEnd);
            }
            return root;
        }

        public Node buildByPost(int[] inOrderSequence, int[] postOrderSequence) {
            root = buildByPost(postOrderSequence, 0, postOrderSequence.length - 1, inOrderSequence, 0, inOrderSequence.length - 1);
            return root;
        }
        //这是依据中序、后序来构造二叉树
        private Node buildByPost(int[] postSequence, int postStart, int postEnd, int[] inSequence, int inStart, int inEnd) {
            //获取ROOT
            Node root = new Node(postSequence[postEnd]);
            int rootIdx = inStart;
            //找到在中序中的坐标
            while (rootIdx <= inEnd && inSequence[rootIdx] != root.value) {
                rootIdx++;
            }
            //计算左子树规模
            int leftLength = rootIdx - inStart;
            int leftPostEnd = postStart + leftLength;
            if (leftLength > 0) {
                root.left = buildByPost(postSequence, postStart, leftPostEnd - 1, inSequence, inStart, rootIdx - 1);
            }
            if (leftPostEnd < postEnd - postStart) {
                root.right = buildByPost(postSequence, leftPostEnd, postEnd - 1, inSequence, rootIdx + 1, inEnd);
            }
            return root;
        }
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Tree tree = new RebuildBinaryTree.Tree();
        /*int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        tree.build(pre, in);
        tree.printFront(tree.root);
        System.out.println("");
        tree.printBetween(tree.root);
        System.out.println("");*/
        int[] inorder = {1, 2, 3, 4, 5, 6, 7};
        int[] postorder = {2, 4, 3, 1, 6, 7, 5};
        tree.buildByPost(inorder, postorder);
        tree.printBetween(tree.root);
        System.out.println("");
        tree.printAfter(tree.root);
    }
}
