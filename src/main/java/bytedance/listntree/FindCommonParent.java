package bytedance.listntree;

import comeon.binarytree.TreeNode;

/**
 * @author linxu
 * @date 2020/3/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class FindCommonParent {
    /**
     * 查找P Q的公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            //说明在右子树上
            return right;
        } else if (right == null) {
            //在左子树上
            return left;
        }
        //两者都不为空，当前节点为祖先
        return root;
    }
}
