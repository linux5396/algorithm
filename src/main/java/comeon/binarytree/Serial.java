package comeon.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author linxu
 * @date 2020/2/22
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 二叉树的序列化
 * :<p>
 * 原则就是用什么遍历序列化，就用什么遍历反序列化
 * </p>
 */
public class Serial {
    private static final String NULL_NODE = "#";
    private static final String END_SPILT = "!";

    public static String serial(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        preOrder(root, stringBuilder);
        return stringBuilder.toString();
    }

    private static void preOrder(TreeNode node, StringBuilder stringBuilder) {
        if (node == null) {
            stringBuilder.append(NULL_NODE + END_SPILT);
            return;
        }
        stringBuilder.append(node.val).append(END_SPILT);
        if (node.left != null) {
            preOrder(node.left, stringBuilder);
        } else {
            stringBuilder.append(NULL_NODE + END_SPILT);
        }
        if (node.right != null) {
            preOrder(node.right, stringBuilder);
        } else {
            stringBuilder.append(NULL_NODE + END_SPILT);
        }
    }

    public static TreeNode deSerial(String stream) {
        //non verify
        String[] node = stream.split(END_SPILT);
        Deque<String> queue = new ArrayDeque<>();
        for (String s : node) {
            queue.addLast(s);
        }
        node=null;
        return buildNode(queue);
    }

    private static TreeNode buildNode(Deque<String> nodes) {
        if (!nodes.isEmpty()) {
            String val = nodes.pollFirst();
            if (NULL_NODE.equals(val)) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(val));
            root.left = buildNode(nodes);
            root.right = buildNode(nodes);
            return root;
        }
        return null;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.val = 8;
        TreeNode n1 = new TreeNode();
        n1.val = 6;
        TreeNode n2 = new TreeNode();
        n2.val = 10;
        TreeNode n3 = new TreeNode();
        n3.val = 5;
        TreeNode n4 = new TreeNode();
        n4.val = 7;
        TreeNode n5 = new TreeNode();
        n5.val = 9;
        TreeNode n6 = new TreeNode();
        n6.val = 11;

        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        System.out.println(serial(root));
        System.err.println(serial(deSerial(serial(root))));
    }
}
