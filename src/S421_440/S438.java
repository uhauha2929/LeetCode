package S421_440;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * <p>
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * <p>
 * 说明：
 * <p>
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * <p>
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * 输出:
 * [0, 6]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * 示例 2:
 * <p>
 * 输入:
 * s: "abab" p: "ab"
 * <p>
 * 输出:
 * [0, 1, 2]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 */
public class S438 {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length())
            return res;
        int[] map = new int[26];
        for (char ch : p.toCharArray())
            map[ch - 'a']++;
        int counter = p.length();
        int l = 0, r = 0;
        char[] sChars = s.toCharArray();
        while (r < s.length()) {
            // 每次右移r, 如果s里r对应字符在p中, 将counter减1, map的该位置减1
            if (map[sChars[r] - 'a']-- >= 1)
                counter--;
            r++;
            // 如果counter减为0, 将左端点加入返回值
            if (counter == 0)
                res.add(l);
            // 如果窗口大小超出限制, 需要将l右移一位
            // 同时判断l对应的字符是否在p中，考虑是否还原(counter加1,map对应位置加1)
            if (r - l == p.length()) {
                if (map[sChars[l] - 'a']++ >= 0)
                    counter++;
                l++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S438().findAnagrams("abab", "ab"));
    }
}
