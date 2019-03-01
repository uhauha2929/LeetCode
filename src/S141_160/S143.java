package S141_160;

import utils.ListNode;

import java.util.LinkedList;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */
public class S143 {

    // 双端队列
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;
        // 节点数至少为3
        LinkedList<ListNode> deque = new LinkedList<>();
        ListNode cur = head;
        // 全部放入队列
        while (cur != null) {
            deque.addLast(cur);
            cur = cur.next;
        }
        // 每次连接队列的前一个后一个
        cur = deque.removeFirst();
        cur.next = deque.removeLast();
        cur = cur.next;
        while (deque.size() > 1) {
            cur.next = deque.removeFirst();
            cur.next.next = deque.removeLast();
            cur = cur.next.next;
        }
        // 如果队列中还剩最后一个
        if (!deque.isEmpty()) {
            cur.next = deque.remove();
            cur = cur.next;
        }
        // 链表最后赋值null
        cur.next = null;
    }

    public void reorderList2(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = reverse(slow.next);
        slow.next = null;
        ListNode left = head.next, cur = head;
        while (left != null || right != null) {
            if (right != null) {
                cur.next = right;
                right = right.next;
                cur = cur.next;
            }
            if (left != null) {
                cur.next = left;
                left = left.next;
                cur = cur.next;
            }
        }
    }

    // Solution206
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4, 5});
        System.out.println(head);
        new S143().reorderList2(head);
        System.out.println(head);
    }
}
