package S141_160;

import util.ListNode;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 */
public class S142 {
    /**
     * 类似solution141
     * 先要判断是否有环,如果快指针追上慢指针,则存在环
     * 从开始点和相遇点分别出发,再次相遇则为入环点
     * 理由:
     * 假设从开始节点到入环点的长度为a,从入环点到相遇点的长度为b,环内剩余长度为c. 则:
     * 2(a+b) = a + n(b + c) + b
     * 推出 a = (n-1)b + nc
     * 即 a = c + (n-1)(b+c)
     * 此时快指针再走a步后回到原点.
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // 如果快指针追上慢指针,则存在环
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = head.next;
        System.out.println(new S142().detectCycle(head).val);
    }
}
