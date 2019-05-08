package S321_340;

import java.util.Arrays;

/**
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 */
public class S338 {
    /**
     * 类似solution191
     * 如果等于i&(i-1)=0, 则为1, 否则为i&(i-1)位置的计数加1
     * 1 & 0 = 0 : 1
     * 10 & 01 = 0 : 1
     * 11 & 10 = 10 : 2
     * 100 & 011 = 0 : 1
     * 101 & 100 = 100 : 2
     * 110 & 101 = 100 : 2
     */
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }

    /**
     * i>>1会把最低位去掉, 因此i>>1也是比i小的，同样也是在前面的数组里算过.
     * 当i的最低位是0, 则i中1的个数和i>>1中1的个数相同;
     * 当i的最低位是1, i中1的个数是 i>>1中1的个数再加1.
     */
    public int[] countBits2(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = res[i >> 1] + (i & 1);  //注意i&1需要加括号
        }
        return res;
    }

    /**
     * 对于二进制数，如果它的最低位为1(%2=1),则它与n/2的1个数相差1.
     * 如果它的最低位为0，则它与n/2的1个数相同
     */
    public int[] countBits3(int num) {
        int[] dp = new int[num + 1];
        for (int i = 0; i <= num / 2; i++) {
            dp[i * 2] = dp[i];
            if (i * 2 + 1 <= num)
                dp[i * 2 + 1] = dp[i] + 1;
        }
        return dp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new S338().countBits(8)));
    }
}
