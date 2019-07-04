package S701_720;

import java.util.*;

/**
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 示例 1:
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S718 {

    // 动态规划 如果A[i]=B[j], 则dp[i][j]=dp[i+1][j+1]+1, 否则d[i][j]=0
    public int findLength(int[] A, int[] B) {
        int ans = 0;
        int[][] memo = new int[A.length + 1][B.length + 1];
        for (int i = A.length - 1; i >= 0; --i) {
            for (int j = B.length - 1; j >= 0; --j) {
                if (A[i] == B[j]) {
                    memo[i][j] = memo[i + 1][j + 1] + 1;
                    if (ans < memo[i][j]) ans = memo[i][j];
                }
            }
        }
        return ans;
    }

    // 常量
    private int ALPHA = 13;

    private long[] rolling(int[] source, int m) {
        // 所有窗口的Hash值
        int n = source.length;
        if (m > n) throw new RuntimeException();
        long[] ans = new long[n - m + 1];
        if (m == 0) return ans;
        // 计算第一个窗口的Hash
        long firstWindowHash = 0;
        // 窗口内所有元素的系数, m的阶乘
        long[] coefficients = new long[m];
        coefficients[0] = 1;
        for (int i = 1; i < m; i++)
            coefficients[i] = coefficients[i - 1] * ALPHA;
        for (int i = 0; i < m; i++)
            firstWindowHash += coefficients[m - 1 - i] * source[i];

        ans[0] = firstWindowHash;
        // 计算后面的窗口的Hash
        for (int i = 1; i < ans.length; ++i) {
            ans[i] = ALPHA * (ans[i - 1] - coefficients[m - 1] * source[i - 1]) + source[i + m - 1];
        }
        return ans;
    }

    private boolean check(int guess, int[] A, int[] B) {
        Map<Long, List<Integer>> hashes = new HashMap<>();
        int k = 0;
        for (long x : rolling(A, guess)) {
            // 记录A中不同的窗口的Hash对应的开始位置
            if (!hashes.containsKey(x)) {
                hashes.put(x, new ArrayList<>());
            }
            hashes.get(x).add(k++);
        }
        int j = 0;
        for (long x : rolling(B, guess)) {
            // 直接从A和B的Hash值相同的位置开始比较
            for (int i : hashes.getOrDefault(x, new ArrayList<>()))
                // 如果Hash值相同, 为了避免冲突, 还要再次比较确认是否完全一致
                if (Arrays.equals(Arrays.copyOfRange(A, i, i + guess),
                        Arrays.copyOfRange(B, j, j + guess))) {
                    return true;
                }
            j++;
        }
        return false;
    }

    public int findLength2(int[] A, int[] B) {
        // 公共子数组的长度在0~min(len(A),len(B))之间
        int lo = 0, hi = Math.min(A.length, B.length) + 1;
        // 对窗口长度进行二分查找
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (check(mi, A, B)) lo = mi + 1;
            else hi = mi;
        }
        return lo - 1;
    }

    public static void main(String[] args) {
        System.out.println(new S718()
                .findLength2(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7}));
    }
}
