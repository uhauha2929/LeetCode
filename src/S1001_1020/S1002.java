package S1001_1020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 * <p>
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 */
public class S1002 {

    public List<String> commonChars(String[] A) {
        int[] t1 = new int[26];
        Arrays.fill(t1, Integer.MAX_VALUE);
        for (String s : A) {
            int[] t2 = new int[26];
            for (char c : s.toCharArray()) {
                t2[c - 'a'] += 1;
            }
            for (int i = 0; i < t2.length; i++) {
                t2[i] = Math.min(t1[i], t2[i]);
            }
            t1 = t2;
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < t1.length; i++) {
            for (int j = 0; j < t1[i]; j++) {
                res.add((char) ('a' + i) + "");
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S1002().commonChars(
                new String[]{"bella","label","roller"}));
    }
}
