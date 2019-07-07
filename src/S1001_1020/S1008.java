package S1001_1020;

import util.TreeNode;

/**
 * 返回与给定先序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
 * <p>
 * (回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于 node.left 的任何后代，值总 < node.val，而 node.right 的任何后代，值总 > node.val。此外，先序遍历首先显示节点的值，然后遍历 node.left，接着遍历 node.right。）
 * 示例：
 * 输入：[8,5,1,7,10,12]
 * 输出：[8,5,10,1,7,null,12]
 * 提示：
 * 1 <= preorder.length <= 100
 * 先序 preorder 中的值是不同的。
 */
public class S1008 {

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        for (int val : preorder)
            root = add(root, val);
        return root;
    }

    private TreeNode add(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val)
            root.right = add(root.right, val);
        else
            root.left = add(root.left, val);
        return root;
    }

    public TreeNode bstFromPreorder2(int[] preorder) {
        return build(preorder, 0, preorder.length - 1);
    }

    private TreeNode build(int[] preorder, int i, int j) {
        if (i > j) return null;
        TreeNode root = new TreeNode(preorder[i]);
        int k = i + 1; // 第一个数为根节点, 找到第一个大于根节点的值
        while (k <= j && preorder[k] < preorder[i])
            k++;
        root.left = build(preorder, i + 1, k - 1);
        root.right = build(preorder, k, j);
        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new S1008().bstFromPreorder(new int[]{8, 5, 1, 7, 10, 12});
        TreeNode.inOrder(root);
    }
}
