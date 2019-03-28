package S181_200;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * *
 * *    1            <---
 * *  /   \
 * * 2     3         <---
 * *  \     \
 * *   5     4       <---
 */
public class S199 {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        recursion(res, root, 0);
        return res;
    }

    // 根->右->左, 只存深度最深的节点, 即最右边的节点
    private void recursion(List<Integer> res, TreeNode root, int depth) {
        if (root == null) return;
        if (depth > res.size() - 1)
            res.add(root.val);
        recursion(res, root.right, depth + 1);
        recursion(res, root.left, depth + 1);
    }

    // 层次遍历只存每层最后一个节点
    public List<Integer> rightSideView2(TreeNode root) {
        if (root == null) return new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        queue.add(cur);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                cur = queue.remove();
                if (cur.left != null)
                    queue.add(cur.left);
                if (cur.right != null)
                    queue.add(cur.right);
                if (size == 1)
                    res.add(cur.val);
                size--;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 2, 3, null, 5, null, 4});
        System.out.println(new S199().rightSideView2(root));
    }
}
