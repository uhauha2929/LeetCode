package S61_80;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class S70 {

    /**
     * 暴力法, 时间复杂度O(2^n), 空间复杂度O(n)
     * *                   (0,4)
     * *               /          \
     * *           (1,4)          (2,4)
     * *           /     \       /      \
     * *        (2,4)   (3,4) (3,4) (4,4)
     * *        /     \   /      /
     * *       (3,4)(4,4)(4,4)(4,4)
     * *       /
     * *      (4,4)
     */
    public int climbStairs(int n) {
        return climb_Stairs(0, n);
    }

    public int climb_Stairs(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
    }

    /**
     * 记忆化递归, 使用数组存储部分结果, 避免重复计算.
     * 例如法1中, (2,4)和(3,4)进行了重复计算.
     * 时间复杂度O(n), 空间复杂度O(n)
     */
    public int climbStairs2(int n) {
        int[] memo = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }

    public int climb_Stairs(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }

    /**
     * 动态规划
     * 第 i 阶可以由以下两种方法得到：
     * 在第 (i-1) 阶后向上爬1阶。
     * 在第 (i-2) 阶后向上爬2阶。
     * dp[i]=dp[i−1]+dp[i−2]
     * 时间复杂度O(n), 空间复杂度O(n)
     */
    public int climbStairs3(int n) {
        if (n == 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 斐波那契数
     * 时间复杂度O(n), 空间复杂度O(1)
     */
    public int climbStairs4(int n) {
        if (n == 1) return 1;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    /**
     * Binets方法
     * 时间复杂度O(log(n)), 空间复杂度O(n)
     * https://leetcode-cn.com/articles/climbing-stairs/
     */
    public int climbStairs5(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    /**
     * 斐波那契公式
     * 时间复杂度O(log(n)), 空间复杂度O(n)
     */
    public int climbStairs6(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) (fibn / sqrt5);
    }

    public static void main(String[] args) {
        System.out.println(new S70().climbStairs2(4));
    }
}
