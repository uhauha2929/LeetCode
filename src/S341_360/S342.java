package S341_360;

/**
 * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 16
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 */
public class S342 {

    // 类似solution326
    public boolean isPowerOfFour(int num) {
        return Integer.toString(num, 4).matches("^10*$");
    }

    public boolean isPowerOfFour2(int num) {
        // 保证是2的幂, 同时1只出现在奇数位
        // 0x55555555 : 1010-1010-1010-1010-1010-1010-1010-1010
        return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) != 0;
    }

    public boolean isPowerOfFour3(int num) {
        if (num < 4 && num != 1) {
            return false;
        }
        while (num != 1) {
            if (num % 4 != 0) {
                return false;
            }
            num /= 4;
        }
        return true;
    }


    public static void main(String[] args) {

    }
}
