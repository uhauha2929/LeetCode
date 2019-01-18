/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class S21 {

    public static class ListNode {
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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur1 = l1, cur2 = l2, cur3 = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                cur3.next = cur1;
                cur3 = cur1;
                cur1 = cur1.next;
            } else {
                cur3.next = cur2;
                cur3 = cur2;
                cur2 = cur2.next;
            }
        }
        // 如果两个链表其中之一没有遍历完，把剩余的接上
        cur3.next = cur1 == null ? cur2 : cur1;
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new S21().mergeTwoLists(l1, l2);
        System.out.println(l3);
    }
}
