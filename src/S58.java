/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * <p>
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: "Hello World"
 * 输出: 5
 */
public class S58 {

    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        int lenLastWord = 0;
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        while (i >= 0 && s.charAt(i) != ' ') {
            lenLastWord++;
            i--;
        }
        return lenLastWord;
    }


    public static void main(String[] args) {
        System.out.println(new S58().lengthOfLastWord("Hello World"));
    }
}
