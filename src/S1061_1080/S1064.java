package S1061_1080;

/**
 * 给定已经按升序排列、由不同整数组成的数组 A，返回满足 A[i] == i 的最小索引 i。如果不存在这样的 i，返回 -1。
 * 示例 1：
 * 输入：[-10,-5,0,3,7]
 * 输出：3
 * 解释：
 * 对于给定的数组，A[0] = -10，A[1] = -5，A[2] = 0，A[3] = 3，因此输出为 3 。
 * 示例 2：
 * 输入：[0,2,5,8,17]
 * 输出：0
 * 示例：
 * A[0] = 0，因此输出为 0 。
 * 示例 3：
 * 输入：[-10,-5,3,4,7,9]
 * 输出：-1
 * 解释：
 * 不存在这样的 i 满足 A[i] = i，因此输出为 -1 。
 * 提示：
 * 1 <= A.length < 10^4
 * -10^9 <= A[i] <= 10^9
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fixed-point
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1064 {

    public int fixedPoint(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (i == A[i]) return i;
        }
        return -1;
    }

    // 考虑有序，故用二分法
    public int fixedPoint2(int[] A) {
        int l = 0, r = A.length - 1;
        int ans = -1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (A[m] < m) {
                l = m + 1;
            } else if (A[m] > m) {
                r = m;
            } else {
                ans = m; // 找到一个还需继续向前找最小的
                r = m;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new S1064().fixedPoint2(new int[]{0, 1, 3, 7, 8, 9}));
    }
}
