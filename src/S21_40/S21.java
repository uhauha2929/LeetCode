package S21_40;

import utils.ListNode;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class S21 {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
        ListNode l1 = new ListNode(new int[]{1, 2, 4});
        ListNode l2 = new ListNode(new int[]{1, 3, 4});
        ListNode l3 = mergeTwoLists(l1, l2);
        System.out.println(l3);
    }
}
