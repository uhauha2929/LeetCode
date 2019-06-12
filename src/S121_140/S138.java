package S121_140;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的深拷贝。 
 * 输入：
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 * 解释：
 * 节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
 * 节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 * 提示：
 * 你必须返回给定头的拷贝作为对克隆列表的引用。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S138 {

    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node dummyHead = new Node();
        Node cur1 = head, cur2 = dummyHead;
        while (cur1 != null) {
            cur2.next = new Node(cur1.val, cur1.next, cur1.random);
            // 保存原节点和克隆节点的映射
            map.put(cur1, cur2.next);
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        cur2 = dummyHead.next;
        while (cur2 != null) {
            // 只需更改随机指针, 当前随机指针还指向原节点, 通过映射找到克隆的节点
            cur2.random = map.getOrDefault(cur2.random, null);
            cur2 = cur2.next;
        }
        return dummyHead.next;
    }

    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        // 构建[原节点->克隆节点->原节点->克隆节点...]的交织结构
        Node cur = head;
        while (cur != null) {
            Node node = new Node(cur.val, null, null);
            node.next = cur.next;
            cur.next = node;
            cur = cur.next.next;
        }
        // first指向原节点, second指向相应的克隆节点
        Node first = head, second;
        while (first != null) {
            // 原节点的下一个节点即为其克隆节点
            second = first.next;
            if (first.random != null)
                // 随机指针的下一个节点即为其克隆节点
                second.random = first.random.next;
            first = second.next;
        }
        // 将交织的链表拆开
        first = head;
        Node newHead = head.next;
        while (first.next != null) {
            second = first.next;
            first.next = second.next;
            first = second;
        }
        return newHead;
    }


    public static void main(String[] args) {
        Node two = new Node();
        two.val = 2;
        two.random = two;
        Node one = new Node(1, two, two);
        Node newHead = new S138().copyRandomList2(one);
        System.out.println(newHead);
    }
}
