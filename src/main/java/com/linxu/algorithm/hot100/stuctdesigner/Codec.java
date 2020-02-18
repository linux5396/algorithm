package com.linxu.algorithm.hot100.stuctdesigner;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author linxu
 * @date 2020/2/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，
 * 进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 示例: 
 * <p>
 * 你可以将以下二叉树：
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * 序列化为 "[1,2,3,null,null,4,5]"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        final String start = "[";
        final String end = "]";
        StringBuilder strBuilder = new StringBuilder(start);
        Deque<TreeNode> queue = new ArrayDeque<>();
        int rareNodes = 1;
        //下一层实际节点数
        int nextLevelNodes = 0;
        //下一层需要追加null的数量；
        int nextAppendNulls = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode headNode = queue.pollFirst();
            //append
            strBuilder.append(headNode.val + ",");
            rareNodes--;
            if (headNode.left != null) {
                nextLevelNodes++;
                queue.add(headNode.left);
            } else {
                nextAppendNulls++;
            }
            if (headNode.right != null) {
                nextLevelNodes++;
                queue.add(headNode.right);
            } else {
                nextAppendNulls++;
            }
            if (rareNodes == 0) {
                for (int i = 0; i < nextAppendNulls && nextLevelNodes != 0; i++) {
                    strBuilder.append("null,");
                }
                rareNodes = nextLevelNodes;
                nextLevelNodes = 0;
            }
        }
        strBuilder.deleteCharAt(strBuilder.lastIndexOf(","));
        strBuilder.append(end);
        return strBuilder.toString();
    }

    /**
     * //TODO 目前存在一个BUG就是丢失引用，只需要利用节点的值一次性预先构造出node，之后对这些node进行操作即可。
     * @param data
     * @return
     */
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = preProcess(data);
        //build root
        TreeNode root =null;
        List<String[]> list = new LinkedList<>();
        //基数
        int base = 1;
        int lastPosit = 0;
        for (int i = 0; i < nodes.length; i++) {
            if (i == base - 1) {
                String[] s = new String[base];
                for (int j = lastPosit; j < lastPosit + base; j++) {
                    s[j - lastPosit] = nodes[j];
                }
                list.add(s);
                lastPosit = lastPosit + base;
                base <<= 1;
            }
        }
        int levels = list.size();
        for (int i = 0; i < levels; i++) {
            //最后一层无需处理
            if (i == levels - 1) {
                break;
            }
            String[] curLevelNodes = list.get(i);
            String[] nextLevelNodes = list.get(i + 1);
            Deque<String> curQueue = new ArrayDeque<>();
            Deque<String> nextQueue = new ArrayDeque<>();
            //ini queue
            for (String s : curLevelNodes
                    ) {
                curQueue.addLast(s);
            }
            for (String s : nextLevelNodes
                    ) {
                nextQueue.addLast(s);
            }
            //build nodes
            //TODO full build
            //否则丢失引用
            while (!curQueue.isEmpty()) {
                String curNode = curQueue.pollFirst();
                if (!curNode.equals("null")) {
                    TreeNode node = new TreeNode(Integer.valueOf(curNode));
                    String left = nextQueue.pollFirst();
                    String right = nextQueue.pollFirst();
                    if (!left.equals("null")) {
                        TreeNode leftNode = new TreeNode(Integer.valueOf(left));
                        node.left = leftNode;
                    }
                    if (!right.equals("null")) {
                        TreeNode rightNode = new TreeNode(Integer.valueOf(right));
                        node.right = rightNode;
                    }
                }
            }
        }
        return root;
    }

    private String[] preProcess(String serialData) {
        String pre = serialData.substring(1, serialData.length() - 1);
        return pre.split(",");
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left_1 = new TreeNode(2);
        TreeNode right_1 = new TreeNode(3);
        TreeNode ri_lef = new TreeNode(4);
        TreeNode ri_ri = new TreeNode(5);
        //relate
        root.left = left_1;
        root.right = right_1;
        right_1.left = ri_lef;
        right_1.right = ri_ri;
        System.out.println(new Codec().serialize(root));

        System.err.println(new Codec().serialize( new Codec().deserialize(new Codec().serialize(root))));
    }
}
