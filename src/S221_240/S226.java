package S221_240;

import util.TreeNode;

/**
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * *
 * *      4
 * *    /   \
 * *   2     7
 * *  / \   / \
 * * 1   3 6   9
 * 输出：
 * *
 * *      4
 * *    /   \
 * *   7     2
 * *  / \   / \
 * * 9   6 3   1
 */


public class S226 {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        if (root.left != null || root.right != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


    public static void main(String[] args) {
        TreeNode.inOrder(new S226().invertTree(
                new TreeNode(new Integer[]{4, 2, 7, 1, 3, 6, 9})));
    }
}
