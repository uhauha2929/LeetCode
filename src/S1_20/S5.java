package S1_20;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S5 {

    /**
     * 动态规划
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n^2), 可优化
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";
        // 记录下标i~j(包含两端点)之间的字串是否是回文串, 显然单个字符必是回文串.
        boolean[][] memo = new boolean[n][n];
        // 最小长度为1, 取首字符
        int maxLen = 1;
        String ans = s.substring(0, 1);

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= i; j--) {
                // 如果子串的长度小于等于1, 或者(i+1, j-1)为回文串并且i和j字符相等.
                if ((j - i <= 1 || memo[i + 1][j - 1])
                        && s.charAt(i) == s.charAt(j)) {
                    memo[i][j] = true;
                    if (j - i + 1 > maxLen) {
                        // 保存最大长度的回文字串
                        ans = s.substring(i, j + 1);
                        maxLen = ans.length();
                    }
                }
            }
        }
        return ans;
    }

    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    // Manacher's Algorithm
    private char start = '^';
    private char sep = '#';

    private String addSeparators(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        for (int i = 0; i < s.length(); i++) {
            sb.append(sep);
            sb.append(s.charAt(i));
        }
        sb.append(sep);
        return sb.toString();
    }

    public String longestPalindrome3(String string) {
        if (string == null || string.length() == 0)
            return "";
        String str = addSeparators(string);
        int[] radius = new int[str.length()];
        int maxRight = 0, center = 0;
        for (int i = 1; i < str.length(); i++) {
            if (i <= maxRight)
                radius[i] = Math.min(maxRight - i, radius[2 * center - i]);

            int left, right;
            while ((left = i - radius[i] - 1) >= 0
                    && (right = i + radius[i] + 1) < str.length()
                    && str.charAt(left) == str.charAt(right))
                radius[i] += 1;

            if (radius[i] > maxRight - center) {
                center = i;
                maxRight = i + radius[i];
            }
        }
        int startIndex = (center - radius[center]) / 2;
        int endIndex = startIndex + radius[center]; // exclusive
        return string.substring(startIndex, endIndex);
    }

    public static void main(String[] args) {
        System.out.println(new S5().longestPalindrome3("cbbd"));
    }
}
