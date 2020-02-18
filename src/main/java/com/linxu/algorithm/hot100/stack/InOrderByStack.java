package com.linxu.algorithm.hot100.stack;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author linxu
 * @date 2020/2/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class InOrderByStack {

    //用递归
    private static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorder(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            inorder(root.right, list);
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        inorderByStack(root, list);
        return list;
    }

    private List<Integer> inorderByStack(TreeNode root, List<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            //有左节点则持续入栈
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                //直到没左节点，开始出栈，出栈的同时，指向右节点
                curNode = stack.peek();
                stack.pop();
                list.add(curNode.val);
                curNode = curNode.right;
            }
        }
        return list;
    }

    /**
     * 先序遍历
     *
     * @param root
     * @param list
     * @return
     */
    private List<Integer> preOrderByStack(TreeNode root, List<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            if (curNode != null) {
                stack.push(curNode);
                list.add(curNode.val);
                curNode = curNode.left;
            } else {
                curNode = stack.pop();
                curNode = curNode.right;
            }
        }
        return list;
    }

    private List<Integer> backOrderByStack(TreeNode root, List<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            if (curNode != null) {
                stack.push(curNode);
                list.add(curNode.val);
                curNode = curNode.right;
            } else {
                curNode = stack.pop();
                curNode = curNode.left;
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
