package comeon.binarytree;

import java.util.*;

/**
 * @author linxu
 * @date 2020/2/22
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 二叉树的一系列的遍历问题
 * <p>
 * 由于递归版的先序、后序、中序比较简单，就不写了。
 * </p>
 */
public class Traverse {
    /**
     * 常量换行符
     */
    private static final String ENTER = "\n";

    /**
     * 不分行的层次遍历；
     */
    public ArrayList<Integer> print(TreeNode root) {
        if (root != null) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            Queue<TreeNode> queue = new ArrayDeque<>();
            ((ArrayDeque<TreeNode>) queue).addFirst(root);
            while (!queue.isEmpty()) {
                TreeNode headTreeNode = ((ArrayDeque<TreeNode>) queue).pollFirst();
                arrayList.add(headTreeNode.val);
                System.err.print(headTreeNode.val);
                if (headTreeNode.left != null) {
                    ((ArrayDeque<TreeNode>) queue).addLast(headTreeNode.left);
                }
                if (headTreeNode.right != null) {
                    ((ArrayDeque<TreeNode>) queue).addLast(headTreeNode.right);
                }
            }
            return arrayList;
        }
        return new ArrayList<>();
    }

    /**
     * 使用两个变量控制换行；一个为当前行还没打印的节点数；一个为下一行的节点数。
     */
    public void printWithEnter(TreeNode root) {
        if (root != null) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            ((ArrayDeque<TreeNode>) queue).addFirst(root);
            int rareTreeNode = 1;
            int nextLevelTreeNodes = 0;
            while (!queue.isEmpty()) {
                TreeNode headTreeNode = ((ArrayDeque<TreeNode>) queue).pollFirst();
                rareTreeNode--;
                System.err.print(headTreeNode.val + ",");
                if (headTreeNode.left != null) {
                    ((ArrayDeque<TreeNode>) queue).addLast(headTreeNode.left);
                    nextLevelTreeNodes++;
                }
                if (headTreeNode.right != null) {
                    ((ArrayDeque<TreeNode>) queue).addLast(headTreeNode.right);
                    nextLevelTreeNodes++;
                }
                if (rareTreeNode == 0) {
                    System.out.print(ENTER);
                    rareTreeNode = nextLevelTreeNodes;
                    nextLevelTreeNodes = 0;
                }
            }
        }
    }

    /**
     * 使用两个变量控制换行；一个为当前行还没打印的节点数；一个为下一行的节点数。
     * “之”型
     *
     * @param root
     */
    public void printInZ(TreeNode root) {
        if (root != null) {
            Stack<TreeNode>[] stacks = new Stack[2];
            int current = 0;
            int next = 1;
            stacks[0] = new Stack<>();
            stacks[1] = new Stack<>();
            stacks[current].push(root);
            while (!stacks[0].empty() || !stacks[1].empty()) {
                TreeNode head = stacks[current].pop();
                System.err.print(head.val + ",");
                if (current == 0) {
                    if (head.left != null) {
                        stacks[next].push(head.left);
                    }
                    if (head.right != null) {
                        stacks[next].push(head.right);
                    }
                } else {
                    if (head.right != null) {
                        stacks[next].push(head.right);
                    }
                    if (head.left != null) {
                        stacks[next].push(head.left);
                    }
                }
                if (stacks[current].empty()) {
                    System.out.print(ENTER);
                    current = 1 - current;
                    next = 1 - next;
                }
            }
        }
    }

    /****************非递归版的用栈遍历二叉树***************/
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            if (curNode != null) {
                stack.push(curNode);
                list.add(curNode.val);
                curNode = curNode.left;
            } else {
                curNode = stack.pop();
                curNode = curNode.right;
            }
        }
        return list;
    }

    public List<Integer> inOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            //有左节点则持续入栈
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                //直到没左节点，开始出栈，出栈的同时(执行访问)，指向右节点
                curNode = stack.pop();
                list.add(curNode.val);
                curNode = curNode.right;
            }
        }
        return list;
    }
    public List<Integer> postOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            //与先序相反，先走右
            if (curNode != null) {
                stack.push(curNode);
                list.add(curNode.val);
                curNode = curNode.right;
            } else {
                //再走左
                curNode = stack.pop();
                curNode = curNode.left;
            }
        }
        //执行逆序即可
        Collections.reverse(list);
        return list;
    }
}
