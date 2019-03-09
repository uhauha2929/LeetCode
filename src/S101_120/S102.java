package S101_120;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * *
 * *     3
 * *    / \
 * *   9  20
 * *     /  \
 * *    15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class S102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            while (count > 0) {
                TreeNode node = queue.remove();
                list.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
                count--;
            }
            res.add(list);
        }
        return res;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        recursion(result, root, 1);
        return result;
    }

    private void recursion(List<List<Integer>> result, TreeNode node, int depth) {
        if (node == null) return;
        if (result.size() < depth)
            result.add(new ArrayList<>());
        result.get(depth - 1).add(node.val);
        recursion(result, node.left, depth + 1);
        recursion(result, node.right, depth + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(new S102().levelOrder(root));
    }
}
