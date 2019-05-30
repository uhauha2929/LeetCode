package S441_460;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * 找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
 * 示例:
 * 输入:
 * [[0,0],[1,0],[2,0]]
 * 输出:
 * 2
 * 解释:
 * 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 */
public class S447 {

    public int numberOfBoomerangs(int[][] points) {
        Map<Double, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                double d = Math.pow(points[i][0] - points[j][0], 2) +
                        Math.pow(points[i][1] - points[j][1], 2);
                map.put(d, map.getOrDefault(d, 0) + 1);
            }
            for (double d : map.keySet()) {
                int count = map.get(d);
                res += count * (count - 1);
            }
            map.clear();
        }
        return res;
    }

    public int numberOfBoomerangs2(int[][] points) {
        Map<Double, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                double d = Math.pow(points[i][0] - points[j][0], 2) +
                        Math.pow(points[i][1] - points[j][1], 2);
                if (map.containsKey(d)) {
                    int count = map.get(d);
                    res += count * 2;  // (n+1)*n - n*(n-1) = 2n
                    map.put(d, count + 1);
                } else {
                    map.put(d, 1);
                }
            }
            map.clear();
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S447().numberOfBoomerangs(new int[][]{
                {0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        }));
    }
}
