package S661_680;

import utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。
 * <p>
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * *     2
 * *    / \
 * *   2   5
 * *      / \
 * *     5   7
 * <p>
 * 输出: 5
 * 说明: 最小的值是 2 ，第二小的值是 5 。
 * 示例 2:
 * <p>
 * 输入:
 * *     2
 * *    / \
 * *   2   2
 * <p>
 * 输出: -1
 * 说明: 最小的值是 2, 但是不存在第二小的值。
 */
public class S671 {

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode temp;
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                temp = queue.remove();
                int max = -1;
                if (temp.left != null) {
                    max = Math.max(max, temp.left.val);
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    max = Math.max(max, temp.right.val);
                    queue.add(temp.right);
                }
                if (max != -1 && max != root.val) {
                    ans = Math.min(ans, max);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int res = Integer.MAX_VALUE;

    public int findSecondMinimumValue2(TreeNode root) {
        if (root == null) return -1;
        find(root, root.val);
        if (res != Integer.MAX_VALUE)
            return res;
        return -1;
    }

    private void find(TreeNode root, int val) {
        if (root == null) return;
        if (val != root.val)
            res = Math.min(res, root.val);
        find(root.left, val);
        find(root.right, val);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{2, 2, 5, null, null, 5, 7});
        System.out.println(new S671().findSecondMinimumValue(root));
    }
}
