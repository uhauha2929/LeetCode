package S561_580;

import utils.TreeNode;

/**
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 * 示例 1:
 * 给定的树 s:
 * *
 * *      3
 * *     / \
 * *    4   5
 * *   / \
 * *  1   2
 * 给定的树 t：
 * *    4
 * *   / \
 * *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 * 示例 2:
 * 给定的树 s：
 * *      3
 * *     / \
 * *    4   5
 * *   / \
 * *  1   2
 * *     /
 * *    0
 * 给定的树 t：
 * *    4
 * *   / \
 * *  1   2
 * 返回 false。
 */
public class S572 {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return identical(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean identical(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && identical(s.left, t.left) && identical(s.right, t.right);
    }


    public static void main(String[] args) {
        TreeNode s = new TreeNode(new Integer[]{3, 4, 5, 1, 2, null, 0});
        TreeNode t = new TreeNode(new Integer[]{4, 1, 2});
        System.out.println(new S572().isSubtree(s, t));
    }
}
