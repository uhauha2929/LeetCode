package S81_100;

import utils.TreeNode;

import java.util.ArrayDeque;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * *     2
 * *    / \
 * *   1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * *     5
 * *    / \
 * *   1   4
 * *      / \
 * *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class S98 {

    // 二叉排序树中序遍历一定是递增的
    public boolean isValidBST(TreeNode root) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, tmp;
        long pre = Long.MIN_VALUE; // 每次保存前一个节点的值
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            tmp = stack.pop();
            if (tmp.val <= pre)  // 每次和前一个节点值进行比较
                return false;
            pre = tmp.val;
            if (tmp.right != null)
                cur = tmp.right;
        }
        return true;
    }

    private long pre = Long.MIN_VALUE; // 每次保存前一个节点的值

    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        if (isValidBST2(root.left)) {  // 这里把左子树的递归加入判断
            if (root.val <= pre)
                return false;
            pre = root.val;
            return isValidBST2(root.right);
        }
        return false;
    }

    // 递归, 类似于二分查找
    public boolean isValidBST3(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    public static void main(String[] args) {
        System.out.println(new S98().isValidBST3(new TreeNode(
                new Integer[]{10, 5, 15, null, null, 6, 20})));
    }
}
