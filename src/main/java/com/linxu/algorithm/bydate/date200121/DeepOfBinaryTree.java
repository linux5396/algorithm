package com.linxu.algorithm.bydate.date200121;

/**
 * @author linxu
 * @date 2020/1/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：查找二叉树的最深深度。必须从根节点开始，到叶子节点。
 */
public class DeepOfBinaryTree {
    /**
     * 优雅递归
     */
    public int TreeDepthInGradefully(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDeep = TreeDepthInGradefully(root.left);
        int rightDeep = TreeDepthInGradefully(root.right);
        return (leftDeep > rightDeep) ? leftDeep + 1 : rightDeep + 1;
    }

    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] maxDeep = new int[1];
        maxDeep[0] = 0;
        int[] curDeep = new int[1];
        curDeep[0] = 0;
        travel(root, curDeep, maxDeep);
        return maxDeep[0];
    }

    private void travel(TreeNode root, int[] curDeep, int[] maxDeep) {
        if (root != null) {
            curDeep[0]++;
            if (maxDeep[0] < curDeep[0]) {
                maxDeep[0] = curDeep[0];
            }
            if (root.left != null) {
                travel(root.left, curDeep, maxDeep);
            }
            if (root.right != null) {
                travel(root.right, curDeep, maxDeep);
            }
            //回溯
            curDeep[0]--;
        }
    }


    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
