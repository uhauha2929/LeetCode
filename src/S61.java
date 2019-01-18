import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * <p>
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 */
public class S61 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            if (this.next == null) return "" + this.val;
            return this.val + "->" + this.next.toString();
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k < 1)
            return head;
        // 按原顺序从尾部依次入队，并记录总长度
        Deque<ListNode> deque = new LinkedList<>();
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            deque.addLast(cur);
            len += 1;
            cur = cur.next;
        }
        k = k % len;  // 比如k=2000000000可k=2的效果一致
        if (k == 0) return head;
        while (k > 0) {
            // 从尾部移除，并添加到头部，执行k次
            ListNode last = deque.removeLast();
            deque.addFirst(last);
            k--;
        }
        // 再从头部依次出队，重新连接成链表
        ListNode start = new ListNode(0);
        ListNode cursor = start;
        while (!deque.isEmpty()) {
            cursor.next = deque.removeFirst();
            cursor = cursor.next;
        }
        cursor.next = null; // 最后一个节点的next赋值为null
        return start.next;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        S61 s = new S61();
        System.out.println(head);
        head = new S61().rotateRight(head, 2000000000);
        System.out.println(head);
    }
}
