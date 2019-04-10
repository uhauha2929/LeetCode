package S41_60;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 说明：
 * <p>
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class S60 {

    /**
     * 逆康拓展开:
     * 对于n=4, k=15 找到k=15排列的过程:
     * <p>
     * 1 + 对2,3,4的全排列 (3!个)
     * 2 + 对1,3,4的全排列 (3!个)         3, 1 + 对2,4的全排列(2!个)
     * 3 + 对1,2,4的全排列 (3!个)-------> 3, 2 + 对1,4的全排列(2!个)-------> 3, 2, 1 + 对4的全排列(1!个)-------> 3214
     * 4 + 对1,2,3的全排列 (3!个)         3, 4 + 对1,2的全排列(2!个)         3, 2, 4 + 对1的全排列(1!个)
     * <p>
     * 确定第一位:
     * k = 14(从0开始计数)
     * index = k / (n-1)! = 2, 说明第15个数的第一位是3
     * 更新k
     * k = k - index*(n-1)! = 2
     * 确定第二位:
     * k = 2
     * index = k / (n-2)! = 1, 说明第15个数的第二位是2
     * 更新k
     * k = k - index*(n-2)! = 0
     * 确定第三位:
     * k = 0
     * index = k / (n-3)! = 0, 说明第15个数的第三位是1
     * 更新k
     * k = k - index*(n-3)! = 0
     * 确定第四位:
     * k = 0
     * index = k / (n-4)! = 0, 说明第15个数的第四位是4
     * 最终确定n=4时第15个数为3214
     */
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        // 候选数字，刚开始为n个数，然后依次减少
        List<Integer> candidates = new ArrayList<>();
        // 分母的阶乘数
        int[] factorials = new int[n + 1];
        factorials[0] = 1;
        int fact = 1;
        for (int i = 1; i <= n; ++i) {
            candidates.add(i);
            fact *= i;
            factorials[i] = fact;
        }
        k -= 1;
        for (int i = n - 1; i >= 0; --i) {
            // 计算候选数字的index
            int index = k / factorials[i];
            // 每次要移除已确定的数字
            sb.append(candidates.remove(index));
            k -= index * factorials[i];
        }
        return sb.toString();
    }

    public String getPermutation2(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int[] factorials = new int[n + 1];
        factorials[0] = 1;
        for (int i = 1; i <= n; ++i)
            factorials[i] = factorials[i - 1] * i;
        recursion(sb, k, factorials, n, new boolean[n + 1]);
        return sb.toString();
    }

    private void recursion(StringBuilder sb, int k, int[] factorials, int l, boolean[] used) {
        for (int i = 1; i < factorials.length; ++i) {
            if (used[i]) continue;
            int count = factorials[l - 1];
            if (count < k) {
                k -= count;
                continue;
            }
            sb.append(i);
            used[i] = true;
            recursion(sb, k, factorials, l - 1, used);
            if (sb.length() == factorials.length - 1)
                return;
        }
    }


    public static void main(String[] args) {
        System.out.println(new S60().getPermutation2(4, 15));
    }
}
