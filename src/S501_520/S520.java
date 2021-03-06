package S501_520;

/**
 * 给定一个单词，你需要判断单词的大写使用是否正确。
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * 全部字母都是大写，比如"USA"。
 * 单词中所有字母都不是大写，比如"leetcode"。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 * 示例 1:
 * 输入: "USA"
 * 输出: True
 * 示例 2:
 * 输入: "FlaG"
 * 输出: False
 * 注意: 输入是由大写和小写拉丁字母组成的非空单词。
 */
public class S520 {

    public boolean detectCapitalUse(String word) {
        String remain = word.substring(1);
        boolean isLower = isLowerCase(remain);
        if (isUpperCase(word.charAt(0))) {
            return isLower || isUpperCase(remain);
        }
        return isLower;
    }

    private boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean isLowerCase(String s) {
        for (char c : s.toCharArray()) {
            if (isUpperCase(c)) return false;
        }
        return true;
    }

    private boolean isUpperCase(String s) {
        for (char c : s.toCharArray()) {
            if (isLowerCase(c)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new S520().detectCapitalUse("isA"));
    }
}
