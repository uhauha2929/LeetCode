package S781_800;

import util.TreeNode;

import java.util.Stack;

/**
 * 给定一个二叉搜索树的根结点 root, 返回树中任意两节点的差的最小值。
 * <p>
 * 示例：
 * <p>
 * 输入: root = [4,2,6,1,3,null,null]
 * 输出: 1
 * 解释:
 * 注意，root是树结点对象(TreeNode object)，而不是数组。
 * <p>
 * 给定的树 [4,2,6,1,3,null,null] 可表示为下图:
 * *
 * *           4
 * *         /   \
 * *       2      6
 * *      / \
 * *     1   3
 * <p>
 * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
 * 注意：
 * <p>
 * 二叉树的大小范围在 2 到 100。
 * 二叉树总是有效的，每个节点的值都是整数，且不重复。
 */
public class S783 {

    // 类似solution98
    public int minDiffInBST(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, tmp;
        Integer preVal = null;
        int res = Integer.MAX_VALUE;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            tmp = stack.pop();
            if (preVal != null)
                res = Math.min(res, tmp.val - preVal);
            preVal = tmp.val;
            if (tmp.right != null)
                cur = tmp.right;
        }
        return res;
    }

    private Integer preVal = null, minVal = Integer.MAX_VALUE;

    public int minDiffInBST2(TreeNode root) {
        inOrder(root);
        return minVal;
    }

    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            if (preVal != null)
                minVal = Math.min(minVal, root.val - preVal);
            preVal = root.val;
            inOrder(root.right);
        }
    }


    public static void main(String[] args) {
        System.out.println(new S783().minDiffInBST2(
                new TreeNode(new Integer[]{4, 2, 6, 1, 3, null, null})
        ));
    }
}
