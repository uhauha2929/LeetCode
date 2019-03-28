package S101_120;

import utils.TreeNode;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * *
 * *               5
 * *              / \
 * *             4   8
 * *            /   / \
 * *           11  13  4
 * *          /  \      \
 * *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 */
public class S112 {

    private int currentSum = 0;

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        currentSum += root.val;
        if (root.left == null && root.right == null)
            if (currentSum == sum)
                return true;
        if (hasPathSum(root.left, sum) || hasPathSum(root.right, sum))
            return true;
        currentSum -= root.val; // currentSum在函数外, 向上回溯时需减去当前值
        return false;
    }

    // 类似
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        System.out.println(new S112().hasPathSum(root, 22));
    }
}
