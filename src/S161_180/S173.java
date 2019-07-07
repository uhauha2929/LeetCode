package S161_180;

import util.TreeNode;

import java.util.Stack;

/**
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * 示例：
 * *          7
 * *         / \
 * *        3  15
 * *           / \
 * *          9  20
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 * 提示：
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 */
public class S173 {

    static class BSTIterator {

        private Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(TreeNode root) {
            pushLeftIntoStack(root);
        }

        private void pushLeftIntoStack(TreeNode left) {
            // 一直往左走, 将左节点全部入栈
            TreeNode cur = left;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode next = stack.pop();
            pushLeftIntoStack(next.right);
            return next.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{7, 3, 15, null, null, 9, 20});
        BSTIterator iter = new BSTIterator(root);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
