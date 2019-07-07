package S221_240;

import util.TreeNode;

import java.util.ArrayDeque;

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * <p>
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,1,4,null,2], k = 1
 * *    3
 * *   / \
 * *  1   4
 * *   \
 * *    2
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * *        5
 * *       / \
 * *      3   6
 * *     / \
 * *    2   4
 * *   /
 * *  1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 */
public class S230 {

    private Integer kthMin, count;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        inOrder(root);
        return kthMin;
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        if (--count == 0) {
            kthMin = root.val;
            return;
        }
        inOrder(root.right);
    }

    public int kthSmallest2(TreeNode root, int k) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, tmp;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            tmp = stack.pop();
            if (--k == 0) return tmp.val;
            if (tmp.right != null) {
                cur = tmp.right;
            }
        }
        throw new RuntimeException();
    }


    public static void main(String[] args) {
        System.out.println(new S230().kthSmallest2(new TreeNode(
                new Integer[]{1, null, 2}), 2));
    }
}
