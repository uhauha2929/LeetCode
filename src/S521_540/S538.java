package S521_540;

import utils.TreeNode;

import java.util.Stack;

/**
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * <p>
 * 例如：
 * <p>
 * 输入: 二叉搜索树:
 * *    5
 * *  /   \
 * * 2     13
 * <p>
 * 输出: 转换为累加树:
 * *     18
 * *    /   \
 * *  20     13
 */
public class S538 {

    // 右, 根, 左
    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root, tmp;
        int sum = 0;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.right;
            }
            if (!stack.isEmpty()) {
                tmp = stack.pop();
                sum += tmp.val;
                tmp.val = sum;
                current = tmp.left;
            }
        }
        return root;
    }

    private int sum = 0;

    public TreeNode convertBST2(TreeNode root) {
        convert(root);
        return root;
    }

    private void convert(TreeNode root) {
        if (root != null) {
            convert(root.right);
            sum += root.val;
            root.val = sum;
            convert(root.left);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{2, 1, 3});
        TreeNode.inOrder(root);
        root = new S538().convertBST2(root);
        TreeNode.inOrder(root);
    }
}
