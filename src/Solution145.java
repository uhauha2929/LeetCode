import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Solution145 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return postorder(root, list);
    }

    private List<Integer> postorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            postorder(root.left, list);
            postorder(root.right, list);
            list.add(root.val);
        }
        return list;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        // root->right->left reverse left->right->root
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root, tmp;
        while (currentNode != null || !stack.isEmpty()) {
            //先一直往左走到底，边走边入栈
            while (currentNode != null) {
                list.add(currentNode.val);
                stack.push(currentNode);
                currentNode = currentNode.right;
            }
            if (!stack.isEmpty()) {
                tmp = stack.pop();
                // 变换方向
                currentNode = tmp.left;
            }
        }
        Collections.reverse(list);
        return list;
    }

    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode currentNode = root, pre = root, tmp;
        Stack<TreeNode> stack = new Stack<>();
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            if (!stack.isEmpty()) {
                tmp = stack.peek();
                // 如果当前栈顶的节点的右节点还未被访问过
                if (tmp.right != null && tmp.right != pre) {
                    currentNode = tmp.right;
                } else {
                    tmp = stack.pop();
                    list.add(tmp.val);
                    pre = tmp;  // 记录前一个节点
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        Solution145 s = new Solution145();
        List<Integer> list = s.postorderTraversal3(root);
        System.out.println(list);
    }
}
