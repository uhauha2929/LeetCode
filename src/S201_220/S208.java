package S201_220;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 */

public class S208 {

    private static class Trie {

        private Node root = new Node('$');

        class Node {
            Node[] children = new Node[26];
            char value;
            boolean isEnd;

            Node(char val) {
                value = val;
            }
        }


        /**
         * Initialize your data structure here.
         */
        public Trie() {

        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Node currentNode = root;
            for (char c : word.toCharArray()) {
                int nextPos = c - 'a';
                Node nextNode = currentNode.children[nextPos];
                if (nextNode == null) {
                    nextNode = new Node(c);
                    currentNode.children[nextPos] = nextNode;
                }
                currentNode = nextNode;
            }
            currentNode.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Node currentNode = root;
            for (char c : word.toCharArray()) {
                currentNode = currentNode.children[c - 'a'];
                if (currentNode == null)
                    return false;
            }
            return currentNode.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Node currentNode = root;
            for (char c : prefix.toCharArray()) {
                currentNode = currentNode.children[c - 'a'];
                if (currentNode == null)
                    return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}
