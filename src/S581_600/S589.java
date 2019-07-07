package S581_600;

import util.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 * *
 * *           1
 * *        /  \   \
 * *       3    2   4
 * *      / \
 * *     5  6
 * <p>
 * 返回其前序遍历: [1,3,5,6,2,4]。
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 */
public class S589 {


    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        traversal(res, root);
        return res;
    }

    private void traversal(List<Integer> res, Node root) {
        if (root == null) return;
        if (root.children == null || root.children.size() == 0) {
            res.add(root.val);
            return;
        }
        res.add(root.val);
        for (Node child : root.children) {
            traversal(res, child);
        }
    }

    public List<Integer> preorder2(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node temp;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            res.add(temp.val);
            if (temp.children != null) {
                for (int i = temp.children.size() - 1; i >= 0; i--) {
                    stack.push(temp.children.get(i));
                }
            }
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
        System.out.println(new S589().preorder2(root));
    }
}
