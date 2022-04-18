package S61_80;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 注意:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 *  
 *
 * 示例 1:
 *
 * 输入: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 *
 * 输入:words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 *
 * 输入:words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]，maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *  
 *
 * 提示:
 *
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] 由小写英文字母和符号组成
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/text-justification
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S68 {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();
        int wordsLength = 0;
        int start = 0; // 行首单词下标
        int end = 0;  // 行尾单词下标 + 1
        while (end < words.length) {
            wordsLength += words[end].length();
            // 如果当前行所有单词的总长度加上中间至少一个空格的长度大于行的最大长度
            if (wordsLength + (end - start) > maxWidth) {
                wordsLength -= words[end].length();
                // 如果该行只有一个单词
                if (end - start == 1) {
                    StringBuilder sb = new StringBuilder();
                    // 加入该单词，剩余长度补空格
                    sb.append(words[start]);
                    for (int j = 0; j < maxWidth - words[start].length(); j++) {
                        sb.append(" ");
                    }
                    lines.add(sb.toString());
                } else {
                    // 剩余空格
                    int numSpaces = maxWidth - wordsLength;
                    // end-start表示该行单词个数，单词之间至少有avgSpaces个空格
                    int avgSpaces = numSpaces / (end - start - 1);
                    // 多出来的额外空格，填充在前extraSpaces个单词之间
                    int extraSpaces = numSpaces % (end -start - 1);
                    StringBuilder sb = new StringBuilder();
                    for (int j = start; j < end - 1; j++) {
                        sb.append(words[j]);
                        // 前extraSpaces个单词多添加一个空格
                        if (j <= start + extraSpaces - 1) {
                            for (int k = 0; k < avgSpaces + 1; k++) {
                                sb.append(" ");
                            }
                        } else {
                            for (int k = 0; k < avgSpaces; k++) {
                                sb.append(" ");
                            }
                        }
                    }
                    sb.append(words[end - 1]);
                    lines.add(sb.toString());
                }
                start = end;
                wordsLength = 0;
            } else {
                end++;
            }
        }
        // 最后一行
        if (end >= start) {
            StringBuilder sb = new StringBuilder();
            for (int j = start; j < end - 1; j++) {
                sb.append(words[j]);
                sb.append(" ");
            }
            sb.append(words[end - 1]);
            // 剩余空格不光要减去总单词长度，还要减去上面补充的end-start-1个空格个数
            for (int j = 0; j < maxWidth - wordsLength - (end - start - 1); j++) {
                sb.append(" ");
            }
            lines.add(sb.toString());
        }
        return lines;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"What","must","be","acknowledgment","shall","be"};
        System.out.println(new S68().fullJustify(words, 16));
    }
}
