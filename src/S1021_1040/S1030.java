package S1021_1040;

import java.util.*;

/**
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，
 * 其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。
 * （你可以按任何满足此条件的顺序返回答案。）
 * 示例 1：
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 * 示例 2：
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1
 * 输出：[[0,1],[0,0],[1,1],[1,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 * 示例 3：
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 * 提示：
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 * 在真实的面试中遇到过这道
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1030 {

    private double dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[] target = new int[]{r0, c0};
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                double diff = dist(o1, target) - dist(o2, target);
                if (diff == 0) return 0;
                return diff > 0 ? 1 : -1;
            }
        });
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                queue.add(new int[]{i, j});
            }
        }
        int[][] res = new int[R * C][2];
        for (int i = 0; i < R * C; i++) {
            res[i] = queue.remove();
        }
        return res;
    }

    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        int[] target = new int[]{r0, c0};
        int k = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                res[k++] = new int[]{i, j};
            }
        }
        Arrays.sort(res, (o1, o2) -> {
            double diff = dist(o1, target) - dist(o2, target);
            if (diff == 0) return 0;
            return diff > 0 ? 1 : -1;
        });
        return res;
    }

    // BFS
    public int[][] allCellsDistOrder3(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r0, c0});
        boolean[][] visited = new boolean[R][C];
        int i = 0;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            // 距离最近的已经被访问过了
            if (visited[row][col]) continue;
            res[i++] = new int[]{row, col};
            if (col < C - 1)
                queue.offer(new int[]{row, col + 1});
            if (row < R - 1)
                queue.offer(new int[]{row + 1, col});
            if (row > 0)
                queue.offer(new int[]{row - 1, col});
            if (col > 0)
                queue.offer(new int[]{row, col - 1});
            visited[row][col] = true;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new S1030().allCellsDistOrder(1, 2, 0, 0)));
    }
}
