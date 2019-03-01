package S41_60;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class S59 {

    public int[][] generateMatrix(int n) {
        int i = 0, j = 0;
        int[][] res = new int[n][n];
        int k = 1;
        int s = 0;
        while (true) {
            res[i][j] = k++;
            // 向右走
            while (j < n - 1) {
                res[i][++j] = k++;
            }
            // 向下走
            while (i < n - 1) {
                res[++i][j] = k++;
            }
            // 往左走
            while (j > s) {
                res[i][--j] = k++;
            }
            // 往上走
            s++; // 开始位置加1
            while (i > s) {
                res[--i][j] = k++;
            }
            n--; // 结束位置减1
            // 转完一圈看看能不能进入内圈
            if (s < n) {
                j++;
            } else break;
        }
        return res;
    }


    public static void print(int[][] matrix) {
        for (int[] matrix1 : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix1[j] + "\t");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        S59.print(new S59().generateMatrix(5));
    }
}
