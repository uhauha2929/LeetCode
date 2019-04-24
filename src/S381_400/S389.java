package S381_400;

/**
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 * 示例:
 * 输入：
 * s = "abcd"
 * t = "abcde"
 * 输出：
 * e
 * 解释：
 * 'e' 是那个被添加的字母。
 */
public class S389 {

    public char findTheDifference(String s, String t) {
        //使用异或消除相同的字母
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        int temp = 0;
        for (int i = 0; i < a.length; i++) {
            temp ^= (a[i] ^ b[i]);
        }
        return (char) (temp ^ b[b.length - 1]);
    }

    public char findTheDifference2(String s, String t) {
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        int sumA = 0, sumB = 0;
        for (char c : a) {
            sumA += c;
        }
        for (char c : b) {
            sumB += c;
        }
        return (char) (sumB - sumA);
    }


    public static void main(String[] args) {
        System.out.println(new S389().findTheDifference2("abcd", "cdbae"));
    }
}
