package com.linxu.algorithm.data_struct;

import java.util.*;

/**
 * @author linxu
 * @date 2020/2/23
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class ConsistentHash<T> {
    private final HashFunction hashFunction = new HashFunction();
    private final int factorOfVirtualNode;
    private final SortedMap<Long, T> circle = new TreeMap<>();

    public ConsistentHash(int factorOfVirtualNode, Collection<T> nodes) {
        this.factorOfVirtualNode = factorOfVirtualNode;
        for (T node : nodes) {
            //add
            add(node);
        }
    }

    public void add(T node) {
        for (int i = 0; i <factorOfVirtualNode; i++) {
            circle.put(hashFunction.hash(node.toString() + i), node);
        }
    }

    /*
     * 获得一个最近的顺时针节点,根据给定的key 取Hash
     * 然后再取得顺时针方向上最近的一个虚拟节点对应的实际节点
     * 再从实际节点中取得 数据
     */
    public T get(Object key) {
        if (circle.isEmpty())
            return null;
        long hash = hashFunction.hash((String) key);// node 用String来表示,获得node在哈希环中的hashCode
        if (!circle.containsKey(hash)) {//数据映射在两台虚拟机器所在环之间,就需要按顺时针方向寻找机器
            //tailMap其实就是sortMap的一个视图，返回hash值大于等于key的部分
            SortedMap<Long, T> tailMap = circle.tailMap(hash);
            System.err.println(hash);
            System.err.println(tailMap.toString());
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

    public void remove(T node) {
        for (int i = 0; i < factorOfVirtualNode; i++)
            circle.remove(hashFunction.hash(node.toString() + i));
    }

    public long getSize() {
        return circle.size();
    }

    public void testBalance() {
        Set<Long> sets = circle.keySet();//获得TreeMap中所有的Key
        SortedSet<Long> sortedSets = new TreeSet<Long>(sets);//将获得的Key集合排序
        for (Long hashCode : sortedSets) {
            System.out.println(hashCode);
        }

        System.out.println("----each location 's distance are follows: ----");
        /*
         * 查看用MD5算法生成的long hashCode 相邻两个hashCode的差值
         */
        Iterator<Long> it = sortedSets.iterator();
        Iterator<Long> it2 = sortedSets.iterator();
        if (it2.hasNext())
            it2.next();
        long keyPre, keyAfter;
        while (it.hasNext() && it2.hasNext()) {
            keyPre = it.next();
            keyAfter = it2.next();
            System.out.println(keyAfter - keyPre);
        }
    }
    public void printView(){
        System.err.println("current view is:");
        System.err.println(circle.toString());
    }
    public static void main(String[] args) {
        Set<String> nodes = new HashSet<String>();
        nodes.add("10.21.56.130");
        nodes.add("10.21.56.132");
        nodes.add("10.21.56.134");


        ConsistentHash<String> consistentHash = new ConsistentHash<String>(2, nodes);
        //consistentHash.add("D");

       // System.out.println("hash circle size: " + consistentHash.getSize());
       // System.out.println("location of each node are follows: ");
        consistentHash.testBalance();
        System.err.println(consistentHash.get("D"));
        consistentHash.printView();
        consistentHash.add("10.21.56.153");
        consistentHash.printView();
    }

}
