package S621_640;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * *      3
 * *    /  \
 * *   9   20
 * *  /  \
 * * 15   7
 * 输出: [3, 14.5, 11]
 * 解释:
 * 第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].
 * 注意：
 * <p>
 * 节点值的范围在32位有符号整数范围内。
 */
public class S637 {
    // 类似Solution102
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return null;
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode temp;
        int count, i;
        while (!queue.isEmpty()) {
            count = queue.size();
            i = count;
            double sum = 0;
            while (i > 0) {
                temp = queue.remove();
                sum += temp.val;
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
                i--;
            }
            res.add(sum / count);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(new S637().averageOfLevels(root));
    }
}
