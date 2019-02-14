import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class S54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 0) {
            return list;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0, j = 0;
        int sm = 0, sn = 0; // 表示新的一圈列和行的开始
        while (true) {
            list.add(matrix[i][j]);
            // 向右走
            while (j < n - 1) {
                list.add(matrix[i][++j]);
            }
            sm++;
            // 向下走
            while (i < m - 1) {
                list.add(matrix[++i][j]);
            }
            n--;
            // 如果是单行，没必要再往左走
            while (sm < m && j > sn) {
                list.add(matrix[i][--j]);
            }
            m--;
            // 如果是单列，没必要再往上走
            while (sn < n && i > sm) {
                list.add(matrix[--i][j]);
            }
            sn++;
            // 转完一圈看看能不能进入内圈
            if (sm < m && sn < n) {
                j++;
            } else break;
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new S54().spiralOrder(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {11, 12, 13}
        }));
    }
}
