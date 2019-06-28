package S1061_1080;

import utils.TreeNode;

/**
 * 给定一棵二叉树的根 root，请你考虑它所有 从根到叶的路径：从根到任何叶的路径。（所谓一个叶子节点，就是一个没有子节点的节点）
 * 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为「不足节点」，需要被删除。
 * 请你删除所有不足节点，并返回生成的二叉树的根。
 * 示例:
 * *                       1
 * *                 /           \
 * *                2             3
 * *               /  \         /   \
 * *              4   -99     -99    7
 * *             / \  /  \    /  \   / \
 * *            8  9 -99 -99 12 13 -99 14
 * *
 * *                    1
 * *                  /  \
 * *                 2   3
 * *               /    /
 * *              4    7
 * *             / \  /
 * *            8  9 14
 * 提示：
 * 给定的树有 1 到 5000 个节点
 * -10^5 <= node.val <= 10^5
 * -10^9 <= limit <= 10^9
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insufficient-nodes-in-root-to-leaf-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1080 {

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        // 如果根节点为空或根节点也需要删除, 返回null
        if (root == null || DFS(root, limit, root.val)) return null;
        return root;
    }

    private boolean DFS(TreeNode root, int limit, int sum) {
        // 如果为叶子节点, 如果值小于limit, 则可以删除
        if (root.left == null && root.right == null)
            return sum < limit;
        // 如果左(右)子树为null, 默认为true
        boolean left = true, right = true;
        if (root.left != null) {
            left = DFS(root.left, limit, sum + root.left.val);
            // 如果左子树可以被删除, 则删除
            if (left) root.left = null;
        }
        if (root.right != null) {
            right = DFS(root.right, limit, sum + root.right.val);
            // 如果右子树可以被删除, 则删除
            if (right) root.right = null;
        }
        // 如果节点的左右子树都可以被删除, 则该节点也可以被删除
        return left && right;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 2, -3, -5, null, 4, null});
        root = new S1080().sufficientSubset(root, -1);
        TreeNode.inOrder(root);
    }
}
