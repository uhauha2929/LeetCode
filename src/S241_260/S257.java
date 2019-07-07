package S241_260;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * *
 * *    1
 * *  /   \
 * * 2     3
 * *  \
 * *   5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * <p>
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class S257 {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        recursion(root, res, "");
        return res;
    }

    private void recursion(TreeNode node, List<String> res, String cur) {
        if (node == null) return;
        cur += node.val;
        if (node.left == null && node.right == null) {
            res.add(cur);
        }
        recursion(node.left, res, cur + "->");
        recursion(node.right, res, cur + "->");
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 2, 3, null, 5});
        System.out.println(new S257().binaryTreePaths(root));
    }
}
