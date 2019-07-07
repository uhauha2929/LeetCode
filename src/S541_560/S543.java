package S541_560;

import util.TreeNode;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 * <p>
 * 示例 :
 * 给定二叉树
 * *
 * *           1
 * *          / \
 * *         2   3
 * *        / \
 * *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * <p>
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class S543 {

    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        recursion(root);
        return diameter;
    }

    private int recursion(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = recursion(root.left);
        int rightDepth = recursion(root.right);
        diameter = Math.max(diameter, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(new S543().diameterOfBinaryTree(root));
    }
}
