package S661_680;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * <p>
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * *
 * *            1
 * *          /   \
 * *         3     2
 * *        / \     \
 * *       5   3     9
 * <p>
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 * 示例 2:
 * <p>
 * 输入:
 * *
 * *           1
 * *          /
 * *         3
 * *        / \
 * *       5   3
 * <p>
 * 输出: 2
 * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 * 示例 3:
 * <p>
 * 输入:
 * <p>
 * *           1
 * *          / \
 * *         3   2
 * *        /
 * *       5
 * <p>
 * 输出: 2
 * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 * 示例 4:
 * <p>
 * 输入:
 * <p>
 * *           1
 * *          / \
 * *         3   2
 * *        /     \
 * *       5       9
 * *      /         \
 * *     6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 * 注意: 答案在32位有符号整数的表示范围内。
 */
public class S662 {

    private static class Pair {
        TreeNode node;
        int id;

        Pair(TreeNode node, int id) {
            this.node = node;
            this.id = id;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 1));
        Pair temp, pair;
        int size, maxWidth = 1, layerWidth;
        List<Pair> layerNodes;
        while (!queue.isEmpty()) {
            size = queue.size();
            layerNodes = new ArrayList<>();
            while (size-- > 0) {
                temp = queue.remove();
                if (temp.node.left != null) {
                    pair = new Pair(temp.node.left, temp.id * 2 - 1);
                    queue.add(pair);
                    layerNodes.add(pair);
                }
                if (temp.node.right != null) {
                    pair = new Pair(temp.node.right, temp.id * 2);
                    queue.add(pair);
                    layerNodes.add(pair);
                }
            }
            if (layerNodes.size() > 0) {
                layerWidth = layerNodes.get(layerNodes.size() - 1).id
                        - layerNodes.get(0).id + 1;
                maxWidth = Math.max(maxWidth, layerWidth);
            }
        }
        return maxWidth;
    }

    // 类似方法, 使用另一个队列保存序号
    public int widthOfBinaryTree2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> posQueue = new LinkedList<>();
        queue.add(root);
        posQueue.add(1);
        int maxWidth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int start = posQueue.element();
            int end = start;
            while (size-- > 0) {
                TreeNode temp = queue.remove();
                end = posQueue.remove();
                if (temp.left != null) {
                    queue.add(temp.left);
                    posQueue.add(end * 2 - 1);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                    posQueue.add(end * 2);
                }
            }
            maxWidth = Math.max(maxWidth, end - start + 1);
        }
        return maxWidth;
    }

    private int maxWidth = 0;

    public int widthOfBinaryTree3(TreeNode root) {
        dfs(root, 1, 1, new ArrayList<>());
        return maxWidth;
    }

    private void dfs(TreeNode r, int level, int index, List<Integer> left) {
        if (r == null) return;
        // 保存每一层最左边的节点
        if (level > left.size()) left.add(index);
        // 每次求和该节点所在层最左边的节点的距离
        maxWidth = Math.max(maxWidth, index - left.get(level - 1) + 1);
        dfs(r.left, level + 1, index * 2 - 1, left);
        dfs(r.right, level + 1, index * 2, left);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 1, 1, 1, null, null, 1, 1, null, null, 1});
        System.out.println(new S662().widthOfBinaryTree3(root));
    }
}
