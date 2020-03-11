package com.linxu.algorithm.data_struct;


/**
 * @author linxu
 * @date 2020/3/11
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class TrieTreeT {
    private final TrieNode root;

    public TrieTreeT() {
        this.root = new TrieNode();
    }

    public void insert(String ip) {
        String[] subIp = ip.split("\\.");
        TrieNode node = root;
        for (int i = 0; i < subIp.length; i++) {
            if (!node.contains(subIp[i])) {
                node.put(subIp[i]);
            }
            node = node.get(subIp[i]);
        }
        node.isLeaf = true;
    }

    private TrieNode searchPrefix(String ip) {
        String[] subIp = ip.split("\\.");
        TrieNode node = root;
        for (int i = 0; i < subIp.length; i++) {
            if (node.contains(subIp[i])) {
                node = node.get(subIp[i]);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String ip) {
        TrieNode node=searchPrefix(ip);
        return node != null && node.isLeaf;
    }

    public boolean startWith(String ip) {
        return searchPrefix(ip) != null;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[256];
        boolean isLeaf;

        public TrieNode() {

        }

        public final boolean isLeaf() {
            return isLeaf;
        }

        public boolean contains(String key) {
            return children[Integer.valueOf(key)] != null;
        }

        public void put(String key) {
            children[Integer.valueOf(key)] = new TrieNode();
        }

        public TrieNode get(String key) {
            return children[Integer.valueOf(key)];
        }
    }

    public static void main(String[] args) {
        TrieTreeT t=new TrieTreeT();
        t.insert("10.21.56.130");
        System.out.println(t.search("10.21.56.131"));
    }
}
