package S81_100;

import utils.TreeNode;

/**
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * 示例 1:
 * <p>
 * * 输入:       1         1
 * *           / \       / \
 * *          2   3     2   3
 * *
 * *         [1,2,3],   [1,2,3]
 * *
 * 输出: true
 * 示例 2:
 * <p>
 * * 输入:      1          1
 * *           /           \
 * *          2             2
 * *
 * *         [1,2],     [1,null,2]
 * *
 * 输出: false
 * 示例 3:
 * <p>
 * * 输入:       1         1
 * *           / \       / \
 * *          2   1     1   2
 * *
 * *         [1,2,1],   [1,1,2]
 * *
 * 输出: false
 */
public class S100 {

    // 同时对p,q进行前序遍历
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null || p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    public static void main(String[] args) {
        TreeNode p = new TreeNode(new Integer[]{1, 2, 3});
        TreeNode q = new TreeNode(new Integer[]{1, 2, 3});
        System.out.println(new S100().isSameTree(p, q));
    }
}
