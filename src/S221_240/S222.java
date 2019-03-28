package S221_240;

import utils.TreeNode;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 * <p>
 * 说明：
 * <p>
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * *     1
 * *    / \
 * *   2   3
 * *  / \  /
 * * 4  5 6
 * <p>
 * 输出: 6
 */
public class S222 {

    public int countNodes(TreeNode root) {
        return root == null ? 0 : countNodes(root.left) + countNodes(root.right) + 1;
    }

    public int countNodes2(TreeNode root) {
        if (root == null) return 0;

        int height = 1;
        TreeNode left = root.left, right = root.right;
        // 一直往左走同时一直往右走
        while (left != null && right != null) {
            height++;
            left = left.left;
            right = right.right;
        }
        // 如果left==right,说明是满二叉树, 节点总数2^{h} - 1, h=1,2...
        // 否则对左右子树进行递归将总数相加, 并加上根节点(+1)
        return left == right ?
                (1 << height) - 1 :
                1 + countNodes(root.left) + countNodes(root.right);
    }


    public static void main(String[] args) {

    }
}
