package S541_560;

/**
 * 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。
 * 如果剩余少于 k 个字符，则将剩余的所有全部反转。
 * 如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。
 * <p>
 * 示例:
 * <p>
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 * 要求:
 * <p>
 * 该字符串只包含小写的英文字母。
 * 给定字符串的长度和 k 在[1, 10000]范围内。
 */
public class S541 {

    public String reverseStr(String s, int k) {
        char[] sc = s.toCharArray();
        for (int i = 0; i < sc.length; i = i + 2 * k) {
            int r = i + k - 1;
            if (r > sc.length - 1)
                r = sc.length - 1;
            reverse(sc, i, r);
        }

        return new String(sc);
    }

    private void reverse(char[] sc, int l, int r) {
        while (l < r) {
            char temp = sc[l];
            sc[l++] = sc[r];
            sc[r--] = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println(new S541().reverseStr("abcdefg", 2));
    }
}
