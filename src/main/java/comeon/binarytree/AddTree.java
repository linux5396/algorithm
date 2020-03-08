package comeon.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author linxu
 * @date 2020/3/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 把二叉查找树转为累加树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
 * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * 输入: 原始二叉搜索树:
 * 5
 * /   \
 * 2     13
 * <p>
 * 输出: 转换为累加树:
 * 18
 * /   \
 * 20     13
 */
public class AddTree {
    public TreeNode convertBST(TreeNode root) {
        int[] sum = {0};
        convert(root, sum);
        return root;
    }

    public TreeNode convertBSTInStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        int sum = 0;
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.right;
            } else {
                node = stack.pop();
                sum += node.val;
                node.val = sum;
                node = node.left;
            }
        }
        return root;
    }

    private void convert(TreeNode root, int[] curSum) {
        if (root == null) {
            return;
        }
        if (root.right != null) {
            convert(root.right, curSum);
        }
        curSum[0] += root.val;
        root.val = curSum[0];
        if (root.left != null) {
            convert(root.left, curSum);
        }
    }
}
