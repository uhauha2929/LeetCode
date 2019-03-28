package S681_700;

import utils.TreeNode;

/**
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * <p>
 * 例如，
 * <p>
 * 给定二叉搜索树:
 * *
 * *         4
 * *        / \
 * *       2   7
 * *      / \
 * *     1   3
 * <p>
 * 和值: 2
 * 你应该返回如下子树:
 * *
 * *       2
 * *      / \
 * *     1   3
 * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 */
public class S700 {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        if (root.val > val)
            return searchBST(root.left, val);
        else
            return searchBST(root.right, val);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{4, 2, 7, 1, 3});
        TreeNode.inOrder(root);
        System.out.println();
        TreeNode.inOrder(new S700().searchBST(root, 2));
    }
}
