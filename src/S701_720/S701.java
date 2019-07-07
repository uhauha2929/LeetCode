package S701_720;

import util.TreeNode;

/**
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
 * 例如,
 * 给定二叉搜索树:
 * *         4
 * *        / \
 * *       2   7
 * *      / \
 * *     1   3
 * 和 插入的值: 5
 * 你可以返回这个二叉搜索树:
 * *          4
 * *        /   \
 * *       2     7
 * *      / \   /
 * *     1   3 5
 * 或者这个树也是有效的:
 * *          5
 * *        /   \
 * *       2     7
 * *      / \
 * *     1   3
 * *          \
 * *           4
 */
public class S701 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode y = null;
        TreeNode x = root;
        while (x != null) {
            y = x; // 记录要插入位置的父节点
            if (val < x.val)
                x = x.left;
            else
                x = x.right;
        }
        TreeNode node = new TreeNode(val);
        if (y == null)
            return node;  // 空树, 直接作为根节点返回
        else if (val < y.val)
            y.left = node;
        else
            y.right = node;
        return root;
    }

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val < val)
            root.right = insertIntoBST2(root.right, val);
        else
            root.left = insertIntoBST2(root.left, val);
        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{
                4, 2, 7, 1, 3
        });
        root = new S701().insertIntoBST2(root, 5);
        TreeNode.inOrder(root);
    }
}
