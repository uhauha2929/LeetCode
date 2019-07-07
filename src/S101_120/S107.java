package S101_120;

import util.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其自底向上的层次遍历为：
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 */
public class S107 {

    // 类似Solution102
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        recursion(result, root, 1);
        return result;
    }

    private void recursion(LinkedList<List<Integer>> result, TreeNode node, int depth) {
        if (node == null) return;
        if (result.size() < depth)
            result.addFirst(new ArrayList<>()); // 往头部插
        result.get(result.size() - depth).add(node.val);
        recursion(result, node.left, depth + 1);
        recursion(result, node.right, depth + 1);
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        if (root == null)
            return new LinkedList<>();
        LinkedList<List<Integer>> res = new LinkedList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            while (size-- > 0) {
                TreeNode node = queue.remove();
                list.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            res.addFirst(list);  //插入头部
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(new S107().levelOrderBottom2(root));
    }
}
