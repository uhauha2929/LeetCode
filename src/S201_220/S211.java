package S201_220;

/**
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 * <p>
 * 示例:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 说明:
 * <p>
 * 你可以假设所有单词都是由小写字母 a-z 组成的。
 */

public class S211 {

    static class WordDictionary {

        private Node[] children;

        private static class Node {
            char val;
            Node[] children;
            boolean end;

            Node(char val) {
                this.val = val;
            }
        }

        public WordDictionary() {
            children = new Node[26];
        }

        public void addWord(String word) {
            if (word == null || word.length() == 0)
                return;
            char[] chars = word.toCharArray();
            int i = 0;
            Node[] cur = children;
            while (true) {
                int p = chars[i] - 'a';
                if (cur[p] == null) {
                    cur[p] = new Node(chars[i]);
                }
                if (i == chars.length - 1) {
                    cur[p].end = true;
                    break;
                }
                i++;
                if (cur[p].children == null) {
                    cur[p].children = new Node[26];
                }
                cur = cur[p].children;
            }
        }

        public boolean search(String word) {
            return backtrack(0, word.toCharArray(), children);
        }

        /**
         * l: 表示递归树的深度, 同时也表示当前word的第l个字符位置
         */
        private boolean backtrack(int l, char[] chars, Node[] children) {
            if (children == null) return false;
            for (Node node : children) {
                if (node == null) continue;
                if (l == chars.length - 1) {
                    // 如果深度达到最后一个字符, 判断该节点是否与当前字符相等或等于'.'
                    // 并且该节点有结束标志
                    if ((node.val == chars[l] || '.' == chars[l]) && node.end)
                        return true;
                    continue;
                }

                if (!(node.val == chars[l] || '.' == chars[l])) {
                    continue;
                }
                // 当前字符满足要求, 进入下一层
                if (backtrack(l + 1, chars, node.children))
                    return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("");
        dict.addWord("at");
        dict.addWord("and");
        dict.addWord("an");
        System.out.println(dict.search(".t"));
    }
}
