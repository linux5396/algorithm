package com.linxu.algorithm.hot100.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linxu
 * @date 2020/2/21
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 莫里斯高效遍历二叉树
 */
public class MorrisTravell {
    public static List<Integer> preOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        TreeNode curNode, prevNode;
        curNode = root;
        prevNode = null;
        while (curNode != null) {
            if (curNode.left == null) {
                list.add(curNode.val);
                curNode = curNode.right;
            } else {
                prevNode = curNode.left;
                while (prevNode.right != null && prevNode.right != curNode) {
                    prevNode = prevNode.right;
                }
                if (prevNode.right == null) {
                    list.add(curNode.val);
                    prevNode.right = curNode;
                    curNode = curNode.left;
                } else {
                    prevNode.right = null;
                    curNode = curNode.right;
                }
            }
        }
        return list;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
