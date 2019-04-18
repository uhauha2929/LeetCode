package S501_520;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 您需要在二叉树的每一行中找到最大的值。
 * 示例：
 * 输入:
 * *
 * *           1
 * *          / \
 * *         3   2
 * *        / \   \
 * *       5   3   9
 * 输出: [1, 3, 9]
 */
public class S515 {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            while (size-- > 0) {
                TreeNode temp = queue.remove();
                if (temp.val > max)
                    max = temp.val;
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
            res.add(max);
        }
        return res;
    }

    public List<Integer> largestValues2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        recursion(root, res, 0);
        return res;
    }

    private void recursion(TreeNode root, List<Integer> res, int depth) {
        if (root == null) return;
        if (depth >= res.size())
            res.add(Integer.MIN_VALUE);
        res.set(depth, Math.max(res.get(depth), root.val));
        recursion(root.left, res, depth + 1);
        recursion(root.right, res, depth + 1);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 3, 2, 5, 3, null, 9});
        System.out.println(new S515().largestValues2(root));
    }
}
