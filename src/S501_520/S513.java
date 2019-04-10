package S501_520;

import utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * *
 * *     2
 * *    / \
 * *   1   3
 * <p>
 * 输出:
 * 1
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * *
 * *         1
 * *        / \
 * *       2   3
 * *      /   / \
 * *     4   5   6
 * *        /
 * *       7
 * <p>
 * 输出:
 * 7
 * <p>
 * 注意: 您可以假设树（即给定的根节点）不为 NULL。
 */
public class S513 {

    private int maxDepth = Integer.MIN_VALUE;
    private int res;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    public void dfs(TreeNode node, int depth) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            if (maxDepth < depth) {
                maxDepth = depth;
                res = node.val;
            }
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }

    public int findBottomLeftValue2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode temp;
        int ans = root.val, size;
        while (!queue.isEmpty()) {
            size = queue.size();
            ans = queue.peek().val;
            while (size-- > 0) {
                temp = queue.remove();
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]
                {1, 2, 3, 4, null, 5, 6, null, null, 7});
        System.out.println(new S513().findBottomLeftValue2(root));
    }
}
