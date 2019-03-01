package S61_80;

/**
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 */
public class S69 {

    // 二分法
    public int mySqrt(int x) {
        if (x < 0)
            throw new RuntimeException("integerSqrt works for only nonnegative inputs");
        else if (x <= 1)
            return x;
        else {
            int left = 1, right = x / 2;
            int ans = 0;
            while (left <= right) {
                int mid = (right + left) / 2;
                if (mid < x / mid) {  // 用除法防止溢出
                    left = mid + 1;
                    ans = mid;
                } else if (mid > x / mid) {
                    right = mid - 1;
                } else
                    return mid;
            }
            return ans;
        }
    }

    // 位运算
    public int mySqrt2(int x) {
        if (x < 0)
            throw new RuntimeException("integerSqrt works for only nonnegative inputs");
        else if (x < 2)
            return x;
        else {
            // Recursive call:
            int smallCandidate = mySqrt2(x >> 2) << 1;
            int largeCandidate = smallCandidate + 1;
            if (largeCandidate > x / largeCandidate)
                return smallCandidate;
            else
                return largeCandidate;
        }
    }

    /**
     * 牛顿法或者不动点迭代
     * x_{k+1} = 1 / 2 (x_k + n / x_k)
     * 只要|x_{k+1} - x_k| < 1，取x_{k+1}的整数部分
     * https://en.wikipedia.org/wiki/Integer_square_root#Using_only_integer_division
     */
    public int mySqrt3(int x) {
        if (x < 0)
            throw new RuntimeException("integerSqrt works for only nonnegative inputs");
        else if (x <= 1)
            return x;
        else {
            long i = x;
            long j = (i + x / i) / 2;
            while (Math.abs(j - i) >= 1) {
                i = j;
                j = (i + x / i) / 2;
                if (i + 1 == j)
                    break;
            }
            return (int)i;
        }
    }

    public static void main(String[] args) {
        System.out.println(new S69().mySqrt3(2147483647));
    }
}
