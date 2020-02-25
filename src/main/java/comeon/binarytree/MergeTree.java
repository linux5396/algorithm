package comeon.binarytree;

/**
 * @author linxu
 * @date 2020/2/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class MergeTree {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return merge(t1, t2);
    }

    /**
     * 递归版
     * @param t1
     * @param t2
     * @return
     */
    private TreeNode merge(TreeNode t1, TreeNode t2) {
        TreeNode root = null;
        if (t1 != null && t2 != null) {
            root = new TreeNode(t1.val + t2.val);
            root.left = merge(t1.left, t2.left);
            root.right = merge(t1.right, t2.right);
        } else if (t1 != null) {
            root = new TreeNode(t1.val);
            root.left = merge(t1.left, null);
            root.right = merge(t1.right, null);
        } else if (t2 != null) {
            root = new TreeNode(t2.val);
            root.left = merge(t2.left, null);
            root.right = merge(t2.right, null);
        } else {
            return null;
        }
        return root;
    }
    //TODO 可以用层次遍历来实现。
}
