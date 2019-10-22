package com.linxu.algorithm.bydate.date191022;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;

/**
 * @author linxu
 * @date 2019/10/22
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 输入：二叉树的根节点和一个targetVal.
 * 输出：打印和为targetVal的paths.(可能有多条，也可能一条都不存在)
 */
public class BinaryTreePathOfTarget {
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int targetVal) {
        if (root != null) {
            ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
            ArrayList<Integer> path = new ArrayList<>();
            findPath(root, targetVal, 0, paths, path);
            paths.sort((o1, o2) -> o2.size() - o1.size());
            return paths;
        }
        return new ArrayList<>();
    }

    /**
     * @param root       节点
     * @param targetVal  目标和
     * @param currentSum 当前的和
     * @param paths      路径集
     * @param path       当前路径
     */
    private void findPath(TreeNode root, int targetVal, int currentSum, ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> path) {
        currentSum += root.val;
        path.add(root.val);
        boolean isLeaf = root.left == null && root.right == null;
        if (isLeaf && currentSum == targetVal) {
            paths.add(new ArrayList<>(path));
        }
        if (root.left != null/*&&currentSum<=targetVal*/) {
            findPath(root.left, targetVal, currentSum, paths, path);
        }
        //&&currentSum<=targetVal 如果这个树的节点都是非负数，那么可以通过这个约束
        //减少栈的深度
        if (root.right != null/*&&currentSum<=targetVal*/) {
            findPath(root.right, targetVal, currentSum, paths, path);
        }
        //返回父结点之前，删除本节点
        path.remove(path.size() - 1);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.val = 10;

        TreeNode rc = new TreeNode();
        rc.val = 12;
        TreeNode lc = new TreeNode();
        lc.val = 5;
        root.right = rc;
        root.left = lc;

        lc.left = new TreeNode();
        lc.left.val = 4;
        lc.right = new TreeNode();
        lc.right.val = 7;

        for (ArrayList<Integer> path : new BinaryTreePathOfTarget().findPath(root, 22)
                ) {
            for (Integer i : path
                    ) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
