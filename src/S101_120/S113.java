package S101_120;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * *
 * *               5
 * *              / \
 * *             4   8
 * *            /   / \
 * *           11  13  4
 * *          /  \    / \
 * *         7    2  5   1
 * 返回:
 * *
 * * [
 * *    [5,4,11,2],
 * *    [5,8,4,5]
 * * ]
 */
public class S113 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), 0, root, sum);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> cur, int curSum,
                           TreeNode root, int sum) {
        if (root == null) return;

        curSum += root.val;
        cur.add(root.val);

        if (root.left == null && root.right == null) {
            if (curSum == sum) {
                res.add(new ArrayList<>(cur));
            }
        }
        backtrack(res, cur, curSum, root.left, sum);
        backtrack(res, cur, curSum, root.right, sum);

        // 向上层回溯, 这里curSum不共享, 不用curSum-=root.val
        cur.remove(cur.size() - 1);
    }


    // 类似Solution112
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1});
        System.out.println(new S113().pathSum(root, 22));
    }
}
