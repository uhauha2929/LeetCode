package S241_260;

/**
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 * <p>
 * 示例:
 * <p>
 * 输入: 38
 * 输出: 2
 * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
 */
public class S258 {

    // https://en.wikipedia.org/wiki/Digital_root
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }


    public static void main(String[] args) {
        System.out.println(new S258().addDigits(1853));
    }
}
