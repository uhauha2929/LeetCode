package S961_980;

import utils.TreeNode;

/**
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 * <p>
 * 示例 1：
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：[2,2,2,5,2]
 * 输出：false
 * 提示：
 * <p>
 * 给定树的节点数范围是 [1, 100]。
 * 每个节点的值都是整数，范围为 [0, 99] 。
 */
public class S965 {
    private Integer val = null;

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        if (val == null)
            val = root.val;
        if (root.val != val)
            return false;
        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 1, 1, 1, 1, null, 1});
        System.out.println(new S965().isUnivalTree(root));
    }
}
