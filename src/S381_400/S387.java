package S381_400;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * 案例:
 * s = "leetcode"
 * 返回 0.
 * s = "loveleetcode",
 * 返回 2.
 * 注意事项：您可以假定该字符串只包含小写字母。
 */
public class S387 {

    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++)
            freq[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++)
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }

    public int firstUniqChar2(String s) {
        int res = -1;
        for (char c = 'a'; c <= 'z'; c++) {
            // indexOf 返回此字符串中第一次出现处的索引
            // lastIndexOf 返回指定字符在此字符串中最后一次出现处的索引
            int idx = s.indexOf(c);

            // 第一次出现的索引id与最后一次出现的值相同，则满足题目要求
            if (idx != -1 && idx == s.lastIndexOf(c)) {
                res = (res == -1) ? idx : Math.min(res, idx);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new S387().firstUniqChar2("loveleetcode"));
    }
}
