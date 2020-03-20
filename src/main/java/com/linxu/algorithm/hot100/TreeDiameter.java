package com.linxu.algorithm.hot100;

import comeon.binarytree.TreeNode;

/**
 * @author linxu
 * @date 2020/3/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点。
 * <p>
 * <p>
 * <p>
 * 示例 :
 * 给定二叉树
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 */
public class TreeDiameter {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] depth = {0};
        depth(root, depth);
        return depth[0];
    }

    private int depth(TreeNode root, int[] depth) {
        if (root == null) {
            return 0;
        }
        //左边深度
        int left = depth(root.left, depth);
        //右边深度
        int right = depth(root.right, depth);
        //最大深度为左边最大深度加右边最大深度+当前节点1
        depth[0] = Math.max(left + right + 1, depth[0]);
        //返回的深度为某个节点的最大深度，应该为左、右边的之间的最大深度+1
        return Math.max(left, right) + 1;
    }
}
