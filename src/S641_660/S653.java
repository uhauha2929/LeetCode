package S641_660;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 * 案例 1:
 * 输入:
 * *     5
 * *    / \
 * *   3   6
 * *  / \   \
 * * 2   4   7
 * Target = 9
 * 输出: True
 * 案例 2:
 * 输入:
 * *     5
 * *    / \
 * *   3   6
 * *  / \   \
 * * 2   4   7
 * Target = 28
 * 输出: False
 */
public class S653 {

    // 中序遍历后采用双指针, 类似solution167
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inOrder(list, root);
        int i = 0, j = list.size() - 1;
        int sum;
        while (i < j) {
            sum = list.get(i) + list.get(j);
            if (sum > k) j--;
            else if (sum < k) i++;
            else return true;
        }
        return false;
    }

    private void inOrder(List<Integer> list, TreeNode root) {
        if (root == null) return;
        inOrder(list, root.left);
        list.add(root.val);
        inOrder(list, root.right);
    }

    private Set<Integer> set = new HashSet<>();

    public boolean findTarget2(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return findTarget2(root.left, k) || findTarget2(root.right, k);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{5, 3, 6, 2, 4, null, 7});
        System.out.println(new S653().findTarget2(root, 9));
    }
}
