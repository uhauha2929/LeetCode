package S101_120;

import utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * *
 * *     3
 * *    / \
 * *   9  20
 * *     /  \
 * *    15   7
 * 返回它的最小深度  2.
 */
public class S111 {

    // 层次遍历
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode temp;
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            while (size-- > 0) {
                temp = queue.remove();
                if (temp.left == null && temp.right == null) // 遇到叶子节点
                    return depth;
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
        }
        return depth;
    }

    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int l = minDepth(root.left);
        int r = minDepth(root.right);
        if (l == 0 || r == 0)
            return l + r + 1;//在左子树和右子树中仅有一个存在的情况下，返回存在的值（即其中一颗树的minDepth为0）
        return Math.min(l, r) + 1;//在左子树和右子树都存在的情况下返回两者最小值
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(new S111().minDepth2(root));
    }
}
