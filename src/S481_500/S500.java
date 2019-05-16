package S481_500;

import java.util.*;
import java.util.stream.Stream;

/**
 * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。
 * 示例：
 * 输入: ["Hello", "Alaska", "Dad", "Peace"]
 * 输出: ["Alaska", "Dad"]
 * 注意：
 * 你可以重复使用键盘上同一字符。
 * 你可以假设输入的字符串将只包含字母。
 */
public class S500 {

    public String[] findWords(String[] words) {
        List<String> res = new ArrayList<>();
        Set<Character> line1 = new HashSet<>(
                Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'));
        Set<Character> line2 = new HashSet<>(
                Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'));
        Set<Character> line3 = new HashSet<>(
                Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm'));
        for (String word : words) {
            boolean flag1 = true, flag2 = true, flag3 = true;
            for (char c : word.toLowerCase().toCharArray()) {
                if (flag1 && !line1.contains(c)) {
                    flag1 = false;
                }
                if (flag2 && !line2.contains(c)) {
                    flag2 = false;
                }
                if (flag3 && !line3.contains(c)) {
                    flag3 = false;
                }
            }
            if (flag1 || flag2 || flag3) {
                res.add(word);
            }
        }
        return res.toArray(new String[]{});
    }

    private static final int[] MAP = new int[]{1, 2, 2, 1, 0, 1, 1, 1, 0, 1, 1, 1, 2, 2, 0, 0, 0, 0, 1, 0, 0, 2, 0, 2, 0, 2};

    private boolean isValid(String word) {
        word = word.toLowerCase();
        int row = MAP[word.charAt(0) - 'a'];
        for (int i = 1; i < word.length(); i++) {
            if (MAP[word.charAt(i) - 'a'] != row)
                return false;
        }
        return true;
    }

    public String[] findWords2(String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (isValid(word))
                res.add(word);
        }
        return res.toArray(new String[]{});
    }

    public String[] findWords3(String[] words) {
        return Stream.of(words)
                .filter(s -> s.toLowerCase()
                        .matches("[qwertyuiop]+|[asdfghjkl]+|[zxcvbnm]+"))
                .toArray(String[]::new);
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new S500().findWords3(
                new String[]{"Hello", "Alaska", "Dad", "Peace"})));
    }
}
