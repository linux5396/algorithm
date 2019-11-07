package com.linxu.algorithm.bydate.date191030;

import com.linxu.algorithm.sort.Sorts;
import com.linxu.algorithm.utils.GenerationUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;


/**
 * @author linxu
 * @date 2019/10/29
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：对一个序列的字符实现全排列
 */
public class Permutation {
    /**
     * 递归实现全排列算法
     *
     * @param seq   序列
     * @param start 起始
     * @param end   seq.length-1
     */
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

    /**
     * 数值全排列的最接近的最小值算法
     *
     * @param seq       seq
     * @param start     the idx which is the up order by last one.
     * @param end       length-1
     * @param stack     栈顶元素为最小值
     * @param iniNumber 数值
     */
    public void permutation(char[] seq, int start, int end, Stack<String> stack, char[] iniNumber) {
        if (end <= 1) {
            return;
        }
        if (start == end) {
            //find the min algorithm
            String minNumber = stack.pop();
            String currentNumber = new String(seq);
            if (Integer.valueOf(minNumber) > Integer.valueOf(currentNumber) && Integer.valueOf(new String(iniNumber)) < Integer.valueOf(currentNumber)) {
                stack.push(currentNumber);
            } else {
                stack.push(minNumber);
            }
        } else {
            //每一个字符都要有做第一个元素的机会，即将剩下的元素依次与第一个进行交换
            for (int i = start; i <= end; i++) {
                //执行依次交换
                GenerationUtil.swap(seq, i, start);
                permutation(seq, start + 1, end, stack, iniNumber);
                //本次交换后变回原本序列，保证下次交换是在原本的基础上
                GenerationUtil.swap(seq, i, start);
            }
        }
    }

    /**
     * 查找最后一个升序序列的坐标
     *
     * @param numbers 数字字符
     * @return idx or 0(could not find) or -1(ex)
     */
    private int findLastUpOrderIdx(char[] numbers) {
        //TODO some verify.
        if (numbers != null) {
            for (int i = numbers.length - 1; i > 0; i--) {
                try {
                    if (numbers[i] > numbers[i - 1]) {
                        return --i;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    return -1;
                }
            }
        }
        return 0;
    }

    /**
     * 查找最全排列中比当前值大的最小值算法的实现
     *
     * @param numbers
     */
    public void findTheMostNearlyNumber(char[] numbers) {
        int idx = findLastUpOrderIdx(numbers);
        //solve special case.
        if (idx == numbers.length - 2) {
            GenerationUtil.swap(numbers, idx, idx + 1);
            System.err.println("the near number is : " + new String(numbers));
            return;
        }
        Stack<String> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE + "");
        char[] copy = numbers.clone();
        permutation(numbers, idx, numbers.length - 1, stack, copy);
        System.err.println("the near number is : " + stack.pop());
    }

    public static void main(String[] args) {
        new Permutation().findTheMostNearlyNumber("42531".toCharArray());
    }

}
