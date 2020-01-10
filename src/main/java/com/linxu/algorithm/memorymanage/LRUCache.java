package com.linxu.algorithm.memorymanage;

import java.util.*;


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
public class LRUCache<K, V> {
    private int size;
    /**
     * virtual nodes
     */
    private Node tail, head;
    private int capacity;

    private final Map<K, Node> cache = new HashMap<>();


    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity can not be < 1");
        }
        tail = new Node(null, null);
        head = new Node(null, null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
        this.capacity = capacity;
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        } else {
            V val = cache.get(key).val;
            //调用put更新即可
            put(key, val);
            return val;
        }
    }

    public void put(K key, V val) {
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

    public void print() {
        for (Map.Entry e : cache.entrySet()
                ) {
            System.out.println(e.getKey() + ":" + ((Node) e.getValue()).val);
        }
    }

    class Node {
        K key;
        V val;
        Node next, prev;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }


    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(4);
        cache.put("L", 10);
        cache.put("X", 12);
        cache.put("Z", 13);
        cache.put("M", 14);
        System.out.println(cache.get("L"));
        cache.put("T", 15);

    }

}
