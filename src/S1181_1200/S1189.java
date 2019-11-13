package S1181_1200;

/**
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 * 示例 1：
 * 输入：text = "nlaebolko"
 * 输出：1
 * 示例 2：
 * 输入：text = "loonbalxballpoon"
 * 输出：2
 * 示例 3：
 * 输入：text = "leetcode"
 * 输出：0
 * 提示：
 * 1 <= text.length <= 10^4
 * text 全部由小写英文字母组成
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1189 {

    public int maxNumberOfBalloons(String text) {
        int[] map1 = new int[26];
        for (char c : "balloon".toCharArray()) {
            map1[c - 'a']++;
        }
        int[] map2 = new int[26];
        for (char c : text.toCharArray()) {
            map2[c - 'a']++;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (map1[i] != 0) {
                int t = map2[i] / map1[i];
                if (t == 0)
                    return 0;
                if (t < ans)
                    ans = t;
            }
        }
        return ans;
    }

    public int maxNumberOfBalloons2(String text) {
        int[] map = new int[256];
        for (char c : text.toCharArray()) {
            map[c]++;
        }
        int ans = Integer.MAX_VALUE;
        ans = Math.min(ans, map['b']);
        ans = Math.min(ans, map['a']);
        ans = Math.min(ans, map['l'] / 2);
        ans = Math.min(ans, map['o'] / 2);
        ans = Math.min(ans, map['n']);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new S1189().maxNumberOfBalloons2("loonbalxballpoon"));
    }
}
