package S821_840;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个字符串 S 和一个字符 C。返回一个代表字符串 S 中每个字符到字符串 S 中的字符 C 的最短距离的数组。
 * 示例 1:
 * 输入: S = "loveleetcode", C = 'e'
 * 输出: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 * 说明:
 * 字符串 S 的长度范围为 [1, 10000]。
 * C 是一个单字符，且保证是字符串 S 里的字符。
 * S 和 C 中的所有字母均为小写字母。
 */
public class S821 {

    public int[] shortestToChar(String S, char C) {
        int[] res = new int[S.length()];
        List<Integer> zeros = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C) {
                zeros.add(i);
            }
        }
        int pos = zeros.get(0);
        for (int j = 0; j <= pos; j++) {
            res[j] = pos - j;
        }
        int start = pos;
        for (int i = 1; i < zeros.size(); i++) {
            pos = zeros.get(i);
            for (int j = start + 1; j <= pos; j++) {
                res[j] = Math.min(j - start, pos - j);
            }
            start = pos;
        }
        for (int j = pos + 1; j < res.length; j++) {
            res[j] = j - pos;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] res = new S821().shortestToChar("asfdloveleetcodeasfd", 'e');
        System.out.println(Arrays.toString(res));
    }
}
