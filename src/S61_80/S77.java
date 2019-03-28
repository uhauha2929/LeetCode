package S61_80;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class S77 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || k > n)
            return res;
        backtrack(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    /**
     * 这里i <= n 换成 i <= (n-(k-cur.size())) + 1 避免不必要的搜索
     * 例如:  n=4, k=3时
     * *       1         2     i <= (4 - (3 - 0)) + 1 = 2
     * *     / |        /
     * *    2  3       3       i <= (4 - (3 - 1)) + 1 = 3
     * *   / \  \     /
     * *  3  4   4   4         i <= (4 - (3 - 2)) + 1 = 4
     */
    private void backtrack(List<List<Integer>> res, List<Integer> cur,
                           int n, int k, int start) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i <= (n - (k - cur.size())) + 1; i++) {
            cur.add(i);
            backtrack(res, cur, n, k, i + 1);
            cur.remove(cur.size() - 1);
        }
    }


    public static void main(String[] args) {
        System.out.println(new S77().combine(4, 3));
    }
}
