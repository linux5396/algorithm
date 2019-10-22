package com.linxu.algorithm.bydate.date191022;

/**
 * @author linxu
 * @date 2019/10/22
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：判断一个序列是否是二叉搜索树的后序遍历序列。
 */
public class BinaryTreePostOrder {
    public boolean isPostSeq(int[] seq) {
        //solve special case
        if (seq == null||seq.length==0) {
            return false;
        }
        //normal do
        return isPostSeq(seq, 0, seq.length - 1);
    }

    private boolean isPostSeq(int[] seq, int start, int end) throws ArrayIndexOutOfBoundsException {
        //当start=end,传入递归下一层则是start,end-1;则会出现这种情况。
        //当start-end=1;则刚好相等
        if (start == end||start-end==1) {
            return true;
        }
        int leftIdx = start;
        //left child tree end bound= start~leftIdx-1;
        for (; leftIdx < end; leftIdx++) {
            if (seq[leftIdx] > seq[end]) {
                break;
            }
        }
        int rightIdx = leftIdx;
        //check right child tree
        for (; rightIdx < end; rightIdx++) {
            if (seq[rightIdx] < seq[end]) {
                return false;
            }
        }
        return isPostSeq(seq, start, leftIdx - 1)&&isPostSeq(seq, rightIdx, end - 1);
    }
    public static void main(String[] args) {
        int[] seq = {5, 7, 6, 9, 11, 10, 8};
        System.out.println(new BinaryTreePostOrder().isPostSeq(seq));
    }
}
