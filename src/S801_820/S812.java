package S801_820;

/**
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 * 示例:
 * 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * 输出: 2
 * 注意:
 * 3 <= points.length <= 50.
 * 不存在重复的点。
 *  -50 <= points[i][j] <= 50.
 * 结果误差值在 10^-6 以内都认为是正确答案。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-triangle-area
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S812 {

    private double dist(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    // 暴力法 海伦公式
    public double largestTriangleArea(int[][] points) {
        double a, b, c, p, s;
        double ans = Double.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    a = dist(points[i], points[j]);
                    b = dist(points[i], points[k]);
                    c = dist(points[j], points[k]);
                    p = (a + b + c) / 2;
                    s = p * (p - a) * (p - b) * (p - c);
                    if (s > ans) ans = s;
                }
            }
        }
        return Math.sqrt(ans);
    }


    public static void main(String[] args) {
        int[][] points = new int[][]{{0, 0}, {0, 1}, {1, 0}, {0, 2}, {2, 0}};
        System.out.println(new S812().largestTriangleArea(points));
    }
}
