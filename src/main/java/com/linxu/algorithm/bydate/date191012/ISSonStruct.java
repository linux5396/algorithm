package com.linxu.algorithm.bydate.date191012;

/**
 * @author linxu
 * @date 2019/10/12
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 输入两个二叉树A和B，判断B是否是A的子结构
 */
public class ISSonStruct<T extends Comparable<? super T>> {
    public boolean hasTheSameStruct(BinaryTreeNode rootA, BinaryTreeNode rootB) {
        boolean result = false;
        if (rootA != null && rootB != null) {
            if (rootA.value == rootB.value) {
                result = doesTreeAHasTreeB(rootA, rootB);
            }
            if (!result) {
                result = hasTheSameStruct(rootA.left, rootB);
            }
            if (!result) {
                result = hasTheSameStruct(rootA.right, rootB);
            }
        }
        return result;
    }

    private boolean doesTreeAHasTreeB(BinaryTreeNode rootA, BinaryTreeNode rootB) {
        if (rootB == null) {
            return true;
        }
        if (rootA == null) {
            return false;
        }
        if (rootA.value != rootB.value) {
            return false;
        }
        return doesTreeAHasTreeB(rootA.left, rootB.left) && doesTreeAHasTreeB(rootA.right, rootB.right);
    }

    private class BinaryTreeNode {
        T value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }
}
