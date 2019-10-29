package com.linxu.algorithm.bydate.date191030;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.List;

/**
 * @author linxu
 * @date 2019/10/29
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：对一个序列的字符实现全排列
 */
public class Permutation {
    public void permutation(char[] seq, int start, int end) {
        if (end <= 1) {
            return;
        }
        if (start == end) {
            for (char aSeq : seq) {
                System.out.print(aSeq);
            }
            System.out.println("");
        } else {
            //每一个字符都要有做第一个元素的机会，即将剩下的元素依次与第一个进行交换
            for (int i = start; i <= end; i++) {
                //执行依次交换
                GenerationUtil.swap(seq, i, start);
                permutation(seq, start + 1, end);
                //本次交换后变回原本序列，保证下次交换是在原本的基础上
                GenerationUtil.swap(seq, i, start);
            }
        }
    }

    public static void main(String[] args) {
        new Permutation().permutation("abc".toCharArray(), 0, 2);
    }

}
