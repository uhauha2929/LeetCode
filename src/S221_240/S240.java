package S221_240;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = m - 1;
        // 找到可能的行的范围
        while (i < m && matrix[i][n - 1] < target)
            i++;
        if (i == m) return false;
        while (j >= 0 && matrix[j][0] > target)
            j--;
        if (j == -1) return false;
        // 可能存在的行[i,j], 对每一行使用二分查找
        while (i <= j) {
            int l = 0, r = n - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (matrix[i][mid] == target)
                    return true;
                else if (matrix[i][mid] > target)
                    r = mid - 1;
                else
                    l = mid + 1;
            }
            i++;
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        // 从最右上角的元素开始
        while (i >= 0 && i <= m - 1 && j >= 0 && j <= n - 1) {
            if (matrix[i][j] == target)
                return true;
            else if (matrix[i][j] > target)
                // 大于就左移一格
                j--;
            else
                // 小于就下移一格
                i++;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(new S240().searchMatrix2(matrix, 19));
    }
}
