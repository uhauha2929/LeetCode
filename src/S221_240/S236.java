package S221_240;

import util.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * *           3
 * *         /  \
 * *        5    1
 * *       / \  / \
 * *      6  2 0  8
 * *        / \
 * *       7   4
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 */
public class S236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left != null && right != null ? root : (left == null ? right : left);
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = new ArrayList<>();
        List<TreeNode> qPath = new ArrayList<>();
        findPath(root, p, pPath);
        findPath(root, q, qPath);
        // 从根节点开始寻找两个路径中最后一个公共节点
        int i = 1;
        for (; i < pPath.size() && i < qPath.size(); i++) {
            if (pPath.get(i) != qPath.get(i))
                break;
        }
        // 至少包含根节点
        return pPath.get(i - 1);
    }

    // 回溯法, 求出根节点到目标节点的路径
    private boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) return false;
        path.add(root); // 路径包含目标节点
        if (root == target) return true;
        if (findPath(root.left, target, path) || findPath(root.right, target, path))
            return true;
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        TreeNode p = root.left.left;
        TreeNode q = root.left.right.left;
        TreeNode ancestor = new S236().lowestCommonAncestor2(root, p, q);
        System.out.println(ancestor.val);
    }
}
