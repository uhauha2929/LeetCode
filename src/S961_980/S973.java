package S961_980;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * <p>
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * <p>
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 * <p>
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class S973 {

    private double dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            double diff = dist(o1) - dist(o2);
            if (diff == 0) return 0;
            return diff > 0 ? 1 : -1;
        });
        queue.addAll(Arrays.asList(points));
        int[][] res = new int[K][];
        for (int i = 0; i < K; i++) {
            res[i] = queue.poll();
        }
        return res;
    }

    public int[][] kClosest2(int[][] points, int K) {
        int N = points.length;
        double[] dists = new double[N];
        for (int i = 0; i < N; ++i)
            dists[i] = dist(points[i]);

        Arrays.sort(dists);
        double distK = dists[K - 1];

        int[][] ans = new int[K][2];
        int t = 0;
        for (int[] point : points)
            if (dist(point) <= distK)
                ans[t++] = point;
        return ans;
    }

    // 类似solution215
    public int[][] kClosest3(int[][] points, int K) {
        int[][] res = new int[K][];
        int k = 0;
        int l = 0, r = points.length - 1;
        while (l <= r) {
            int p = partition(points, l, r);
            if (p <= K - 1) {
                // 从l到p的所有加入结果集
                for (int i = l; i <= p; i++) {
                    res[k++] = points[i];
                }
                l = p + 1;
            } else {
                r = p - 1;
            }
        }
        return res;
    }

    private int partition(int[][] points, int l, int r) {
        // 可以取中间值或随机位置
        int m = (l + r) / 2;
//        int m = l + (int) (Math.random() * (r - l));
        // 把最小的放在第一个位置
        if (dist(points[m]) < dist(points[l])) {
            int[] t = points[m];
            points[m] = points[l];
            points[l] = t;
        }
        // 也可以直接取第一个, 上面可以省略
        int[] key = points[l];
        while (l < r) {
            while (dist(points[r]) >= dist(key) && l < r) {
                r--;
            }
            points[l] = points[r];
            while (dist(points[l]) <= dist(key) && l < r) {
                l++;
            }
            points[r] = points[l];
        }
        points[r] = key;
        return r;
    }


    public static void main(String[] args) {
        int[][] res = new S973().kClosest3(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2);
        for (int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
    }
}
