package S421_440;

import util.TreeNode;

import java.util.HashMap;

/**
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 找出路径和等于给定数值的路径总数。
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * 示例：
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * *       10
 * *      /  \
 * *     5   -3
 * *    / \    \
 * *   3   2   11
 * *  / \   \
 * * 3  -2   1
 * 返回 3。和等于 8 的路径有:
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S437 {

    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return preOrder(root, sum) +
                pathSum(root.left, sum) +
                pathSum(root.right, sum);
    }

    // 把每个节点当做根节点寻找路径
    private int preOrder(TreeNode root, int sum) {
        if (root == null) return 0;
        sum -= root.val;
        return (sum == 0 ? 1 : 0) +
                preOrder(root.left, sum) +
                preOrder(root.right, sum);
    }

    public int pathSum2(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return helper(root, map, sum, 0);
    }

    private int helper(TreeNode root, HashMap<Integer, Integer> map,
                       int sum, int pathSum) {
        if (root == null) return 0;
        int count = 0;
        pathSum += root.val;
        // 如果之前出现过pathSum-sum, 说明上次出现的节点到当前节点的路径和为sum
        count += map.getOrDefault(pathSum - sum, 0);
        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
        count += helper(root.left, map, sum, pathSum) +
                helper(root.right, map, sum, pathSum);
        map.put(pathSum, map.get(pathSum) - 1); // 回溯
        return count;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{
                1, -2, -3, 1, 3, -2, null, -1
        });
        System.out.println(new S437().pathSum2(root, -1));
    }
}
