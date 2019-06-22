package S61_80;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 说明：m 和 n 的值均不超过 100。
 * 示例 1:
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S63 {
    /**
     * 动态规划
     * 令 dp[i][j]为到达位置i,j的路径数目
     * 动态方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 对于第一行 dp[0][j], 或者第一列 dp[i][0], 由于都是在边界, 所以最多只能为1
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 如果起始点有障碍, 总路径数为0
        if (obstacleGrid[0][0] == 1)
            return 0;
        // 起始点没有障碍, 到起点的路径数为1
        dp[0][0] = 1;
        // 设置第一行
        for (int i = 1; i < n; i++) {
            // 如果之前和当前位置都没有障碍
            if (dp[0][i - 1] == 1 && obstacleGrid[0][i] != 1)
                dp[0][i] = 1;
            else break;
        }
        // 设置第一列
        for (int i = 1; i < m; i++) {
            // 如果之前和当前位置都没有障碍
            if (dp[i - 1][0] == 1 && obstacleGrid[i][0] != 1)
                dp[i][0] = 1;
            else break;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 如果该位置有障碍, 默认值为0, 无需设置
                if (obstacleGrid[i][j] == 1)
                    continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }


    public static void main(String[] args) {
        System.out.println(new S63().uniquePathsWithObstacles(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0},
        }));
    }
}
