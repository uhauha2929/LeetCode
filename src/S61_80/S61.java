package S61_80;

import utils.ListNode;

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

    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null || k < 1)
            return head;
        ListNode slow = head, fast = head;
        int len = 0;
        // fast先走k步
        while (k > 0) {
            fast = fast.next;
            k--;
            len++;
            // 如果fast走到末尾,fast重新从头走k%len步
            if (fast == null) {
                k %= len;
                fast = head;
            }
        }
        // fast和slow同时走直到fast走到末尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 此时slow之后共有k个元素,将它们作为头
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{0, 1, 2});
        System.out.println(head);
        head = new S61().rotateRight2(head, 4);
        System.out.println(head);
    }
}
