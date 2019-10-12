package com.linxu.algorithm.bydate.date191012;

/**
 * @author linxu
 * @date 2019/10/12
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 1、判断一棵树是否是对称二叉树，即原树与镜像相同
 */
public class SymmetryBinaryTree {
    public static boolean isSummetryBinaryTree(Node root) {
        boolean result = false;
        if (root != null) {
            result = isSymmetrical(root, root);
        }
        return result;
    }

    private static boolean isSymmetrical(Node root1, Node root2) {
        //都相同，最后也都为null
        if (root1 == null && root2 == null) {
            return true;
        }
        //只有一个不同的，那么不对称
        if (root1 == null || root2 == null) {
            return false;
        }
        //找到不同的立即返回
        if (root1.val != root2.val) {
            return false;
        }
        return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.right, root2.left);
    }


    public static class Node {
        int val;
        Node left;
        Node right;
    }

    public static void main(String[] args) {

    }
}
