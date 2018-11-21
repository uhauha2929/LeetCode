import java.util.ArrayList;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 */
public class Solution19 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void print(ListNode node) {
        if (node == null)
            return;
        System.out.print(node.val + "\t");
        print(node.next);
    }

    /**
     * 一次遍历删除单向链表的倒数第n个节点
     * @param head 头节点
     * @param n 倒数第n个
     * @return 删除后的链表
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 使用两个指针cur表示当前遍历的节点，最终的pre表示要删除的节点的前一个节点
        ListNode cur = head, pre = head;
        // 提前让cur走n步
        while (n > 0) {
            cur = cur.next;
            n--;
        }
        // 如果cur为null，说明已经遍历完毕，要删除的是第一个节点
        if (cur == null)
            return head.next;
        // 遍历使得cur为最后一个节点，此时pre为要删除节点的前一个节点
        while (cur.next != null) {
            cur = cur.next;
            pre = pre.next;
        }
        // 将节点删除
        pre.next = pre.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);

        Solution19 s = new Solution19();
        s.print(node);
        ListNode newNode = s.removeNthFromEnd(node, 3);
        System.out.println();
        s.print(newNode);
    }
}
