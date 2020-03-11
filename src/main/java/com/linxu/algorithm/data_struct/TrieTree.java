package com.linxu.algorithm.data_struct;

/**
 * @author linxu
 * @date 2020/3/11
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TrieTree {
    private final TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public TrieTree() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if (!curNode.contains(curChar)) {
                curNode.put(curChar, new TrieNode());
            }
            curNode = curNode.get(curChar);
        }
        curNode.isLeaf = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isLeaf;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    private final TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (node.contains(ch)) {
                node = node.get(ch);
            } else {
                return null;
            }
        }
        return node;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isLeaf;
        private final char BASE = 'a';

        public TrieNode() {
        }

        public boolean contains(char ch) {
            return children[ch - BASE] != null;
        }

        public TrieNode get(char ch) {
            return children[ch - BASE];
        }

        public void put(char ch, TrieNode node) {
            children[ch - BASE] = node;
        }

        public final boolean isLeaf() {
            return isLeaf;
        }
    }

    public static void main(String[] args) {
        TrieTree trieTree=new TrieTree();
        trieTree.insert("apple");
        trieTree.insert("cats");
        System.out.println(trieTree.search("applq"));
        System.out.println(trieTree.startsWith("app"));
        System.out.println(trieTree.startsWith("ack"));
        System.out.println(trieTree.startsWith("ca"));
    }
}
