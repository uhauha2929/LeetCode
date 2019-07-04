package S121_140;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 * 示例:
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S131 {

    private List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0)
            return res;
        List<String> list = new ArrayList<>();
        backtrack(list, s, 0);
        return res;
    }

    private void backtrack(List<String> list, String s, int start) {
        if (start == s.length())
            res.add(list);
        for (int j = start + 1; j <= s.length(); ++j) {
            String substr = s.substring(start, j);
            if (isPalindrome(substr)) {
                list.add(substr);
                backtrack(new ArrayList<>(list), s, j);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        if (s.length() <= 1) return true;
        return s.charAt(0) == s.charAt(s.length() - 1) &&
                isPalindrome(s.substring(1, s.length() - 1));
    }

    public static void main(String[] args) {
        System.out.println(new S131().partition("aab"));
    }
}
