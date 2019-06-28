package S41_60;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * 示例 1:
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S50 {

    /**
     * 快速幂算法
     * 时间复杂度: O(logn)
     * 空间复杂度: O(logn)
     */
    public double myPow(double x, int n) {
        long N = (long) n;
        if (n < 0) {
            x = 1 / x;
            N *= -1;
        }
        return fastPow(x, N);
    }

    private double fastPow(double x, long n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        double half = fastPow(x, n / 2);
        return (n & 1) == 0 ? half * half : half * half * x;
    }

    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(1)
     */
    public double myPow2(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double cur = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * cur;
            }
            cur *= cur;
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new S50().myPow(2, 4));
    }
}
