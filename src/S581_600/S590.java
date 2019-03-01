package S581_600;

import java.util.*;

/**
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 * 1
 * /  |  \
 * 3  2  4
 * /\
 * 5 6
 * 返回其后序遍历: [5,6,3,2,4,1].
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 */
public class S590 {

    private static class Node {
        int val;
        List<Node> children;

        public Node() {
        }

        Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        traversal(root, res);
        return res;
    }

    private void traversal(Node node, List<Integer> res) {
        if (node == null) return;
        if (node.children == null || node.children.size() == 0) {
            res.add(node.val);
            return;
        }
        for (Node child : node.children) {
            traversal(child, res);
        }
        res.add(node.val);
    }

    public List<Integer> postorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.val);
            if (root.children != null && root.children.size() > 0) {
                for (Node child : root.children)
                    stack.push(child);
            }
        }
        // 根，右，左  ==> 左，右，根
        Collections.reverse(list);
        return list;
    }

    // 类似Solution145
    public List<Integer> postorder3(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.peek();
            // 如果该节点是叶子节点，或者该节点有孩子（上一个节点为其最后一个孩子）
            if (cur.children == null || cur.children.size() == 0
                    || (pre != null && cur.children.get(cur.children.size() - 1) == pre)) {
                res.add(cur.val);
                pre = cur;
                stack.pop();
            } else {
                // 将该节点的孩子节点从后向前压入栈（从前往后出栈）
                for (int i = cur.children.size() - 1; i >= 0; i--) {
                    stack.push(cur.children.get(i));
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        List<Node> children = new ArrayList<>();
        List<Node> grandchildren = new ArrayList<>();
        grandchildren.add(new Node(5, null));
        grandchildren.add(new Node(6, null));
        children.add(new Node(3, grandchildren));
        children.add(new Node(2, null));
        children.add(new Node(4, null));
        Node root = new Node(1, children);
        System.out.println(new S590().postorder2(root));
    }
}
