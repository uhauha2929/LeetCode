package S701_720;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。
 * <p>
 * 若无答案，则返回空字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * words = ["w","wo","wor","worl", "world"]
 * 输出: "world"
 * 解释:
 * 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
 * 示例 2:
 * <p>
 * 输入:
 * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出: "apple"
 * 解释:
 * "apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。
 * 注意:
 * <p>
 * 所有输入的字符串都只包含小写字母。
 * words数组长度范围为[1,1000]。
 * words[i]的长度范围为[1,30]。
 */
public class S720 {
    class Node {
        char val;
        boolean end;
        Node[] children;

        Node(char val) {
            this.val = val;
        }
    }

    class Trie {

        Node[] children;

        Trie() {
            children = new Node[26];
        }

        void insert(String word) {
            if (word == null || word.length() == 0)
                return;
            insert(0, word.toCharArray(), children);
        }

        private void insert(int l, char[] chars, Node[] children) {
            int i = chars[l] - 'a';
            if (children[i] == null) {
                children[i] = new Node(chars[l]);
            }
            if (l == chars.length - 1) {
                children[i].end = true; // 设置最后一个字符的结束标志
                return; // 最后一个字符的children=null
            }
            if (children[i].children == null)
                children[i].children = new Node[26];
            insert(l + 1, chars, children[i].children);
        }
    }

    private String longestWord = "";

    public String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String word : words)
            trie.insert(word);
        searchLongestWord(new StringBuilder(), trie.children);
        return longestWord;
    }

    private void searchLongestWord(StringBuilder sb, Node[] children) {
        if (children == null) return;
        for (Node node : children) {
            if (node != null && node.end) { // 该节点必须为某个单词的结束字符
                sb.append(node.val);
                if (sb.length() > longestWord.length())
                    longestWord = sb.toString();
                searchLongestWord(sb, node.children);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public String longestWord2(String[] words) {
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        String res = "";
        for (String s : words) {
            // 只能添加Set中包含有前缀(除去最后一个字符)的单词
            // 如果只有一个字母, 直接添加到Set
            if (s.length() == 1 || set.contains(s.substring(0, s.length() - 1))) {
                res = s.length() > res.length() ? s : res;
                set.add(s);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S720().longestWord(new String[]{
                "a", "banana", "app", "appl", "ap", "apply", "apple"
        }));
    }
}
