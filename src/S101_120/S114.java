package S101_120;

import util.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，原地将它展开为链表。
 * <p>
 * 例如，给定二叉树
 * *
 * *     1
 * *    / \
 * *   2   5
 * *  / \   \
 * * 3   4   6
 * 将其展开为：
 * <p>
 * * 1
 * *  \
 * *   2
 * *    \
 * *     3
 * *      \
 * *       4
 * *        \
 * *         5
 * *          \
 * *           6
 */
public class S114 {

    // 类似solution144 前序遍历
    public void flatten(TreeNode root) {
        if (root == null) return;
        List<TreeNode> nodeList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                nodeList.add(cur);
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty())
                cur = stack.pop().right;
        }
        Iterator<TreeNode> iter = nodeList.iterator();
        cur = iter.next();
        while (iter.hasNext()) {
            cur.left = null;
            cur.right = iter.next();
            cur = cur.right;
        }
    }

    public void flatten2(TreeNode root) {
        if (root == null)
            return;
        flatten(root.left);
        flatten(root.right);
        if (root.left != null) {
            // 找到左子树最右边的节点
            TreeNode left = root.left;
            while (left.right != null) {
                left = left.right;
            }
            left.right = root.right;
            root.right = root.left;
            root.left = null;  // 左子树置空
        }
    }

    private TreeNode pre = null; // 左子树最右边的节点

    // 右 -> 左 -> 根
    public void flatten3(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 2, 5, 3, 4, 6});
        new S114().flatten2(root);
        TreeNode.inOrder(root);
    }
}
