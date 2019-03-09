package S601_620;

import utils.TreeNode;

/**
 * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 * <p>
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 二叉树: [1,2,3,4]
 * *      1
 * *    /   \
 * *   2     3
 * *  /
 * * 4
 * <p>
 * 输出: "1(2(4))(3)"
 * <p>
 * 解释: 原本将是“1(2(4)())(3())”，
 * 在你省略所有不必要的空括号对之后，
 * 它将是“1(2(4))(3)”。
 * 示例 2:
 * <p>
 * 输入: 二叉树: [1,2,3,null,4]
 * *        1
 * *      /   \
 * *     2     3
 * *      \
 * *       4
 * <p>
 * 输出: "1(2()(4))(3)"
 * <p>
 * 解释: 和第一个示例相似，
 * 除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
 */
public class S606 {

    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        traverse(t, sb);
        return sb.toString();
    }

    private void traverse(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.val);
        if (node.left != null || node.right != null) {
            sb.append('(');
            traverse(node.left, sb);
            sb.append(')');
        }
        if (node.right != null) {
            sb.append('(');
            traverse(node.right, sb);
            sb.append(')');
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, 2, 3, null, 4});
        System.out.println(new S606().tree2str(root));
    }
}
