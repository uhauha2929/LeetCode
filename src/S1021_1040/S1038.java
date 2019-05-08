package S1021_1040;

import utils.TreeNode;

/**
 * 给出二叉搜索树的根节点，该二叉树的节点值各不相同，修改二叉树，使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * 提醒一下，二叉搜索树满足下列约束条件：
 * 节点的左子树仅包含键小于节点键的节点。
 * 节点的右子树仅包含键大于节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * 示例：
 * *                   4(30)
 * *               /         \
 * *            1(36)       6(21)
 * *           /    \        /    \
 * *          0(36)  2(35) 5(26)  7(15)
 * *                  \             \
 * *                  3(33)         8(8)
 * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * 提示：
 * 树中的节点数介于 1 和 100 之间。
 * 每个节点的值介于 0 和 100 之间。
 * 给定的树为二叉搜索树。
 */
public class S1038 {
    private int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root == null) return null;
        bstToGst(root.right);
        root.val += sum;
        sum = root.val;
        bstToGst(root.left);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{
                4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8
        });
        TreeNode.inOrder(root);
        root = new S1038().bstToGst(root);
        TreeNode.inOrder(root);
    }
}
