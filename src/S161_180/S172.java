package S161_180;

/**
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * 示例 1:
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * 示例 2:
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 */
public class S172 {
    /**
     * n! = x * (10)^k = x * (2^k * 5^k)
     * k即为末尾0的个数
     * 其中x可能是2的倍数, 所以2的个数一定比5多
     * 而n!为递减相乘, 只需要统计n中5的个数
     */
    public int trailingZeroes(int n) {
        return n < 5 ? 0 : n / 5 + trailingZeroes(n / 5);
    }


    public static void main(String[] args) {
        System.out.println(new S172().trailingZeroes(20));
    }
}
