package S61_80;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * 说明：m 和 n 的值均不超过 100。
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 */
public class S62 {

    /**
     * 动态规划
     * 令 dp[i][j]为到达位置i,j的路径数目
     * 动态方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 对于第一行 dp[0][j], 或者第一列 dp[i][0], 由于都是在边界, 所以只能为 1
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 排列组合
     * C(n,m)=n!/(m!(n-m)!)
     * 总共会走m+n-2步, 从中挑m-1步向下走
     */
    public int uniquePaths2(int m, int n) {
        int N = m + n - 2;
        int M = m < n ? m - 1 : n - 1;
        long ans = 1;
        for (int i = 1; i <= M; i++)
            ans = ans * (N - i + 1) / i;
        return (int) ans;
    }


    public static void main(String[] args) {
        System.out.println(new S62().uniquePaths2(7, 3));
    }
}
