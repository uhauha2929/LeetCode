package S101_120;

import util.TreeNode;

import java.util.LinkedList;

/**
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * *     3
 * *   /   \
 * *  9    20
 * *      /  \
 * *     15   7
 * 返回它的最大深度 3 。
 */
public class S104 {

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepth2(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        int cnt = 0;
        q.add(root);
        while (!q.isEmpty()) {
            cnt++;
            for (int i = 0; i < q.size(); i++) {
                TreeNode node = q.poll();
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
        }
        return cnt;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{3, 9, 20, null, null, 5});
        System.out.println(new S104().maxDepth2(root));
    }
}
