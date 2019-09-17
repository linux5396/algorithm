package com.linxu.algorithm.bydate.date190915;

import com.linxu.algorithm.bydate.date190912.RebuildBinaryTree;

/**
 * @author linxu
 * @date 2019/9/15
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class NextTreeNode {
    Node root;
    Node waitFind;

    public void setRoot(Node root) {
        this.root = root;
    }

    private void printInOrder(NextTreeNode.Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.value);
            printInOrder(node.right);
        }
    }

    Node getCurrentNodeByValue(int value, Node node) {
        if (node != null && node.value == value) {
            waitFind = node;
            return node;
        } else if (node != null) {
            getCurrentNodeByValue(value, node.left);
            getCurrentNodeByValue(value, node.right);
        }
        return null;
    }

    Node findNextNode(Node currentNode) throws NullPointerException {
        if (currentNode == null) {
            throw new NullPointerException("null");
        }
        Node nextNode = null;
        //情况一，如果有右子树，下个节点就是右子树的最左节点(右子树如果只有根节点，那么即为根节点)
        if (hasRightChild(currentNode)) {
            Node right = currentNode.right;
            nextNode = currentNode.right;
            while (right.left != null) {
                nextNode = right.left;
                right.left = right.left.left;
            }
            return nextNode;
        }
        //情况二，如果一个节点没有右子树，并且是父节点的左孩子，那么下一个节点就是父亲节点
        if (!hasRightChild(currentNode)&&!isRightChild(currentNode,currentNode.parent)) {
            nextNode = currentNode.parent;
            return nextNode;
        }
        //情况三，如果没有右子树，还是父结点的右节点，那么就需要向上追溯
        if (!hasRightChild(currentNode) && isRightChild(currentNode, currentNode.parent)) {
            Node parent = currentNode.parent;
            while (isRightChild(parent, parent.parent)) {
                parent = parent.parent;
            }
            //直到是父节点的左节点，那么下一个就是父节点
            nextNode = parent.parent;
            return nextNode;
        }
        return nextNode;
    }

    private boolean isRightChild(Node cur, Node parent) throws NullPointerException {
        if (cur == null || parent == null) {
            throw new NullPointerException("null");
        }
        return cur == parent.right;
    }

    private boolean hasRightChild(Node node) {
        return node.right != null;
    }

    public Node build(int[] preOrderSequence, int[] inOrderSequence) {
        root = build(preOrderSequence, 0, preOrderSequence.length - 1, inOrderSequence, 0, inOrderSequence.length - 1);
        return root;
    }

    private Node build(int[] preSequence, int preStart, int preEnd, int[] inSequence, int inStart, int inEnd) {
        //创建根节点
        Node root = new Node(preSequence[preStart]);
        //中序序列的开端
        int rootIdx = inStart;
        //计算ROOT在中序序列中的位置
        //TODO 如果上层没有对序列进行验证，这里会存在数组越界、空指针等问题
        while (rootIdx <= inEnd && inSequence[rootIdx] != root.value) {
            rootIdx++;
        }
        //计算左子树的规模
        int leftLength = rootIdx - inStart;
        //计算在前序序列中的左子树的结束坐标=前序的起始坐标+左子树规模
        int leftPreEnd = preStart + leftLength;
        //如果左子树存在
        if (leftLength > 0) {
            Node node = build(preSequence, preStart + 1, leftPreEnd, inSequence, inStart, rootIdx - 1);
            root.left = node;
            node.parent = root;
        }
        //如果右子树存在
        if (leftLength < preEnd - preStart) {
            Node node = build(preSequence, leftPreEnd + 1, preEnd, inSequence, rootIdx + 1, inEnd);
            root.right = node;
            node.parent = root;
        }
        return root;
    }

    private static class Node {
        private final int value;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int[] preSequence = {1, 2, 4, 5, 8, 9, 3, 6, 7};
        int[] inSequence = {4, 2, 8, 5, 9, 1, 6, 3, 7};
        NextTreeNode tree = new NextTreeNode();
        tree.build(preSequence, inSequence);
        tree.getCurrentNodeByValue(2, tree.root);
        if (tree.waitFind != null) {
            Node find = tree.findNextNode(tree.waitFind);
            System.out.println(find.value);
        }
    }
}
