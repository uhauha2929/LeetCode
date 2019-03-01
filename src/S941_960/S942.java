package S941_960;

import java.util.Arrays;

/**
 * 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
 * <p>
 * 返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
 * <p>
 * 如果 S[i] == "I"，那么 A[i] < A[i+1]
 * 如果 S[i] == "D"，那么 A[i] > A[i+1]
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输出："IDID"
 * 输出：[0,4,1,3,2]
 * 示例 2：
 * <p>
 * 输出："III"
 * 输出：[0,1,2,3]
 * 示例 3：
 * <p>
 * 输出："DDI"
 * 输出：[3,2,0,1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 1000
 * S 只包含字符 "I" 或 "D"。
 */
public class S942 {

    public int[] diStringMatch(String S) {
        int[] ans = new int[S.length() + 1];
        int min = 0, max = S.length();
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == 'D') {
                ans[i] = max--;
            } else {
                ans[i] = min++;
            }
        }
        ans[ans.length - 1] = min;
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new S942().diStringMatch("IDID")));
    }
}
