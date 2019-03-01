package S361_380;

/**
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: a = -2, b = 3
 * 输出: 1
 */
public class S371 {

    // 位运算
    public int getSum(int a, int b) {
        int sum = a ^ b;  // 无进位相加的结果
        int carry = (a & b) << 1; // 将进位左移
        if (carry != 0) { // 如果存在进位进行递归
            sum = getSum(sum, carry);
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(new S371().getSum(-1, 2));
    }
}
