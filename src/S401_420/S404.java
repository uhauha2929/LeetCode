package S401_420;

import utils.TreeNode;

/**
 * 计算给定二叉树的所有左叶子之和。
 * 示例：
 * *     3
 * *    / \
 * *   9  20
 * *     /  \
 * *    15   7
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
public class S404 {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null
                && root.left.left == null
                && root.left.right == null) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(new S404().sumOfLeftLeaves(root));
    }
}
