package bytedance.listntree;

import comeon.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author linxu
 * @date 2020/3/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class ZLevelTree {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> nodes = new ArrayList<>();
        Stack<TreeNode>[] stacks = new Stack[2];
        if (root==null){
            return nodes;
        }
        stacks[0] = new Stack<>();
        stacks[1] = new Stack<>();
        int whichStack = 0;
        boolean ctl = true;
        stacks[whichStack].push(root);
        while (!stacks[0].isEmpty() || !stacks[1].isEmpty()) {
            List<Integer> list = new ArrayList<>();
            while (!stacks[whichStack].isEmpty()) {
                TreeNode pop = stacks[whichStack].pop();
                list.add(pop.val);
                if (ctl) {
                    if (pop.left != null) {
                        stacks[1 - whichStack].push(pop.left);
                    }
                    if (pop.right != null) {
                        stacks[1 - whichStack].push(pop.right);
                    }
                } else {
                    if (pop.right != null) {
                        stacks[1 - whichStack].push(pop.right);
                    }
                    if (pop.left != null) {
                        stacks[1 - whichStack].push(pop.left);
                    }
                }
            }
            nodes.add(list);
            if (stacks[whichStack].isEmpty()) {
                whichStack = 1 - whichStack;
                ctl = !ctl;
            }
        }
        return nodes;
    }
}
