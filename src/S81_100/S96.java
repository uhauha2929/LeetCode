package S81_100;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * *
 * *    1         3     3      2      1
 * *     \       /     /      / \      \
 * *      3     2     1      1   3      2
 * *     /     /       \                 \
 * *    2     1         2                 3
 */
public class S96 {
    /**
     * G(n)表示长度为n的序列不同结构的二叉搜索树的个数
     * G(0) = G(1) = 1
     * F(i), 1<=i<=n, 以i作为根节点的二叉树的数量
     * G(n) = F(1) + F(2) + ... + F(n)
     * 而 F(i) = G(i-1) + G(n-i)
     * 直接递归可能会超时, 采用动态规划
     */
    public int numTrees(int n) {
        if (n <= 1) return 1;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res += numTrees(i - 1) * numTrees(n - i);
        }
        return res;
    }

    // 动态规划
    public int numTrees2(int n) {
        if (n == 0 || n == 1) return 1;
        int[] res = new int[n + 1]; // 使用数组保存前面的状态
        res[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                res[i] += res[j] * res[i - j - 1];
            }
        }
        return res[n];
    }

    // 卡特兰数 G(n)
    // =C(2n,n)/(n+1)=P(2n,n)/(n+1)!
    // =(2n)!/(n!*(n+1)!)
    // =(2n) * ... * (n+1) / (n+1)!     (n=1,2,3,...)
    public int numTrees3(int n) {
        long ans = 1;
        for (int i = 1; i <= n; ++i)
            ans = ans * (i + n) / i;
        ans /= (n + 1);
        return (int) ans;
    }


    public static void main(String[] args) {
        System.out.println(new S96().numTrees3(3));
    }
}
