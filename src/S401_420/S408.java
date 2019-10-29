package S401_420;

/**
 * 给一个 非空 字符串 s 和一个单词缩写 abbr ，判断这个缩写是否可以是给定单词的缩写。
 * 字符串 "word" 的所有有效缩写为：
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * 注意单词 "word" 的所有有效缩写仅包含以上这些。任何其他的字符串都不是 "word" 的有效缩写。
 * 注意:
 * 假设字符串 s 仅包含小写字母且 abbr 只包含小写字母和数字。
 * 示例 1:
 * 给定 s = "internationalization", abbr = "i12iz4n"
 * 函数返回 true.
 * 示例 2:
 * 给定 s = "apple", abbr = "a2e":
 * 函数返回 false.
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-word-abbreviation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S408 {

    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        char[] wordChars = word.toCharArray();
        char[] abbrChars = abbr.toCharArray();
        while (i < abbrChars.length && j < wordChars.length) {
            if (abbrChars[i] >= 'a' && abbrChars[i] <= 'z') {
                if (wordChars[j++] != abbrChars[i++]) {
                    return false;
                }
            } else {
                if (abbrChars[i] == '0') // 第一个数不能为0
                    return false;
                int step = 0;
                while (i < abbrChars.length &&
                        abbrChars[i] >= '0' &&
                        abbrChars[i] <= '9') {
                    step *= 10;
                    step += abbr.charAt(i) - '0';
                    i++;
                }
                j += step;
            }
        }
        return j == word.length() && i == abbr.length();
    }


    public static void main(String[] args) {
        System.out.println(new S408()
                .validWordAbbreviation("a",
                        "01"));
    }
}
