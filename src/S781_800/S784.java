package S781_800;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 * 输入: S = "12345"
 * 输出: ["12345"]
 * 注意：
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 */
public class S784 {

    /**
     * 回溯法
     * *      a    A
     * *       \  /
     * *        1
     * *       / \
     * *      b  B
     * *      \  /
     * *       2
     */
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        backtrack(S.toCharArray(), res, new char[S.length()], 0);
        return res;
    }

    private void backtrack(char[] arr, List<String> res, char[] cur, int i) {
        if (i == arr.length) {
            res.add(new String(cur));
            return;
        }
        if (Character.isDigit(arr[i])) {
            cur[i] = arr[i];
            backtrack(arr, res, cur, i + 1);
        } else {
            cur[i] = Character.toLowerCase(arr[i]);
            backtrack(arr, res, cur, i + 1);
            cur[i] = Character.toUpperCase(arr[i]);
            backtrack(arr, res, cur, i + 1);
        }
    }

    // 位运算 类似solution78
    public List<String> letterCasePermutation2(String S) {
        List<String> res = new ArrayList<>();
        char[] arr = S.toCharArray();
        char[] temp = new char[arr.length];
        List<Integer> pos = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isLetter(arr[i])) {
                count++;
                pos.add(i);
            } else {
                temp[i] = arr[i];
            }
        }
        for (int i = 0; i < 1 << count; i++) {
            for (int j = 0; j < count; j++) {
                int p = pos.get(j);
                if ((i & 1 << j) > 0) {
                    temp[p] = Character.toUpperCase(arr[p]);
                } else {
                    temp[p] = Character.toLowerCase(arr[p]);
                }
            }
            res.add(new String(temp));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new S784().letterCasePermutation2("a1b2"));
    }
}
