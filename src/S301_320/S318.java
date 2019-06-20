package S301_320;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。
 * 你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 * 示例 1:
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * 示例 2:
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-word-lengths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S318 {

    public int maxProduct(String[] words) {

        int maxProduct = 0;

        Arrays.sort(words, new Comparator<>() {
            public int compare(String a, String b) {
                return b.length() - a.length();
            }
        });

        int[] flags = new int[words.length];

        for (int i = 0; i < flags.length; i++) {
            for (char c : words[i].toCharArray()) {
                // 每个值从低位0~26代表该单词是否出现过该字母
                flags[i] |= 1 << (c - 'a');
            }
        }

        for (int i = 0; i < flags.length; i++) {
            // 因为按长度降序, 如果当前长度的平方比最大长度小, break
            if (words[i].length() * words[i].length() <= maxProduct) break;
            for (int j = i + 1; j < flags.length; j++) {
                // 两个值每个位都要有一个为0, 如果都为1代表有重复字母, 与运算结果不为0
                if ((flags[i] & flags[j]) == 0) {
                    maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
                    break;
                }
            }
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        System.out.println(new S318().maxProduct(
                new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
    }
}
