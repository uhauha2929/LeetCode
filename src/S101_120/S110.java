package S101_120;

import util.TreeNode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 * *
 * *     3
 * *    / \
 * *   9  20
 * *     /  \
 * *    15   7
 * 返回 true 。
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * *
 * *        1
 * *       / \
 * *      2   2
 * *     / \
 * *    3   3
 * *   / \
 * *  4   4
 * 返回 false 。
 */
public class S110 {

    // 计算每个子树的高度会重复计算
    public boolean isBalanced(TreeNode root) {
        return root == null || isBalanced(root.left) && isBalanced(root.right)
                && Math.abs(height(root.left) - height(root.right)) <= 1;
    }

    private int height(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(height(root.left), height(root.right));
    }

    public boolean isBalanced2(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1)
            return -1;
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1)
            return -1;
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4});
        System.out.println(new S110().isBalanced2(root));
    }
}
