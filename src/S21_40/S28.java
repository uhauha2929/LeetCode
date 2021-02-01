package S21_40;

/**
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S28 {
    // kmp算法
    public int strStr3(String haystack, String needle) {
        if (needle.length() > haystack.length())
            return -1;

        int n = haystack.length();
        int m = needle.length();

        int[] next_pos = new int[m + 1];
        next_pos[0] = -1;

        int i = 0;
        int j = -1;
        while (i < m) {
            if (j == -1 || needle.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                next_pos[i] = j;
            } else {
                j = next_pos[j];
            }
        }
        i = 0; j = 0;
        while (i < n && j < m) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next_pos[j];
            }
        }
        return j == m ? i - j : -1;
    }

    public int strStr2(String haystack, String needle) {
        if (needle.length() > haystack.length())
            return -1;
        int m = needle.length(), n = haystack.length();
        int i = 0, j = 0;
        // 当剩余部分的长度小于字串的长度时退出
        while (n - i >= m) {
            int start = i, k = i;
            while (haystack.charAt(k++) == needle.charAt(j++)) {
                if (j == m) return start;
            }
            i++;
            j = 0;
        }
        return -1;
    }

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        System.out.println(new S28().strStr3("llhellao", "lla"));
    }
}
