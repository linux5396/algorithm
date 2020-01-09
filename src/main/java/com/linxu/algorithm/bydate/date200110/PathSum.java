package com.linxu.algorithm.bydate.date200110;

import java.util.*;

/**
 * @author linxu
 * @date 2020/1/9
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 路径总和：
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * <p>
 * 找出路径和等于给定数值的路径总数。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 */
public class PathSum {
    List<ArrayList<TreeNode>> paths = new ArrayList<>();
    ArrayList<TreeNode> path = new ArrayList<>();

    int intPaths = 0;

    public int pathSum(TreeNode root, int sum) {
        pathSumImpl(root, sum);
        return intPaths;
    }

    /**
     * 路径总数
     */
    private void pathSumImpl(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        search(root, 0, sum);
        if (root.left != null) {
            pathSumImpl(root.left, sum);
        }
        if (root.right != null) {
            pathSumImpl(root.right, sum);
        }
    }

    private void search(TreeNode node, int curSum, int targetVal/*, List<ArrayList<TreeNode>> paths, ArrayList<TreeNode> path*/) {
        if (node != null) {
            curSum += node.val;
            //path.add(node);
            if (curSum == targetVal) {
                //paths.add(new ArrayList<>(path));
                intPaths++;
            }
            if (node.left != null) {
                search(node.left, curSum, targetVal/*, paths, path*/);
            }
            if (node.right != null) {
                search(node.right, curSum, targetVal/*, paths, path*/);
            }
            //剔除当前节点
            //path.remove(node);
        }
    }

    /**
     * 所以第二种做法，采取了类似于数组的前n项和的思路，比如sum[4] == sum[1]，那么1到4之间的和肯定为0
     *
     * 对于树的话，采取DFS加回溯，每次访问到一个节点，把该节点加入到当前的pathSum中
     * 然后判断是否存在一个之前的前n项和，其值等于pathSum与sum之差
     * 如果有，就说明现在的前n项和，减去之前的前n项和，等于sum，那么也就是说，这两个点之间的路径和，就是sum
     *
     * 最后要注意的是，记得回溯，把路径和弹出去
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSumFast(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return helper(root, map, sum, 0);
    }

    int helper(TreeNode root, HashMap<Integer, Integer> map, int sum, int pathSum){
        int res = 0;
        if(root == null) {
            return 0;
        }

        pathSum += root.val;
        //累加上到当前节点为止有多少条路径和等于pathSum的个数（此处若是pathSum-sum==0,则返回1，否则返回默认值0）
        res += map.getOrDefault(pathSum - sum, 0);

        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
        res = helper(root.left, map, sum, pathSum) + helper(root.right, map, sum, pathSum) + res;
        map.put(pathSum, map.get(pathSum) - 1);
        return res;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
