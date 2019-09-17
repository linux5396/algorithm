package com.linxu.algorithm.bydate.date190911;

import com.linxu.algorithm.data_struct.DoubleList;

import java.util.*;

/**
 * @author linxu
 * @date 2019/9/11
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 单向链表的反向打印
 * 解决思想：
 * 1、栈
 * 2、递归
 */
public class PrintLinkedList {
    private static int ENDLESS = Integer.MAX_VALUE;

    /**
     * @param list     链表
     * @param useStack 是否使用栈
     */
    public static void printListReversingly(List<Integer> list, boolean useStack) {
        if (list == null || list.size() == 0) {
            throw new NullPointerException("empty ex");
        }
        if (useStack) {
            Stack<Integer> stack = new Stack<>();
            for (Integer aList : list) {
                stack.push(aList);
            }
            ArrayList<Integer> list1=new ArrayList<>();
            int size=stack.size();
            for (int i = 0; i <size ; i++) {
                list1.add(stack.pop());
            }
            System.out.println(list1);
           /* while (!stack.empty()) {
                System.out.println(stack.pop());
            }*/
        } else {
            //使用递归
            //递归输出链表需要指针，list无法提供
        }
    }

    //为了展示递归的一个简单的应用，采用求2进制数
    public static void binary(int number) {
        int rare = 0;
        if (number != 0) {
            rare = number % 2;
            binary(number / 2);
        }
        System.out.println(rare);
    }

    public static void main(String[] args) {
       // binary(4);
        List<Integer> list=new ArrayList<>();
        for (int i = 1; i <7 ; i++) {
            list.add(i);
        }
        printListReversingly(list,true);
    }
}
