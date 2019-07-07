package S981_1000;

import util.TreeNode;

import java.util.*;

/**
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
 * 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
 * 提示：
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cousins-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S993 {

    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        // 每个节点的值都是唯一的
        Map<Integer, Integer> parent = new HashMap<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Set<Integer> keySet = parent.keySet();
            // 如果该层同时包含两个节点
            if (keySet.contains(x) && keySet.contains(y)) {
                // 并且两个节点的父节点不同, 则是堂兄弟节点
                if (!parent.get(x).equals(parent.get(y)))
                    return true;
            } else {
                // 如果该层只包含一个节点, 则不是
                if (keySet.contains(x) || keySet.contains(y))
                    return false;
            }
            parent.clear();

            int size = queue.size();
            // 遍历该层所有节点, 并将下一层的节点的父节点设置为该层节点
            while (size-- > 0) {
                TreeNode temp = queue.remove();
                if (temp.left != null) {
                    queue.add(temp.left);
                    parent.put(temp.left.val, temp.val);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                    parent.put(temp.right.val, temp.val);
                }
            }
        }
        return false;
    }

    private Map<Integer, Integer> depth;
    private Map<Integer, TreeNode> parent;

    public boolean isCousins2(TreeNode root, int x, int y) {
        depth = new HashMap<>();
        parent = new HashMap<>();
        dfs(root, null);
        return (depth.get(x).equals(depth.get(y)) && parent.get(x) != parent.get(y));
    }

    private void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            depth.put(node.val, par != null ? 1 + depth.get(par.val) : 0);
            parent.put(node.val, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    private int xLevel = 0;
    private int yLevel = 0;
    private int xParent = 0;
    private int yParent = 0;

    public boolean isCousins3(TreeNode root, int x, int y) {
        recursion(root, x, y, 0, 0);
        return xLevel > 0 && xLevel == yLevel && xParent != yParent;
    }

    private void recursion(TreeNode root, int x, int y, int level, int parent) {
        if (root == null) {
            return;
        }
        if (x == root.val) {
            xLevel = level;
            xParent = parent;
        } else if (y == root.val) {
            yLevel = level;
            yParent = parent;
        }
        recursion(root.left, x, y, level + 1, root.val);
        recursion(root.right, x, y, level + 1, root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 2, 3, null, 4, null, 5});
        System.out.println(new S993().isCousins(root, 4, 5));
    }
}
