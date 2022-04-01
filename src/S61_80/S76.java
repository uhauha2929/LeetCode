package S61_80;

import java.util.HashMap;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * 提示：
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S76 {

    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen == 0 || tLen == 0 || sLen < tLen) {
            return "";
        }
        char[] charArrS = s.toCharArray();
        char[] charArrT = t.toCharArray();

        HashMap<Character, Integer> tFreq = new HashMap<>();
        for (char c : charArrT) {
            tFreq.put(c, tFreq.getOrDefault(c, 0) + 1);
        }
        // 滑动窗口内部还差多少T中的字符
        int distance = tLen;
        int minLen = sLen + 1;
        int begin = 0;
        int left = 0;
        int right = 0;

        while (right < sLen) {
            char charRight = charArrS[right];
            if (!tFreq.containsKey(charRight)) {
                right++;
                continue;
            }
            if (tFreq.get(charRight) > 0) {
                distance--;
            }
            tFreq.put(charRight, tFreq.get(charRight) - 1);
            right++;
            while (distance == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    begin = left;
                }
                char charLeft = charArrS[left];
                if (!tFreq.containsKey(charLeft)) {
                    left++;
                    continue;
                }
                if (tFreq.get(charLeft) == 0) {
                    distance++;
                }
                tFreq.put(charLeft, tFreq.get(charLeft) + 1);
                left++;
            }
        }
        if (minLen == sLen + 1) {
            return "";
        }
        return s.substring(begin, begin + minLen);
    }

    public static void main(String[] args) {
        System.out.println(new S76().minWindow("ADOBECODEBANC", "ABC"));
    }
}
