package S501_520;

/**
 * 给定一个整数，将其转化为7进制，并以字符串形式输出。
 * 示例 1:
 * 输入: 100
 * 输出: "202"
 * 示例 2:
 * 输入: -7
 * 输出: "-10"
 * 注意: 输入范围是 [-1e7, 1e7] 。
 */
public class S504 {

    public String convertToBase7(int num) {
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        String base7 = "0123456";
        int n = num;
        while (n != 0) {
            sb.append(base7.charAt(Math.abs(n % 7)));
            n /= 7;
        }
        if (num < 0) sb.append('-');
        return sb.reverse().toString();
//         return Integer.toString(num ,7);
    }


    public static void main(String[] args) {
        System.out.println(new S504().convertToBase7(-7));
    }
}
