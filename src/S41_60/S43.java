package S41_60;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S43 {

    // https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation
    //  num1[i] * num2[j] will be placed at indices [i + j, i + j + 1]`
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        // 两数相乘最多的位数不超过m+n, 可能开头会多出0.
        int[] res = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + res[p2]; // 加上低位
                res[p1] += sum / 10; // 向高位进位
                res[p2] = sum % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        // 去除开头多余的0
        int i = 0;
        while (i < res.length - 1 && res[i] == 0) i++;
        while (i < res.length) sb.append(res[i++]);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new S43().multiply("6", "501"));
    }
}
