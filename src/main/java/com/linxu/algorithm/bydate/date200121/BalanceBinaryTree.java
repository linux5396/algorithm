package com.linxu.algorithm.bydate.date200121;

/**
 * @author linxu
 * @date 2020/1/21
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：输入一个树的根节点，判断该树是否是平衡二叉树
 */
public class BalanceBinaryTree {
    /**
     * 重复计算多次，不够好
     *
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        /**
         * 明显会存在大量的重复计算
         */
        int leftDepth = TreeDepthInGradefully(root.left);
        int rightDepth = TreeDepthInGradefully(root.right);
        int diff = leftDepth - rightDepth;
        if (diff > 1 || diff < -1) {
            return false;
        }
        return isBalanced(root.right) && isBalanced(root.left);
    }

    public static int TreeDepthInGradefully(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDeep = TreeDepthInGradefully(root.left);
        int rightDeep = TreeDepthInGradefully(root.right);
        return (leftDeep > rightDeep) ? leftDeep + 1 : rightDeep + 1;
    }

    /********************fast*******************************/
    /**
     * 利用后序遍历的特性，即计算节点深度，也比较是否平衡
     * @param root
     * @return
     */
    public static boolean isBalancedFast(TreeNode root) {
        if (root == null) {
            return true;
        }
        int[] depth = new int[1];
        depth[0] = 0;
        return isBalancedFastImpl(root, depth);
    }

    private static boolean isBalancedFastImpl(TreeNode root, int[] depth) {
        if (root == null) {
            depth[0] = 0;
            return true;
        }
        int[] leftDepth = new int[1];
        int[] rightDepth = new int[1];
        if (isBalancedFastImpl(root.left, leftDepth) && isBalancedFastImpl(root.right, rightDepth)) {
            int diff = leftDepth[0] - rightDepth[0];
            if (diff >= -1 && diff <= 1) {
                //record cur node depth
                depth[0] = 1 + (leftDepth[0] > rightDepth[0] ? leftDepth[0] : rightDepth[0]);
                return true;
            }
        }
        return false;
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
