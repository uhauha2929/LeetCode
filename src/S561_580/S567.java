package S561_580;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S567 {

    // 类似solution438 字母异位词
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;

        int[] map = new int[26];
        for (char c : s1.toCharArray())
            map[c - 'a']++;

        int l = 0, r = 0;
        char[] arr = s2.toCharArray();
        while (r < arr.length) {
            if (map[arr[r] - 'a'] > 0)
                map[arr[r++] - 'a']--;
            else
                map[arr[l++] - 'a']++;
            if (r - l == s1.length())
                return true;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new S567().checkInclusion("ab", "eidbaooo"));
    }
}
