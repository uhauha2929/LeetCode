package S681_700;

/**
 * 给定一个正整数，检查他是否为交替位二进制数：换句话说，就是他的二进制数相邻的两个位数永不相等。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 5
 * 输出: True
 * 解释:
 * 5的二进制数是: 101
 * 示例 2:
 * <p>
 * 输入: 7
 * 输出: False
 * 解释:
 * 7的二进制数是: 111
 * 示例 3:
 * <p>
 * 输入: 11
 * 输出: False
 * 解释:
 * 11的二进制数是: 1011
 * 示例 4:
 * <p>
 * 输入: 10
 * 输出: True
 * 解释:
 * 10的二进制数是: 1010
 */
public class S693 {

    // n > 0
    public boolean hasAlternatingBits(int n) {
        int t1, t2;
        while (n > 0) {
            t1 = n & 1;
            n >>= 1;
            t2 = n & 1;
            if ((t1 ^ t2) != 1)
                return false;
        }
        return true;
    }

    public boolean hasAlternatingBits2(int n) {
        int t = n ^ (n >> 1); // 如果交替, t所有位数为1
        return (t & (t + 1)) == 0;
    }


    public static void main(String[] args) {
        System.out.println(new S693().hasAlternatingBits(10));
    }
}
