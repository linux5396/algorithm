package com.linxu.algorithm.data_struct;

import java.lang.reflect.Array;

/**
 * @author linxu
 * @since JDK1.8
 * <p>
 *  抽象前缀树
 * </p>
 */
public abstract class AbstractTrieTree<T extends Comparable<T>> {
    private final int size;
    private final TrieNode root;

    /**
     * @param size children的大小，比如character就是26；IP的就是256
     */
    public AbstractTrieTree(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size can not be less than 0.");
        }
        this.size = size;
        root = new TrieNode();
    }

    /**
     * @param key 前缀串
     */
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

    /**
     * @param key 前缀串
     * @return 是否存在
     */
    @SafeVarargs
    public final boolean search(T... key) {
        if (key == null) {
            return false;
        }
        TrieNode node = searchPrefix(key);
        return node != null && node.isLeaf;
    }

    /**
     * @param key 最前缀
     * @return 是否存在
     */
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
     * 正常来讲，前缀树不属于局部使用，而是全局的调用；
     * 如果当想要释放该前缀树的内存，而由于全局的某个包装对象
     * 还不能被回收，因此，可以调用该方法对children以下的所有内存进行GC ROOTS释放
     */
    public void clear() {
        this.root.children = null;
    }

    /**
     * 如果想对前缀树的目前数据做全盘清理，只需要调用该方法
     */
    @SuppressWarnings("unchecked")
    public void resume() {
        clear();
        this.root.children = (TrieNode[]) Array.newInstance(TrieNode.class, size);
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
    final class TrieNode {
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
