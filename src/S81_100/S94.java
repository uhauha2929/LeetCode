package S81_100;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * *    1
 * *     \
 * *     2
 * *    /
 * *   3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class S94 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return inorder(root, list);
    }

    private List<Integer> inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
        return list;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode, tmp;
        currentNode = root;
        while (currentNode != null || !stack.isEmpty()) {
            //先一直往左走到底，边走边入栈
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            //结点没有左孩子了,出栈,访问结点
            if (!stack.isEmpty()) {
                tmp = stack.pop();
                list.add(tmp.val);
                // 把出栈节点的右节点作为当前节点，再次循环
                currentNode = tmp.right;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, null, 2, 3});
        List<Integer> list = new S94().inorderTraversal(root);
        System.out.println(list);
    }
}
