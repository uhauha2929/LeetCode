import java.util.HashMap;

/**
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 无重复字符的最长子串是 "abc"，其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 无重复字符的最长子串是 "b"，其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 无重复字符的最长子串是 "wke"，其长度为 3。
 * 请注意，答案必须是一个子串，"pwke" 是一个子序列 而不是子串。
 */
public class S3 {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLengh = 0;
        int lastPos = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.keySet().contains(c) && map.get(c) > lastPos) {
                // 如果出现重复，保存最近的一次重复的位置，只能往右走
                lastPos = map.get(c);
            }
            // 每次比较当前字符与上次重复位置的距离，取最大值
            maxLengh = Math.max((i - lastPos + 1), maxLengh);
            map.put(c, i + 1);
        }
        return maxLengh;
    }

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        // 可以用数组替换map
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }


    public static void main(String[] args) {
        String s = "abba";
        System.out.println(new S3().lengthOfLongestSubstring2(s));
    }
}
