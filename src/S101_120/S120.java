package S101_120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 例如，给定三角形：
 * *
 * * [
 * *      [2],
 * *     [3,4],
 * *    [6,5,7],
 * *   [4,1,8,3]
 * * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class S120 {
    /**
     * 动态规划
     * * [
     * *      [-1],
     * *     [2, 3],
     * *    [1,-1,-3]
     * * ]
     * dp数组自底向上保存最小和
     * * [
     * *      [  -1, 0, -3, 0],
     * *     [  1,  0, -3, 0],
     * *    [ 1, -1, -3, 0],
     * *   [0,  0,  0,  0]
     * * ]
     * dp[0]=-1即为最小和.
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        // 只需要记录每一层的最小值即可
        int[] dp = new int[triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> layer = triangle.get(i);
            for (int j = 0; j < layer.size(); j++) {
                //这里的dp[j] 使用的时候默认是上一层的，赋值之后变成当前层
                dp[j] = Math.min(dp[j], dp[j + 1]) + layer.get(j);
            }
        }
        return dp[0];
    }

    public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 可以直接用最后一层保存, 节省空间
        List<Integer> cache = triangle.get(n - 1);

        for (int layer = n - 2; layer >= 0; layer--) {
            //for each layer
            for (int i = 0; i <= layer; i++) {
                //check its very node
                int value = Math.min(cache.get(i), cache.get(i + 1)) + triangle.get(layer).get(i);
                cache.set(i, value);
            }
        }
        return cache.get(0);
    }


    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Collections.singletonList(-1));
        triangle.add(Arrays.asList(2, 3));
        triangle.add(Arrays.asList(1, -1, -3));
        System.out.println(new S120().minimumTotal2(triangle));
    }
}
