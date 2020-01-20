package com.linxu.algorithm.bydate.date200120;

/**
 * @author linxu
 * @date 2020/1/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：查找二叉排序树的第K个节点
 */
public class FindTheKthNodeInBinarySortTree {
    TreeNode KthNode(TreeNode pRoot, int k) {
        if (k < 1 || pRoot == null) {
            return null;
        }
        int[] kth = new int[1];
        kth[0] = k;
        return find(pRoot, kth);
    }

    private TreeNode find(TreeNode pRoot, int[] kth) {
        TreeNode findNode = null;
        if (pRoot.left != null) {
            findNode = find(pRoot.left, kth);
        }
        if (findNode == null) {
            if (kth[0] == 1) {
                return pRoot;
            }
            kth[0]--;
        }
        if (findNode == null && pRoot.right != null) {
            findNode = find(pRoot.right, kth);
        }
        return findNode;
    }

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public static void main(String[] args) {
        int[] a = {1};
        System.out.println(a[0]++);
        System.out.println(a[0]++);
    }
}
