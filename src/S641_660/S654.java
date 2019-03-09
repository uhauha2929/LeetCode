package S641_660;

import utils.TreeNode;

/**
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * <p>
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 * <p>
 * Example 1:
 * <p>
 * 输入: [3,2,1,6,0,5]
 * 输出: 返回下面这棵树的根节点：
 * *
 * *       6
 * *     /   \
 * *    3     5
 * *     \    /
 * *      2  0
 * *        \
 * *         1
 * 注意:
 * <p>
 * 给定的数组的大小在 [1, 1000] 之间。
 */
public class S654 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return recursion(nums, 0, nums.length - 1);
    }

    private TreeNode recursion(int[] nums, int i, int j) {
        if (i > j) return null;
        int maxNum = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int k = i; k <= j; k++) {
            if (nums[k] > maxNum) {
                maxNum = nums[k];
                maxIndex = k;
            }
        }
        TreeNode node = new TreeNode(maxNum);
        node.left = recursion(nums, i, maxIndex - 1);
        node.right = recursion(nums, maxIndex + 1, j);
        return node;
    }


    public static void main(String[] args) {
        TreeNode root = new S654().constructMaximumBinaryTree(
                new int[]{3, 2, 1, 6, 0, 5});
        TreeNode.preOrder(root);
    }
}
