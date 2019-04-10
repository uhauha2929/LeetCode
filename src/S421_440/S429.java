package S421_440;

import utils.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 * <p>
 * 例如，给定一个 3叉树 :
 * *
 * *           1
 * *        /  \   \
 * *       3    2   4
 * *      / \
 * *     5  6
 * 返回其层序遍历:
 * <p>
 * [
 * [1],
 * [3,2,4],
 * [5,6]
 * ]
 * <p>
 * 说明:
 * <p>
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 */
public class S429 {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        recursion(res, root, 0);
        return res;
    }

    private void recursion(List<List<Integer>> res, Node root, int depth) {
        if (root == null) return;
        if (depth >= res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(depth).add(root.val);
        if (root.children != null) {
            for (Node node : root.children) {
                recursion(res, node, depth + 1);
            }
        }
    }

    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node temp;
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                temp = queue.poll();
                list.add(temp.val);
                if (temp.children != null) {
                    for (Node node : temp.children) {
                        queue.add(node);
                    }
                }
            }
            res.add(list);
        }
        return res;
    }


    public static void main(String[] args) {
        List<Node> rootChildren = new ArrayList<>();
        List<Node> children = new ArrayList<>();
        children.add(new Node(5));
        children.add(new Node(6));
        rootChildren.add(new Node(3, children));
        rootChildren.add(new Node(2));
        rootChildren.add(new Node(4));
        Node root = new Node(1, rootChildren);
        System.out.println(new S429().levelOrder2(root));
    }
}
