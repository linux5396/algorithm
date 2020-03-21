package com.linxu.algorithm.hot100;

import comeon.binarytree.TreeNode;

/**
 * @author linxu
 * @date 2020/3/21
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class BuildTreeBySeq {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        //build root
        TreeNode root = new TreeNode(pre[preStart]);
        //get rootIdx in inorder
        int rootIndex = inStart;
        //fixme i<=inend
        for (int i = rootIndex; i <= inEnd; i++) {
            if (root.val == in[i]) {
                rootIndex = i;
                break;
            }
        }
        //spilt left 、right sub-tree
        int lengthOfLeft = rootIndex - inStart;
        //构造左子树
        if (lengthOfLeft > 0) {
            root.left = build(pre, preStart + 1, preStart + lengthOfLeft, in, inStart, rootIndex - 1);
        }
        //构造右子树
        if (lengthOfLeft < preEnd - preStart) {
            root.right = build(pre, preStart + lengthOfLeft + 1, preEnd, in, rootIndex + 1, inEnd);
        }
        return root;
    }

    private TreeNode buildByInAndPost(int[] post, int postStart, int postEnd, int[] in, int inStart, int inEnd) {
        TreeNode root = new TreeNode(post[postEnd]);
        //find rootIndex in inorder seq
        int rootIndex = inStart;
        for (int i = rootIndex; i <= inEnd; i++) {
            if (root.val == in[i]) {
                rootIndex = i;
                break;
            }
        }
        //left length
        int leftLength = rootIndex - inStart;
        if (leftLength > 0) {
            root.left = buildByInAndPost(post, postStart, postStart + leftLength - 1, in, inStart, rootIndex - 1);
        }
        if (leftLength < postEnd - postStart) {
            root.right = buildByInAndPost(post, postStart + leftLength, postEnd - 1, in, rootIndex + 1, inEnd);
        }
        return root;
    }

    public static void main(String[] args) {
        int[] a = {1, 2};
        int[] b = {2, 1};
        new BuildTreeBySeq().buildTree(a, b);
    }
}
