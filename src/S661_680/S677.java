package S661_680;

/**
 * 实现一个 MapSum 类里的两个方法，insert 和 sum。
 * 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。
 * 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
 * 示例 1:
 * 输入: insert("apple", 3), 输出: Null
 * 输入: sum("ap"), 输出: 3
 * 输入: insert("app", 2), 输出: Null
 * 输入: sum("ap"), 输出: 5
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/map-sum-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S677 {

    // 字典树, 类似参考solution720, solution208
    private static class MapSum {

        private static class Node {
            char letter;
            boolean end;
            // 因为插入字符为26个字母, 这里采用Node数组, 也可以采用Map
            Node[] children;
            int val;

            Node(char letter) {
                this.letter = letter;
            }
        }

        // 仅代表前缀树的根节点
        private Node root = new Node('^');

        public MapSum() {
            root.children = new Node[26];
        }

        public void insert(String key, int val) {
            if (key == null || key.length() == 0)
                return;
            // 非递归插入
            char[] arr = key.toCharArray();
            Node[] cur = root.children;
            for (int i = 0; i < arr.length; i++) {
                int index = arr[i] - 'a';
                if (cur[index] == null) {
                    cur[index] = new Node(arr[i]);
                }
                if (cur[index].children == null) {
                    cur[index].children = new Node[26];
                }
                if (i == arr.length - 1) {
                    cur[index].val = val;
                    cur[index].end = true;
                    break;
                }
                cur = cur[index].children;
            }
//            insert(0, key.toCharArray(), root.children, val);
        }

        // 递归插入, d为深度也即前缀字符串当前位置的下标
        private void insert(int d, char[] arr, Node[] children, int val) {
            int index = arr[d] - 'a';
            if (children[index] == null)
                children[index] = new Node(arr[d]);
            if (children[index].children == null) {
                children[index].children = new Node[26];
            }
            if (d == arr.length - 1) {
                children[index].end = true; // 设置最后一个字符的结束标志
                children[index].val = val;  // 设置value
                return;
            }
            insert(d + 1, arr, children[index].children, val);
        }

        private int sum = 0;

        private void search(char[] prefix, int i, Node root) {
            if (root == null) return;
            // 当i小于前缀长度时, 直接根据下标取出节点
            if (i < prefix.length) {
                Node node = root.children[prefix[i] - 'a'];
                // 只有当该节点存在并且其与前缀该位置的字符相等时才进行递归
                if (node != null && node.letter == prefix[i]) {
                    // 单独处理单词正好为前缀的情况
                    if (i == prefix.length - 1 && node.end)
                        sum += node.val;
                    search(prefix, i + 1, node);
                }
            } else {
                // 在超出前缀长度的部分, 需要搜索剩余的节点
                for (Node child : root.children) {
                    if (child != null) {
                        // 因为node.val默认为0, 所以也可以不判断结束标志
                        if (child.end) sum += child.val;
                        search(prefix, i + 1, child);
                    }
                }
            }
        }

        public int sum(String prefix) {
            sum = 0; // 每次调用重置为0
            search(prefix.toCharArray(), 0, root);
            return sum;
        }

        public int sum2(String prefix) {
            Node last = root;  // 记录前缀最后一个节点
            for (char ch : prefix.toCharArray()) {
                if (last == null) return 0;
                last = last.children[ch - 'a'];
            }
            // 对超出前缀部分进行搜索累计求和
            sum = 0;
            sum2(last);
            return sum;
        }

        private void sum2(Node last) {
            if (last == null) return;
            if (last.end) sum += last.val;
            for (Node node : last.children) {
                sum2(node);
            }
        }
    }


    public static void main(String[] args) {
        MapSum map = new MapSum();
        map.insert("apple", 3);
        System.out.println(map.sum2("ap"));
        map.insert("app", 2);
        System.out.println(map.sum2("ap"));
    }
}
