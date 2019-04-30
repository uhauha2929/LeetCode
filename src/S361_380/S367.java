package S361_380;

/**
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 * 说明：不要使用任何内置的库函数，如  sqrt。
 * 示例 1：
 * 输入：16
 * 输出：True
 * 示例 2：
 * 输入：14
 * 输出：False
 */
public class S367 {
    /**
     * 平方数可以表示成奇数的和
     * 1 = 1
     * 4 = 1 + 3
     * 9 = 1 + 3 + 5
     * 16 = 1 + 3 + 5 + 7
     */
    public boolean isPerfectSquare(int num) {
        for (int i = 1; num > 0; i += 2) {
            num -= i;
        }
        return num == 0;
    }

    // 二分法
    public boolean isPerfectSquare2(int num) {
        if (num < 0) return false;
        if (num == 0 || num == 1) return true;
        //二分找到平方比num小的
        int start = 0;
        int end = num;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            //可能溢出
            //int pow = mid * mid;
            if (mid == num * 1.0 / mid) {
                return true;
            } else if (mid < num * 1.0 / mid) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new S367().isPerfectSquare(16));
    }
}
