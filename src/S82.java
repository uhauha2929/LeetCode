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
public class S82 {

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

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        if (head.val == head.next.val) {
            int sameVal = head.val;
            while (head != null) {
                if (head.val == sameVal) {
                    head = head.next;
                } else {
                    return deleteDuplicates(head);
                }
            }
            return null;
        } else {
            head.next = deleteDuplicates2(head.next);
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(3);

        S82 s = new S82();
        System.out.println(head);
        head = new S82().deleteDuplicates2(head);
        System.out.println(head);
    }
}
