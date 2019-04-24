package S201_220;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 * 示例:
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */
public class S204 {
    /**
     * 埃拉托色尼筛选法(the Sieve of Eratosthenes)
     * https://blog.csdn.net/xiaoquantouer/article/details/51817803
     */
    public int countPrimes(int n) {
        boolean[] mask = new boolean[n];
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (!mask[i]) {
                cnt++;
                if (i * i < n) {
                    // 当i小于sqrt(n)时, 把其倍数遮盖掉, 例如n=120, i<11时.
                    // 当i>=11时, 之后所有的未遮盖掉的数字全是质数
                    for (int j = 2; j * i < n; j++) {
                        mask[i * j] = true;
                    }
                }
            }
        }
        return cnt;
    }


    public static void main(String[] args) {
        System.out.println(new S204().countPrimes(10));
    }
}
