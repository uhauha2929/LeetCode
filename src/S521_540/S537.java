package S521_540;

/**
 * 给定两个表示复数的字符串。
 * <p>
 * 返回表示它们乘积的字符串。注意，根据定义 i2 = -1 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "1+1i", "1+1i"
 * 输出: "0+2i"
 * 解释: (1 + i) * (1 + i) = 1 + i^2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * 示例 2:
 * <p>
 * 输入: "1+-1i", "1+-1i"
 * 输出: "0+-2i"
 * 解释: (1 - i) * (1 - i) = 1 + i^2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 * 注意:
 * <p>
 * 输入字符串不包含额外的空格。
 * 输入字符串将以 a+bi 的形式给出，其中整数 a 和 b 的范围均在 [-100, 100] 之间。输出也应当符合这种形式。
 */
public class S537 {

    public String complexNumberMultiply(String a, String b) {
        int p1 = a.indexOf('+');
        int p2 = b.indexOf('+');

        int x1 = Integer.parseInt(a.substring(0, p1));
        int y1 = Integer.parseInt(a.substring(p1 + 1, a.length() - 1));

        int x2 = Integer.parseInt(b.substring(0, p2));
        int y2 = Integer.parseInt(b.substring(p2 + 1, b.length() - 1));

        return (x1 * x2 - y1 * y2) + "+" + (x1 * y2 + y1 * x2) + "i";
    }


    public static void main(String[] args) {
        System.out.println(new S537().complexNumberMultiply("1+-1i", "0+0i"));
    }
}
