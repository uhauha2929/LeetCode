package S501_520;

import util.TreeNode;

import java.util.*;

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * 假定 BST 有如下定义：
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 * *    1
 * *     \
 * *      2
 * *     /
 * *    2
 * 返回[2].
 * 提示：如果众数超过1个，不需考虑输出顺序
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S501 {

    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        count(root, map);
        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                return e2.getValue() - e1.getValue();
            }
        });
        queue.addAll(map.entrySet());
        List<Integer> res = new ArrayList<>();
        Map.Entry<Integer, Integer> entry;
        Integer first = null;
        while (!queue.isEmpty()) {
            entry = queue.remove();
            if (first == null) {
                first = entry.getValue();
                res.add(entry.getKey());
            } else {
                if (entry.getValue().equals(first)) {
                    res.add(entry.getKey());
                } else break;
            }
        }
        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }

    private void count(TreeNode root, Map<Integer, Integer> map) {
        if (root != null) {
            map.put(root.val, map.getOrDefault(root.val, 0) + 1);
            count(root.left, map);
            count(root.right, map);
        }
    }

    public int[] findMode2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }

    private Integer pre, cnt = 0, maxCnt = 0;
    // 二叉搜索树中序遍历有序, 重复数相邻
    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inOrder(root.left, list);
        // 如果当前数为第一个数或另一个数, 当前计数置为1
        if (pre == null || root.val != pre) {
            cnt = 1;
        } else cnt++;
        // 如果当前计数比当前最大计数大
        if (cnt > maxCnt) {
            // 更新当前最大计数, 之前集合中的都不是众数
            maxCnt = cnt;
            // 清空当前集合并将该数加入集合
            list.clear();
            list.add(root.val);
        } else if (cnt.equals(maxCnt)) {
            // 如果当前计数等于当前最大计数, 则有可能是多个众数之一
            list.add(root.val);
        }
        // 始终保留前一个数
        pre = root.val;
        inOrder(root.right, list);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, null, 2});
        System.out.println(Arrays.toString(new S501().findMode2(root)));
    }
}
