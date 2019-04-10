package S541_560;

import utils.Node;

import java.util.*;

/**
 * 给定一个 N 叉树，找到其最大深度。
 * <p>
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * <p>
 * 例如，给定一个 3叉树 :
 * *            1
 * *         /  |  \
 * *        3   2   4
 * *       / \
 * *      5   6
 * <p>
 * 我们应返回其最大深度，3。
 * <p>
 * 说明:
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 */
public class S559 {

    public int maxDepth(Node root) {
        if (root == null) return 0;
        int maxDepth = 1;
        if (root.children != null) {
            for (Node child : root.children) {
                maxDepth = Math.max(maxDepth, maxDepth(child) + 1);
            }
        }
        return maxDepth;
    }

    public int maxDepth2(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        Node temp;
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                temp = queue.remove();
                if (temp.children != null) {
                    queue.addAll(temp.children);
                }
            }
            depth++;
        }
        return depth;
    }


    public static void main(String[] args) {
        List<Node> children = new ArrayList<>();
        children.add(new Node(3,
                Arrays.asList(new Node(5), new Node(6))));
        children.add(new Node(2));
        children.add(new Node(4));
        Node root = new Node(1, children);

        System.out.println(new S559().maxDepth2(root));
    }
}
