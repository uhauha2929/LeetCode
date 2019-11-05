package S1161_1180;

/**
 * 给你一个字符串 S，返回只含 单一字母 的子串个数。
 * 示例 1：
 * 输入： "aaaba"
 * 输出： 8
 * 解释：
 * 只含单一字母的子串分别是 "aaa"， "aa"， "a"， "b"。
 * "aaa" 出现 1 次。
 * "aa" 出现 2 次。
 * "a" 出现 4 次。
 * "b" 出现 1 次。
 * 所以答案是 1 + 2 + 4 + 1 = 8。
 * 示例 2:
 * 输入： "aaaaaaaaaa"
 * 输出： 55
 * 提示：
 * 1 <= S.length <= 1000
 * S[i] 仅由小写英文字母组成。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-substrings-with-only-one-distinct-letter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1180 {

    public int countLetters(String S) {
        char[] arr = S.toCharArray();
        int i = 0;
        int ans = 0;
        while (i < arr.length) {
            int cnt = 1;
            while (i + 1 < arr.length && arr[i + 1] == arr[i]) {
                cnt++;
                i++;
            }
            ans += (1 + cnt) * cnt / 2;
            i++;
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new S1180().countLetters("aaaaaaaaaa"));
    }
}
