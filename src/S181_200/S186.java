package S181_200;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 示例：
 * 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * 输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * 注意：
 * 单词的定义是不包含空格的一系列字符
 * 输入字符串中不会包含前置或尾随的空格
 * 单词与单词之间永远是以单个空格隔开的
 * 进阶：使用 O(1) 额外空间复杂度的原地解法。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S186 {

    public void reverseWords(char[] s) {
        int start = 0, end = 0;
        while (end < s.length) {
            while (end < s.length && s[end] != ' ')
                end++;
            // 先对每个单词进行反转
            reverseWord(s, start, end - 1);
            end++;
            start = end;
        }
        // 再对整句话进行反转
        reverseWord(s, 0, s.length - 1);
    }

    private void reverseWord(char[] s, int start, int end) {
        if (start >= end) return;
        char temp = s[start];
        s[start] = s[end];
        s[end] = temp;
        reverseWord(s, start + 1, end - 1);
    }

    public static void main(String[] args) {
        char[] s = "the sky is blue".toCharArray();
        new S186().reverseWords(s);
        System.out.println(s);
    }
}
