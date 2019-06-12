package S341_360;

/**
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 * 示例:
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S357 {

    private int[] ans = new int[]{1, 10, 91, 739, 5275, 32491, 168571, 712891, 2345851, 5611771, 8877691};

    // 排列组合k为长度.f(1)=10, f(k)=9*9*8*...*(9-k+2), ans[k]=f(k)+f(k-1)
    public int countNumbersWithUniqueDigits(int n) {
        if (n < 0) return 0;
        if (n >= 10) return ans[10];
        return ans[n];
    }

    public static void main(String[] args) {
        System.out.println(new S357().countNumbersWithUniqueDigits(2));
    }
}
