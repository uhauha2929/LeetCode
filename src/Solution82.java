import java.util.HashSet;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class Solution82 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void printNode(ListNode node) {
        if (node != null) {
            System.out.print(node.val + "\t");
            printNode(node.next);
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode cur1 = start, cur2 = head.next;
        // 使用两个指针
        while (cur2 != null) {
            // 如果cur1.next与cur2两个指针的值相同，移动cur2至不同
            if (cur1.next.val == cur2.val) {
                while (cur1.next.val == cur2.val) {
                    cur2 = cur2.next;
                    // 如果cur2指向末尾的null
                    if (cur2 == null)
                        break;
                }
                cur1.next = cur2;
                if (cur2 != null)
                    cur2 = cur2.next;
            } else {
                // 如果cur1.next与cur2的值不同，两者同时往后移动
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
        }
        return start.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(3);

        Solution82 s = new Solution82();
        s.printNode(head);
        System.out.println();
        head = s.deleteDuplicates(head);
        s.printNode(head);
    }
}
