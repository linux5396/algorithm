package com.linxu.algorithm.bydate.date191024;


import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author linxu
 * @date 2019/10/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 将二叉搜索树转换为排序的双向链表，不允许创建新的节点，只允许改变指针。
 * <p>
 *     其实二叉搜索树的实质就是二叉排序链，而二叉搜索树的多个子root其实相当于
 *     二叉排序链的节点索引，利用root来进行二分查找。
 *     (直接的二叉排序链是无法进行二分查找的)
 * </p>
 */
public class ConvertBinarySortTreeToDoubleList {
    TreeNode tail = null;  //定义链表的最后一个节点
    TreeNode head = null;  //链表的第一个节点

    public TreeNode Convert(TreeNode pRootOfTree) {
        ConvertSub(pRootOfTree);
        return head;
    }
    //构造一个递归函数中序遍历二叉树
    private void ConvertSub(TreeNode pRootOfTree) {
        if(pRootOfTree==null) {
            return;
        }

        ConvertSub(pRootOfTree.left);    //递归遍历左子树
        //这个的触发情况为左子树的最左节点。
        if (tail == null) {              //左子树为空
            tail = pRootOfTree;          //根节点即为尾节点
            head = pRootOfTree;
        }
        else {
            tail.right = pRootOfTree;    //把根节点连接到左子树的末尾
            pRootOfTree.left = tail;
            tail = pRootOfTree;          //更新尾节点
        }
        ConvertSub(pRootOfTree.right);   //递归遍历右子树
    }


    public TreeNode ConvertInList(TreeNode pRootOfTree) {
        if(pRootOfTree == null){
            return null;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        Convert(pRootOfTree, list);
        return Convert(list);

    }
    //中序遍历，在list中按遍历顺序保存
    public void Convert(TreeNode pRootOfTree, ArrayList<TreeNode> list){
        if(pRootOfTree.left != null){
            Convert(pRootOfTree.left, list);
        }

        list.add(pRootOfTree);

        if(pRootOfTree.right != null){
            Convert(pRootOfTree.right, list);
        }
    }
    //遍历list，修改指针
    public TreeNode Convert(ArrayList<TreeNode> list){
        for(int i = 0; i < list.size() - 1; i++){
            list.get(i).right = list.get(i + 1);
            list.get(i + 1).left = list.get(i);
        }
        return list.get(0);
    }


    class TreeNode {
        int val;
        //left as prev
        TreeNode left;
        //right as next
        TreeNode right;
    }

}
