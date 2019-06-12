package S801_820;

import utils.TreeNode;

/**
 * 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。
 * 返回移除了所有不包含 1 的子树的原二叉树。
 * (节点 X 的子树为 X 本身，以及所有 X 的后代。)
 * 示例1:
 * 输入: [1,null,0,0,1]
 * 输出: [1,null,0,null,1]
 * *      1            1
 * *       \            \
 * *        0            0
 * *       / \            \
 * *      0   1            1
 */
public class S814 {

    public TreeNode pruneTree(TreeNode root) {
        // 避免只有一个0的情况
        if (allZeros(root)) return null;
        return root;
    }

    private boolean allZeros(TreeNode root) {
        if (root == null) return true;
        boolean left = allZeros(root.left);
        boolean right = allZeros(root.right);
        if (left) root.left = null;
        if (right) root.right = null;
        return root.val == 0 && left && right;
    }

    public TreeNode pruneTree2(TreeNode root) {
        if (root == null) return null;
        // 先修剪左右子树
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        // 当前节点值为0，且是叶子节点，则需要修剪
        if (root.val == 0 && root.left == null && root.right == null)
            return null;
        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, null, 0, 0, 1});
        root = new S814().pruneTree(root);
        TreeNode.inOrder(root);
    }
}
