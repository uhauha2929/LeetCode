package S941_960;

import utils.TreeNode;

/**
 * 我们可以为二叉树 T 定义一个翻转操作，如下所示：选择任意节点，然后交换它的左子树和右子树。
 * <p>
 * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转等价于二叉树 Y。
 * <p>
 * 编写一个判断两个二叉树是否是翻转等价的函数。这些树由根节点 root1 和 root2 给出。
 * <p>
 * 示例：
 * *         1                1
 * *       /  \             /   \
 * *      2    3           3     2
 * *     / \  /             \   / \
 * *    4  5  6             6   4  5
 * *      / \                     / \
 * *     7   8                   8   7
 * 输入：root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * 输出：true
 * 解释：We flipped at nodes with values 1, 3, and 5.
 * <p>
 * 提示：
 * 每棵树最多有 100 个节点。
 * 每棵树中的每个值都是唯一的、在 [0, 99] 范围内的整数。
 */
public class S951 {

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true; // 都为空 true
        if (root1 == null || root2 == null) return false; // 一个为空,一个不为空 false
        if (root1.val != root2.val) return false;  // 值不同 false
        // 只要两个节点的子节点相同或者翻转后相同就行
        return (flipEquiv(root1.left, root2.left)
                && flipEquiv(root1.right, root2.right))
                ||
                (flipEquiv(root1.left, root2.right)
                        && flipEquiv(root1.right, root2.left));
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(new Integer[]{1, 2, 3, 4, 5, 6, null, null, null, 7, 8});
        TreeNode root2 = new TreeNode(new Integer[]{1, 3, 2, null, 6, 4, 5, null, null, null, null, 8, 7});
        System.out.println(new S951().flipEquiv(root1, root2));
    }
}
