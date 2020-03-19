package bytedance.listntree;

import comeon.binarytree.Serial;
import comeon.binarytree.TreeNode;



/**
 * @author linxu
 * @date 2020/3/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 二叉树的两个节点的最短路径
 */
public class DiffOfTreeNode {
    public static int findTheShortestPath(TreeNode root, TreeNode p, TreeNode q) {
        //non verify
        TreeNode commonAncestor = findCommonAncestor(root, p, q);
        int diff;
        int[] path = new int[1];
        //case 1 ancestor is p
        if (commonAncestor == p) {
            getPath(commonAncestor, q, 0, path);
            return path[0];
        }
        //case 2 ancestor is q
        if (commonAncestor == q) {
            getPath(commonAncestor, p, 0, path);
            return path[0];
        }
        //case 3 ancestor is not either
        getPath(commonAncestor, q, 0, path);
        diff = path[0];
        getPath(commonAncestor, p, 0, path);
        diff += path[0];
        return diff;
    }

    /**
     * 获取路径
     * @param root
     * @param target
     * @param currentPath
     * @param path
     * @return
     */
    private static boolean getPath(TreeNode root, TreeNode target, int currentPath, int[] path) {
        if (root == null) {
            return false;
        }
        if (root == target) {
            path[0] = currentPath;
            return true;
        }
        boolean find = getPath(root.left, target, currentPath + 1, path);
        if (find) {
            return true;
        } else {
            find = getPath(root.right, target, currentPath + 1, path);
        }
        return find;
    }

    /**
     * 查找公共节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    private static TreeNode findCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = findCommonAncestor(root.left, p, q);
        TreeNode right = findCommonAncestor(root.right, p, q);
        TreeNode common = root;
        if (left == null) {
            common = right;
        } else if (right == null) {
            common = left;
        }
        return common;
    }

    public static void main(String[] args) {
        TreeNode root = Serial.deSerial("8!6!5!#!#!7!#!#!10!9!#!#!11!#!#!");
        System.out.println(findTheShortestPath(root, root, root.right.left));
    }
}
