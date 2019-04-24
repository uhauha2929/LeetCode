package S61_80;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class S67 {

    public String addBinary(String a, String b) {
        char[] ac = a.toCharArray();
        char[] bc = b.toCharArray();
        int i = ac.length - 1, j = bc.length - 1;
        int carry = 0; // 表示进位，可以是0或1
        int k;
        char[] res;
        if (i > j) {
            res = ac;
            k = i;
        } else {
            res = bc;
            k = j;
        }
        while (i >= 0 && j >= 0) {
            int sum = carry + (ac[i] - '0') + (bc[j] - '0');
            res[k] = (char) (sum % 2 + '0');
            carry = sum < 2 ? 0 : 1;
            i--;
            j--;
            k--;
        }
        while (k >= 0) {
            int sum = carry + res[k] - '0';
            res[k] = (char) (sum % 2 + '0');
            if (sum < 2) {
                return new String(res); // 如果剩余部分不再进位
            } else {
                carry = 1;
            }
            k--;
        }
        // 如果最后仍然需要进位
        if (carry == 1) {
            return "1" + new String(res);
        }
        return new String(res);
    }

    public String addBinary2(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0, i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            if (i >= 0) carry += a.charAt(i--) - '0';
            if (j >= 0) carry += b.charAt(j--) - '0';
            sb.insert(0, carry % 2);
            carry /= 2;
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(new S67().addBinary2("1", "11"));
    }
}
