package S321_340;

/**
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 27
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: 0
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: 9
 * 输出: true
 * 示例 4:
 * <p>
 * 输入: 45
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 */
public class S326 {

    public boolean isPowerOfThree(int n) {
        if (n == 0) return false;
        if (n == 1) return true;
        return n % 3 == 0 && isPowerOfThree(n / 3);
    }

    public boolean isPowerOfThree2(int n) {
//        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
        return n > 0 && 1162261467 % n == 0; // 整数范围最大3的次幂, 3^19
    }

    public boolean isPowerOfThree3(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }


    public static void main(String[] args) {
        System.out.println(new S326().isPowerOfThree3(45));
    }
}
