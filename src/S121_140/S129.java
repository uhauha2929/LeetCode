package S121_140;

import utils.TreeNode;

/**
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * *     1
 * *    / \
 * *   2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 * <p>
 * 输入: [4,9,0,5,1]
 * *     4
 * *    / \
 * *   9   0
 * *  / \
 * * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 */
public class S129 {
    private int totalSum;

    public int sumNumbers(TreeNode root) {
        totalSum = 0;
        backtrack(root, 0);
        return totalSum;
    }


    private void backtrack(TreeNode root, int cur) {
        if (root == null) return;
        cur = cur * 10 + root.val;
        if (root.left == null && root.right == null)
            totalSum += cur;
        backtrack(root.left, cur);
        backtrack(root.right, cur);
    }

    public int sumNumbers2(TreeNode root) {
        return recursion(root, 0);
    }

    private int recursion(TreeNode root, int cur) {
        if (root == null)
            return 0;
        cur = cur * 10 + root.val; // 当前计算的数字, 向下一层传递
        // 只有当为叶子节点是返回最终的数字, 对左子树右子树进行递归求和
        return root.left == null && root.right == null ?
                cur : recursion(root.right, cur) + recursion(root.left, cur);
    }

    public static void main(String[] args) {
        System.out.println(new S129().sumNumbers(new TreeNode(
                new Integer[]{4, 9, 0, 5, 1})));
    }
}
