package S181_200;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S200 {

    // DFS
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int num_islands = 0;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    ++num_islands; // 递归访问过的节点被标记为0, 再次遇到1说明遇到新的岛屿
                    dfs(grid, i, j);
                }
            }
        }

        return num_islands;
    }

    private void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';  // visited
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    private int[][] offsets = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // BFS
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int num_islands = 0;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    ++num_islands;
                    grid[i][j] = '0'; // mark as visited
                    Queue<int[]> neighbors = new LinkedList<>();
                    neighbors.add(new int[]{i, j});
                    while (!neighbors.isEmpty()) {
                        int[] xy = neighbors.remove();
                        for (int[] offset : offsets) {
                            int nx = xy[0] + offset[0];
                            int ny = xy[1] + offset[1];
                            if (nx >= 0 && nx < m
                                    && ny >= 0 && ny < n
                                    && grid[nx][ny] == '1') {
                                neighbors.add(new int[]{nx, ny});
                                grid[nx][ny] = '0'; // mark as visited
                            }
                        }
                    }
                }
            }
        }
        return num_islands;
    }

    private int[] parent;
    private int[] rank; // 表示树的高度
    private int count;

    // 并查集
    public int numIslands3(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;

        rank = new int[m * n]; // 默认为0
        parent = new int[m * n];
        Arrays.fill(parent, -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    count++;  // 统计1的总数
                    for (int[] offset : offsets) {
                        int r = offset[0] + i;
                        int c = offset[1] + j;
                        if (r >= 0 && r < m
                                && c >= 0 && c < n
                                && grid[r][c] == '1') {
                            union(r * n + c, i * n + j);
                        }
                    }
                }
            }
        }
        // 1的总数减去合并的数量即为岛屿的数量
        return count;
    }

    private void union(int x, int y) {
        int xRoot = getRoot(x);
        int yRoot = getRoot(y);
        if (xRoot != yRoot) {
            if (rank[xRoot] > rank[yRoot]) {
                parent[yRoot] = xRoot;
            } else if (rank[xRoot] < rank[yRoot]) {
                parent[xRoot] = yRoot;
            } else {
                rank[xRoot]++;
                parent[yRoot] = xRoot;
            }
            count--; // 只要有新的节点真正发生合并, 1的总数减1
        }
    }

    private int getRoot(int idx) {
        if (parent[idx] == -1)
            return idx;
        parent[idx] = getRoot(parent[idx]); // 路径压缩
        return parent[idx];
    }


    public static void main(String[] args) {
        System.out.println(new S200().numIslands3(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        }));
    }
}
