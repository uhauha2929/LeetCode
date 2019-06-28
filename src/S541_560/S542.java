package S541_560;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 * 示例 1:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 示例 2:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * 注意:
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/01-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S542 {

    // 动态规划
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 第一次遍历, 正向遍历, 根据相邻左元素和上元素得出当前元素的对应结果
        // 遍历方向是从第一行到最后一行依次从左到右的, 因此总是可以找到当前遍历元素的左边和上方的元素(如果存在)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0)
                    continue;
                // 如果该位置值为1, 则设置一个很大的数(Integer.MAX_VALUE+1会溢出)
                // 强迫必须从其左边和上方选择最小的
                matrix[i][j] = Math.max(m, n);
                if (i > 0)
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i - 1][j] + 1);
                if (j > 0)
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][j - 1] + 1);
            }
        }
        // 第二次遍历, 反向遍历, 根据相邻右元素和下元素及当前元素的结果得出最终结果
        // 遍历方向是从最后一行依次从右至左的, 因此总是可以找到当前遍历元素的右边和下方的元素(如果存在)
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i < m - 1)
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i + 1][j] + 1);
                if (j < n - 1)
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][j + 1] + 1);
            }
        }
        return matrix;
    }

    // 右, 左, 下, 上
    private int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // BFS
    public int[][] updateMatrix2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // 将所有0元素作为BFS第一层
                    queue.add(new int[]{i, j});
                } else {
                    matrix[i][j] = Math.max(m, n);
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            // 搜索上下左右四个方向
            for (int[] d : direction) {
                int r = s[0] + d[0], c = s[1] + d[1];
                if (r >= 0 && r < m
                        && c >= 0 && c < n
                        && matrix[r][c] > matrix[s[0]][s[1]] + 1) {
                    matrix[r][c] = matrix[s[0]][s[1]] + 1;
                    queue.add(new int[]{r, c});
                }
            }
        }
        return matrix;
    }

    private int m, n;

    public int[][] updateMatrix3(int[][] matrix) {
        m = matrix.length;
        if (m == 0) return matrix;
        n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1 && !hasZeroAround(i, j, matrix)) {
                    matrix[i][j] = 9999;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dfs(matrix, i, j, -1);
                }
            }
        }

        return matrix;
    }

    private void dfs(int[][] matrix, int x, int y, int val) {
        if (x < 0 || y < 0 || y >= n || x >= m || matrix[x][y] <= val)
            return;
        if (val > 0) matrix[x][y] = val;
        for (int[] dir : direction) {
            int x1 = x + dir[0];
            int y1 = y + dir[1];
            dfs(matrix, x1, y1, matrix[x][y] + 1);
        }
    }

    private boolean hasZeroAround(int x, int y, int[][] matrix) {
        for (int[] d : direction) {
            int x1 = x + d[0];
            int y1 = y + d[1];
            if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && matrix[x1][y1] == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        };
        System.out.println(Arrays.deepToString(new S542().updateMatrix3(mat)));
    }
}
