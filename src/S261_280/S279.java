package S261_280;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S279 {
    // 动态规划, dp[i]表示数字i可以表示的平方和的最小个数
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 默认为全是1的和
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    // BFS
    public int numSquares2(int n) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int step = 0;
        queue.add(n);
        while (!queue.isEmpty()) {
            step++; // 向外延伸一圈
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.remove();
                if (!set.add(cur)) continue; // 跳过访问过的边
                // 如果两个数的差为平方则这两个数存在一条边
                for (int j = 1; j <= Math.sqrt(cur); j++) {
                    int next = cur - j * j;
                    if (next == 0) return step;
                    queue.offer(next);
                }
            }
        }
        return 0;
    }
    // 任何一个正整数都可以表示成不超过四个整数的平方之和
    // 推论：满足四数平方和定理的数n（四个整数的情况），必定满足 n=4^a(8b+7)
    public int numSquares3(int n) {
        while (n % 4 == 0) n = n / 4;
        if (n % 8 == 7) return 4;
        int a = 0;
        while (a * a <= n) {
            int b = (int) Math.sqrt(n - a * a);
            if (n == (a * a + b * b)) {
                if (a != 0 && b != 0)
                    return 2;
                else
                    return 1;
            }
            a++;
        }
        return 3;
    }

    public static void main(String[] args) {
        System.out.println(new S279().numSquares(12));
    }
}
