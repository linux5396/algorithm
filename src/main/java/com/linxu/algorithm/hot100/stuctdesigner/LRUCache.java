package com.linxu.algorithm.hot100.stuctdesigner;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @author linxu
 * @date 2020/1/10
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，
 * 它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 */
public class LRUCache implements Serializable,Cloneable {
    private int size;
    /**
     * virtual nodes
     */
    private Node tail, head;
    private int capacity;

    private final Map<Integer, Node> cache = new HashMap<>();


    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity can not be < 1");
        }
        tail = new Node(0,0);
        head = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        } else {
          int   val = cache.get(key).val;
            //调用put更新即可
            put(key, val);
            return val;
        }
    }

    public void resize(int newCapacity) {
        if (newCapacity < 1) {
            throw new IllegalArgumentException("newCapacity can not be less than 1");
        }
        //只有容量减少才需要操作
        if (newCapacity < capacity) {
            for (int i = capacity - newCapacity; i >= 0; i--) {
                removeLast();
            }
        }
        capacity = newCapacity;
    }

    public void put(int key, int val) {
        //构造新节点
        Node newNode = new Node(key, val);
        if (cache.containsKey(key)) {
            //remove old
            remove(cache.get(key));
            //add new
            addFirst(newNode);
            //更新map
            cache.put(key, newNode);
        } else {
            if (capacity == size()) {
                Node last = removeLast();
                assert last != null;
                cache.remove(last.key);
            }
            //添加到头部
            addFirst(newNode);
            cache.put(key, newNode);
        }
    }

    private void addFirst(Node node) {
        if (node != null) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }
    }

    private void remove(Node node) {
        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }

    private Node removeLast() {
        if (tail.prev == head) {
            return null;
        }
        Node last = tail.prev;
        remove(last);
        return last;
    }

    public int size() {
        return size;
    }
    @SuppressWarnings("unchecked")
    public void print() {
        for (Map.Entry e : cache.entrySet()
                ) {
            System.out.println(e.getKey() + ":" + ((Node) e.getValue()).val);
        }
    }

    class Node {
        int key;
        int val;
        Node next, prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(4);
    }

}
