package S881_900;

import util.TreeNode;

import java.util.ArrayDeque;

/**
 * 给定一个树，按中序遍历重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。
 * <p>
 * <p>
 * <p>
 * 示例 ：
 * <p>
 * 输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]
 * *
 * *        5
 * *       / \
 * *     3    6
 * *    / \    \
 * *   2   4    8
 * *  /        / \
 * * 1        7   9
 * *
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * *
 * *  1
 * *   \
 * *    2
 * *     \
 * *      3
 * *       \
 * *        4
 * *         \
 * *          5
 * *           \
 * *            6
 * *             \
 * *              7
 * *               \
 * *                8
 * *                 \
 * *                  9
 * *
 * <p>
 * 提示：
 * <p>
 * 给定树中的结点数介于 1 和 100 之间。
 * 每个结点都有一个从 0 到 1000 范围内的唯一整数值。
 */
public class S897 {

    // 中序遍历非递归版本
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) return null;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, tmp;
        TreeNode tmp2 = new TreeNode(0);
        TreeNode cur2 = tmp2;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                tmp = stack.pop();
                tmp.left = null;
                cur2.right = tmp;
                cur2 = cur2.right;
                cur = tmp.right;
            }
        }
        return tmp2.right;
    }

    public TreeNode increasingBST2(TreeNode root) {
        TreeNode left = new TreeNode(0);
        bst(root, left);
        return left.right;
    }

    public TreeNode bst(TreeNode root, TreeNode node) {
        if (root != null) {
            node = bst(root.left, node);
            node.right = new TreeNode(root.val);
            node = node.right;
            node = bst(root.right, node);
        }
        return node;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new Integer[]{5, 3, 6, 2, 4, null, 8, 1, null, null, null, 7, 9});
        root = new S897().increasingBST2(root);
        TreeNode.preOrder(root);
    }
}
