package com.linxu.algorithm.bydate.date191029;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author linxu
 * @date 2019/10/29
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：序列化二叉树,对二叉树进行序列化与反序列化。
 * 按照一个 序列序列化，按照相同的方式进行反序列化
 */
public class SerialBinaryTree {
    public void serialBinaryTree(TreeNode root, OutputStream os) throws IOException {
        if (root == null) {
            os.write(Byte.valueOf("$"));
            return;
        }
        os.write(Byte.valueOf(root.val + ","));
        serialBinaryTree(root.left, os);
        serialBinaryTree(root.right, os);
    }

    public void deserialBinaryTree(TreeNode root, InputStream os){

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

}
