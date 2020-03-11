package com.linxu.algorithm.data_struct;

import java.lang.reflect.Array;

/**
 * @author linxu
 * @date 2020/3/11
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public abstract class AbstractTrieTree<T extends Comparable<T>> {
    private final int size;
    private final TrieNode root;

    public AbstractTrieTree(int size) {
        this.size = size;
        root = new TrieNode();
    }

    @SafeVarargs
    public final void insert(T... key) {
        if (key == null || key.length == 0) {
            return;
        }
        TrieNode node = root;
        for (int i = 0; i < key.length; i++) {
            if (!node.contains(key[i])) {
                node.put(key[i]);
            }
            node = node.get(key[i]);
        }
        node.isLeaf = true;
    }

    @SafeVarargs
    public final boolean search(T... key) {
        if (key == null) {
            return false;
        }
        TrieNode node = searchPrefix(key);
        return node != null && node.isLeaf;
    }

    @SafeVarargs
    public final boolean startWith(T... key) {
        if (key == null) {
            return false;
        }
        TrieNode node = searchPrefix(key);
        return node != null;
    }

    @SafeVarargs
    private final TrieNode searchPrefix(T... prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length; i++) {
            if (node.contains(prefix[i])) {
                node = node.get(prefix[i]);
            } else {
                return null;
            }
        }
        return node;
    }

    /**
     * must impl this method to locate treeNode child index.
     *
     * @param key key
     * @return index of children array
     */
    protected int index(T key) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @SuppressWarnings("unchecked")
    class TrieNode {
        TrieNode[] children;
        boolean isLeaf = false;

        TrieNode() {
            this.children = (TrieNode[]) Array.newInstance(TrieNode.class, size);
        }

        boolean contains(T key) {
            return children[index(key)] != null;
        }

        void put(T key) {
            children[index(key)] = new TrieNode();
        }

        TrieNode get(T key) {
            return children[index(key)];
        }
    }
}
