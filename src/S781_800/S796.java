package S781_800;

/**
 * 给定两个字符串, A 和 B。
 * <p>
 * A 的旋转操作就是将 A 最左边的字符移动到最右边。 例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。如果在若干次旋转操作之后，A 能变成B，那么返回True。
 * <p>
 * 示例 1:
 * 输入: A = 'abcde', B = 'cdeab'
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: A = 'abcde', B = 'abced'
 * 输出: false
 * 注意：
 * <p>
 * A 和 B 长度不超过 100。
 */
public class S796 {

    public boolean rotateString(String A, String B) {
        if (A == null || B == null) return false;
        if (A.length() != B.length()) return false;
        if ("".equals(A)) return true;
        StringBuilder sb = new StringBuilder(A);
        char first;
        for (int i = 0; i < A.length(); i++) {
            if (B.equals(sb.toString())) return true;
            first = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(first);
        }
        return false;
    }

    public boolean rotateString2(String A, String B) {
        return A.length() == B.length() && (A + A).lastIndexOf(B) != -1;
    }


    public static void main(String[] args) {
        System.out.println(new S796().rotateString("abcde", "cdeab"));
    }
}
