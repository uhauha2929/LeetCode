package S621_640;

import util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
 * 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
 * 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
 * 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
 * 示例 1:
 * 输入:
 * 二叉树如下所示:
 * *        4
 * *      /   \
 * *     2     6
 * *    / \   /
 * *   3   1 5
 * v = 1
 * d = 2
 * 输出:
 * *        4
 * *       / \
 * *      1   1
 * *     /     \
 * *    2       6
 * *   / \     /
 * *  3   1   5
 * 示例 2:
 * 输入:
 * 二叉树如下所示:
 * *       4
 * *      /
 * *     2
 * *    / \
 * *   3   1
 * v = 1
 * d = 3
 * 输出:
 * *       4
 * *      /
 * *     2
 * *    / \
 * *   1   1
 * *  /     \
 * * 3       1
 * 注意:
 * 输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
 * 输入的二叉树至少有一个节点。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-one-row-to-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S623 {

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        TreeNode temp;
        while (!queue.isEmpty()) {
            // 当深度为d-1退出, 此时队列中只保留了d-1层的节点
            if (depth == d - 1) break;
            int size = queue.size();
            while (size-- > 0) {
                temp = queue.remove();
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
            depth++;
        }
        // 遍历d-1层的节点, 该层节点的左右节点都要设置, 不管是否为空
        TreeNode parent;
        while (!queue.isEmpty()) {
            parent = queue.remove();

            temp = new TreeNode(v);
            temp.left = parent.left;
            parent.left = temp;

            temp = new TreeNode(v);
            temp.right = parent.right;
            parent.right = temp;
        }
        return root;
    }

    public TreeNode addOneRow2(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        recursion(root, 1, v, d - 1);
        return root;
    }

    private void recursion(TreeNode root, int depth, int v, int d) {
        if (root == null) return;
        if (depth == d) {
            TreeNode temp = new TreeNode(v);
            temp.left = root.left;
            root.left = temp;
            temp = new TreeNode(v);
            temp.right = root.right;
            root.right = temp;
            return;
        }
        recursion(root.left, depth + 1, v, d);
        recursion(root.right, depth + 1, v, d);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 2, 3, 4});
        TreeNode.inOrder(root);
        System.out.println();
        root = new S623().addOneRow2(root, 5, 4);
        TreeNode.inOrder(root);
    }
}
