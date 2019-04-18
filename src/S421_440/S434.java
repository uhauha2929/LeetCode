package S421_440;

/**
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * <p>
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 * <p>
 * 示例:
 * <p>
 * 输入: "Hello, my name is John"
 * 输出: 5
 */
public class S434 {

    public int countSegments(String s) {
        if (s == null || s.isEmpty()) return 0;
        char[] chars = s.toCharArray();
        int cnt = chars[0] == ' ' ? 0 : 1;
        for (int i = 1; i < chars.length - 1; i++) {
            if (chars[i] == ' ' && chars[i + 1] != ' ')
                cnt++; // 遇到一个空格, 并且后一个字符非空格, 计数器加1
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new S434().countSegments("Hello, my name is John"));
    }
}
