package com.linxu.algorithm.data_struct;

import lombok.Data;

import java.util.*;

/**
 * @author linxu
 * @date 2019/10/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class SkipList<T extends Comparable<? super T>> {
    /**
     * 提升的概率阈值
     */
    private static final Double PROBABILITY = 1D;
    private static final Random RANDOM= new Random();
    //default level
    private int level = 4;
    private int size;

    private SkipListNode top;

    public SkipList(int level, T start, T end) {
        if (level > 0) {
            this.level = level;
        }
        SkipListNode skipListNode = null;
        SkipListNode temp = top;
        SkipListNode tempDown = null;
        SkipListNode tempNextDown = null;
        int tempLevel = level;
        while (tempLevel-- != 0) {
            skipListNode = new SkipListNode();
            skipListNode.setValue(start);
            temp = skipListNode;
            skipListNode = new SkipListNode();
            skipListNode.setValue(end);
            temp.setNext(skipListNode);
            temp.setBelowNext(tempDown);
            temp.getNext().setBelowNext(tempNextDown);
            tempDown = temp;
            tempNextDown = temp.getNext();
        }
        top = temp;
    }

    /**
     * 该值插入的层数
     */
    private int randomLevel() {
        int k = 1;
        //simulate the throw cow. and add second condition to avoid the overmuch compute.
        while (RANDOM.nextInt(2) == PROBABILITY && k <= level) {
            ++k;
        }
        return k;
    }

    public SkipListNode search(T value) {
        SkipListNode node = top;
        int sum = 0;
        while (true) {
            while (node.next.value.compareTo(value) < 0) {
                sum++;
                //优化，提前返回，但是这个概率特别小
                if (node.next.value.compareTo(value) == 0) {
                    return node.next;
                }

                node = node.next;
            }
            if (node.belowNext == null) {
                System.err.println("查找次数：" + sum);
                return node;
            }
            node = node.belowNext;
            sum++;
        }
    }

    public void add(T value) {
        SkipListNode newNode = null;
        //获取层次
        int k = randomLevel();
        //对应层次的根节点
        SkipListNode levelRoot = top;
        //当前的表的最大层次
        int tempLevel = level;
        SkipListNode tempNode = null;
        SkipListNode prevNode = null;
        //当在第n行插入后，在第n - 1行插入时需要将第n行backTempNode的belowNext域指向第n - 1的域
        SkipListNode backTempNode = null;
        int flag = 1;
        //执行降级，直到对应的层次
        while (tempLevel-- != k && levelRoot != null) {
            levelRoot = levelRoot.belowNext;
        }
        //找到对应的层次
        tempNode = levelRoot;
        //小于k层的都需要进行插入
        while (k-- != 0) {
            //在第k层寻找要插入的位置
            while (tempNode != null && tempNode.next.value.compareTo(value) < 0) {
                //直到找到一个比要插入的数值大的
                tempNode = tempNode.next;
            }
            prevNode = tempNode;
            //构造新结点
            newNode = new SkipListNode();
            newNode.value = value;
            //如果不是顶层
            if (flag != 1) {
                backTempNode.belowNext = newNode;
            }
            backTempNode = newNode;
            //执行插入,tempNode.next.value>newNode.value && newNode.value>tempNode.value
            if (prevNode != null) {
                //如果tempNode不为空
                newNode.next = prevNode.next;
                prevNode.next = newNode;
                //调整标志位，意味着后续每层需要链接
                flag = 0;
                //tempNode下移一层，因为下一层的插入肯定是从tempNode.belowNext开始查找插入。而不必每次都从levelRoot去遍历。
                tempNode = prevNode.belowNext;
            } else {
                newNode.next = new SkipListNode(null, null, null);
                flag = 0;
            }
        }
    }


    @Data
    private class SkipListNode {
        T value;
        SkipListNode next;
        SkipListNode belowNext;

        SkipListNode() {
        }

        SkipListNode(T value, SkipListNode next, SkipListNode belowNext) {
            this.value = value;
            this.next = next;
            this.belowNext = belowNext;
        }
    }

    public void print() {
        SkipListNode node = top;
        SkipListNode below = top.belowNext;
        while (below != null) {
            System.out.print(node.getValue() + ",");
            node = node.next;
            if (node.next == null) {
                System.out.println();
                node = below;
                below = below.belowNext;
                //最下层
                if (below == null) {
                    while (node != null) {
                        System.out.print(node.getValue() + ",");
                        node = node.next;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        long startTime = 0;
        long endTime = 0;
        SkipList<Integer> skipList = new SkipList<>(32, Integer.MIN_VALUE, Integer.MAX_VALUE);
        LinkedList<Integer> list=new LinkedList<>();

        Set<Integer> set=new HashSet<>();
        for (int i = 1; i <100000 ; i++) {
            set.add(i);
            list.add(i);
            skipList.add(i);
        }

        endTime = System.currentTimeMillis();
        System.out.printf("createSkipList:%d\n", endTime - startTime);
        startTime = System.currentTimeMillis();
        System.out.printf("find：%d\n", skipList.search(56798).getNext().getValue());
        endTime = System.currentTimeMillis();
        System.out.printf("skipList spent:%d\n\n", endTime - startTime);

        startTime = System.currentTimeMillis();
        boolean f= set.contains(56798);
        endTime = System.currentTimeMillis();
        if (f) {
            System.out.printf("hash set spent：%d\n",endTime-startTime);
        }
        startTime = System.currentTimeMillis();
         f= list.contains(56789);
        endTime = System.currentTimeMillis();
        if (f){
            System.out.printf("linked list spent：%d\n",endTime-startTime);
        }
     //   skipList.print();
    }
}
