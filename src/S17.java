import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 2 abc
 * 3 def
 * 4 ghi
 * 5 jkl
 * 6 mno
 * 7 pqrs
 * 8 tuv
 * 9 wxyz
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class S17 {

    private static final String[] DIGITS2LETTERS
            = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        result.add("");  // 这步不可少
        for (int i = 0; i < digits.length(); i++) {
            String letters = DIGITS2LETTERS[digits.charAt(i) - '0'];
            result = combine(letters, result);
        }
        return result;
    }

    private List<String> combine(String letters, List<String> result) {
        List<String> newResult = new ArrayList<>();
        for (String str : result) {
            for (int i = 0; i < letters.length(); i++) {
                newResult.add(str + letters.charAt(i));
            }
        }
        return newResult;
    }
    // 回溯法
    public List<String> letterCombinations2(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        backtracking(result, digits, "");
        return result;
    }

    private void backtracking(List<String> list, String digits, String s) {
        if (s.length() == digits.length()) {
            list.add(s);
            return;
        }
        String letters = DIGITS2LETTERS[digits.charAt(s.length()) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            s += letters.charAt(i);
            backtracking(list, digits, s);
            // 递归结束后，每次都要向上层回溯，即删除最后一个字母
            s = s.substring(0, s.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new S17().letterCombinations2("23"));
    }
}
