package S521_540;

import util.TreeNode;

/**
 * 给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。
 * <p>
 * 示例 :
 * <p>
 * 输入:
 * *
 * *    1
 * *     \
 * *      3
 * *     /
 * *    2
 * <p>
 * 输出:
 * 1
 * <p>
 * 解释:
 * 最小绝对差为1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 * 注意: 树中至少有2个节点。
 */
public class S530 {


    private Integer pre = null;
    private Integer min = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        recursion(root);
        return min;
    }

    private void recursion(TreeNode root) {
        if (root == null) return;
        recursion(root.left);
        if (pre != null)
            min = Math.min(min, Math.abs(pre - root.val));
        pre = root.val;
        recursion(root.right);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, null, 3, 2});
        System.out.println(new S530().getMinimumDifference(root));
    }
}
