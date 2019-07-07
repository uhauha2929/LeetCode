package S101_120;

import util.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * *
 * *     3
 * *    / \
 * *   9  20
 * *     /  \
 * *    15   7
 * 返回锯齿形层次遍历如下：
 * *
 * * [
 * *   [3],
 * *   [20,9],
 * *   [15,7]
 * * ]
 */
public class S103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(root);
        TreeNode temp;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> layerNodes = new ArrayList<>();
            if (!stack1.isEmpty()) {
                while (!stack1.isEmpty()) {
                    temp = stack1.pop();
                    layerNodes.add(temp.val);
                    // 从左至右压入另一个栈
                    if (temp.left != null)
                        stack2.push(temp.left);
                    if (temp.right != null)
                        stack2.push(temp.right);
                }
            } else {
                while (!stack2.isEmpty()) {
                    temp = stack2.pop();
                    layerNodes.add(temp.val);
                    // 从右至左压入另一个栈
                    if (temp.right != null)
                        stack1.push(temp.right);
                    if (temp.left != null)
                        stack1.push(temp.left);
                }
            }
            res.add(layerNodes);
        }
        return res;
    }

    // 类似solution102
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        recursion(result, root, 1);
        return result;
    }

    private void recursion(List<List<Integer>> result, TreeNode node, int depth) {
        if (node == null) return;
        if (result.size() < depth)
            result.add(new LinkedList<>());
        if (depth % 2 == 0)
            result.get(depth - 1).add(0, node.val);  // 在头部插入
        else
            result.get(depth - 1).add(node.val);  // 在尾部添加
        recursion(result, node.left, depth + 1);
        recursion(result, node.right, depth + 1);
    }


    public static void main(String[] args) {
        System.out.println(new S103().zigzagLevelOrder2(new TreeNode(
                new Integer[]{3, 9, 20, null, null, 15, 7})));
    }
}
