package S461_480;

/**
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * 示例 :
 * 输入:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * 输出: 16
 */
public class S463 {

    public int islandPerimeter(int[][] grid) {
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                total += getPerimeter(grid, i, j);
            }
        }
        return total;
    }

    private int getPerimeter(int[][] grid, int i, int j) {
        if (grid[i][j] == 0) return 0;
        int count = 0;
        if (i <= 0 || grid[i - 1][j] == 0) count++;
        if (i >= grid.length - 1 || grid[i + 1][j] == 0) count++;
        if (j <= 0 || grid[i][j - 1] == 0) count++;
        if (j >= grid[0].length - 1 || grid[i][j + 1] == 0) count++;
        return count;
    }

    /**
     * 方块总个数 * 4 - 相邻
     */
    public int islandPerimeter2(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int totalNum = 0, closeNum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    totalNum++;   // 计算贴近的,右下算
                    if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                        closeNum++;
                    }
                    if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
                        closeNum++;
                    }
                }
            }
        }
        return totalNum * 4 - closeNum * 2;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };
        System.out.println(new S463().islandPerimeter(grid));
    }
}
