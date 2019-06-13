package S421_440;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * 说明：
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * 输出:
 * [0, 6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * 示例 2:
 * 输入:
 * s: "abab" p: "ab"
 * 输出:
 * [0, 1, 2]
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

        int l = 0, r = 0;
        char[] arr = s.toCharArray();
        while (r < arr.length) {
            if (map[arr[r] - 'a'] > 0)
                // 如果r位置字符在p中, 该字符计数减1, r右移
                map[arr[r++] - 'a']--;
            else
                // 否则, l位置字符加1, l右移
                map[arr[l++] - 'a']++;
            // 如果l和r之间的距离为p的长度, l则为起始索引
            if (r - l == p.length())
                res.add(l);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S438().findAnagrams("abab", "ab"));
    }
}
