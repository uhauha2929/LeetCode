package S401_420;

/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * 示例 1:
 * 输入:
 * "abccccdd"
 * 输出:
 * 7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */
public class S409 {

    public int longestPalindrome(String s) {
        int[] count = new int[58];
        for (char c : s.toCharArray()) {
            count[c - 'A']++;
        }
        boolean extraOne = false;
        int countPair = 0;
        for (int c : count) {
            countPair += c / 2;
            if (!extraOne && c % 2 == 1)
                extraOne = true;
        }
        return countPair * 2 + (extraOne ? 1 : 0);
    }

    public static void main(String[] args) {
        System.out.println(new S409()
                .longestPalindrome("AAAAAA"));
    }
}
