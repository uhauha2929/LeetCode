package S1161_1180;

import java.util.Arrays;

/**
 * 我们来定义一个函数 f(s)，其中传入参数 s 是一个非空字符串；该函数的功能是统计 s  中（按字典序比较）最小字母的出现频次。
 * 例如，若 s = "dcce"，那么 f(s) = 2，因为最小的字母是 "c"，它出现了 2 次。
 * 现在，给你两个字符串数组待查表 queries 和词汇表 words，请你返回一个整数数组 answer 作为答案，其中每个 answer[i] 是满足 f(queries[i]) < f(W) 的词的数目，W 是词汇表 words 中的词。
 * 示例 1：
 * 输入：queries = ["cbd"], words = ["zaaaz"]
 * 输出：[1]
 * 解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
 * 示例 2：
 * 输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * 输出：[1,2]
 * 解释：第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
 * 提示：
 * 1 <= queries.length <= 2000
 * 1 <= words.length <= 2000
 * 1 <= queries[i].length, words[i].length <= 10
 * queries[i][j], words[i][j] 都是小写英文字母
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/compare-strings-by-frequency-of-the-smallest-character
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1170 {

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] ans = new int[queries.length];

        // 统计f值出现的频数，f值的大小最多为10（单词长度）
        // 这里counter的下标要取到11，表示前10个数的累计和
        int[] counter = new int[12];
        for (String word : words) {
            counter[smallestFrequency(word)]++;
        }
        // 累和后，下标为i的数表示大于i-1的频数
        for (int i = counter.length - 2; i >= 0; i--) {
            counter[i] += counter[i + 1];
        }

        for (int i = 0; i < queries.length; i++) {
            // 这里可以直接取，而不需要每次遍历比较
            ans[i] = counter[smallestFrequency(queries[i]) + 1];
        }
        return ans;
    }

    private int smallestFrequency(String word) {
        char smallest = 'z';
        int n = 0;
        for (char c : word.toCharArray()) {
            if (c < smallest) {
                smallest = c;
                n = 1;
            } else if (c == smallest) {
                n++;
            }
        }
        return n;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new S1170().numSmallerByFrequency(
                new String[]{"bbb", "cc"},
                new String[]{"a", "aa", "aaa", "aaaa"})));
    }
}
