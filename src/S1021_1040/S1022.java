package S1021_1040;

import util.TreeNode;

/**
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 * 以 10^9 + 7 为模，返回这些数字之和。
 * 示例：
 * *              1
 * *             / \
 * *            0   1
 * *           / \ / \
 * *          0  1 0  1
 * 输入：[1,0,1,0,1,0,1]
 * 输出：22
 * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 * 提示：
 * 树中的结点数介于 1 和 1000 之间。
 * node.val 为 0 或 1 。
 */
public class S1022 {

    private int total = 0;

    public int sumRootToLeaf(TreeNode root) {
        dfs(root, 0);
        return total;
    }

    // 类似solution257
    private void dfs(TreeNode root, int sum) {
        if (root == null) return;
        sum <<= 1;
        sum += root.val;
        if (root.left == null && root.right == null) {
            total += sum;
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{
                1, 0, 1, 0, 1, 0, 1
        });
        System.out.println(new S1022().sumRootToLeaf(root));
    }
}
